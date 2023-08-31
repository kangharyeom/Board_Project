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
    margin-bottom: 90px;
}

.commentPostSources{
    display: flex;
    justify-content: center;
    align-items: center;
    border: 3px solid #DCDCDC;
    height: 30px;
    padding: 1px 10px 1px 10px;
}

youranswercontainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-weight: bold;
    margin: 0 0 30px 0;
    width: 700px;
}

Answer12{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    justify-content: left;
    width: 600px;
    margin-bottom: 10px;
    gap: 10px;
}

commentpostbutton{
    display: flex;
    justify-content: right;
}

#commentPost_btn{
    margin: 10px 0px 30px 0 ;
    width: 600px;

}

#commentInput{
    width: 600px;
    height: 200px;
}

</style>
    </head>    
    <body>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
        <commentPostcontainer>
            <yourAnswerContainer>
                    <Answer12>
                        <div class="form-floating">
                            <input type="number" class="form-control"  id="userIdInput" placeholder="Id">
                            <label for="floatingPassword">userId</label>
                        </div>
                        
                        <div class="form-floating">
                            <input type="number" class="form-control"  id="idInput" placeholder="Id">
                            <label for="floatingPassword">Id</label>
                        </div>
                        
                        <div class="form-floating">
                            <input type="text" class="form-control"  id="nameInput" placeholder="Name">
                        <label for="floatingPassword">Name</label>
                        </div>
                    </Answer12>
                    
                    <Answer4 height="50">
                        <commentTextContainer class="form-floating">
                            <input type="textarea" class="form-control"  id="commentInput" placeholder="댓글을 작성해주세요.">
                            <label for="floatingPassword">댓글을 작성해주세요.</label>
                        </commentTextContainer>
                    </Answer4>
                <commentpostbutton>
                    <button class="btn btn-primary" type="button" id="commentPost_btn">댓글 등록</button>
                </commentpostbutton>
            </yourAnswerContainer>

            <script>
                $(document).ready(function () {
                    var postId = window.location.href.match(/\/board\/detail\?id=(\d+)/)[1];
                    // 버튼 클릭 시
                    $("#commentPost_btn").click(function () {
                        console.log("commentPost_btn clicked");
        
                        var jsonData = {
                            "userId": $('#idInput').val(),
                            "contentId": postId,
                            "name": $('#nameInput').val(),
                            "comment": $('#commentInput').val(),
                        };
        
                        $.ajax({
                            url: "/api/comments",
                            type: "POST",
                            data: JSON.stringify(jsonData),
                            contentType: "application/json; charset=utf-8",
                            success: function (response) {
                                // 서버 응답 처리
                                console.log("Response from server: " + response);
                                alert("댓글이 성공적으로 등록되었습니다.")
                                window.location.href = "/board/detail?id="+postId;
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
    </body>
</html>