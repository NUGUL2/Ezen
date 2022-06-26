<%@page import="middlego.domain.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("midx")==null){
	session.setAttribute("saveUrl", request.getRequestURI());
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미들고</title>


</head>
<body>
<div style="margin: 100px 200px 30px 200px">
<a href="../main.do"><img src="./image/Logo.jpg" id="Logo"></a>
<a href="myPage.jsp"><img src="./image/mypage.png" id="mypage"></a>
<br>
<hr>




</div>



</body>
</html>