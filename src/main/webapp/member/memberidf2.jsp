<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="middlego.domain.MemberVo" %>
<% String memberId = (String)session.getAttribute("memberId");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미들고 ID찾기</title>

<style>
@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
table hr {
	border:0px;
	width: 260px;
	height:1px;
	color:#71c4e9;
	background-color:#71c4e9;
}
input:focus{outline:2px solid #71c4e9;}
</style>

</head>
<body>

<p style="text-align: center; font-size: 30px; font-family: IBMPlexSansKR-Regular">ID 찾기</p>

  <form name="frm" align="center">
      <% 
       if ( memberId != null) {
       %>
	      <h4>  회원님의 아이디는 </h4><%=memberId%><h4>  입니다 </h4>

     
   <% } else {%>
         <h4>  등록된 정보가 없습니다 </h4> 
	    <input type="button" id="btnagin" value="다시찾기" onclick="location.href='<%=request.getContextPath() %>/member/memberidf.do'"/>
         <%}%>  
	
	    <input type="button" id="btnjoin" value="회원가입" onclick="location.href='<%=request.getContextPath() %>/member/memberJoin.do'"/>
        <input type="button" id="btnpwdf" value="PWD찾기" onclick="location.href='<%=request.getContextPath() %>/member/memberPwdf.do'"/>
        <input type="button" id="btnLogin" value="로그인" onclick="location.href='<%=request.getContextPath() %>/member/memberLogin.do'"/>
   
      </form>

</body>
</html>