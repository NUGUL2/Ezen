<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="middlego.domain.BoardVo" %>
<% BoardVo bv = (BoardVo)request.getAttribute("bv"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
if(session.getAttribute("midx")==null){
	session.setAttribute("saveUrl", request.getRequestURI());
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}

%>
<script>
function clicked() {
	var fm = document.frm;
	fm.action = "<%=request.getContextPath()%>/board/boardDeleteAction.do";
	fm.method="POST";
	fm.submit();
	return;
}
</script>
<title>게시물 삭제</title>
<style>
@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
</head>
<body>
<div style="margin: 100px 200px 30px 200px">
<form name="frm">
<input type="hidden" name="bidx" value="<%=bv.getBidx()%>">
<table width="1500px">
<tr>
<td style="width: 100px"><a href="../main.do"><img src="../image/Logo.jpg" style="width: 200px;" id="Logo"></a></td>
<td style="width: 200px; text-align:right;"><a href="<%=request.getContextPath() %>/mypage/myPage.do"><img src="../image/mypage.png" style="width: 50px; height: 50px" id="mypage"></a></td>
</tr>
</table>

<hr>

<h1 style="font-family:IBMPlexSansKR-Regular">게시물 삭제</h1>

<div style="font-size: 50px">삭제하시겠습니까?</div><br>
<input type="button" name="ok" value="확인" onclick="clicked();">
<input type="button" name="no" value="취소" onclick="location.href='<%=request.getContextPath() %>/main.do'">

</form>
</div>




</body>
</html>