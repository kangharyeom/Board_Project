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
<style>

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

</style>

</head>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
        <headerContainer>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button id="homeButton" onclick="location.href='/'" type="button" class="btn btn-primary">Home</button>
                    <button  id="postButton" onclick="location.href='/board/post'" type="button" class="btn btn-primary">New Post</button>
                    <%
                    String id = (String)session.getAttribute("id");
                    String center = request.getParameter("center");
                    %>
                    <%
                    if(id!=null){
                        %>
                        <%=id %> 님
                            <button id="logOutButton" onclick="location.href='/'" type="button" class="btn btn-primary">로그아웃</button>
                        <%
                        
                    }else if(center==null){
                        //center에 값이 존재하는 경우에만 로그인버튼을 띄움.
                        //처리를 안해주고 로그인 버튼을 누를시 LoginForm.jsp로 넘어가면 Top.jsp에 있는 로그인 버튼이 그대로 나옴.
                        %>
                            <button id="loginButton" onclick="location.href='/login'"  type="button" class="btn btn-primary">Login</button>
                        <%
                    }
                    else{
                    %>
                    <%}
                    %>
                        <button id="postButton" onclick="location.href='/users/mypage'" type="button" class="btn btn-primary" >My Page</button>
            </div>
        </headerContainer>
    </body>
</html>
