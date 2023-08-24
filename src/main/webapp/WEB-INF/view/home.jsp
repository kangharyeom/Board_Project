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
	background-color:  #DCDCDC;
    cursor: pointer;
	color: black;
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

contentHeader{
    margin-top: 10px;
    height: 150px;
}

contentHeaderContainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 200px;
}

searchBar{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    margin-bottom: 20px;
}

#titleAndContentFilter{
    width: 90px;
    height: 30px;
}

#ContentFilterByUserName{
    width: 90px;
    height: 30px;
}

titleAndContentUserName{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

titleAndPost{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    justify-content: space-around;
    width: 900px;
    
}

Input{
    border-radius: 5px;
    width: 500px;
    height: 50px;
    border: 4px solid #DCDCDC;
}

Input::placeholder{
    padding-left: 10px;
}

#newestFilter{
    width: 90px;
    height: 30px;
}

#latestFilter{
    width: 90px;
    height: 30px;
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
				<%@ include file="/WEB-INF/view/header/boardHeader.jsp" %>
			</contentColumnHeader>
			
			<contentColumnBody>
				<contentListDefault>
					<%@ include file="/WEB-INF/view/board/boardList.jsp" %>
				</contentListDefault>
			</contentColumnBody>
			<contentColumnFooter>
				
			</contentColumnFooter>
		</contentContainer>
	</bodyContainer>
</body>
</html>
