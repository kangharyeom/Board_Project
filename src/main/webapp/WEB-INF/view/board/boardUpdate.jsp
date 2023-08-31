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
    margin: 50px 0 30px 0;
    gap: 10px;
}
.userInfoClass{
    width: 200px;
    height: 40px;

}
postInfo{
    display: flex;
    flex-direction: column;
}

#titleInput{
    height: 40px;
    margin-bottom: 30px;
}

#contentInput{
    height: 200px;
}
input{
    border: 4px solid #DCDCDC;
}
input::placeholder{
    padding-left: 10px;
}

contentUpdateButton{
    margin: 20px 0 50px 0;

    display: flex;
    align-items: center;
    justify-content: right;
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
            <contentId class="form-floating">
                <input type="number" class="form-control"  id="contentIdInput" placeholder="contentId">
                <label for="floatingPassword">contentId</label>
            </contentId>
            <userName class="form-floating">
                <input type="text" class="form-control"  id="nameInput" placeholder="Name">
                <label for="floatingPassword">Name</label>

            </userName>
        </userInfo>

        <postInfo>
            <boardTitle class="form-floating">
                <td >제목</td>
                <input type="text" class="form-control"  id="titleInput" placeholder="제목">
            </boardTitle>
            <boardContent class="form-floating">
                <td >내용</td>
                <input type="text" class="form-control"  id="contentInput" placeholder="내용">

            </boardContent>
        </postInfo>

        <contentUpdateButton>
            <button class="btn btn-primary" type="button" id="contentUpdate_btn">게시글 수정하기</button>

        </contentUpdateButton>
    </boardPostContainer>

    <script>
        $(document).ready(function () {
           
            // 버튼 클릭 시
            $("#contentUpdate_btn").click(function () {
                var contentId = $('#contentIdInput').val();
                console.log("contentPost_btn clicked");

                var jsonData = {};

                var titleValue = $('#titleInput').val();
                if (titleValue) {
                    jsonData["title"] = titleValue;
                }

                var contentValue = $('#contentInput').val();
                if (contentValue) {
                    jsonData["password"] = contentValue;
                }

                $.ajax({
                    url: "/api/contents/"+contentId,
                    type: "PATCH",
                    data: JSON.stringify(jsonData),
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        // 서버 응답 처리
                        console.log("Response from server: " + response);
                        alert("게시글이 성공적으로 수정되었습니다.");
                        window.location.href = "/";
                        },
                        error: function (error) {

                        // 서버 응답이 오류인 경우 /join 페이지로 리다이렉트
                        console.log("Error from server: " + error.statusText);
                        alert("정보를 확인 해주세요");
                        window.location.href = "/contents/upadate";

                    }
                });
            });
        });
    </script>

    <boardDeleteContainer>
        <%@ include file="/WEB-INF/view/board/boardDelete.jsp" %>
    </boardDeleteContainer>
</body>
</html>
