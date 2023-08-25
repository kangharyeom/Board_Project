<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<style>

boardpostContainer{
    font-weight: bold;
    margin-top: 60px;
    display: flex;
    flex-direction: column;
    justify-content: left;
    width: 900px;
}
deleteWarn{
    width: 900px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 20px;
}
warnId{
    width: 500px;
}
deldeteButton{
    width: 500px;
}

input{
    border: 4px solid #DCDCDC;
}

    
</style>

<body>
    <boardPostContainer>
        
        <deleteWarn>
            <warnId height="50">
                <td height="50">회원을 탈퇴 하시고 싶으시다면 "회원 탈퇴" 버튼을 눌러주세요</td>
                <td height="50"><input type="number" name="userId" id="idInput" class="userDeleteClass"></td>
            </warnId>
            <deldeteButton>
                <button type="button" id="delete_btn">회원 탈퇴</button>
            </deldeteButton>
        </deleteWarn>

    </boardPostContainer>

    <script>
        $(document).ready(function () {
            // 버튼 클릭 시
            $("#delete_btn").click(function () {
                var userId = $('#idInput').val()

                console.log("delete_btn");

                $.ajax({
                    url: "/api/users/"+userId,
                    type: "DELETE",
                    data: JSON.stringify(),
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        // 서버 응답 처리
                        console.log("Response from server: " + response);
                        alert("회원 탈퇴가 성공적으로 처리되었습니다.");
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
