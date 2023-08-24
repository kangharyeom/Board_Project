<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

</style>
</head>
<body>
    <header>
        <%@ include file="/WEB-INF/view/header/header.jsp" %>
    </header>

    <userUpdateContainer>
        <%@ include file="/WEB-INF/view/users/userUpdate.jsp" %>
    </userUpdateContainer>

    <userDeleteContainer>
        <%@ include file="/WEB-INF/view/users/userDelete.jsp" %>
    </userDeleteContainer>
</body>
</html>
