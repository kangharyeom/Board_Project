<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
        <h2>JSON Data Input</h2>
        <form id="jsonDataForm">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title"><br>

            <label for="content">Content:</label>
            <textarea id="content" name="content"></textarea><br>

            <input type="submit" value="Submit">
        </form>

        <script>
            $(document).ready(function () {
                $("#jsonDataForm").submit(function (event) {
                    event.preventDefault(); // 폼 기본 동작 방지

                    // 입력된 title 및 content 값을 가져옵니다.
                    var title = $("#title").val();
                    var content = $("#content").val();

                    // JSON 데이터 생성
                    var jsonData = {
                        title: title,
                        content: content,
                    };

                    // JSON 데이터를 서버로 POST 요청으로 보냅니다.
                    $.ajax({
                        type: "POST",
                        url: "/contents", // 엔드포인트를 "/contents"로 변경
                        contentType: "application/json",
                        data: JSON.stringify(jsonData),
                        success: function (response) {
                            // 서버 응답 처리
                            console.log("Response from server: " + response);
                        }
                    });
                });
            });
        </script>
    </body>
</html>
