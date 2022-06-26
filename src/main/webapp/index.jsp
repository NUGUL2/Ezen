<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미들고</title>
<style>
@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#keyword {
width:600px; 
font-size:30px;
border: 3px solid #71c4e9;
}

 #button {
 	border-radius : 5px;
	border: 1px solid #71c4e9;
	background-color: #71c4e9;
	color:white;
	padding: 5px;
}
</style>

</head>
<body>
<div style="margin: 70px 200px 30px 200px">
<form name="frm" action="<%=request.getContextPath()%>/board/boardList.do" method="post">
<table width="100%">
<tr>
<td style="width: 100px"><a href="<%=request.getContextPath()%>/main.do"><img src="<%=request.getContextPath()%>/image/Logo.jpg" style="width: 150px;" id="Logo"></a></td>

<td style="text-align:right; width: 1200px"><input type="text" name="keyword" id="keyword"></td>
<td style="width: 500px;"><input type="submit" name="submit" value="검색" style="height: 43px; width:80px; border: 3px solid #71c4e9; background-color: white;"></td>

<td width="12%" style="text-align: right;">
<%
if (session.getAttribute("midx")!=null){
	out.println(session.getAttribute("memberId")+"님");
%>
<input type="button" value="로그아웃" id="button" onclick="location.href='<%=request.getContextPath()%>/member/memberLogout.do'">
	<%}%>
<% if(session.getAttribute("midx")==null){%>
<a href="<%=request.getContextPath() %>/member/memberJoin.do" style="text-decoration: none; color: #71c4e9;">회원가입</a>
<a href="<%=request.getContextPath() %>/member/memberLogin.do"style="text-decoration: none; color: #71c4e9;">회원로그인</a>
<%}%>
</td><%-- 
<td style="width: 100px; text-align:right;"><a href="<%=request.getContextPath()%>/admin/adminmain.do"><img src ="<%=request.getContextPath()%>/image/admin.png" style="width: 40px; height:40px"></a></td>
<td style="width: 100px; text-align:right;"><a href="<%=request.getContextPath()%>/mypage/myPage.do"><img src="<%=request.getContextPath()%>/image/mypage.png" style="width: 50px; height: 50px" id="mypage"></a></td>
 --%></tr>
</table>
</form>

<hr>

<table width="100%" style="font-size: 20px">
<td style="font-family: IBMPlexSansKR-Regular">전체글</td>

<td style="text-align: right;"><input type="button" id="button" name="boardWrite" value="글쓰기" onclick="location.href='<%=request.getContextPath() %>/board/boardWrite.do'"></td>
</table>


<%@include file="/board/boardList.jsp" %>


</div>
</body>
</html>