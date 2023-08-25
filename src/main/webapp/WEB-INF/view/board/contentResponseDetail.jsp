<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#contentDiv{
    display: flex;
flex-direction: column;
}
contentUpdateButton{
    margin-right: 50px;
    display: flex;
    justify-content: right;
}
#contentUpdate{
    width: 90px;
}
    
contentContainer{
    margin-top: 50px;
    width: 900px;
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
ContentAndName{
padding: 30px;
display: flex;
flex-direction: column;
}
p4{
padding-left: 30px;
}
p5{
font-weight: bold;
padding: 0 0 20px 750px;
}

</style>
    </head>    
    <body>
        <contentContainer>
            <div id="contentDiv">
                <contentUpdateButton>
                    <button id="contentUpdate" onclick="location.href='/contents/update/'">게시글 수정</button>
                </contentUpdateButton>
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
    </body>
</html>