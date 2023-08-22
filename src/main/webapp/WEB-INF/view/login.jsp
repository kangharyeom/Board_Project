<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<table>
<tr height="50">
	<td height="50">로그인</td>
	<td height="50"><input type="text" name="id"></td>
</tr>
<tr height="50">
	<td height="50">패스워드</td>
	<td height="50"><input type="password" name="pass"></td>
</tr>
<tr height="50">
    <button onclick="location.href='/join'">회원가입</button>
</tr>
<tr height="50">
    <form method="post" action="/login">
	<td height="50"><input type="submit" value="로그인"></td>
</tr>
</table>
</form>
</body>
</html>