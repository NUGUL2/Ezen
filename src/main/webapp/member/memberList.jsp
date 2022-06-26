<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="middlego.domain.*" %> <!-- MemberDao 객체 생성, jspstudy 안에 있는 모든 것을 쓰겠다 -->
<%@ page import="java.util.*" %> <!-- ArrayList 사용 -->
<%
	ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>

회원 목록 만들기
<table border="1" style="width:800px">

<tr style="text-align: center">
<td style="color: green">midx 번호</td>
<td>이름</td>
<td>전화번호</td>
<td>작성일</td>
</tr>
<% for (MemberVo mv : alist){%>
<tr>
<td><% out.println(mv.getMidx()); %></td>
<td><% out.println(mv.getMembername()); %></td>
<td><% out.println(mv.getMemberid()); %></td>
<td><% out.println(mv.getMembermail()); %></td>
<td><% out.println(mv.getMemberphone()); %></td>
<td><% out.println(mv.getMemberadder()); %></td>
</tr>
<% } %>
</table>



</body>
</html>