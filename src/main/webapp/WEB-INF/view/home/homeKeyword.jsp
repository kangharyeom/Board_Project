<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
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


/* boardHeader */
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
    background-color: rgb(107, 107, 238);
    color: white;
    border: 2px solid blue;
}

#ContentFilterByUserName{
    width: 90px;
    height: 30px;
}

#searchButtonId{
    width: 90px;
    height: 60px;
    border: 4px solid rgb(185, 185, 185);
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

/* boardKeyword */

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
                            <titleAndContentUserName>
                                <titleAndContentFilter>
                                    <button class="titleAndContentFilter" id="titleAndContentFilter" onclick="location.href='/contents/search'">제목/내용</button>
                                </titleAndContentFilter>
                                <nameFilter>
                                    <button class="ContentFilterByUserName" id="ContentFilterByUserName" onclick="location.href='/contents/search/username'">작성자</button>
                                </nameFilter>
                            </titleAndContentUserName>
                            <searchButton>
                                <button class="titleAndContentFilter" id="searchButtonId">검색</button>
                            </searchButton>
                        </searchBar>
                        
                        <filters>
                            <newestFilter>
                                <button class="newestFilter" id="newestFilter" onclick="location.href='/contents/newest'">최신글</button>
                            </newestFilter>
                            <latestFilter>
                                <button class="newestFilter" id="latestFilter" onclick="location.href='/contents/latest'">오래된 글</button>
                            </latestFilter>
                        </filters>
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
                                            var keyword= $("#searchBarInput").val();
                                            $.ajax({
                                                type: "GET",
                                                url: "/api/contents/search?keyword="+keyword, // 실제 엔드포인트에 맞게 변경
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
    
