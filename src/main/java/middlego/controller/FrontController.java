package middlego.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import middlego.domain.BoardVo;
import middlego.domain.PageMaker;
import middlego.domain.SearchCriteria;
import middlego.service.BoardDao;

@WebServlet("/FrontController") //서버 설정으로 *.do로 오는 것을 FrontController로 받을 수 있게 설정
public class FrontController extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length()); //프로젝트 이름을 뺀 나머지 가상경로
		System.out.println("command2 : "+command);
		
		String[] subpath = command.split("/");
		String location = subpath[1];
		
		if(command.equals("/main.do")) {
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
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
			
			
		}else if(location.equals("member")) {
			MemberController mc = new MemberController();
			mc.doGet(request, response);
			
		}else if (location.equals("board")) {
			BoardController bc = new BoardController();
			bc.doGet(request, response);
		
		}else if (location.equals("mypage")) {
			MyPageController pc = new MyPageController();
			pc.doGet(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
