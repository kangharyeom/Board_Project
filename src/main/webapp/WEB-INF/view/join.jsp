<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
            <table>
                <tr height="50">
                    <td height="50">아이디</td>
                    <td height="50"><input type="text" name="loginId" id="idInput"></td>
                </tr>
                <tr height="50">
                    <td height="50">이메일</td>
                    <td height="50"><input type="text" name="email" id="emailInput"></td>
                </tr>
                <tr height="50">
                    <td height="50">이름</td>
                    <td height="50"><input type="text" name="name" id="nameInput"></td>
                </tr>
                <!-- 다른 입력 필드들도 동일하게 처리 -->
                <tr height="50">
                    <td height="50">패스워드</td>
                    <td height="50"><input type="password" name="password" id="passInput"></td>
                </tr>
                <tr height="50">
                    <td height="50">휴대폰 번호</td>
                    <td height="50"><input type="text" name="phone" id="phoneInput"></td>
                </tr>
            </table>
            <button type="button" id="join_btn">제출</button>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js">
            (function(){
                $('#join_btn').click(function(){
                    console.log("join_btn clicked");

                    var jsonData = {
                        loginId:$('#idInput').val(),
                        email:$('#emailInput').val(),
                        name:$('#nameInput').val(),
                        password:$('#passInput').val(),
                        phone:$('#phoneInput').val()
                    };

                    $.ajax({
                       url: "/api/users/join",
                       type: "POST",
                       data: JSON.stringify(jsonData),
                       contentType: "application/json; charset=utf-8",
                       success: function(data){
                       },
                       error: function (request, status, error){
                       console.log("Response from server: " + data);
                   }
                });
            });

        </script>
    </body>
</html>
