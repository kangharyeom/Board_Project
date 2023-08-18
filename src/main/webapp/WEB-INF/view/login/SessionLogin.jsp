<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");

	session.setAttribute("id", id);
	session.setAttribute("pass", pass);
	//세션 유지시간 설정
	session.setMaxInactiveInterval(60); //1분간 아이디 유지
	response.sendRedirect("Main.jsp");
%>
</body>
</html>