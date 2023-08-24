<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

loginContainer{
	font-weight: bold;
	margin-top: 30px;
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 400px;
}

loginInfo{
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
}

passwordInfo{
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
}

input{
	margin-top: 10px;
	height: 30px;
}

input::placeholder{
	padding: 10px 10px;
}

RegisterButtons{
	margin-top: 30px;
	display: flex;
	flex-direction: column;
}

#registerButton{
	font-weight: bold;
	height: 30px;
	width: 200px;
	margin-bottom: 20px;
}

.loginButton{
	height: 30px;
	width: 200px;
}

button{
	font-weight: bold;
	background-color:  #107dc9;
    cursor: pointer;
	color: white;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
}

</style>
</head>
	<body>
		<header>
			<%@ include file="/WEB-INF/view/header/header.jsp" %>
		</header>

		<loginContainer>
			<loginInfo height="50">
				<login height="50">ID</login>
				<loginSource height="50"><input type="text" name="id"></loginSource>
			</loginInfo>
			<passwordInfo height="50">
				<password height="50">password</password>
				<passwordSource height="50"><input placeholder="비밀번호를 입력하세요." type="password" name="pass"></passwordSource>
			</passwordInfo>
			<RegisterButtons>

				<RegisterInfo height="50">
					<button id="registerButton" onclick="location.href='/join'">회원가입</button>
				</RegisterInfo>
				
				<loginButton height="50">
					<button class="loginButton" onclick="location.href='/login'">로그인</button>
				</loginButton>

			</RegisterButtons>
		</loginContainer>
	</body>
</html>