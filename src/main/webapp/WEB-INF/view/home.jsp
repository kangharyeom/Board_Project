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

header {
margin-top: 20px;
display:flex;
flex-direction: row;
}

contentContainer{
display:flex;
justify-content: center;
align-items: center;
flex-direction: column;
margin-top: 20px;
width: 1264px;
}
	contentColumnBody{
	display:flex;
	justify-content: center;
	align-items: center;
	width: 1264px;
	border-top: 1px solid #DCDCDC;
	}

</style>

    </head>

    <body>
        <header>
			<headerFirst>
				<%
				String id = (String)session.getAttribute("id");
				String center = request.getParameter("center");
				%>
				<%
				if(id!=null){
					%>
					<%=id %> 님
					<button onclick="location.href='/'">로그아웃</button>
					<%
					
				}else if(center==null){
					//center에 값이 존재하는 경우에만 로그인버튼을 띄움.
					//처리를 안해주고 로그인 버튼을 누를시 LoginForm.jsp로 넘어가면 Top.jsp에 있는 로그인 버튼이 그대로 나옴.
					%>
					<button onclick="location.href='/login'">로그인</button>
					<%
				}
				else{
					%>
					<%}
					%>
			</headerFirst>
			
			<headerSecond>
				
			</headerSecond>
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
