<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
contentContainer{
    width: 1000px;
}
body{
margin-top: 50px;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
}
time{
display: flex;
flex-direction: row;
gap: 20px;
margin-bottom: 30px;
padding: 10px 0 30px 0 ;
border-bottom: 1px solid #DCDCDC ;
font-size: 14px;
}
strong{
    color: gray;
}
p1{
font-size: 24px;
font-weight: bold;
}
p2{

}
p3{

}
ContentAndName{
display: flex;
flex-direction: column;
}
p4{
padding-left: 30px;
}
p5{
font-weight: bold;
padding: 0 0 20px 900px;
}

commentContainer{
    margin-top: 30px;
    width: 900px;
}
answer{
    font-size: 30px;
}
.commentListBody{
    display: flex;
    flex-direction: column;
    margin: 30px 0 30px 30px;
    padding: 30px 0px 30px 30px;
    border-bottom: 1px solid #DCDCDC;

}
p6{
    font-size: 20px;
    padding-bottom: 30px;
}
p7{
    font-size: 14px;
}
p8{
    font-size: 14px;
}
p9{
    font-size: 14px;
    padding-left: 750px;
    font-weight: bold;
}
yourAnswer{
    font-size: 30px;
}
commentpostcontainer{
    width: 900px;
    display: flex;
    flex-direction: column;
    margin: 30px 0 30px 30px;
    padding: 30px 0px 30px 30px;
    border-bottom: 1px solid #DCDCDC;

}
table{
    padding-left: 300px;
    display: flex;
    flex-direction: column;
}
</style>
    </head>    
    <body>
        <contentContainer>
            <div id="contentDiv">
                <contentListBody>
                    <p1><span id="title"></span></p1>
                    <time>
                        <p2><strong>Asked</strong> <span id="createdAt"></span></p2>
                        <p3><strong>Modified</strong> <span id="modifiedAt"></span></p3>
                    </time>
                    <ContentAndName>
                        <p4><span id="content"></span></p4>
                        <p5> <span id="name"></span></p5>
                    </ContentAndName>
                </contentListBody>
            </div>

            <script>
                $(document).ready(function () {
                    var postId = window.location.href.match(/\/board\/detail\?id=(\d+)/)[1];
                    $.ajax({
                        type: "GET",
                        url: "/api/contents/" + postId, // postId를 이용하여 요청 URL을 구성
                        dataType: "json",
                        success: function (data) {
                            // 서버 응답을 받았을 때 화면에 표시합니다.
                            console.log("Response from server: " + data);
                            $("#title").text(data.title);
                            $("#content").text(data.content);
                            $("#name").text(data.name);
                            $("#createdAt").text(data.createdAt);
                            $("#modifiedAt").text(data.modifiedAt);
                        },
                        error: function () {
                            alert("데이터를 가져올 수 없습니다.");
                        }
                    });
                });
            </script>
            
        </contentContainer>

        <commentContainer>
            <div id="commentDiv">
                <answer>Answer</answer>
                <commentListBody>
                    <script>
                        $(document).ready(function () {
                            var postId = window.location.href.match(/\/board\/detail\?id=(\d+)/)[1];
                            $.ajax({
                                type: "GET",
                                url: "/api/comments/contents/"+ postId, // 실제 엔드포인트에 맞게 변경
                                dataType: "json",
                                success: function (data) {
                                console.log("통신성공");
                                console.log(data);
                                
                            // 데이터를 동적으로 생성하고 화면에 추가
                                $.each(data, function(index, item) {
                                    let commentItem = $("<div>", {
                                        class: "commentListBody",
                                });
                                $("<p6>").html(item.comment).appendTo(commentItem);
                                    $("<p7>").html("<strong>작성된 시간:</strong> " + item.createdAt).appendTo(commentItem);
                                        $("<p8>").html("<strong>수정된 시간:</strong> " + item.modifiedAt).appendTo(commentItem);
                                            $("<p9>").html(item.name).appendTo(commentItem);
                                    commentItem.appendTo("#commentDiv");

                                    });
                                },
                                error: function () {
                                    alert("데이터를 가져올 수 없습니다.");
                                }
                            });
                });
                </script>
                </commentListBody>
            </div>
        </commentContainer>
        <commentPostcontainer>
            <table>
                <yourAnswer>Your Answer</yourAnswer>
                <yourAnswerContainer>
                    <tr1 height="50">
                        <td1 height="50">userId</td1>
                        <td2 height="50"><input type="number" name="userId" id="idInput"></td2>
                    </tr1>
                    <tr2 height="50">
                        <td3 height="50">ContentId</td3>
                        <td4 height="50"><input type="number" name="contentId" id="contentIdInput"></td4>
                    </tr2>
                    <tr3 height="50">
                        <td height="50">닉네임</td>
                        <td height="50"><input type="text" name="name" id="nameInput"></td>
                    </tr3>
                    <tr4 height="50">
                        <td5 height="50">내용</td5>
                        <td6 height="50"><input type="text" name="comment" id="commentInput"></td6>
                    </tr4>
                </yourAnswerContainer>
            </table>
            <button type="button" id="join_btn">제출</button>
        
            <script>
                $(document).ready(function () {
                    var postId = window.location.href.match(/\/board\/detail\?id=(\d+)/)[1];
                    // 버튼 클릭 시
                    $("#join_btn").click(function () {
                        console.log("join_btn clicked");
        
                        var jsonData = {
                            "userId": $('#idInput').val(),
                            "contentId": $('#contentIdInput').val(),
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