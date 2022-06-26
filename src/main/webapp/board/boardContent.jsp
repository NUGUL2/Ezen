<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="middlego.domain.BoardVo" %>
<% BoardVo bv = (BoardVo)request.getAttribute("bv"); //(BoardVo 로 강제 형변환) %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매글</title>
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
<table width="1500px">
<tr>
<td style="width: 100px"><a href="../main.do"><img src="../image/Logo.jpg" style="width: 200px;" id="Logo"></a></td>
<%-- <td style="width: 200px; text-align:right;"><a href="<%=request.getContextPath() %>/mypage/myPage.do"><img src="../image/mypage.png" style="width: 50px; height: 50px" id="mypage"></a></td>
 --%></tr>
</table>

<hr>

<h1 style="font-family:IBMPlexSansKR-Regular">판매글</h1>

<table border="1" style="width:100%; height:40px; border-collapse: collapse;">
<tr height="20px" style="border-left: none; border-right: none;">
<td width="100px" style="background-color:#71c4e9; font-weight:600;" align="center">제목</td>
<td width="900px"><%=bv.getSubject()%></td>
<td width="100px" style="background-color:#71c4e9; font-weight: 600;" align="center">작성자</td>
<td width="150px"><%=bv.getWriter()%></td>
<td width="5%" style="background-color:#71c4e9; font-weight:600;" align="center">작성일</td>
<td width="120px"><%=bv.getWriteday().substring(0,10) %></td>
</tr>
</table>
<br>

<table border="1" style="width:100%; border-collapse: collapse;">
<tr style="height:400px;">
<td><%=bv.getContent()%>
<img src="<%=request.getContextPath()%>/image/<%=bv.getFilename()%>"></td>
</tr>
</table>

<table align="right">
<tr>
<td colspan="2" style="text-align: right;">
<input type="button" name="modify" value="수정" onclick="location.href='<%=request.getContextPath() %>/board/boardModify.do?bidx=<%=bv.getBidx()%>'">
<input type="button" name="delete" value="삭제" onclick="location.href='<%=request.getContextPath() %>/board/boardDelete.do?bidx=<%=bv.getBidx()%>'">
<input type="button" name="reply" value="답변" onclick="location.href='<%=request.getContextPath() %>/board/boardReply.do?bidx=<%=bv.getBidx()%>&originbidx=<%=bv.getOriginbidx()%>&depth=<%=bv.getDepth()%>&level_=<%=bv.getLevel_()%>'">
<input type="button" name="list" value="목록" onclick="location.href='<%=request.getContextPath() %>/board/boardList.do'">
</td>
</tr>
</table>

</div>




</body>
</html>