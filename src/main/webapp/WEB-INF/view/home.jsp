<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>게시판 만들기 프로젝트</title>
<style>

body {
display:flex;
align-items: center;
flex-direction: column;
}

button{
	background-color:  #107dc9;
    cursor: pointer;
	color: white;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
}

contentContainer{
display:flex;
justify-content: center;
align-items: center;
flex-direction: column;
width: 1264px;
}
contentColumnBody{
display:flex;
justify-content: center;
align-items: center;
width: 1264px;

}

</style>

    </head>

    <body>
        <header>
			<%@ include file="/WEB-INF/view/header/header.jsp" %>
		</header>
			
		<contentContainer>
			<contentColumnHeader>
			</contentColumnHeader>
			
			<contentColumnBody>
				<%@ include file="/WEB-INF/view/board/boardList.jsp" %>
			</contentColumnBody>
			<contentColumnFooter>
			</contentColumnFooter>
		</contentContainer>
    </body>
</html>
