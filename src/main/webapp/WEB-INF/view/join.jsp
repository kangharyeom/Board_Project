<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<form method="post" action="http://localhost:8080/users">
</head>
<body>

<table>
<tr height="50">
	<td height="50">아이디</td>
	<td height="50"><input type="text" name="id"></td>
</tr>
<tr height="50">
	<td height="50">이메일</td>
	<td height="50"><input type="text" name="email"></td>
</tr>
<tr height="50">
	<td height="50">이름</td>
	<td height="50"><input type="text" name="name"></td>
</tr>
<tr height="50">
	<td height="50">패스워드</td>
	<td height="50"><input type="password" name="pass"></td>
</tr>
<tr height="50">
	<td height="50">휴대폰 번호</td>
	<td height="50"><input type="text" name="phone"></td>
</tr>
<tr height="50">
    <form method="post" action="http://localhost:8080/users/join">
    <td height="50"><input type="submit" value="회원가입"></td>
</tr>
</table>
</form>
</body>
</html>