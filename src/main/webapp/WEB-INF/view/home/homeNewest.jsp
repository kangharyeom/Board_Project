<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
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
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
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
						<%@ include file="/WEB-INF/view/board/filter/boardNewest.jsp" %>
					</contentListDefault>
				</contentColumnBody>
				<contentColumnFooter>

				</contentColumnFooter>
		</contentContainer>
	</bodyContainer>
</body>
</html>
