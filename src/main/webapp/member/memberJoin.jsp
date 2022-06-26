<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML>
<HTML>
 <HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>미들고 회원가입</TITLE>
  
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
  
  
  <script>
  function check() {
//	  alert("테스트입니다.");
	  var fm = document.frm;
	  
	  if(fm.memberId.value==""){
		  alert("아이디를 입력하세요");
		  fm.memberId.focus();
		  return;
	  }else if (fm.memberPwd.value==""){
		  alert("비밀번호를 입력하세요");
		  fm.memberPwd.focus();
		  return;
	  }else if (fm.memberPwd2.value==""){
		  alert("비밀번호 확인을 입력하세요");
		  fm.memberPwd2.focus();
		  return;
	  }else if (fm.memberPwd.value != fm.memberPwd2.value){
		  alert("비밀번호가 일치하지 않습니다.");
		  fm.memberPwd2.value="";
		  fm.memberPwd2.focus();
		  return;
	  }else if (fm.memberName.value==""){
		  alert("이름을 입력하세요");
		  fm.memberPwd2.focus();
		  return;
	  }else if (fm.memberJumin.value==""){
		  alert("주민번호를 입력하세요");
		  fm.memberJumin.focus();
		  return;
		  }else if (fm.memberPhone.value==""){
		  alert("연락처를 입력하세요");
		  fm.memberPhone.focus();
		  return;
	  }else if (fm.memberMail.value==""){
		  alert("이메일을 입력하세요");
		  fm.memberMail.focus();
		  return;  
	  }

	  
  alert("전송합니다.");
	fm.action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
	fm.method = "post";
	fm.submit();
	return;
	}
 
 </script>
 </HEAD>

 <BODY>
 <div style="margin: 100px 200px 30px 200px;" align="center">

<center><a href="../main.do"><img src="../image/Logo.jpg"></a></center><br>
<center><h1 style="font-family: IBMPlexSansKR-Regular">회원가입</h1></center>

<form name="frm"> 
 <table style="width:550px; height:500px; border-collapse: collapse;">

<tr>
<td style="text-align: right;">아이디</td>
<td><input type="text" name="memberId" size="30"></td>
</tr>

<tr>
<td style="text-align: right;">비밀번호</td>
<td><input type="password" name="memberPwd" size="30"></td>
</tr>

<tr>
<td style="text-align: right;">비밀번호확인</td>
<td><input type="password" name="memberPwd2" size="30"></td>
</tr>

<tr>
<td style="text-align: right;">이름</td>
<td><input type="text" name="memberName" size="30"></td>
</tr>

<tr>
<td style="text-align: right;">주민번호</td>
<td><input type="text" name="memberJumin" size="30">	
</td>
</tr>

<tr>
<td style="text-align: right;">연락처</td>
<td>
<input type="text" name="memberPhone" size="30">
</td>
</tr>

<tr>
<td style="text-align: right;">지역</td>
<td><input type="text" name="memberAddr"  size="30">
</td>
</tr>

<tr>
<td style="text-align: right;">이메일</td>
<td><input type="email" name="memberMail" size="30"></td>
</tr>

<tr>
<td colspan="2" style="text-align: center;" >
<input id="button" type="submit" value="확인" onclick="check();"> 
<input id="button" type="reset" value="다시작성"> 
</td>
</tr>
 </table>
 </form>
 </div>
 </BODY>
</HTML>
