<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<style>

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
    gap: 10px;
}

postInfo{
    display: flex;
    flex-direction: column;
}


#contentInput{
    margin-top: 0px;
    height: 200px;
}

contentPostButton{
    margin-top: 10px;
    display: flex;
    align-items: center;
    justify-content: right;
}

fileInput{
    margin-top: 20px;
}

</style>

<body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <header>
        <%@ include file="/WEB-INF/view/header/header.jsp" %>
    </header>

    <boardPostContainer>
        <userInfo>
            <userId class="form-floating">
                <input type="number" class="form-control"  id="userIdInput" placeholder="Id">
                <label for="floatingPassword">userId</label>
            </userId>
            <userName class="form-floating">
                <input type="text" class="form-control"  id="nameInput" placeholder="Name">
            <label for="floatingPassword">Name</label>
            </userName>
        </userInfo>

        <postInfo>
            <boardTitle class="form-floating">
                <input type="text" class="form-control"  id="titleInput" placeholder="Name">
            <label for="floatingPassword">제목</label>
            </boardTitle>
            <fileInput class="input-group mb-3">
                <label class="input-group-text" for="inputGroupFile01">Upload</label>
                <input type="file" class="form-control" id="inputGroupFile01">
              </fileInput>
            <boardContent class="form-floating">
                <input type="text" class="form-control"  id="contentInput" placeholder="Name">
            <label for="floatingPassword">내용</label>
            </boardContent>
        </postInfo>

        <contentPostButton>
            <button class="btn btn-primary" type="button" id="contentPost_btn">게시글 등록하기</button>
        </contentPostButton>
    </boardPostContainer>

    <script>
        $(document).ready(function () {
            // 버튼 클릭 시
            $("#contentPost_btn").click(function () {
                console.log("contentPost_btn clicked");

                var jsonData = {
                    "userId": $('#userIdInput').val(),
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
