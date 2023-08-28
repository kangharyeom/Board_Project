<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

commentContainer{
    width: 900px;
}
commentButtonContainer{
    margin-top: 30px;
    display: flex;
    justify-content: right;
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

#commentUpdate_btn{
    width: 150px;
    margin-right: 30px;
}

</style>
    </head>    
    <body>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
        <commentContainer>
            <commentButtonContainer>
                <button class="btn btn-primary" onclick="location.href='/comments/update'" id="commentUpdate_btn"> 댓글 수정 </button>
            </commentButtonContainer>
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