<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
 
 button{
	background-color:  #107dc9;
    cursor: pointer;
	color: white;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
} 

joinContainer{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 30px 0 30px 0;
    height: 400px;
}

#join_btn{
    width: 300px;
    height: 30px;
}

idInfo{
    font-weight: bold;
    display: flex;
    flex-direction: column;
}

emailInfo{
    font-weight: bold;
    display: flex;
    flex-direction: column;
}

nameInfo{
    font-weight: bold;
    display: flex;
    flex-direction: column;
}

passwordInfo{
    font-weight: bold;
    display: flex;
    flex-direction: column;
}

phoneInfo{
    font-weight: bold;
    display: flex;
    flex-direction: column;
}

input {
    width: 240px;
    height: 24px;
}

</style>
</head>
<body>
    <header>
        <%@ include file="/WEB-INF/view/header/header.jsp" %>
    </header>

    <joinContainer>
        <idInfo height="50">
            <td height="50">아이디</td>
            <td height="50"><input type="text" name="loginId" id="idInput"></td>
        </idInfo>
        <emailInfo height="50">
            <td height="50">이메일</td>
            <td height="50"><input type="text" name="email" id="emailInput"></td>
        </emailInfo>
        <nameInfo height="50">
            <td height="50">이름</td>
            <td height="50"><input type="text" name="name" id="nameInput"></td>
        </nameInfo>
        <passwordInfo height="50">
            <td height="50">패스워드</td>
            <td height="50"><input type="password" name="password" id="passInput"></td>
        </passwordInfo>
        <phoneInfo height="50">
            <td height="50">휴대폰 번호</td>
            <td height="50"><input type="text" name="phone" id="phoneInput"></td>
        </phoneInfo>
    </joinContainer>
    <button type="button" id="join_btn">제출</button>

    <script>
        $(document).ready(function () {
            // 버튼 클릭 시
            $("#join_btn").click(function () {
                console.log("join_btn clicked");

                var jsonData = {
                    "email": $('#emailInput').val(),
                    "loginId": $('#idInput').val(),
                    "name": $('#nameInput').val(),
                    "password": $('#passInput').val(),
                    "phone": $('#phoneInput').val()
                };

                $.ajax({
                    url: "/api/users/join",
                    type: "POST",
                    data: JSON.stringify(jsonData),
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        // 서버 응답 처리
                        console.log("Response from server: " + response);
                        alert("회원가입이 성공적으로 처리되었습니다.");
                        window.location.href = "/login";
                        },
                        error: function (error) {

                        // 서버 응답이 오류인 경우 /join 페이지로 리다이렉트
                        console.log("Error from server: " + error.statusText);
                        alert("정보를 확인 해주세요");
                        window.location.href = "/join";

                    }
                });
            });
        });
    </script>
</body>
</html>
