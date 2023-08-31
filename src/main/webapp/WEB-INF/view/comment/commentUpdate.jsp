<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

commentpost{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 900px;
}

commentpostcontainer{
    display: flex;
    flex-direction: column;
    border-bottom: 1px solid #DCDCDC;
    width: 700px;
    margin: 40px 0 30px 0;
    height: 400px;
}

.commentPostSources{
    display: flex;
    justify-content: center;
    align-items: center;
    border: 3px solid #DCDCDC;
    height: 30px;
    width: 80px;
    margin-left: 5px;
    padding: 1px 10px 1px 10px;
}

.Answer1234Input{
    border: 3px solid #DCDCDC;
    height: 30px;
    width: 150px;
}

youranswercontainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-weight: bold;
    margin: 0 0 10px 0;
}

Answer1234{
    margin: 30px 0 20px 10px ;
    display: flex;
    align-items: center;
    justify-content: left;
    flex-direction: column;
    justify-content: left;
    width: 900px;
}


#commentIdInput{
    border: 3px solid #DCDCDC;
    background-color: white;
}


#commentInput{
    border: 3px solid #DCDCDC;
    width: 572px;
    height: 200px;
    background-color: #eeeded;
}

commentpostbutton{
    display: flex;
    justify-content: right;
    margin-right: 64px;

}

input::placeholder{
    padding-left: 5%;
    color: #8b8b8b;
    font-weight: 3px;
}

</style>
    </head>    
    <body>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>

        <header>
            <%@ include file="/WEB-INF/view/header/header.jsp" %>
        </header>
    
        <commentPostcontainer>
            <yourAnswerContainer>
                <contentId class="form-floating">
                    <input type="number" class="form-control"  id="commentIdInput" placeholder="commentId">
                    <label for="floatingPassword">commentId</label>
                </contentId>
                <commentWrite class="form-floating">
                    <td >내용</td>
                    <input type="text" class="form-control"  id="commentInput" placeholder="내용">
                </commentWrite>
            </yourAnswerContainer>

            <commentpostbutton>
                <button type="button" id="commentUpdate_btn" class="btn btn-primary">댓글 수정</button>

            </commentpostbutton>
            <script>
                $(document).ready(function () {
                    // 버튼 클릭 시
                    $("#commentUpdate_btn").click(function () {
                        var commentId = $('#commentIdInput').val();
                        console.log("commentUpdate_btn clicked");
                        
                        var jsonData = {};

                        var commentValue = $('#commentInput').val();
                        if (commentValue) {
                            jsonData["comment"] = commentValue;
                        }

                        $.ajax({
                            url: "/api/comments/" + commentId,
                            type: "PATCH",
                            data: JSON.stringify(jsonData),
                            contentType: "application/json; charset=utf-8",
                            success: function (response) {
                                // 서버 응답 처리
                                console.log("Response from server: " + response);
                                alert("댓글이 성공적으로 수정되었습니다.")
                                window.location.href = "/board/detail?id="+commentId;
                                },
                                error: function (error) {
                                
        
                                // 서버 응답이 오류인 경우 
                                console.log("Error from server: " + error.statusText);
                                alert("정보를 확인 해주세요");
                            }
                        });
                    });
                });
            </script>
        </commentPostcontainer>

        <commentDeleteContainer>
            <%@ include file="/WEB-INF/view/comment/commentDelete.jsp" %>
        </commentDeleteContainer>
    </body>
</html>