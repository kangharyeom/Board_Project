<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">

    </head>

    <body>
        <th>
         <p>테스트</p>
            <button id="jsonSend" type="button"> show databases; 구문 실행</button>
            <script>
                $('#jsonSend').click(function () {
                    jsonSend();
                });

                function jsonSend() {
                    $.ajax({
                        type: "POST",
                        url: "/contents",
                        data: {sql : 'show databases;'}, //json을 보내는 방법
                        success: function (data) { //서블렛을 통한 결과 값을 받을 수 있습니다.
                            alert(data);
                        },
                        error: function (e) {
                            console.log(e);
                            alert('저장에 실패했습니다.');
                        }
                    });
                }
            </script>
		    <button onclick="location.href='/join'">1번글</button>
		    <button onclick="location.href='/post'">2번글</button>
        </th>
    </body>
</html>