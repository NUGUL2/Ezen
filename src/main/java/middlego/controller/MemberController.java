package middlego.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import middlego.domain.BoardVo;
import middlego.domain.MemberVo;
import middlego.service.BoardDao;
import middlego.service.MemberDao;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String uri = request.getRequestURI(); //getRequestURI():웹 서버로 요청 시, 요청에 사용된 URL 로부터 URI 값을 리턴
		System.out.println("uri : " + uri);
		
		//프로젝트 이름? 클래스 이름?
		String pj = request.getContextPath(); //해당 JSP 페이지가 속한 웹 어플리케이션의 콘텍스트 경로를 리턴
		String command = uri.substring(pj.length());
		System.out.println("command : " + command);
		
		//command가 /member/memberJoinAction.do랑 같으면
		if (command.equals("/member/memberJoinAction.do")) {
			System.out.println("command : " + command);
			
			String memberId = request.getParameter("memberId"); //파라미터 변수 memberId에 저장된 변수를 얻어내는 메소드
			String memberPwd = request.getParameter("memberPwd");
			String memberName = request.getParameter("memberName");
			String memberMail = request.getParameter("memberMail");
			String memberPhone = request.getParameter("memberPhone");
			String memberJumin = request.getParameter("memberJumin");
			String memberAddr = request.getParameter("memberAddr");
			String ip = InetAddress.getLocalHost().getHostAddress();

			//객체 생성
			MemberDao md = new MemberDao();
			int value = md.insertMember(memberId, memberPwd, memberName, memberAddr, memberJumin, memberPhone, memberMail ,ip);
			
			PrintWriter out = response.getWriter();
			//입력이 되었으면 인덱스 페이지로 이동하라
			if (value ==1){
				response.sendRedirect(request.getContextPath() + "/member/memberLogin.do"); //가상경로
				out.println("<script>alert('회원가입 성공'); location.href='"+request.getContextPath()+"'</script>");
			}else{
				response.sendRedirect(request.getContextPath() + "/member/memberJoin.do");
				
				out.println("<script>alert('회원가입 실패'); location.href='./memberJoin.jsp'</script>");
			}
		}else if (command.equals("/member/memberJoin.do")) {
			
			//memberJoin를 리퀘스트 Dispatcher가 담아서
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
			rd.forward(request, response);
			
		}//로그인
		else if(command.equals("/member/memberLogin.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);
	
		}else if(command.equals("/member/memberLoginAction.do")) {
			System.out.println("넘어왔으");
			//1.넘어온 값을 받는다.
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			
			//2.처리한다
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLogin(memberId, memberPwd);
			System.out.println("mv"+mv);
			HttpSession session = request.getSession();
			
			if(mv != null) {
				session.setAttribute("midx", mv.getMidx());
				session.setAttribute("memberId", mv.getMemberid());
				session.setAttribute("memberName", mv.getMembername());
				
				if(session.getAttribute("saveUrl") != null) {
					response.sendRedirect((String)session.getAttribute("saveUrl"));
				}else {
					response.sendRedirect(request.getContextPath()+"/main.do");
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			}
		}//id찾기
		else if(command.equals("/member/memberidf.do")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberidf.jsp");
			rd.forward(request, response);
	
			}else if(command.equals("/member/memberidfAction.do")) {
				//1.넘어온 값을 받는다.
				String memberName = request.getParameter("memberName");
				String memberPhone = request.getParameter("memberPhone");
			
				//2.처리한다
				MemberDao md = new MemberDao();
				String memberId = md.memberIdf(memberName, memberPhone);
				System.out.println("memberId : " +memberId);
				HttpSession session = request.getSession();
			
				session.setAttribute("memberId", memberId);
				response.sendRedirect(request.getContextPath()+"/member/memberidf2.do");
			
		}//id찾기2
			else if(command.equals("/member/memberidf2.do")) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberidf2.jsp");
				rd.forward(request, response);
		
				}else if(command.equals("/member/memberidfAction.do")) {
					//1.넘어온 값을 받는다.
					String memberName = request.getParameter("memberName");
					String memberPhone = request.getParameter("memberPhone");
				
					//2.처리한다
					MemberDao md = new MemberDao();
					String memberId = md.memberIdf(memberName, memberPhone);
					System.out.println("memberId"+memberId);
					HttpSession session = request.getSession();
				
				if(memberId == null) {
					response.sendRedirect(request.getContextPath()+"/memberLogin.do");
		
				}else {
					session.setAttribute("memberId", memberId);
					response.sendRedirect(request.getContextPath()+"/member/memberidf2.do");
				}
			}//PWD찾기
			else if(command.equals("/member/memberPwdf.do")) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberPwdf.jsp");
				rd.forward(request, response);
			
				}else if(command.equals("/member/memberPwdfAction.do")) {
					//1.넘어온 값을 받는다.
					String memberId = request.getParameter("memberId");
					String memberPhone = request.getParameter("memberPhone");
					
					//2.처리한다
					MemberDao md = new MemberDao();
					String memberPwd = md.memberPwdf(memberId, memberPhone);
					HttpSession session = request.getSession();
					
					session.setAttribute("memberPwd", memberPwd);
					response.sendRedirect(request.getContextPath()+"/member/memberPwdf2.do");
					
			}//PWD찾기2
			else if(command.equals("/member/memberPwdf2.do")) {
						
				RequestDispatcher rd = request.getRequestDispatcher("/member/memberPwdf2.jsp");
				rd.forward(request, response);
				
			}else if(command.equals("/member/memberPwdfAction.do")) {
					//1.넘어온 값을 받는다.
					String memberId = request.getParameter("memberId");
					String memberPhone = request.getParameter("memberPhone");
						
					//2.처리한다
					MemberDao md = new MemberDao();
					String memberPwd = md.memberPwdf(memberId, memberPhone);
					HttpSession session = request.getSession();
						
				if(memberId == null) {
					response.sendRedirect(request.getContextPath()+"/memberLogin.do");
				
				}else {
					session.setAttribute("memberPwd", memberPwd);
					response.sendRedirect(request.getContextPath()+"/member/memberPwdf2.do");
				}
		}else if (command.equals("/member/memberLogout.do")) {
				response.getWriter().print("<script>alert('로그아웃합니다.');</script>");
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/main.do");
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}


}
