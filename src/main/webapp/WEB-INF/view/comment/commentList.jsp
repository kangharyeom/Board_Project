<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>

    <body>
        <div id="contentDiv">
            <h1>댓글 입니다.</h1>
            <p><strong>댓글 내용:</strong> <span id="comment"></span></p>
            <p><strong>유저 닉네임:</strong> <span id="commentName"></span></p>
            <p><strong>생성된 시간:</strong> <span id="commentCreatedAt"></span></p>
            <p><strong>수정된 시간:</strong> <span id="commentModifiedAt"></span></p>
        </div>
            <script>
               $(document).ready(function () {
                    // var commentsUrl = "/api/comments?page=" + page + "&size=" + size;
                    var commentsUrl = "/api/comments/1";

                    $.ajax({
                        type: "GET",
                        url: commentsUrl,
                        dataType: "json",
                        success: function (data) {
                            // 서버 응답을 받았을 때 화면에 표시합니다.
                            console.log("Response from server: " + JSON.stringify(data));

                            // JSON 데이터에서 필요한 값을 추출하여 화면에 표시
                            $("#comment").text(data.comment);
                            $("#commentName").text(data.name);
                            $("#commentCreatedAt").text(data.createdAt);
                            $("#commentModifiedAt").text(data.modifiedAt);
                        },
                        error: function () {
                            alert("데이터를 가져올 수 없습니다.");
                        }
                    });
                });

            </script>
    </body>
</html>