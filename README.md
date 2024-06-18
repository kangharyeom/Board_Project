<div align="center">
  <br />
    <h1>휘슬</h1>
    <h2>축구 팀 매칭 플랫폼</h2>
📆2023.08.01 ~ (진행중)📆
</div>

## 목차
| <center>No</center> |<center>내용</center>|
|:-------------------:|:----:|
|        **1**        |[**💡 프로젝트 개요**](#1)
|        **2**        |[**🗒 주요 기능**](#2)
|        **3**        |[**🔍 기술 스택**](#3)
|        **4**        |[**💾 DataBase**](#4)
|        **5**        |[**📂 시스템 아키텍처**](#5) 
|        **6**        |[**📱 기술 소개**](#6)
|        **7**        |[**👪 팀 소개**](#7)
|        **8**        |[**🗂️ 이슈 정리**](#8)
|        **9**        |[**🗂️ Directory 구조**](#9)
|       **10**        |[**🗂️ 구현 완료된 기능**](#10)
|       **11**        |[**🗂️ 구현 예정 기능**](#11)
|       **12**        |[**🗂️ 프로젝트 업데이트 이력**](#12)

## 💡 프로젝트 개요

프로젝트 "휘슬"은 축구를 사랑하는 축구인들을 위한 팀 매칭 서비스를 제공합니다.

축구인들끼리 팀을 형성하고 더 나아가 리그를 생성하여 나만의 추억을 만들 수 있는 서비스 입니다.

## 주요 기능


## 기술 스택
### FrontEnd 
- java script 
- react
- css
- html

### BackEnd
- Java 17
- Spring Boot 3.2.3
- Gradle 8.5
- spring security 6
### DataBase 
- Redis 7.0.5
- Maria DB 10.6.15
### Infra
- Raspberry Pi

### Collaboration Tool
깃, 노션, 디스코드, 포스트맨

## DATABASE
### Maria DB ERD

### Redis DB 구조

## 팀 소개
- 프론트 엔드(강하렴) - 구현중
- 백엔드 (강하렴)

## 이슈 정리
[팀, 매치, 리그] 생성과 [팀 리스트(팀원 목록), 매치 리스트(경기 매치 팀 리스트), 리그 리스트(리그 팀 목록)] 생성 관계
- [팀, 매치, 리그](이하 팀으로 통일)을 생성한 유저는 팀 생성과 동시에 팀원 목록에 보여야 하므로 팀이 생성되는 단계에서 팀 리스트도 생성되어야 한다.
  - 따라서, 팀 컨트롤러 단에서 postTeam이 팀과 팀리스트를 생성하도록 구현했다.

## Directory 구조
### BackEnd Directory 구조
```bash
.
├── README.md
├── appspec.yml
├── build
│   ├── classes
│   │   └── java
│   │       ├── main
│   │       │   └── company
│   │       │       └── board_project
│   │       │           ├── BoardProjectApplication.class
│   │       │           ├── audit
│   │       │           │   └── Auditable.class
│   │       │           ├── aws_s3
│   │       │           │   ├── AwsS3Config.class
│   │       │           │   └── AwsS3Service.class
│   │       │           ├── config
│   │       │           │   ├── RedisConfig.class
│   │       │           │   ├── SecurityConfiguration$CustomFilterConfigurer.class
│   │       │           │   ├── SecurityConfiguration.class
│   │       │           │   └── WebConfig.class
│   │       │           ├── constant
│   │       │           │   ├── AcceptType.class
│   │       │           │   ├── AgeType.class
│   │       │           │   ├── ApplyType.class
│   │       │           │   ├── AuthProvider.class
│   │       │           │   ├── CategoryType.class
│   │       │           │   ├── Formation.class
│   │       │           │   ├── Frequency.class
│   │       │           │   ├── LeagueRole.class
│   │       │           │   ├── LevelType.class
│   │       │           │   ├── LocationType.class
│   │       │           │   ├── LoginType.class
│   │       │           │   ├── MatchResultStatus.class
│   │       │           │   ├── MatchStatus.class
│   │       │           │   ├── MatchType.class
│   │       │           │   ├── Position.class
│   │       │           │   ├── SeasonType.class
│   │       │           │   ├── SessionConst.class
│   │       │           │   ├── SportsType.class
│   │       │           │   ├── TeamMemberRole.class
│   │       │           │   ├── UniformType.class
│   │       │           │   └── UserRole.class
│   │       │           ├── domain
│   │       │           │   ├── apply
│   │       │           │   │   ├── controller
│   │       │           │   │   │   └── ApplyController.class
│   │       │           │   │   ├── dto
│   │       │           │   │   │   ├── ApplyListDto$ApplyListDtoBuilder.class
│   │       │           │   │   │   ├── ApplyListDto.class
│   │       │           │   │   │   ├── ApplyPostDto.class
│   │       │           │   │   │   ├── ApplyResponseDto$ApplyResponseDtoBuilder.class
│   │       │           │   │   │   ├── ApplyResponseDto.class
│   │       │           │   │   │   ├── LeagueApplyPostDto.class
│   │       │           │   │   │   ├── LeagueApplyResponseDto$LeagueApplyResponseDtoBuilder.class
│   │       │           │   │   │   ├── LeagueApplyResponseDto.class
│   │       │           │   │   │   ├── LeagueMatchApplyPostDto.class
│   │       │           │   │   │   ├── LeagueMatchApplyResponseDto$LeagueMatchApplyResponseDtoBuilder.class
│   │       │           │   │   │   ├── LeagueMatchApplyResponseDto.class
│   │       │           │   │   │   ├── MatchApplyPostDto.class
│   │       │           │   │   │   ├── MatchApplyResponseDto$MatchApplyResponseDtoBuilder.class
│   │       │           │   │   │   ├── MatchApplyResponseDto.class
│   │       │           │   │   │   ├── TeamApplyPostDto.class
│   │       │           │   │   │   ├── TeamApplyResponseDto$TeamApplyResponseDtoBuilder.class
│   │       │           │   │   │   └── TeamApplyResponseDto.class
│   │       │           │   │   ├── entity
│   │       │           │   │   │   └── Apply.class
│   │       │           │   │   ├── mapper
│   │       │           │   │   │   ├── ApplyMapper.class
│   │       │           │   │   │   └── ApplyMapperImpl.class
│   │       │           │   │   ├── repository
│   │       │           │   │   │   └── ApplyRepository.class
│   │       │           │   │   └── service
│   │       │           │   │       └── ApplyService.class
│   │       │           │   ├── comment
│   │       │           │   │   ├── controller
│   │       │           │   │   │   └── CommentController.class
│   │       │           │   │   ├── dto
│   │       │           │   │   │   ├── CommentPatchDto.class
│   │       │           │   │   │   ├── CommentPostDto.class
│   │       │           │   │   │   ├── CommentResponseDto$CommentResponseDtoBuilder.class
│   │       │           │   │   │   └── CommentResponseDto.class
│   │       │           │   │   ├── entity
│   │       │           │   │   │   └── Comment.class
│   │       │           │   │   ├── mapper
│   │       │           │   │   │   ├── CommentMapper.class
│   │       │           │   │   │   └── CommentMapperImpl.class
│   │       │           │   │   ├── repository
│   │       │           │   │   │   └── CommentRepository.class
│   │       │           │   │   └── service
│   │       │           │   │       └── CommentService.class
│   │       │           │   ├── content
│   │       │           │   │   ├── controller
│   │       │           │   │   │   └── ContentController.class
│   │       │           │   │   ├── dto
│   │       │           │   │   │   ├── ContentAllResponseDto$ContentAllResponseDtoBuilder.class
│   │       │           │   │   │   ├── ContentAllResponseDto.class
│   │       │           │   │   │   ├── ContentListDto$ContentListDtoBuilder.class
│   │       │           │   │   │   ├── ContentListDto.class
│   │       │           │   │   │   ├── ContentPatchDto.class
│   │       │           │   │   │   ├── ContentPostDto.class
│   │       │           │   │   │   ├── ContentResponseDto$ContentResponseDtoBuilder.class
│   │       │           │   │   │   └── ContentResponseDto.class
│   │       │           │   │   ├── entity
│   │       │           │   │   │   ├── Content.class
│   │       │           │   │   │   └── ContentFile.class
│   │       │           │   │   ├── mapper
│   │       │           │   │   │   ├── ContentMapper.class
│   │       │           │   │   │   └── ContentMapperImpl.class
│   │       │           │   │   ├── repository
│   │       │           │   │   │   ├── ContentFileRepository.class
│   │       │           │   │   │   └── ContentRepository.class
│   │       │           │   │   └── service
│   │       │           │   │       └── ContentService.class
│   │       │           │   ├── home
│   │       │           │   │   └── HomeController.class
│   │       │           │   ├── league
│   │       │           │   │   ├── controller
│   │       │           │   │   │   └── LeagueController.class
│   │       │           │   │   ├── dto
│   │       │           │   │   │   ├── LeagueListDto$LeagueListDtoBuilder.class
│   │       │           │   │   │   ├── LeagueListDto.class
│   │       │           │   │   │   ├── LeaguePatchDto.class
│   │       │           │   │   │   ├── LeaguePostDto.class
│   │       │           │   │   │   ├── LeagueResponseDto$LeagueResponseDtoBuilder.class
│   │       │           │   │   │   └── LeagueResponseDto.class
│   │       │           │   │   ├── entity
│   │       │           │   │   │   ├── League.class
│   │       │           │   │   │   └── LeagueParticipantsList.class
│   │       │           │   │   ├── mapper
│   │       │           │   │   │   ├── LeagueMapper.class
│   │       │           │   │   │   └── LeagueMapperImpl.class
│   │       │           │   │   ├── repository
│   │       │           │   │   │   ├── LeagueParticipantsListRepository.class
│   │       │           │   │   │   └── LeagueRepository.class
│   │       │           │   │   └── service
│   │       │           │   │       └── LeagueService.class
│   │       │           │   ├── match
│   │       │           │   │   ├── leaguematch
│   │       │           │   │   │   ├── controller
│   │       │           │   │   │   │   └── LeagueMatchController.class
│   │       │           │   │   │   ├── dto
│   │       │           │   │   │   │   ├── LeagueMatchPostDto.class
│   │       │           │   │   │   │   └── LeagueMatchResponseDto.class
│   │       │           │   │   │   ├── entity
│   │       │           │   │   │   │   └── LeagueMatch.class
│   │       │           │   │   │   ├── mapper
│   │       │           │   │   │   │   ├── LeagueMatchMapper.class
│   │       │           │   │   │   │   └── LeagueMatchMapperImpl.class
│   │       │           │   │   │   ├── repository
│   │       │           │   │   │   │   └── LeagueMatchRepository.class
│   │       │           │   │   │   └── service
│   │       │           │   │   │       └── LeagueMatchService.class
│   │       │           │   │   └── match
│   │       │           │   │       ├── controller
│   │       │           │   │       │   └── MatchController.class
│   │       │           │   │       ├── dto
│   │       │           │   │       │   ├── MatchEndDto.class
│   │       │           │   │       │   ├── MatchEndResponseDto$MatchEndResponseDtoBuilder.class
│   │       │           │   │       │   ├── MatchEndResponseDto.class
│   │       │           │   │       │   ├── MatchListDto$MatchListDtoBuilder.class
│   │       │           │   │       │   ├── MatchListDto.class
│   │       │           │   │       │   ├── MatchPatchDto.class
│   │       │           │   │       │   ├── MatchPostDto.class
│   │       │           │   │       │   ├── MatchResponseDto$MatchResponseDtoBuilder.class
│   │       │           │   │       │   └── MatchResponseDto.class
│   │       │           │   │       ├── entity
│   │       │           │   │       │   └── Match.class
│   │       │           │   │       ├── mapper
│   │       │           │   │       │   ├── MatchMapper.class
│   │       │           │   │       │   └── MatchMapperImpl.class
│   │       │           │   │       ├── repository
│   │       │           │   │       │   └── MatchRepository.class
│   │       │           │   │       └── service
│   │       │           │   │           └── MatchService.class
│   │       │           │   ├── team
│   │       │           │   │   ├── controller
│   │       │           │   │   │   └── TeamController.class
│   │       │           │   │   ├── dto
│   │       │           │   │   │   ├── TeamListDto$TeamListDtoBuilder.class
│   │       │           │   │   │   ├── TeamListDto.class
│   │       │           │   │   │   ├── TeamMemberListPostDto.class
│   │       │           │   │   │   ├── TeamPatchDto.class
│   │       │           │   │   │   ├── TeamPostDto.class
│   │       │           │   │   │   ├── TeamResponseDto$TeamResponseDtoBuilder.class
│   │       │           │   │   │   └── TeamResponseDto.class
│   │       │           │   │   ├── entity
│   │       │           │   │   │   ├── Team.class
│   │       │           │   │   │   └── TeamMemberList.class
│   │       │           │   │   ├── mapper
│   │       │           │   │   │   ├── TeamMapper.class
│   │       │           │   │   │   └── TeamMapperImpl.class
│   │       │           │   │   ├── repository
│   │       │           │   │   │   ├── TeamMemberListRepository.class
│   │       │           │   │   │   └── TeamRepository.class
│   │       │           │   │   └── service
│   │       │           │   │       └── TeamService.class
│   │       │           │   └── user
│   │       │           │       ├── controller
│   │       │           │       │   └── UserController.class
│   │       │           │       ├── dto
│   │       │           │       │   ├── UserListDto$UserListDtoBuilder.class
│   │       │           │       │   ├── UserListDto.class
│   │       │           │       │   ├── UserPatchDto.class
│   │       │           │       │   ├── UserPostDto.class
│   │       │           │       │   ├── UserResponseDto$UserResponseDtoBuilder.class
│   │       │           │       │   └── UserResponseDto.class
│   │       │           │       ├── entity
│   │       │           │       │   └── User.class
│   │       │           │       ├── mapper
│   │       │           │       │   ├── UserMapper.class
│   │       │           │       │   └── UserMapperImpl.class
│   │       │           │       ├── repository
│   │       │           │       │   └── UserRepository.class
│   │       │           │       └── service
│   │       │           │           └── UserService.class
│   │       │           ├── exception
│   │       │           │   ├── BusinessLogicException.class
│   │       │           │   ├── ErrorResponder.class
│   │       │           │   ├── ErrorResponse$ConstraintViolationError.class
│   │       │           │   ├── ErrorResponse$FieldError.class
│   │       │           │   ├── ErrorResponse.class
│   │       │           │   └── Exceptions.class
│   │       │           ├── response
│   │       │           │   ├── MultiResponseDto.class
│   │       │           │   ├── PageInfo.class
│   │       │           │   └── SingleResponseDto.class
│   │       │           ├── security
│   │       │           │   ├── auth
│   │       │           │   │   ├── controller
│   │       │           │   │   │   └── AuthController.class
│   │       │           │   │   ├── dto
│   │       │           │   │   │   ├── AuthEmailConfirmDto.class
│   │       │           │   │   │   ├── AuthEmailDto.class
│   │       │           │   │   │   ├── AuthEmailQuestionDto.class
│   │       │           │   │   │   └── AuthPasswordDto.class
│   │       │           │   │   └── service
│   │       │           │   │       └── AuthService.class
│   │       │           │   ├── jwt
│   │       │           │   │   ├── component
│   │       │           │   │   │   └── JwtTokenizer.class
│   │       │           │   │   ├── filter
│   │       │           │   │   │   ├── JwtAuthenticationFilter.class
│   │       │           │   │   │   └── JwtVerificationFilter.class
│   │       │           │   │   └── handler
│   │       │           │   │       ├── OAuth2UserSuccessHandler.class
│   │       │           │   │       ├── UserAccessDeniedHandler.class
│   │       │           │   │       ├── UserAuthenticationEntryPoint.class
│   │       │           │   │       ├── UserAuthenticationFailureHandler.class
│   │       │           │   │       └── UserAuthenticationSuccessHandler.class
│   │       │           │   ├── login
│   │       │           │   │   └── dto
│   │       │           │   │       └── LoginDto.class
│   │       │           │   ├── oauth
│   │       │           │   │   ├── OAuth2Attributes$OAuth2AttributesBuilder.class
│   │       │           │   │   ├── OAuth2Attributes.class
│   │       │           │   │   ├── OAuth2Controller.class
│   │       │           │   │   └── OAuth2UserService.class
│   │       │           │   ├── userDetails
│   │       │           │   │   ├── MemberDetailsService$MemberDetails.class
│   │       │           │   │   └── MemberDetailsService.class
│   │       │           │   └── utils
│   │       │           │       ├── CustomAuthorityUtils.class
│   │       │           │       ├── ErrorResponder.class
│   │       │           │       └── RedisUtils.class
│   │       │           └── util
│   │       │               └── CommonUtils.class
│   │       └── test
│   ├── generated
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       ├── main
│   │       │       │   └── company
│   │       │       │       └── board_project
│   │       │       │           └── domain
│   │       │       │               ├── apply
│   │       │       │               │   └── mapper
│   │       │       │               │       └── ApplyMapperImpl.java
│   │       │       │               ├── comment
│   │       │       │               │   └── mapper
│   │       │       │               │       └── CommentMapperImpl.java
│   │       │       │               ├── content
│   │       │       │               │   └── mapper
│   │       │       │               │       └── ContentMapperImpl.java
│   │       │       │               ├── league
│   │       │       │               │   └── mapper
│   │       │       │               │       └── LeagueMapperImpl.java
│   │       │       │               ├── match
│   │       │       │               │   ├── leaguematch
│   │       │       │               │   │   └── mapper
│   │       │       │               │   │       └── LeagueMatchMapperImpl.java
│   │       │       │               │   └── match
│   │       │       │               │       └── mapper
│   │       │       │               │           └── MatchMapperImpl.java
│   │       │       │               ├── team
│   │       │       │               │   └── mapper
│   │       │       │               │       └── TeamMapperImpl.java
│   │       │       │               └── user
│   │       │       │                   └── mapper
│   │       │       │                       └── UserMapperImpl.java
│   │       │       └── test
│   │       └── headers
│   │           └── java
│   │               ├── main
│   │               └── test
│   ├── libs
│   │   ├── board_project-0.0.1-SNAPSHOT-plain.jar
│   │   └── board_project-0.0.1-SNAPSHOT.jar
│   ├── resolvedMainClassName
│   ├── resources
│   │   └── main
│   │       ├── application.properties
│   │       ├── log4j2.xml
│   │       └── templates
│   │           ├── main.html
│   │           └── oauth-login.html
│   └── tmp
│       ├── bootJar
│       │   └── MANIFEST.MF
│       ├── compileJava
│       │   ├── compileTransaction
│       │   │   ├── backup-dir
│       │   │   └── stash-dir
│       │   │       ├── Apply.class.uniqueId69
│       │   │       ├── ApplyController.class.uniqueId93
│       │   │       ├── ApplyMapper.class.uniqueId61
│       │   │       ├── ApplyMapperImpl.class.uniqueId43
│       │   │       ├── ApplyMapperImpl.java.uniqueId106
│       │   │       ├── ApplyRepository.class.uniqueId113
│       │   │       ├── ApplyService.class.uniqueId111
│       │   │       ├── AuthController.class.uniqueId59
│       │   │       ├── AuthService.class.uniqueId58
│       │   │       ├── BusinessLogicException.class.uniqueId34
│       │   │       ├── Comment.class.uniqueId2
│       │   │       ├── CommentController.class.uniqueId86
│       │   │       ├── CommentMapper.class.uniqueId16
│       │   │       ├── CommentMapperImpl.class.uniqueId108
│       │   │       ├── CommentMapperImpl.java.uniqueId91
│       │   │       ├── CommentRepository.class.uniqueId0
│       │   │       ├── CommentService.class.uniqueId25
│       │   │       ├── Content.class.uniqueId83
│       │   │       ├── ContentController.class.uniqueId12
│       │   │       ├── ContentMapper.class.uniqueId68
│       │   │       ├── ContentMapperImpl.class.uniqueId110
│       │   │       ├── ContentMapperImpl.java.uniqueId9
│       │   │       ├── ContentRepository.class.uniqueId87
│       │   │       ├── ContentService.class.uniqueId31
│       │   │       ├── ErrorResponder.class.uniqueId15
│       │   │       ├── ErrorResponder.class.uniqueId38
│       │   │       ├── ErrorResponse$ConstraintViolationError.class.uniqueId62
│       │   │       ├── ErrorResponse$FieldError.class.uniqueId13
│       │   │       ├── ErrorResponse.class.uniqueId1
│       │   │       ├── Exceptions.class.uniqueId49
│       │   │       ├── JwtAuthenticationFilter.class.uniqueId3
│       │   │       ├── JwtVerificationFilter.class.uniqueId102
│       │   │       ├── League.class.uniqueId5
│       │   │       ├── LeagueController.class.uniqueId109
│       │   │       ├── LeagueListDto$LeagueListDtoBuilder.class.uniqueId51
│       │   │       ├── LeagueListDto.class.uniqueId52
│       │   │       ├── LeagueMapper.class.uniqueId60
│       │   │       ├── LeagueMapperImpl.class.uniqueId90
│       │   │       ├── LeagueMapperImpl.java.uniqueId55
│       │   │       ├── LeagueMatch.class.uniqueId99
│       │   │       ├── LeagueMatchController.class.uniqueId75
│       │   │       ├── LeagueMatchEndDto.class.uniqueId32
│       │   │       ├── LeagueMatchEndResponseDto$LeagueMatchEndResponseDtoBuilder.class.uniqueId92
│       │   │       ├── LeagueMatchEndResponseDto.class.uniqueId33
│       │   │       ├── LeagueMatchListDto$LeagueMatchListDtoBuilder.class.uniqueId94
│       │   │       ├── LeagueMatchListDto.class.uniqueId97
│       │   │       ├── LeagueMatchMapper.class.uniqueId101
│       │   │       ├── LeagueMatchMapperImpl.class.uniqueId35
│       │   │       ├── LeagueMatchMapperImpl.java.uniqueId23
│       │   │       ├── LeagueMatchPatchDto.class.uniqueId30
│       │   │       ├── LeagueMatchPostDto.class.uniqueId50
│       │   │       ├── LeagueMatchRepository.class.uniqueId70
│       │   │       ├── LeagueMatchResponseDto$LeagueMatchResponseDtoBuilder.class.uniqueId46
│       │   │       ├── LeagueMatchResponseDto.class.uniqueId11
│       │   │       ├── LeagueMatchService.class.uniqueId80
│       │   │       ├── LeagueParticipantsList.class.uniqueId53
│       │   │       ├── LeagueParticipantsListRepository.class.uniqueId44
│       │   │       ├── LeagueRepository.class.uniqueId28
│       │   │       ├── LeagueResponseDto$LeagueResponseDtoBuilder.class.uniqueId24
│       │   │       ├── LeagueResponseDto.class.uniqueId84
│       │   │       ├── LeagueService.class.uniqueId39
│       │   │       ├── LoginType.class.uniqueId63
│       │   │       ├── Match.class.uniqueId64
│       │   │       ├── MatchAwayTeamDto.class.uniqueId71
│       │   │       ├── MatchController.class.uniqueId26
│       │   │       ├── MatchList.class.uniqueId10
│       │   │       ├── MatchListController.class.uniqueId27
│       │   │       ├── MatchListMapper.class.uniqueId74
│       │   │       ├── MatchListMapperImpl.class.uniqueId95
│       │   │       ├── MatchListMapperImpl.java.uniqueId41
│       │   │       ├── MatchListPatchDto.class.uniqueId96
│       │   │       ├── MatchListPostDto.class.uniqueId57
│       │   │       ├── MatchListRepository.class.uniqueId89
│       │   │       ├── MatchListResponseDto$MatchListResponseDtoBuilder.class.uniqueId85
│       │   │       ├── MatchListResponseDto.class.uniqueId48
│       │   │       ├── MatchListResponseListDto$MatchListResponseListDtoBuilder.class.uniqueId67
│       │   │       ├── MatchListResponseListDto.class.uniqueId76
│       │   │       ├── MatchListService.class.uniqueId54
│       │   │       ├── MatchMapper.class.uniqueId103
│       │   │       ├── MatchMapperImpl.class.uniqueId56
│       │   │       ├── MatchMapperImpl.java.uniqueId73
│       │   │       ├── MatchPostDto.class.uniqueId14
│       │   │       ├── MatchRepository.class.uniqueId98
│       │   │       ├── MatchService.class.uniqueId4
│       │   │       ├── MemberDetailsService$MemberDetails.class.uniqueId77
│       │   │       ├── MemberDetailsService.class.uniqueId72
│       │   │       ├── OAuth2Attributes$OAuth2AttributesBuilder.class.uniqueId22
│       │   │       ├── OAuth2Attributes.class.uniqueId82
│       │   │       ├── OAuth2Controller.class.uniqueId37
│       │   │       ├── OAuth2UserService.class.uniqueId18
│       │   │       ├── OAuth2UserSuccessHandler.class.uniqueId66
│       │   │       ├── SecurityConfiguration$CustomFilterConfigurer.class.uniqueId21
│       │   │       ├── SecurityConfiguration.class.uniqueId78
│       │   │       ├── Team.class.uniqueId81
│       │   │       ├── TeamController.class.uniqueId7
│       │   │       ├── TeamListDto$TeamListDtoBuilder.class.uniqueId20
│       │   │       ├── TeamListDto.class.uniqueId19
│       │   │       ├── TeamMapper.class.uniqueId6
│       │   │       ├── TeamMapperImpl.class.uniqueId29
│       │   │       ├── TeamMapperImpl.java.uniqueId47
│       │   │       ├── TeamMemberList.class.uniqueId42
│       │   │       ├── TeamMemberListRepository.class.uniqueId105
│       │   │       ├── TeamRepository.class.uniqueId88
│       │   │       ├── TeamResponseDto$TeamResponseDtoBuilder.class.uniqueId36
│       │   │       ├── TeamResponseDto.class.uniqueId79
│       │   │       ├── TeamService.class.uniqueId8
│       │   │       ├── User.class.uniqueId107
│       │   │       ├── UserAuthenticationFailureHandler.class.uniqueId112
│       │   │       ├── UserController.class.uniqueId65
│       │   │       ├── UserMapper.class.uniqueId45
│       │   │       ├── UserMapperImpl.class.uniqueId40
│       │   │       ├── UserMapperImpl.java.uniqueId100
│       │   │       ├── UserRepository.class.uniqueId104
│       │   │       └── UserService.class.uniqueId17
│       │   └── previous-compilation-data.bin
│       ├── compileTestJava
│       │   └── previous-compilation-data.bin
│       └── jar
│           └── MANIFEST.MF
├── build.gradle
├── buildspec.yml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── out
│   ├── production
│   │   ├── classes
│   │   │   └── company
│   │   │       └── board_project
│   │   │           ├── BoardProjectApplication.class
│   │   │           ├── audit
│   │   │           │   └── Auditable.class
│   │   │           ├── aws_s3
│   │   │           │   ├── AwsS3Config.class
│   │   │           │   └── AwsS3Service.class
│   │   │           ├── config
│   │   │           │   ├── RedisConfig.class
│   │   │           │   ├── SecurityConfiguration$CustomFilterConfigurer.class
│   │   │           │   ├── SecurityConfiguration.class
│   │   │           │   └── WebConfig.class
│   │   │           ├── constant
│   │   │           │   ├── AcceptType.class
│   │   │           │   ├── AgeType.class
│   │   │           │   ├── ApplyType.class
│   │   │           │   ├── AuthProvider.class
│   │   │           │   ├── CategoryType.class
│   │   │           │   ├── Formation.class
│   │   │           │   ├── Frequency.class
│   │   │           │   ├── LeagueRole.class
│   │   │           │   ├── LevelType.class
│   │   │           │   ├── LocationType.class
│   │   │           │   ├── LoginType.class
│   │   │           │   ├── MatchResultStatus.class
│   │   │           │   ├── MatchStatus.class
│   │   │           │   ├── MatchType.class
│   │   │           │   ├── Position.class
│   │   │           │   ├── SeasonType.class
│   │   │           │   ├── SessionConst.class
│   │   │           │   ├── SportsType.class
│   │   │           │   ├── TeamMemberRole.class
│   │   │           │   ├── UniformType.class
│   │   │           │   └── UserRole.class
│   │   │           ├── domain
│   │   │           │   ├── apply
│   │   │           │   │   ├── controller
│   │   │           │   │   │   └── ApplyController.class
│   │   │           │   │   ├── dto
│   │   │           │   │   │   ├── ApplyListDto$ApplyListDtoBuilder.class
│   │   │           │   │   │   ├── ApplyListDto.class
│   │   │           │   │   │   ├── ApplyPostDto.class
│   │   │           │   │   │   ├── ApplyResponseDto$ApplyResponseDtoBuilder.class
│   │   │           │   │   │   ├── ApplyResponseDto.class
│   │   │           │   │   │   ├── LeagueApplyPostDto.class
│   │   │           │   │   │   ├── LeagueApplyResponseDto$LeagueApplyResponseDtoBuilder.class
│   │   │           │   │   │   ├── LeagueApplyResponseDto.class
│   │   │           │   │   │   ├── LeagueMatchApplyPostDto.class
│   │   │           │   │   │   ├── LeagueMatchApplyResponseDto$LeagueMatchApplyResponseDtoBuilder.class
│   │   │           │   │   │   ├── LeagueMatchApplyResponseDto.class
│   │   │           │   │   │   ├── MatchApplyPostDto.class
│   │   │           │   │   │   ├── MatchApplyResponseDto$MatchApplyResponseDtoBuilder.class
│   │   │           │   │   │   ├── MatchApplyResponseDto.class
│   │   │           │   │   │   ├── TeamApplyPostDto.class
│   │   │           │   │   │   ├── TeamApplyResponseDto$TeamApplyResponseDtoBuilder.class
│   │   │           │   │   │   └── TeamApplyResponseDto.class
│   │   │           │   │   ├── entity
│   │   │           │   │   │   └── Apply.class
│   │   │           │   │   ├── mapper
│   │   │           │   │   │   ├── ApplyMapper.class
│   │   │           │   │   │   └── ApplyMapperImpl.class
│   │   │           │   │   ├── repository
│   │   │           │   │   │   └── ApplyRepository.class
│   │   │           │   │   └── service
│   │   │           │   │       └── ApplyService.class
│   │   │           │   ├── comment
│   │   │           │   │   ├── controller
│   │   │           │   │   │   └── CommentController.class
│   │   │           │   │   ├── dto
│   │   │           │   │   │   ├── CommentPatchDto.class
│   │   │           │   │   │   ├── CommentPostDto.class
│   │   │           │   │   │   ├── CommentResponseDto$CommentResponseDtoBuilder.class
│   │   │           │   │   │   └── CommentResponseDto.class
│   │   │           │   │   ├── entity
│   │   │           │   │   │   └── Comment.class
│   │   │           │   │   ├── mapper
│   │   │           │   │   │   ├── CommentMapper.class
│   │   │           │   │   │   └── CommentMapperImpl.class
│   │   │           │   │   ├── repository
│   │   │           │   │   │   └── CommentRepository.class
│   │   │           │   │   └── service
│   │   │           │   │       └── CommentService.class
│   │   │           │   ├── content
│   │   │           │   │   ├── controller
│   │   │           │   │   │   └── ContentController.class
│   │   │           │   │   ├── dto
│   │   │           │   │   │   ├── ContentAllResponseDto$ContentAllResponseDtoBuilder.class
│   │   │           │   │   │   ├── ContentAllResponseDto.class
│   │   │           │   │   │   ├── ContentListDto$ContentListDtoBuilder.class
│   │   │           │   │   │   ├── ContentListDto.class
│   │   │           │   │   │   ├── ContentPatchDto.class
│   │   │           │   │   │   ├── ContentPostDto.class
│   │   │           │   │   │   ├── ContentResponseDto$ContentResponseDtoBuilder.class
│   │   │           │   │   │   └── ContentResponseDto.class
│   │   │           │   │   ├── entity
│   │   │           │   │   │   ├── Content.class
│   │   │           │   │   │   └── ContentFile.class
│   │   │           │   │   ├── mapper
│   │   │           │   │   │   ├── ContentMapper.class
│   │   │           │   │   │   └── ContentMapperImpl.class
│   │   │           │   │   ├── repository
│   │   │           │   │   │   ├── ContentFileRepository.class
│   │   │           │   │   │   └── ContentRepository.class
│   │   │           │   │   └── service
│   │   │           │   │       └── ContentService.class
│   │   │           │   ├── home
│   │   │           │   │   └── HomeController.class
│   │   │           │   ├── league
│   │   │           │   │   ├── controller
│   │   │           │   │   │   └── LeagueController.class
│   │   │           │   │   ├── dto
│   │   │           │   │   │   ├── LeagueListDto$LeagueListDtoBuilder.class
│   │   │           │   │   │   ├── LeagueListDto.class
│   │   │           │   │   │   ├── LeaguePatchDto.class
│   │   │           │   │   │   ├── LeaguePostDto.class
│   │   │           │   │   │   ├── LeagueResponseDto$LeagueResponseDtoBuilder.class
│   │   │           │   │   │   └── LeagueResponseDto.class
│   │   │           │   │   ├── entity
│   │   │           │   │   │   ├── League.class
│   │   │           │   │   │   └── LeagueParticipantsList.class
│   │   │           │   │   ├── mapper
│   │   │           │   │   │   ├── LeagueMapper.class
│   │   │           │   │   │   └── LeagueMapperImpl.class
│   │   │           │   │   ├── repository
│   │   │           │   │   │   ├── LeagueParticipantsListRepository.class
│   │   │           │   │   │   └── LeagueRepository.class
│   │   │           │   │   └── service
│   │   │           │   │       └── LeagueService.class
│   │   │           │   ├── match
│   │   │           │   │   ├── leagueMatch
│   │   │           │   │   │   ├── controller
│   │   │           │   │   │   │   └── LeagueMatchController.class
│   │   │           │   │   │   ├── dto
│   │   │           │   │   │   │   ├── LeagueMatchPostDto.class
│   │   │           │   │   │   │   └── LeagueMatchResponseDto.class
│   │   │           │   │   │   ├── entity
│   │   │           │   │   │   │   └── LeagueMatch.class
│   │   │           │   │   │   ├── mapper
│   │   │           │   │   │   │   ├── LeagueMatchMapper.class
│   │   │           │   │   │   │   └── LeagueMatchMapperImpl.class
│   │   │           │   │   │   ├── repository
│   │   │           │   │   │   │   └── LeagueMatchRepository.class
│   │   │           │   │   │   └── service
│   │   │           │   │   │       └── LeagueMatchService.class
│   │   │           │   │   └── match
│   │   │           │   │       ├── controller
│   │   │           │   │       │   └── MatchController.class
│   │   │           │   │       ├── dto
│   │   │           │   │       │   ├── MatchEndDto.class
│   │   │           │   │       │   ├── MatchEndResponseDto$MatchEndResponseDtoBuilder.class
│   │   │           │   │       │   ├── MatchEndResponseDto.class
│   │   │           │   │       │   ├── MatchListDto$MatchListDtoBuilder.class
│   │   │           │   │       │   ├── MatchListDto.class
│   │   │           │   │       │   ├── MatchPatchDto.class
│   │   │           │   │       │   ├── MatchPostDto.class
│   │   │           │   │       │   ├── MatchResponseDto$MatchResponseDtoBuilder.class
│   │   │           │   │       │   └── MatchResponseDto.class
│   │   │           │   │       ├── entity
│   │   │           │   │       │   └── Match.class
│   │   │           │   │       ├── mapper
│   │   │           │   │       │   ├── MatchMapper.class
│   │   │           │   │       │   └── MatchMapperImpl.class
│   │   │           │   │       ├── repository
│   │   │           │   │       │   └── MatchRepository.class
│   │   │           │   │       └── service
│   │   │           │   │           └── MatchService.class
│   │   │           │   ├── team
│   │   │           │   │   ├── controller
│   │   │           │   │   │   └── TeamController.class
│   │   │           │   │   ├── dto
│   │   │           │   │   │   ├── TeamListDto$TeamListDtoBuilder.class
│   │   │           │   │   │   ├── TeamListDto.class
│   │   │           │   │   │   ├── TeamMemberListPostDto.class
│   │   │           │   │   │   ├── TeamPatchDto.class
│   │   │           │   │   │   ├── TeamPostDto.class
│   │   │           │   │   │   ├── TeamResponseDto$TeamResponseDtoBuilder.class
│   │   │           │   │   │   └── TeamResponseDto.class
│   │   │           │   │   ├── entity
│   │   │           │   │   │   ├── Team.class
│   │   │           │   │   │   └── TeamMemberList.class
│   │   │           │   │   ├── mapper
│   │   │           │   │   │   ├── TeamMapper.class
│   │   │           │   │   │   └── TeamMapperImpl.class
│   │   │           │   │   ├── repository
│   │   │           │   │   │   ├── TeamMemberListRepository.class
│   │   │           │   │   │   └── TeamRepository.class
│   │   │           │   │   └── service
│   │   │           │   │       └── TeamService.class
│   │   │           │   └── user
│   │   │           │       ├── controller
│   │   │           │       │   └── UserController.class
│   │   │           │       ├── dto
│   │   │           │       │   ├── UserListDto$UserListDtoBuilder.class
│   │   │           │       │   ├── UserListDto.class
│   │   │           │       │   ├── UserPatchDto.class
│   │   │           │       │   ├── UserPostDto.class
│   │   │           │       │   ├── UserResponseDto$UserResponseDtoBuilder.class
│   │   │           │       │   └── UserResponseDto.class
│   │   │           │       ├── entity
│   │   │           │       │   └── User.class
│   │   │           │       ├── mapper
│   │   │           │       │   ├── UserMapper.class
│   │   │           │       │   └── UserMapperImpl.class
│   │   │           │       ├── repository
│   │   │           │       │   └── UserRepository.class
│   │   │           │       └── service
│   │   │           │           └── UserService.class
│   │   │           ├── exception
│   │   │           │   ├── BusinessLogicException.class
│   │   │           │   ├── ErrorResponder.class
│   │   │           │   ├── ErrorResponse$ConstraintViolationError.class
│   │   │           │   ├── ErrorResponse$FieldError.class
│   │   │           │   ├── ErrorResponse.class
│   │   │           │   └── Exceptions.class
│   │   │           ├── response
│   │   │           │   ├── MultiResponseDto.class
│   │   │           │   ├── PageInfo.class
│   │   │           │   └── SingleResponseDto.class
│   │   │           ├── security
│   │   │           │   ├── auth
│   │   │           │   │   ├── controller
│   │   │           │   │   │   └── AuthController.class
│   │   │           │   │   ├── dto
│   │   │           │   │   │   ├── AuthEmailConfirmDto.class
│   │   │           │   │   │   ├── AuthEmailDto.class
│   │   │           │   │   │   ├── AuthEmailQuestionDto.class
│   │   │           │   │   │   └── AuthPasswordDto.class
│   │   │           │   │   └── service
│   │   │           │   │       └── AuthService.class
│   │   │           │   ├── jwt
│   │   │           │   │   ├── component
│   │   │           │   │   │   └── JwtTokenizer.class
│   │   │           │   │   ├── filter
│   │   │           │   │   │   ├── JwtAuthenticationFilter.class
│   │   │           │   │   │   └── JwtVerificationFilter.class
│   │   │           │   │   └── handler
│   │   │           │   │       ├── OAuth2UserSuccessHandler.class
│   │   │           │   │       ├── UserAccessDeniedHandler.class
│   │   │           │   │       ├── UserAuthenticationEntryPoint.class
│   │   │           │   │       ├── UserAuthenticationFailureHandler.class
│   │   │           │   │       └── UserAuthenticationSuccessHandler.class
│   │   │           │   ├── login
│   │   │           │   │   └── dto
│   │   │           │   │       └── LoginDto.class
│   │   │           │   ├── oauth
│   │   │           │   │   ├── OAuth2Attributes$OAuth2AttributesBuilder.class
│   │   │           │   │   ├── OAuth2Attributes.class
│   │   │           │   │   ├── OAuth2Controller.class
│   │   │           │   │   └── OAuth2UserService.class
│   │   │           │   ├── userDetails
│   │   │           │   │   ├── MemberDetailsService$MemberDetails.class
│   │   │           │   │   └── MemberDetailsService.class
│   │   │           │   └── utils
│   │   │           │       ├── CustomAuthorityUtils.class
│   │   │           │       ├── ErrorResponder.class
│   │   │           │       └── RedisUtils.class
│   │   │           └── util
│   │   │               └── CommonUtils.class
│   │   └── resources
│   │       ├── application.properties
│   │       ├── log4j2.xml
│   │       └── templates
│   │           ├── main.html
│   │           └── oauth-login.html
│   └── test
│       └── classes
├── scripts
│   └── deploy.sh
├── settings.gradle
└── src
    ├── main
    │   ├── generated
    │   │   └── company
    │   │       └── board_project
    │   │           └── domain
    │   │               ├── apply
    │   │               │   └── mapper
    │   │               │       └── ApplyMapperImpl.java
    │   │               ├── comment
    │   │               │   └── mapper
    │   │               │       └── CommentMapperImpl.java
    │   │               ├── content
    │   │               │   └── mapper
    │   │               │       └── ContentMapperImpl.java
    │   │               ├── league
    │   │               │   └── mapper
    │   │               │       └── LeagueMapperImpl.java
    │   │               ├── match
    │   │               │   ├── leagueMatch
    │   │               │   │   └── mapper
    │   │               │   │       └── LeagueMatchMapperImpl.java
    │   │               │   └── match
    │   │               │       └── mapper
    │   │               │           └── MatchMapperImpl.java
    │   │               ├── team
    │   │               │   └── mapper
    │   │               │       └── TeamMapperImpl.java
    │   │               └── user
    │   │                   └── mapper
    │   │                       └── UserMapperImpl.java
    │   ├── java
    │   │   └── company
    │   │       └── board_project
    │   │           ├── BoardProjectApplication.java
    │   │           ├── audit
    │   │           │   └── Auditable.java
    │   │           ├── aws_s3
    │   │           │   ├── AwsS3Config.java
    │   │           │   └── AwsS3Service.java
    │   │           ├── config
    │   │           │   ├── RedisConfig.java
    │   │           │   ├── SecurityConfiguration.java
    │   │           │   └── WebConfig.java
    │   │           ├── constant
    │   │           │   ├── AcceptType.java
    │   │           │   ├── AgeType.java
    │   │           │   ├── ApplyType.java
    │   │           │   ├── AuthProvider.java
    │   │           │   ├── CategoryType.java
    │   │           │   ├── Formation.java
    │   │           │   ├── Frequency.java
    │   │           │   ├── LeagueRole.java
    │   │           │   ├── LevelType.java
    │   │           │   ├── LocationType.java
    │   │           │   ├── LoginType.java
    │   │           │   ├── MatchResultStatus.java
    │   │           │   ├── MatchStatus.java
    │   │           │   ├── MatchType.java
    │   │           │   ├── Position.java
    │   │           │   ├── SeasonType.java
    │   │           │   ├── SessionConst.java
    │   │           │   ├── SportsType.java
    │   │           │   ├── TeamMemberRole.java
    │   │           │   ├── UniformType.java
    │   │           │   └── UserRole.java
    │   │           ├── domain
    │   │           │   ├── apply
    │   │           │   │   ├── controller
    │   │           │   │   │   └── ApplyController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── ApplyListDto.java
    │   │           │   │   │   ├── ApplyPostDto.java
    │   │           │   │   │   ├── ApplyResponseDto.java
    │   │           │   │   │   ├── LeagueApplyPostDto.java
    │   │           │   │   │   ├── LeagueApplyResponseDto.java
    │   │           │   │   │   ├── LeagueMatchApplyPostDto.java
    │   │           │   │   │   ├── LeagueMatchApplyResponseDto.java
    │   │           │   │   │   ├── MatchApplyPostDto.java
    │   │           │   │   │   ├── MatchApplyResponseDto.java
    │   │           │   │   │   ├── TeamApplyPostDto.java
    │   │           │   │   │   └── TeamApplyResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   └── Apply.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── ApplyMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   └── ApplyRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── ApplyService.java
    │   │           │   ├── comment
    │   │           │   │   ├── controller
    │   │           │   │   │   └── CommentController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── CommentPatchDto.java
    │   │           │   │   │   ├── CommentPostDto.java
    │   │           │   │   │   └── CommentResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   └── Comment.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── CommentMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   └── CommentRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── CommentService.java
    │   │           │   ├── content
    │   │           │   │   ├── controller
    │   │           │   │   │   └── ContentController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── ContentAllResponseDto.java
    │   │           │   │   │   ├── ContentListDto.java
    │   │           │   │   │   ├── ContentPatchDto.java
    │   │           │   │   │   ├── ContentPostDto.java
    │   │           │   │   │   └── ContentResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   ├── Content.java
    │   │           │   │   │   └── ContentFile.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── ContentMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   ├── ContentFileRepository.java
    │   │           │   │   │   └── ContentRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── ContentService.java
    │   │           │   ├── home
    │   │           │   │   └── HomeController.java
    │   │           │   ├── league
    │   │           │   │   ├── controller
    │   │           │   │   │   └── LeagueController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── LeagueListDto.java
    │   │           │   │   │   ├── LeaguePatchDto.java
    │   │           │   │   │   ├── LeaguePostDto.java
    │   │           │   │   │   └── LeagueResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   ├── League.java
    │   │           │   │   │   └── LeagueParticipantsList.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── LeagueMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   ├── LeagueParticipantsListRepository.java
    │   │           │   │   │   └── LeagueRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── LeagueService.java
    │   │           │   ├── match
    │   │           │   │   ├── leagueMatch
    │   │           │   │   │   ├── controller
    │   │           │   │   │   │   └── LeagueMatchController.java
    │   │           │   │   │   ├── dto
    │   │           │   │   │   │   ├── LeagueMatchPostDto.java
    │   │           │   │   │   │   └── LeagueMatchResponseDto.java
    │   │           │   │   │   ├── entity
    │   │           │   │   │   │   └── LeagueMatch.java
    │   │           │   │   │   ├── mapper
    │   │           │   │   │   │   └── LeagueMatchMapper.java
    │   │           │   │   │   ├── repository
    │   │           │   │   │   │   └── LeagueMatchRepository.java
    │   │           │   │   │   └── service
    │   │           │   │   │       └── LeagueMatchService.java
    │   │           │   │   └── match
    │   │           │   │       ├── controller
    │   │           │   │       │   └── MatchController.java
    │   │           │   │       ├── dto
    │   │           │   │       │   ├── MatchEndDto.java
    │   │           │   │       │   ├── MatchEndResponseDto.java
    │   │           │   │       │   ├── MatchListDto.java
    │   │           │   │       │   ├── MatchPatchDto.java
    │   │           │   │       │   ├── MatchPostDto.java
    │   │           │   │       │   └── MatchResponseDto.java
    │   │           │   │       ├── entity
    │   │           │   │       │   └── Match.java
    │   │           │   │       ├── mapper
    │   │           │   │       │   └── MatchMapper.java
    │   │           │   │       ├── repository
    │   │           │   │       │   └── MatchRepository.java
    │   │           │   │       └── service
    │   │           │   │           └── MatchService.java
    │   │           │   ├── team
    │   │           │   │   ├── controller
    │   │           │   │   │   └── TeamController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── TeamListDto.java
    │   │           │   │   │   ├── TeamMemberListPostDto.java
    │   │           │   │   │   ├── TeamPatchDto.java
    │   │           │   │   │   ├── TeamPostDto.java
    │   │           │   │   │   └── TeamResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   ├── Team.java
    │   │           │   │   │   └── TeamMemberList.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── TeamMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   ├── TeamMemberListRepository.java
    │   │           │   │   │   └── TeamRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── TeamService.java
    │   │           │   └── user
    │   │           │       ├── controller
    │   │           │       │   └── UserController.java
    │   │           │       ├── dto
    │   │           │       │   ├── UserListDto.java
    │   │           │       │   ├── UserPatchDto.java
    │   │           │       │   ├── UserPostDto.java
    │   │           │       │   └── UserResponseDto.java
    │   │           │       ├── entity
    │   │           │       │   └── User.java
    │   │           │       ├── mapper
    │   │           │       │   └── UserMapper.java
    │   │           │       ├── repository
    │   │           │       │   └── UserRepository.java
    │   │           │       └── service
    │   │           │           └── UserService.java
    │   │           ├── exception
    │   │           │   ├── BusinessLogicException.java
    │   │           │   ├── ErrorResponder.java
    │   │           │   ├── ErrorResponse.java
    │   │           │   └── Exceptions.java
    │   │           ├── response
    │   │           │   ├── MultiResponseDto.java
    │   │           │   ├── PageInfo.java
    │   │           │   └── SingleResponseDto.java
    │   │           ├── security
    │   │           │   ├── auth
    │   │           │   │   ├── controller
    │   │           │   │   │   └── AuthController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── AuthEmailConfirmDto.java
    │   │           │   │   │   ├── AuthEmailDto.java
    │   │           │   │   │   ├── AuthEmailQuestionDto.java
    │   │           │   │   │   └── AuthPasswordDto.java
    │   │           │   │   └── service
    │   │           │   │       └── AuthService.java
    │   │           │   ├── jwt
    │   │           │   │   ├── component
    │   │           │   │   │   └── JwtTokenizer.java
    │   │           │   │   ├── filter
    │   │           │   │   │   ├── JwtAuthenticationFilter.java
    │   │           │   │   │   └── JwtVerificationFilter.java
    │   │           │   │   └── handler
    │   │           │   │       ├── OAuth2UserSuccessHandler.java
    │   │           │   │       ├── UserAccessDeniedHandler.java
    │   │           │   │       ├── UserAuthenticationEntryPoint.java
    │   │           │   │       ├── UserAuthenticationFailureHandler.java
    │   │           │   │       └── UserAuthenticationSuccessHandler.java
    │   │           │   ├── login
    │   │           │   │   └── dto
    │   │           │   │       └── LoginDto.java
    │   │           │   ├── oauth
    │   │           │   │   ├── OAuth2Attributes.java
    │   │           │   │   ├── OAuth2Controller.java
    │   │           │   │   └── OAuth2UserService.java
    │   │           │   ├── userDetails
    │   │           │   │   └── MemberDetailsService.java
    │   │           │   └── utils
    │   │           │       ├── CustomAuthorityUtils.java
    │   │           │       ├── ErrorResponder.java
    │   │           │       └── RedisUtils.java
    │   │           └── util
    │   │               └── CommonUtils.java
    │   └── resources
    │       ├── application.properties
    │       ├── log4j2.xml
    │       └── templates
    │           ├── main.html
    │           └── oauth-login.html
    └── test
        └── java
            └── company
                └── board_project
                    └── BoardProjectApplicationTests.java

```



## 구현 예정 기능 - 2023.09.25
완료된 기능은 구현 하단의 구현 완료로 이동함

### 회원
회원 관련 테이블
- 마이페이지
  - 내가 쓴 게시글
  - 내 팀 정보
  - 경기 일정
  - 경기 결과 모음
    - 리그 경기 
    - 팀 경기
    - 용병으로 참여한 경기

### 게시글
게시글 관련 테이블
- 파일 업로드
- 게시판 카테고리
  - 용병 모집 게시판

### 댓글
댓글 관련 테이블

### 팀
팀 관련 테이블
- 팀 로고 파일 업로드
- 팀 수정
  - 리그 이름 수정 / leagueService / 리그 이름이 변동되면 수정됨 
  - 우승 횟수 수정 / leagueService / 리그 enum이 종료처리되면 승점이 가장 큰 값을 우승처리
  - 매니저가 탈퇴한 경우 - 부매니저가 매니저가 될 수 있도록 (userId도 바꿔주어야함) / 부매니저는 삭제됨

### 매치
매치 관련 테이블

### 리그
리그 관련 테이블
- 리그 로고 파일 업로드
- 리그 생성
  - 리그 생성시 팀 정보에 리그 이름 변경
- 리그 수정
  - 리그 명예 점수 수정(팀 명예점수 종합)


### 신청(Apply) 테이블
팀 가입 신청, 매치 신청, 리그 가입 신청 정보를 관리하는 테이블

### 팀, 매치, 리그 리스트
팀, 매치, 리그 목록 정보를 담는 테이블
- 팀
 
- 매치
  - 
- 리그
  - 리그 조회
    - 랭킹 조회 (쿼리로 구현 / 명예점수를 종합하여 DESC순으로 구현)

    - 리그 리스트에 추가시 팀 정보에서 리그 이름 변경

### 그 외 기능
- 로그 찍기
- 파일 업로드(같은 파일 이름으로 처리되지 않도록 구현 - UUID 고려해보기)
- 로그인(Oauth 2.0)
- 신청
- 팀, 리그, 매치 리스트 CRUD

### @챌린지
- 팀, 리그 개인 기록
- MOM, GOAL, ASSIST
- 팀 신청시 메일 보내기
- 유저 정보 마스킹


## 구현 완료
### 회원
회원 관련 테이블 
  - 회원 가입(Validation 구현(이메일, 비밀번호, 휴대폰 번호))
  - 회원 정보 수정
  - 회원 조회
  - 회원 삭제

### 게시글
게시글 관련 테이블
- 게시글 생성
- 게시글 조회 
  - 게시글 단건 조회
  - 게시글 전체 조회(pagenation)
  - 게시글 filter 
    - 글 제목순 조회
    - 작성자 ID(닉네임)순 조회 
    - 작성 시간순 조쇠 (최신순, 오래된 순서)

### 댓글
댓글 관련 테이블
- 댓글 생성
- 댓글 조회
  - 댓글 상세 조회
  - 댓글 전체 조회
- 댓글 삭제

### 팀
팀 관련 테이블

- 팀 정보 확인 
  - 리그 ID 단위 조회

- 팀 수정
  - 팀 인원, 팀 이름, 연령대, 스포츠 타입(풋살, 축구, 그 외), 실력, 지역, 활동 빈도, 
  유니폼, 포메이션, 팀 소개 수정 
  - 부 매니저 수정 
  - 리그 이름 수정 / leagueService / 리그 이름이 변동되면 수정됨
  - 우승 횟수 수정 / leagueService / 리그 enum이 종료처리되면 승점이 가장 큰 값을 우승처리
  - 리그 경기 수, 리그 승점, 리그 승리 수, 리그 무승부 수, 리그 패배 수, 종합 승리 수, 종합 무승부 수, 종합 패배 수, 종합 전적 수, 명예 점수
    - leagueMatchService
    - leagueMatchController 에서 리그 매치 종료 patch를 날리면 실행됨
      - homeScore && awayScore가 변동되면 변동된 값에 따라 승무패가 결정되며 승점 및 명예점수를 추가한다.
  - 매니저가 탈퇴한 경우 - 부매니저가 매니저가 될 수 있도록 (userId도 바꿔주어야함) / 부매니저는 삭제됨

### 리그
리그 관련 테이블
- 리그 생성
  - 생성과 동시에 리그 리스트도 동시에 생성 
- 리그 조회
  - 명예 점수순 조회
  - 최신순 조회
  - 오래된 순서 조회
- 리그 수정
  - 모든 팀이 경기를 마치면 리그가 자동 종료된다.
  - 리그 리스트에 팀 추가시 참가한 팀 수가 추가된다.
  - 우승 횟수 수정(토너먼트 또는 리그 우승시 count++ / limit 쿼리로 구현)


### 매치
경기 매치 관련 테이블
- 매치 생성
  - 매치 매치 리스트 생성
- 매치 조회
- 매치 삭제
- 매치 정보 수정
  - 매치 점수 수정
  - 매치 결과에 따라 팀 정보 수정
    - 팀 전적 수정(승무패)
    - 리그 리스트 전적 수정(승무패)    
    - 이긴 팀 명예 점수 추가(승리시 +300, 무승부시 +100, 패배시 +10)

### 리그 매치
리그 매치 관련 테이블
- 리그 매치 생성
- 리그 매치 조회
- 리그 매치 삭제
- 리그 매치 수정
  - 매치 결과에 따라 리그 정보를 수정
    - 팀 전적 수정(승무패)
    - 리그 리스트 전적 수정(승점, 승무패)
    - 이긴 팀 명예 점수 추가(승리시 +300, 무승부시 +100, 패배시 +10)

### 신청(Apply) 테이블
팀 가입 신청, 매치 신청, 리그 가입 신청 정보를 관리하는 테이블
- 팀 가입 신청
  - 팀 가입 신청 조회
  - 팀 가입 신청 내역 삭제
- 리그 가입 신청 구현 완료
  - 리그 가입 신청 조회
  - 리그 가입 신청 내역 삭제
- 매치 신청
  - 매치 신청 조회
  - 매치 신청 내역 삭제

### 팀, 리그, 매치 리스트 CRUD
리스트 테이블은 (매치, 팀, 리그) 목록 정보를 담는 테이블입니다.
- 팀 리스트 생성(팀 가입 수락)
  - 팀 리스트 조회 
  - 팀 리스트 삭제 
  - 팀 리스트 수정
    - away팀 생성을 수정으로 구현(matchListId를 기준으로 matchList에 있는 정보를 수정하는 것으로 구현)
    - 팀 리스트에 추가시 팀원 수 ++
- 리그 리스트 생성(리그 가입 수락)
  - 리그 리스트 조회 
    - 리그 순위 조회 (승점을 orderby DESC로 구현)
  - 리그 리스트 삭제
- 매치 리스트 생성(매치 신청 수락) 
  - 매치 리스트 조회
  - 매치 리스트 삭제 


### 보안, 배포, DB관련 기능

#### JWT
- JWT Tolenizer

#### Redis
- login, logOut 구현
- 고가용성을 위한 master, replica 서버 구성 및 sentinel 구성
  - master 1 : slave 2 : sentinel 3 

#### Oauth 2.0
- google login 구현

## 프로젝트 업데이트 이력
#### - 2024.06.10 (project_whistle 0.7 ver) 