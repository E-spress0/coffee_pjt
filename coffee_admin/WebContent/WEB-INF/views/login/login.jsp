<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/asset/css/default_css.css">
<title>로그인</title>
</head>
<body>
<jsp:include page="../default_layout/header.jsp"/>
<section>
	<div class="loginTable" align="center">
		<br/>
		<form action="loginCheck" id="loginF">
				<input type="text" class="s170d16 abs" name="userId">
				<input type="password" class="s170d16t5 abs" name="userPass">
				<input type="button"  id="loginBtn" class="abs" value="로그인">
				<a href="/userCrt" class="abs">회원가입</a>
		</form>
	</div>
</section>
<jsp:include page="../default_layout/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$('#loginBtn').click(function() {
		var id = $('input[name=userId]').val();
		var pw = $('input[name=userPass]').val();
		var alertText = "";
		var valCheck = true;
		
		if(id == '' || id == null || pw == '' || pw == null){
			valCheck = false;
		}
		
		if(valCheck){
			$('#loginF').submit();
		}else{
			alertText = (id == '' || id == null)? "아이디를 입력해 주세요" : (pw == '' || pw == null)? "비밀번호를 입력해주세요." : "알 수 없는 에러";
			alert(alertText);
		}
	});
</script>
</html>