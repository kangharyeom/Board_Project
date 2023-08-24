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

bodyContainer{
	display:flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	width: 900px;
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
width: 900px;
}

contentColumnBody{
display:flex;
justify-content: center;
align-items: center;
width: 900px;
}

</style>

    </head>

    <body>
		<bodyContainer>

			<header>
				<%@ include file="/WEB-INF/view/header/header.jsp" %>
			</header>
			
			<contentContainer>
			<contentColumnHeader>
				<%@ include file="/WEB-INF/view/board/boardHeader.jsp" %>
			</contentColumnHeader>
			
			<contentColumnBody>
				<contentListDefault>
					<%@ include file="/WEB-INF/view/board/boardList.jsp" %>
				</contentListDefault>
				<contentkeyword>
					
				</contentkeyword>
				<contentSearchByName>
					
				</contentSearchByName>
				<contentNewest>

				</contentNewest>
				<contentLatest>
					
				</contentLatest>
			</contentColumnBody>
			<contentColumnFooter>
			</contentColumnFooter>
		</contentContainer>
	</bodyContainer>
</body>
</html>
