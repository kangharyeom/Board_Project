<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
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
    background-color: rgb(107, 107, 238);
    color: white;
    border: 2px solid blue;
}

#ContentFilterByUserName{
    width: 90px;
    height: 30px;
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

#newestFilter{
    width: 90px;
    height: 30px;
}

#latestFilter{
    width: 90px;
    height: 30px;
}
buttonsContainer{
    display: flex;
    flex-direction: row;
}

</style>
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    <contentHeader class="contentHeader">
        <contentHeaderContainer>
            <searchBar>
                <input placeholder="검색어를 입력해주세요." id="searchBarInput">
                <searchButton>
                    <button class="btn btn-primary" id="searchButtonId" >검색</button>
                </searchButton>
            </searchBar>
            <btttonsContainer>

                <titleAndContentUserName class="btn-group" role="group" aria-label="Basic radio toggle button group">
                    <input type="radio" class="btn-check" name="btnradio" id="btnradio1" onclick="location.href='/contents/search'" autocomplete="off" checked>
                    <label class="btn btn-outline-primary" for="btnradio1">제목/내용</label>
                    
                    <input type="radio" class="btn-check" name="btnradio" id="btnradio2" onclick="location.href='/contents/search/username'" autocomplete="off">
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
</body>
</html>