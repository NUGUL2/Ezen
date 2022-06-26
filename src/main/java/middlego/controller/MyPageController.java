package middlego.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import middlego.domain.MemberVo;
import middlego.service.MemberDao;

@WebServlet("/MyPageController")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uri = request.getRequestURI(); //getRequestURI():웹 서버로 요청 시, 요청에 사용된 URL 로부터 URI 값을 리턴
		System.out.println("uri : " + uri);
		
		//프로젝트 이름? 클래스 이름?
		String pj = request.getContextPath(); //해당 JSP 페이지가 속한 웹 어플리케이션의 콘텍스트 경로를 리턴
		String command = uri.substring(pj.length());
		System.out.println("command : " + command);
		
	if(command.equals("/mypage/myPage.do")){
		System.out.println("마이페이지 화면에 진입합니다.");

		//1.넘어온 값을 받는다.
		String midx = request.getParameter("midx");
		int midx_ = Integer.parseInt(midx);
		
		//2.처리한다
		MemberDao md = new MemberDao();
		MemberVo mv = md.memberSelectOne(midx_);
		request.setAttribute("mv", mv); //내부에 같은 위치에서 자원을 공유한다.
			
		RequestDispatcher rd = request.getRequestDispatcher("/mypage/myPage.jsp");
		rd.forward(request, response);
		
	}else if (command.equals("/mypage/memberView.do")) {
		System.out.println("내정보 화면에 진입합니다.");

			//1.넘어온 값을 받는다.
			String memberId = request.getParameter("memberId");
			
			//2.처리한다
			MemberDao md = new MemberDao();
			MemberVo mv = md.selectMember(memberId);
			request.setAttribute("mv", mv); //내부에 같은 위치에서 자원을 공유한다.
				
			//3.이동한다
			RequestDispatcher rd = request.getRequestDispatcher("/mypage/memberView.jsp");
			rd.forward(request, response);
			
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


