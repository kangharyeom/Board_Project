<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <table>
        <tr height="50">
            <td height="50">유저아이디</td>
            <td height="50"><input type="number" name="userId" id="idInput"></td>
        </tr>
        <tr height="50">
            <td height="50">이름</td>
            <td height="50"><input type="text" name="name" id="nameInput"></td>
        </tr>
        <tr height="50">
            <td height="50">제목</td>
            <td height="50"><input type="text" name="title" id="titleInput"></td>
        </tr>
        <tr height="50">
            <td height="50">내용</td>
            <td height="50"><input type="text" name="content" id="contentInput"></td>
        </tr>
    </table>
    <button type="button" id="join_btn">제출</button>

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
