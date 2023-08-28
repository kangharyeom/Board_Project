<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<style>

userUpdateContainer{
    font-weight: bold;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    margin: 20px 0 20px 0;
    height: 560px;
    width: 900px;
    gap: 20px;
}

input {
    width: 240px;
    height: 24px;
    border: 4px solid #DCDCDC;
}

input::placeholder{
    padding-left: 10px;
}
userUpdateButtonContainer{
    width: 900px;
    display: flex;
    justify-content: center;
    align-items: center;
}

#userUpdate_btn{
    width: 250px;
}
</style>
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <userUpdateContainer>
        <div class="form-floating">
            <input type="email" class="form-control" id="emailInput" placeholder="name@example.com">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input type="number" class="form-control"  id="userIdInput" placeholder="Id">
            <label for="floatingPassword">userId</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control"  id="loginIdInput" placeholder="Id">
            <label for="floatingPassword">Id</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="passInput" placeholder="Password">
        <label for="floatingPassword">Password</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control"  id="nameInput" placeholder="Name">
            <label for="floatingPassword">Name</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control"  id="phoneInput" placeholder="Phone">
            <label for="floatingPassword">Phone</label>
        </div>
    </userUpdateContainer>

    <userUpdateButtonContainer>
        <button class="btn btn-primary" type="button" id="userUpdate_btn">회원 정보 수정 </button>
    </userUpdateButtonContainer>
    
    <script>
        $(document).ready(function () {
           
            // 버튼 클릭 시
            $("#userUpdate_btn").click(function () {
                var userId = $('#userIdInput').val();
                console.log("userUpdate_btn clicked");

                var jsonData = {};

                var emailValue = $('#emailInput').val();
                if (emailValue) {
                    jsonData["email"] = emailValue;
                }

                var loginIdValue = $('#idInput').val();
                if (loginIdValue) {
                    jsonData["loginId"] = loginIdValue;
                }

                var nameValue = $('#nameInput').val();
                if (nameValue) {
                    jsonData["name"] = nameValue;
                }

                var passwordValue = $('#passInput').val();
                if (passwordValue) {
                    jsonData["password"] = passwordValue;
                }

                var phoneValue = $('#phoneInput').val();
                if (phoneValue) {
                    jsonData["phone"] = phoneValue;
                }

                $.ajax({
                    url: "/api/users/"+userId,
                    type: "PATCH",
                    data: JSON.stringify(jsonData),
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        // 서버 응답 처리
                        console.log("Response from server: " + response);
                        alert("회원정보 수정이 성공적으로 처리되었습니다.");
                        window.location.href = "/";
                        },
                        error: function (error) {

                        // 서버 응답이 오류인 경우 /join 페이지로 리다이렉트
                        console.log("Error from server: " + error.statusText);
                        alert("정보를 확인 해주세요");
                        // window.location.href = "/users/mypage"+ userId;

                    }
                });
            });
        });
    </script>
</body>
</html>
