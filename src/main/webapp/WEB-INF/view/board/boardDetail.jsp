<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

contentDetail{
    margin-top: 50px;
    width: 900px;
    border-bottom: 2px solid #DCDCDC;
    
}

commentPost{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 50px;
    width: 900px;
}

</style>
    </head>    
    <body>
        <header>
			<%@ include file="/WEB-INF/view/header/header.jsp" %>
		</header>

        <contentDetail>
            <%@ include file="/WEB-INF/view/board/contentResponseDetail.jsp" %>
        </contentDetail>
        
        <commentListByContentId>
            <%@ include file="/WEB-INF/view/comment/commentListByContentId.jsp" %>
        </commentListByContentId>
        
        <commentPost>
            <%@ include file="/WEB-INF/view/comment/commentPost.jsp" %>
        </commentPost>
    </body>
</html>