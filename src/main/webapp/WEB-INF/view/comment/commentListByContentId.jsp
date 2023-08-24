<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

commentContainer{
    margin-top: 30px;
    width: 900px;
}
answer{
    width: 900px;
}
.commentListBody{
    display: flex;
    flex-direction: column;
    padding: 30px 30px 30px 30px;
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

</style>
    </head>    
    <body>
        <commentContainer>
            <answer> </answer>
            <div id="commentDiv">
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
    </body>
</html>