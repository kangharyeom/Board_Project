<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<style>

button{
    font-weight: bold;
	background-color:  #107dc9;
    cursor: pointer;
	color: white;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
}

boardpostContainer{
    font-weight: bold;
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    justify-content: left;
    width: 600px;
}
userInfo{
    display: flex;
    width: 400px;
    justify-content: space-between;
    flex-direction: row;
    margin-bottom: 20px;
}
.userInfoClass{
    width: 150px;
}
postInfo{
    display: flex;
    flex-direction: column;
}
.postClass{
    width: 595px;
    height: 30px;
    margin-bottom: 20px;
}

#contentInput{
    height: 200px;
}
input{
    border: 4px solid #DCDCDC;
}

contentPostButton{
    display: flex;
    align-items: center;
    justify-content: right;
}

#contentPost_btn{
    border: 4px solid #DCDCDC;
    background-color: #DCDCDC;
    width: 150px;
    height: 40px;
    color: black;
}
    
</style>

<body>
    <header>
        <%@ include file="/WEB-INF/view/header/header.jsp" %>
    </header>

    <boardPostContainer>
        
        <userInfo>
            <userId height="50">
                <td height="50">유저아이디</td>
                <td height="50"><input type="number" name="userId" id="idInput" class="userInfoClass"></td>
            </userId>
            <userName height="50">
                <td height="50">이름</td>
                <td height="50"><input type="text" name="name" id="nameInput" class="userInfoClass"></td>
            </userName>
        </userInfo>

        <postInfo>
            <boardTitle height="50">
                <td height="50">제목</td>
                <td height="50"><input type="text" name="title" id="titleInput" class="postClass"></td>
            </boardTitle>
            <boardContent height="50">
                <td height="50">내용</td>
                <td height="50"><input type="text" name="content" id="contentInput" class="postClass"></td>
            </boardContent>
        </postInfo>

        <contentPostButton>
            <button type="button" id="contentPost_btn">게시글 등록하기</button>
        </contentPostButton>
    </boardPostContainer>

    <script>
        $(document).ready(function () {
            // 버튼 클릭 시
            $("#join_btn").click(function () {
                console.log("join_btn clicked");

                var jsonData = {
                    "userId": $('#idInput').val(),
                    "name": $('#nameInput').val(),
                    "title": $('#titleInput').val(),
                    "content": $('#contentInput').val(),
                };

                $.ajax({
                    url: "/api/contents",
                    type: "POST",
                    data: JSON.stringify(jsonData),
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        // 서버 응답 처리
                        console.log("Response from server: " + response);
                        alert("게시글이 성공적으로 등록되었습니다.");
                        window.location.href = "/";
                        },
                        error: function (error) {

                        // 서버 응답이 오류인 경우 /join 페이지로 리다이렉트
                        console.log("Error from server: " + error.statusText);
                        alert("정보를 확인 해주세요");
                        window.location.href = "/board/post";

                    }
                });
            });
        });
    </script>
</body>
</html>
