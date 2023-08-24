<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
<style>

button{
    font-weight: bold;
	background-color:  #DCDCDC;
    cursor: pointer;
	color: black;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
}

body {
display:flex;
align-items: center;
flex-direction: column;
}

header{
    display:flex;
    flex-direction: row;
    justify-content: center;
    width: 900px;
    height: 80px;
}

headerContainer {
width: 900px;
height: 80px;
display:flex;
flex-direction: row;
justify-content: center;
align-items: center;
border-bottom: 1px solid #DCDCDC;
}

#homeButton { 
    width: 90px;
    height: 30px;
}

#postButton { 
    width: 90px;
    height: 30px;
}

#loginButton { 
    width: 90px;
    height: 30px;
}

#logOutButton { 
    width: 90px;
    height: 30px;
}

</style>

</head>
    <body>
        <headerContainer>
            <headerFirst>
                <button id="homeButton" onclick="location.href='/'">Home</button>
            </headerFirst>
            
            <headerSecond>
                <button id="postButton" onclick="location.href='/board/post'">글쓰기</button>
            </headerSecond>
            <headerThird>
                <%
                String id = (String)session.getAttribute("id");
                String center = request.getParameter("center");
                %>
                <%
                if(id!=null){
                    %>
                    <%=id %> 님
                    <button id="logOutButton" onclick="location.href='/'">로그아웃</button>
                    <%
                    
                }else if(center==null){
                    //center에 값이 존재하는 경우에만 로그인버튼을 띄움.
                    //처리를 안해주고 로그인 버튼을 누를시 LoginForm.jsp로 넘어가면 Top.jsp에 있는 로그인 버튼이 그대로 나옴.
                    %>
                    <button id="loginButton" onclick="location.href='/login'">로그인</button>
                    <%
                }
                else{
                    %>
                    <%}
                    %>
            </headerThird>
            <headerFourth>
                <button id="postButton" onclick="location.href='/users/mypage'">마이페이지</button>
            </headerFourth>
        </headerContainer>
    </body>
</html>
