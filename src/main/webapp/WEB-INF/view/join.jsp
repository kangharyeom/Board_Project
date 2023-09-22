<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

joinContainer{
    display: flex;
    align-items: center;
    flex-direction: column;
    gap: 10px;
    justify-content: space-between;
    margin: 20px 0 20px 0;
    height: 400px;
}

.form-floating{
    width: 320px;
}

</style>
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <joinContainer>
        <div class="form-floating">
            <input type="email" class="form-control" id="emailInput" placeholder="name@example.com">
            <label for="floatingInput">Email address</label>
          </div>
          <div class="form-floating">
            <input type="text" class="form-control"  id="loginIdInput" placeholder="Id">
            <label for="floatingPassword">Id</label>
          </div>
          <div class="form-floating">
            <input type="password" class="form-control" id="passwordInput" placeholder="Password">
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
    </joinContainer>
    <!-- <button type="button" id="join_btn">회원 등록</button> -->

    <script>
        $(document).ready(function () {
            // 버튼 클릭 시
            $("#join_btn").click(function () {
                console.log("join_btn clicked");

                var jsonData = {
                    "email": $('#emailInput').val(),
                    "loginId": $('#loginIdInput').val(),
                    "name": $('#nameInput').val(),
                    "password": $('#passwordInput').val(),
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
                            alert(jsonData)
                            console.log($('#emailInput').val(),
                                        $('#loginIdInput').val(),
                                        $('#nameInput').val(),
                                        $('#passInput').val(),
                                        $('#phoneInput').val())

                        // 서버 응답이 오류인 경우 /join 페이지로 리다이렉트
                        console.log("Error from server: " + error.statusText);
                        alert("정보를 확인 해주세요");
                        window.location.href = "/login";

                    }
                });
            });
        });
    </script>
</body>
</html>
