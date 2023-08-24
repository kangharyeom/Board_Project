<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
contentHeader{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    justify-content: space-around;
}

h3 {
    font-size: 30px;
}

postContent{
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;
    width: 160px;
    height: 40px;
    border: 1px solid;
    background-color: #DCDCDC;
    border-radius: 10px;
    cursor: pointer;
}
contentHeader{
    display: flex;
    justify-content: space-around;
    width: 900px;
}
.contentListBody{
    cursor: pointer;
    display: flex;
    justify-content: center;
    flex-direction: column;
    border-top: 2px solid 	#DCDCDC	;
    width: 900px;
    height: 200px;
    padding: 10px 0 10px 30px;
    background-color: white;
}
p1{
    font-size: 15px;
    padding-left: 810px;
    font-weight: bold;
    color: 	black;	
}

p2{
    font-size: 24px;
    font-weight: bold;
    color: 	#4682B4;	
}

p3{
    font-size: 15px;
    color: 	black;	
}
p4{
    font-size: 12px;
    font-weight: bold;
    padding-left: 650px;
    color: 	black;	
}
p5{
    font-size: 12px;
    font-weight: bold;
    padding-left: 650px;
    color: 	black;	
}
</style>
    </head>
    <body>
        <div id="contentDiv">
            <contentList  onclick="location.href='/board/detail'">
                <script>
            $(document).ready(function () {
                let index = 1;
                let item = 40;
                $.ajax({
                    type: "GET",
                    url: "/api/contents/latest", // 실제 엔드포인트에 맞게 변경
                    dataType: "json",
                    success: function (data) {
                        console.log("통신성공");
                        console.log(data);
                        
                        // 데이터를 동적으로 생성하고 화면에 추가
                        $.each(data, function(index, item) {
                            let contentItem = $("<div>", {
                                class: "contentListBody",
                                click: function () {
                                // 게시글을 클릭할 때 상세 페이지로 이동
                                location.href = "/board/detail?id=" + item.contentId;
                            }
                        });
                        
                        $("<p1>").html(item.name).appendTo(contentItem);
                        $("<p2>").html(item.title).appendTo(contentItem);
                        $("<p3>").html(item.content).appendTo(contentItem);
                        $("<p4>").html("<strong>작성됨:</strong> " + item.createdAt).appendTo(contentItem);
                        $("<p5>").html("<strong>수정됨:</strong> " + item.modifiedAt).appendTo(contentItem);
                                            
                                            contentItem.appendTo("#contentDiv");
                                        });
                                        
                                        
                                    },
                                    error: function () {
                                        alert("데이터를 가져올 수 없습니다.");
                                    }
                                });
                });
                </script>
            </contentList>
        </div>
    </body>
</html>