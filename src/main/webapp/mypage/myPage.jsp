<%@page import="middlego.domain.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미들고</title>


</head>
<body>
<div style="margin: 100px 200px 30px 200px">
<a href="index.jsp"><img src="./image/Logo.jpg" id="Logo"></a>
<a href="myPage.jsp"><img src="./image/mypage.png" id="mypage"></a>
<br>
<hr>

<a href="<%=request.getContextPath() %>/mypage/memberView.do">내정보보기</a>



</div>



</body>
</html>