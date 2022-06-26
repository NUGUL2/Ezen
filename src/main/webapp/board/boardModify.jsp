<%@page import="javax.tools.DocumentationTool.Location"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="middlego.domain.BoardVo" %>
<% BoardVo bv = (BoardVo)request.getAttribute("bv"); %>
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
<script>
	function check() {
		var fm = document.frm; //폼타입 이름 지정 선택
		
		if (fm.subject.value==""){
			alert("제목을 입력하세요.");
			fm.subject.focus();
			return;
		}else if (fm.content.value=="") {
			alert("내용을 입력하세요.");
			fm.content.focus();
			return;
		}else if (fm.writer.value=="") {
			alert("작성자를 입력하세요.");
			fm.wrtier.focus();
			return;
		}
		alert("글쓰기가 완료되었습니다.");
		fm.action = "<%=request.getContextPath()%>/board/boardModifyAction.do";
		fm.enctype = "multipart/form-data";
		fm.method="POST";
		fm.submit();
		
	return;
	}
</script>
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
	color:black;
	padding: 5px;
}

 #button:hover {
	color: black;
	background-color: #71c4e9;
}
</style>
<title>글쓰기</title>
</head>

<body>
<div style="margin: 100px 200px 30px 200px">
<form name="frm"> <!-- 폼타입 이름 지정 -->
<input type="hidden" name="bidx" value="<%=bv.getBidx()%>">
<table width="1500px">
<tr>
<td style="width: 100px"><a href="../main.do"><img src="../image/Logo.jpg" style="width: 200px;" id="Logo"></a></td>
<%-- <td style="width: 200px; text-align:right;"><a href="<%=request.getContextPath() %>/mypage/myPage.do"><img src="../image/mypage.png" style="width: 50px; height: 50px" id="mypage"></a></td>
 --%></tr>
</table>

<hr>

<h1 style="font-family: IBMPlexSansKR-Regular">판매글 수정</h1>

<table border="1" style="width:100%; height:40px; border-collapse: collapse;">
<tr style="height:40px; border-left: none; border-right: none;">
<td width="100px" style="background-color:#71c4e9; font-weight: 600;" align="center">제목</td>
<td width="900px"><input type="text" name="subject" style="border: none;" size="100" value="<%=bv.getSubject()%>"></td>
<td width="5%" style="background-color:#71c4e9; font-weight: 600;" align="center">작성자</td>
<td><input type="text" name="writer" size="50" value="<%=bv.getWriter()%>" readonly="readonly" style="border: none"></td>

</tr>
</table>
<br>

<table>
<tr>
<td colspan="2"><textarea name="content" style="width: 100%; height: 400px;" cols="700" rows="300" placeholder="내용을 입력하세요"><%=bv.getContent()%></textarea></td>
</tr>

<tr>
<td><input type="file" name="filename" value="사진첨부"></td>
<td style="text-align: right;"><input type="button" id="button" value="확인" onclick="check();">
</td>
</tr>
</table>

</form>

</div>
</body>
</html>