<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
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

</style>
</head>
<body>
    <contentHeader class="contentHeader">
        <contentHeaderContainer>
            <searchBar>
                <input placeholder="검색어를 입력해주세요.">
                <titleAndContentUserName>
                    <titleAndContentFilter>
                        <button class="titleAndContentFilter" id="titleAndContentFilter" onclick="location.href='/contents/search'">제목/내용</button>
                    </titleAndContentFilter>
                    <nameFilter>
                        <button class="ContentFilterByUserName" id="ContentFilterByUserName" onclick="location.href='/contents/search/username'">작성자</button>
                    </nameFilter>
                </titleAndContentUserName>
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
</body>
</html>