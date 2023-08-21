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
    	#imageArea{
    		width: 800px;
    		margin: 100px auto;
    		display: flex;
    		justify-content : center;
    		align-items : center;
    		height: 70vh;
    	}

    	button {

        }

        body {
         display:flex;
         flex-direction: column;
        }

        header {
            display:flex;
            flex-direction: row;

                }

    	#imageArea{
            		width: 800px;
            		margin: 100px auto;
            		display: flex;
            		justify-content : center;
            		align-items : center;
            		height: 70vh;
            	}

    </style>

    </head>

    <body>
        <header>
        <%@ include file="/WEB-INF/view/common/header.jsp" %>
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
        </header>

	        <%@ include file="/WEB-INF/view/board/boardList.jsp" %>
             <p>Title: ${content.title}</p>
             <p>Content: ${content.content}</p>
             <p>Created Date: ${content.createdDate}</p>
    </body>
</html>
