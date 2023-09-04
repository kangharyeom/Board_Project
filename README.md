# Board_Project v0.5(project_whistle) - 2023.09.04
게시판 만들기 과제 수행을 위한 프로젝트 레파지토리 입니다.

축구 매치업 프로젝트로 구체화 됨


## 개발 환경
- Java 11
- Spring Boot 2.1.15 (Gradle 5.3과 호환되는 버전)
- Gradle 5.3
- Maria DB
- Java Server Page ( 컨펌 완료 및 리액트로 변경 2023.09/04 )
- React


## 필요 기능 
- 로그 찍히기
- 게시글 pageNation(1개 페이지에 40개)
- 기타 필요한 기능 추가


## 구현 완료
- Member Crud, Centent Crud, Comment Crud
- 회원 가입(Validation 구현(이메일, 비밀번호, 휴대폰 번호), 로그인 구현(session으로 구현))
- 파일 업로드(같은 파일 이름으로 처리되지 않도록 구현 - UUID 고려해보기)
- Home (기본적인 기능만 구현)
- 검색 기능 (filter 글 제목, 작성자, 작성 시간)