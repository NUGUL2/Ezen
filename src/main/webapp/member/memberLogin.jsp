<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>미들고 Login</title>


<script>
function check() {
	var fm = document.frm;
	if(fm.memberId.value==""){
		alert("아이디를 입력해주세요");
		fm.memberId.focus();
		return;
	}else if (fm.memberPwd.value=="") {
		alert("비밀번호를 입력해주세요");
		fm.memberPwd.focus();
		return;
	}
	
	alert("로그인 중..");
	fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do";
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

table hr {
	border:0px;
	width: 150px;
	height:1px;
	color:#71c4e9;
	background-color:#71c4e9;
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

</head>
<body>
<form  name="frm">
<div style="margin: 100px 200px 30px 200px">
<center><a href="../main.do"><img src="../image/Logo.jpg"></a></center><br>
<p style="text-align: center; font-size: 30px; font-family:IBMPlexSansKR-Regular;">로그인</p>
<table style="width: 600px;" align="center">
<tr>
<td style="width:150px; text-align: right;">아이디</td>
<td style="text-align: center; width: 100px;"><input type="text" name="memberId" size="30" placeholder="아이디"></td>
<td></td>
</tr>
<tr style="height: 28px"> 
<td>
</td>
</tr>
<tr>
<td style="width:150px; text-align: right;">비밀번호</td>
<td style="text-align: center;"><input type="password" name="memberPwd" size="30" placeholder="비밀번호"></td>
<td style="width: 100px;"><input type="button" style="text-align:left;" id="button" value="확인" onclick="check();"></td>
</tr>
</table>
<br>

<table align="center">
<tr style="text-align: center;">
<td><hr></td>
<td style="width: 50px; color:rgb(113,196,233)">또는</td>
<td><hr></td> 
</tr>

<tr>
<td style="text-align: left;"><a href="<%=request.getContextPath() %>/member/memberJoin.do"><input type="button" id="button" name="join" value="회원가입"></a></td>
<td style="text-align: right;" colspan="2">
<input id="button" type="button" name="idf" value="ID찾기" onclick="location.href='<%=request.getContextPath() %>/member/memberidf.do'">
<input id="button" type="button" name="pwf" value="PW찾기" onclick="location.href='<%=request.getContextPath() %>/member/memberPwdf.do'">
</td>
</tr>

</table>
</div>


</form>
</body>
</html>