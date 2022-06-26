<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미들고 ID찾기</title>

<script>
debugger;
function check() {
	
	var fm = document.frm;
	if(fm.memberName.value==""){
		alert("이름를 입력해주세요");
		fm.memberName.focus();
		return;
	}else if (fm.memberPhone.value=="") {
		alert("전화번호를 입력해주세요");
		fm.memberPhone.focus();
		return;
	}
	
	fm.action = "<%=request.getContextPath()%>/member/memberidfAction.do";
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
</style>

</head>
<body>

<form name="frm">
<center><a href="../main.do"><img src="../image/Logo.jpg"></a></center>
<p style="text-align: center; font-size: 30px; font-family: IBMPlexSansKR-Regular">ID 찾기</p>

<table style="width: 600px;" align="center">

<tr>
<td style="width:150px">이름</td>
<td><input type="text" name="memberName" size="30" placeholder="이름"></td>
</tr>

<tr style="height: 28px"> 
<td>
</td>
</tr>

<tr>
<td style="width:150px">전화번호</td>
<td><input type="text"  name="memberPhone" size="30" placeholder="전화번호"></td>

<td><input type="button" value="찾기" onclick="check();"></td>
</tr>

</table>

</form>

</body>
</html>