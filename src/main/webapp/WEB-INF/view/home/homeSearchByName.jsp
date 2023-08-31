<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

bodyContainer{
	display:flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	width: 900px;
}

button{
	background-color:  #DCDCDC;
    cursor: pointer;
	color: black;
	border-radius: 5px;
	border: 1px solid;
	margin: 0 10px 0 10px;
}

contentContainer{
display:flex;
justify-content: center;
align-items: center;
flex-direction: column;
width: 900px;
}

contentColumnBody{
display:flex;
justify-content: center;
align-items: center;
width: 900px;
}

contentHeader{
    margin-top: 10px;
    height: 150px;
}

contentHeaderContainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 200px;
}

searchBar{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    margin-bottom: 20px;
}

#titleAndContentFilter{
    width: 90px;
    height: 30px;
}

#ContentFilterByUserName{
    width: 90px;
    height: 30px;
}

titleAndContentUserName{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

titleAndPost{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    justify-content: space-around;
    width: 900px;
    
}

Input{
    border-radius: 5px;
    width: 500px;
    height: 50px;
    border: 4px solid #DCDCDC;
}

Input::placeholder{
    padding-left: 10px;
}

#newestFilter{
    width: 90px;
    height: 30px;
}

#latestFilter{
    width: 90px;
    height: 30px;
}

/* boardHeaderByNameHeader */

contentHeader{
    margin-top: 10px;
    height: 150px;
}

contentHeaderContainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 200px;
}

searchBar{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    margin-bottom: 20px;
    width: 900px;
}

#titleAndContentFilter{
    width: 90px;
    height: 30px;
    
}

#ContentFilterByUserName{
    width: 90px;
    height: 30px;
    background-color: rgb(107, 107, 238);
    color: white;
    border: 2px solid blue;
}

#searchButtonId{
    margin: 0 10px 0 10px;
    width: 90px;
    height: 50px;
    border: 3px solid rgb(185, 185, 185);

}

titleAndContentUserName{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;

}

titleAndPost{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: row;
    justify-content: space-around;
    width: 900px;
    
}

Input{
    border-radius: 5px;
    width: 500px;
    height: 50px;
    border: 4px solid #DCDCDC;
}

Input::placeholder{
    padding-left: 10px;
}


/* boardSearchByNameBody */

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
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
		<bodyContainer>

			<header>
				<%@ include file="/WEB-INF/view/header/header.jsp" %>
			</header>
			
			<contentContainer>
				<contentColumnHeader>
					<contentHeader class="contentHeader">
                        <contentHeaderContainer>
                            <searchBar>
                                <input placeholder="검색어를 입력해주세요." id="searchBarInput">
                                <searchButton>
                                    <button class="btn btn-primary" id="searchButtonId">검색</button>
                                </searchButton>
                            </searchBar>
                            <btttonsContainer>
                
                                <titleAndContentUserName class="btn-group" role="group" aria-label="Basic radio toggle button group">
                                    <input type="radio" class="btn-check" name="btnradio" id="btnradio1" onclick="location.href='/contents/search'" autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btnradio1">제목/내용</label>
                                    
                                    <input type="radio" class="btn-check" name="btnradio" id="btnradio2" onclick="location.href='/contents/search/username'" autocomplete="off" checked>
                                    <label class="btn btn-outline-primary" for="btnradio2">작성자</label>
                                </titleAndContentUserName>
                                
                                <filters>
                                    <div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
                                        <input type="checkbox" class="btn-check" id="btncheck1" onclick="location.href='/contents/newest'"  autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btncheck1">최신글</label>
                                      
                                        <input type="checkbox" class="btn-check" id="btncheck2" onclick="location.href='/contents/latest'"  autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btncheck2">오래된 글</label>
                                      </div>
                                </filters>
                            </btttonsContainer>
                        </contentHeaderContainer>
                    </contentHeader>
				</contentColumnHeader>
			
				<contentColumnBody>

					<contentListDefault>
						<div id="contentDiv">
                            <contentList  onclick="location.href='/board/detail'">
                                <script>
                            $(document).ready(function () {
                                let index = 1;
                                let item = 40;
                                $("#searchButtonId").click(function () {

                                    $.ajax({
                                        type: "GET",
                                        url: "/api/contents/search/username?name="+name, // 실제 엔드포인트에 맞게 변경
                                        dataType: "json",
                                        success: function (data) {
                                            console.log("통신성공");
                                            console.log(data);
                                            
                                            
                                            // 데이터를 동적으로 생성하고 화면에 추가
                                            $.each(data.contentResponseDto, function(index, item) {
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
                                                    })
                                                });
                                });
                                </script>
                            </contentList>
                        </div>
					</contentListDefault>
				</contentColumnBody>
				<contentColumnFooter>

				</contentColumnFooter>
		</contentContainer>
	</bodyContainer>
</body>
</html>
