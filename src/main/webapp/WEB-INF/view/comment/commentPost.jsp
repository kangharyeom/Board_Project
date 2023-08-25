<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
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

.Answer123Input{
    border: 3px solid #DCDCDC;
    height: 30px;
    width: 90px;
}

youranswercontainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-weight: bold;
    margin: 0 0 10px 0;
}

Answer12{
    margin-left: 20px ;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    justify-content: left;
    width: 600px;
    margin-bottom: 10px;
    gap: 10px;
}

AnswerFirst{
    display: flex;
    justify-content: center;
    align-items: center;
}

AnswerSecond{
    display: flex;
    justify-content: center;
    align-items: center;
}

AnswerThird{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 900px;
}

#idInput{
    border: 3px solid #DCDCDC;
    width: 204px;
    height: 30px;
    background-color: white;
}

#nameInput{
    border: 3px solid #DCDCDC;
    width: 204px;
    height: 30px;
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
}

#join_btn{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 150px;
    height: 40px;
    color: black;
    font-size: 18px;
    font-weight: 3px;
    background-color: #eeeded;
    border: 3px solid #DCDCDC;
    margin: 0 60px 100px 0 ;
}

input::placeholder{
    padding-left: 5%;
}

</style>
    </head>    
    <body>
        <commentPostcontainer>
            <yourAnswerContainer>
                <Answer12>
                    <AnswerFirst height="50">
                            <sourceFirst class="commentPostSources" height="50">userId</sourceFirst>
                            <sourceFisrt height="50"><input type="number" name="userId" id="idInput" class="Answer123Input"></sourceFisrt>
                        </AnswerFirst>
                    <AnswerSecond height="50">
                        <sourceSecond class="commentPostSources" height="50">닉네임</sourceSecond>
                        <sourceSecond height="50"><input placeholder="작성자명" type="text" name="name" id="nameInput" class="Answer123Input"></sourceSecond>
                    </AnswerSecond>
                </Answer12>
                
                <AnswerThird height="50">
                    <sourceThird height="50"><input type="text" name="comment" id="commentInput"></sourceThird>
                </AnswerThird>
            </yourAnswerContainer>
            <commentpostbutton>
                <button type="button" id="join_btn">댓글 등록</button>
            </commentpostbutton>
            <script>
                $(document).ready(function () {
                    var postId = window.location.href.match(/\/board\/detail\?id=(\d+)/)[1];
                    // 버튼 클릭 시
                    $("#join_btn").click(function () {
                        console.log("join_btn clicked");
        
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