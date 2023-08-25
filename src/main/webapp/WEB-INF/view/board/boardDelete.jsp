<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<style>

button{
    font-weight: bold;
	background-color:  #DCDCDC;
    cursor: pointer;
	color: black;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
}

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

</style>

<body>
    <boardPostContainer>
        
        <deleteWarn>
            <warnId height="50">
                <td height="50">게시글을 삭제 하시고 싶으시다면 "게시글 삭제" 버튼을 눌러주세요</td>
                <td height="50"><input type="number" name="userId" id="idInput" class="contentDeleteButtonClass"></td>
            </warnId>
        </deleteWarn>>

        <deldeteButton>
            <button type="button" id="delete_btn">게시글 삭제</button>
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
