<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="middlego.domain.MemberVo" %>
<% MemberVo mv = (MemberVo)request.getAttribute("mv"); %>
<!DOCTYPE HTML>
<HTML>
 <HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>미들고 내 정보보기</TITLE>
  
<style>
@font-face {
  font-family: 'IBMPlexSansKR-Regular';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
  
  
input:focus{outline:2px solid #71c4e9;}
  
 #button {
 	border-radius : 5px;
	border: 1px solid #71c4e9;
	background-color: #71c4e9;
	color:white;
	padding: 5px;
}

 #button:hover {
	color: black;
	background-color: #71c4e9;
}

  </style>


 </HEAD>

 <BODY>
 <div style="margin: 100px 200px 30px 200px;" align="center">

<center><a href="../main.do"><img src="../image/Logo.jpg"></a></center><br>
<center><h1 style="font-family: IBMPlexSansKR-Regular">내 정보</h1></center>

 <table style="width:550px; height:500px; border-collapse: collapse;">

<tr>
<td style="text-align: right;">아이디</td>
<td><%=mv.getMemberid()%></td>
</tr>

<tr>
<td style="text-align: right;">비밀번호</td>
<td><%=mv.getMemberpwd()%></td>
</tr>

<tr>
<td style="text-align: right;">이름</td>
<td><%=mv.getMembername() %></td>
</tr>

<tr>
<td style="text-align: right;">주민번호</td>
<td><%=mv.getMemberjumin() %></td>
</tr>

<tr>
<td style="text-align: right;">연락처</td>
<td><%=mv.getMemberphone() %></td>
</tr>

<tr>
<td style="text-align: right;">지역</td>
<td><%=mv.getMemberadder()%></td>
</tr>

<tr>
<td style="text-align: right;">이메일</td>
<td><%=mv.getMembermail()%></td>
</tr>

<tr>
<td colspan="2" style="text-align: center;" >
<input id="button" type="submit" value="확인" onclick="check();"> 
<input id="button" type="reset" value="다시작성"> 
</td>
</tr>
 </table>

 </div>
 </BODY>
</HTML>
