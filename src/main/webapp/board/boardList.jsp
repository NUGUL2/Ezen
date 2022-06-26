<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="middlego.domain.*"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist"); %>
<%PageMaker pm = (PageMaker)request.getAttribute("pm");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
</head>
<body>



<table width="100%">
<% for (BoardVo bv : alist){ %>

<td colspan="2">

<table>
<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=bv.getBidx()%>">
<% if(bv.getFilename()!=null) { %>
   <img src="<%=request.getContextPath()%>/image/<%=bv.getFilename()%>" width="200px" height="200px">
<%}else if(bv.getFilename()==null){ %>
<img src="<%=request.getContextPath()%>/image/gi.jpg" width="200px" height="200px">
<%}%>
</a>


<% 
for(int i=1; i<=bv.getLevel_(); i++){
	out.println("&nbsp;&nbsp;");
if(i==bv.getLevel_()) {
		out.println("ㄴ");
	}
}
%>


<tr><td>
<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=bv.getBidx()%>" style="text-decoration: none; color:black;"><%=bv.getSubject()%></a>
</td>

<td>
<%=bv.getWriter() %>
</td>
</tr>

<tr><td>
<%=bv.getWriteday() %>
</td></tr>
</table>
</td>
<%}%>
</table>









<table style="width:800px; text-align: center;">

<tr>
<td style="width:200px; text-align: right;">
<% 
String keyword =pm.getScri().getKeyword();
String searchType=pm.getScri().getSearchType();

if(pm.isPrev()==true)  {
	out.println("<a href ='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
	}
%>
</td>

<td>
<% 
for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+" '>" +i+ "</a>");
}
%>
</td>

<td style="width:200px; text-align: left;">
<%
if(pm.isNext()&&pm.getEndPage()>0){
	out.println("<a href ='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>
</td>
</tr>

</table>


</body>
</html>