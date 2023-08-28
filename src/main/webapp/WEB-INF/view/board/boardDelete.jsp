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

#contentInput{
    height: 200px;
}

input{
    border: 4px solid #DCDCDC;
}

#delete_btn{
    margin-top: 20px;
}

</style>

<body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <boardPostContainer>
        
        <deleteWarn>
            <warnId height="50">
                <td height="50">게시글을 삭제 하시고 싶으시다면 "게시글 삭제" 버튼을 눌러주세요</td>
                <td height="50"><input type="number" name="userId" id="idInput" class="contentDeleteButtonClass"></td>
            </warnId>
        </deleteWarn>

        <deldeteButton>
            <button class="btn btn-primary" type="button" id="delete_btn">게시글 삭제</button>
        </deldeteButton>
    </boardPostContainer>

    <script>
        $(document).ready(function () {
            // 버튼 클릭 시
            $("#delete_btn").click(function () {
                var contentId = $('#idInput').val()
                console.log("delete_btn");

                $.ajax({
                    url: "/api/contents/"+contentId,
                    type: "DELETE",
                    data: JSON.stringify(),
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        // 서버 응답 처리
                        console.log("Response from server: " + response);
                        alert("게시글이 성공적으로 삭제되었습니다.");
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
