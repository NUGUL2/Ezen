package middlego.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import middlego.domain.BoardVo;
import middlego.domain.PageMaker;
import middlego.domain.SearchCriteria;
import middlego.service.BoardDao;



@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//가상 경로로 온 request가 있으면 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		int sizeLimit = 1024*1024*15;
		String uploadPath = "D:\\openApi(B)\\dev\\middlego\\src\\main\\webapp\\image";
		
		
		System.out.println("command"+command);
		
		if (command.equals("/board/boardWrite.do")) {
			System.out.println("글쓰기 화면에 진입합니다.");

			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("글쓰기 처리 화면으로 들어옴");
			
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request, uploadPath, sizeLimit,"UTF-8", new DefaultFileRenamePolicy());
			
			String subject =multipartRequest.getParameter("subject");
			String content =multipartRequest.getParameter("content");
			String writer =multipartRequest.getParameter("writer");
			
			System.out.println(subject+";"+content+";"+writer);
			
			//열거자에 저장될 파일을 담는 객체 생성
			Enumeration files = multipartRequest.getFileNames();
			//담긴 객체의 파일 이름을 얻는다
			String file = (String)files.nextElement();
			//넘어오는 객체 중에 해당되는 파일 이름으로 되어있는 파일 이름을 추출한다(실제로 저장되는 파일 이름)
			String fileName = multipartRequest.getFilesystemName(file);
			//원본의 파일 이름
			String originFileName= multipartRequest.getOriginalFileName(file);
			
			String ip = InetAddress.getLocalHost().getHostAddress();

			int midx = 2;
			int fileSize = 0;
			
			BoardDao bd = new BoardDao();
			int value = bd.insertBoard(subject, content, writer, ip, midx, fileName, fileSize);
			
			//입력이 되었으면 인덱스 페이지로 이동하라
				if (value==1){			//response의 객체 sendRediecter() 특정 url로 재요청
					response.sendRedirect(request.getContextPath()+"/main.do");
				}else{
					response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
				}
		}//리스트
		else if (command.equals("/board/boardList.do")) {
			System.out.println("게시판 리스트 화면 들어왔음");
			
			String page = request.getParameter("page");
			if(page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			String keyword = request.getParameter("keyword");
			if(keyword==null) keyword ="";
			
			String searchType = request.getParameter("searchType");
			if(searchType==null) searchType ="subject";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
			
			//처리
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);				
			
			PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.boardSelectAll(scri);
		
			request.setAttribute("alist", alist);
			request.setAttribute("pm",pm);
			
		
			//이동
			RequestDispatcher rd = request.getRequestDispatcher("/main.do");
			rd.forward(request, response);
	
	}//글보기
		else if (command.equals("/board/boardContent.do")) {
			//1.넘어온 값을 받는다.
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
			
			//2.처리한다
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //내부에 같은 위치에서 자원을 공유한다.
				
			//3.이동한다
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/fileDownload.do")) {
			
			String filename = request.getParameter("filename");
			
			//파일의 전체 경로
			String filePath = uploadPath + File.separator + filename;
	
			//실제 경로에 있는 파일을 꺼내서 소스로 사용
			Path source = Paths.get(filePath);
			
			
			String mimeType = Files.probeContentType(source);
			//파일 형식을 헤더 정보에 담는다
			response.setContentType(mimeType);
			
			String encodingFileName = new String(filename.getBytes("UTF-8"));
			//첨부해서 다운로드 되는 파일을 헤더 정보에 담는다
			response.setHeader("Content-Disposition", "attachment;fileName="+encodingFileName);
			//해당 위치에 있는 파일을 읽어들인다.
			FileInputStream fileInputStream = new FileInputStream(filePath);
			//파일을 쓰는 스트림
			ServletOutputStream servletOutStream= response.getOutputStream();
			byte[] b = new byte[4096];
			int read = 0;
			
			while ((read = fileInputStream.read(b,0,b.length))!=-1) {
				
				servletOutStream.write(b,0,read);
			}
			
			servletOutStream.flush();
			servletOutStream.close();
			fileInputStream.close();
			
		}//게시글 삭제
		else if (command.equals("/board/boardDelete.do")) {
			System.out.println("게시글 삭제 화면으로 들어옴");
			
			String bidx = request.getParameter("bidx"); 
			int bidx_ = Integer.parseInt(bidx);
			
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);
				
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
			rd.forward(request, response);
		
		}else if (command.equals("/board/boardDeleteAction.do")) {
				System.out.println("게시글 삭제 처리");
				
				String bidx = request.getParameter("bidx");
				int bidx_ = Integer.parseInt(bidx);
				
				BoardDao bd = new BoardDao();
				int value = bd.deleteBoard(bidx_);
				
					if (value ==1){
						response.sendRedirect(request.getContextPath()+"/main.do"); //가상경로
					}else{
						response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);
					}
		//게시글 수정 기능
		}else if (command.equals("/board/boardModify.do")) {
			System.out.println("글쓰기 수정 화면으로 들어옴");
								
			//데이터 받아오기
			//1.넘어온 값을 받는다.
			String bidx = request.getParameter("bidx"); 
			int bidx_ = Integer.parseInt(bidx);
								
			//2.처리한다(데이터 받아와서 bv에 저장)
			BoardDao bd = new BoardDao();
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //내부에 같은 위치에서 자원을 공유한다.
									
			//3.이동한다 RequestDispatcher => JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);  //forward() 페이지출력,페이지전환
							
		}else if (command.equals("/board/boardModifyAction.do")) {
			System.out.println("글쓰기 수정 처리화면으로 들어옴");
									
			String subject =request.getParameter("subject");
			String content =request.getParameter("content");
			String writer =request.getParameter("writer");
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);
									
			String ip = InetAddress.getLocalHost().getHostAddress();
			int midx = 2;
									
			BoardDao bd = new BoardDao();
			int value = bd.updateBoard(subject,  content, writer, ip, midx, bidx_);
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv);		
			
									
			//입력이 되었으면 인덱스 페이지로 이동하라
					if (value ==1){
						response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx); //가상경로
					}else{
						response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx="+bidx);
					}
										
			}
		
	}
		

		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
		doGet(request, response);
	}
}


