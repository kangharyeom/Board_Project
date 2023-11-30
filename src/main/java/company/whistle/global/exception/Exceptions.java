package company.whistle.global.exception;

import lombok.Getter;

public enum Exceptions {

    /*
    * 기본 예외 처리
    */
    ID_IS_NULL(400, "ID is null"),
    INVALID_DATE(400, "Invalid Date"),
    INVALID_VALUES(400, "Invalid Values"),
    UNAUTHORIZED(401, "Unauthorized"),
    ACCESS_FORBIDDEN(403, "Access forbidden"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    EMAIL_EXISTS(409, "Email exists"),
    LOGINID_EXISTS(409, "Login Id exists"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),


    /*
    * auth 관련 예외 처리
    */
    INVALID_EMAIL_AUTH_NUMBER(400, "Invalid email authNumber"),
    INVALID_EMAIL_AUTH(400, "Invalid email auth"),
    INVALID_REFRESH_TOKEN(400, "Invalid refresh token"),
    EXPIRED_JWT_TOKEN(421, "Expired jwt token"),
    EMAIL_AUTH_REQUIRED(403, "Email auth required"),


    /*
    * User 예외 처리
    */
    INVALID_MEMBER_STATUS(400, "Invalid User status"),
    INVALID_PASSWORD (400, "Invalid Password"),
    PROFILE_IMAGE_NOT_FOUND(404,  "Profile 이미지가 업로드 되지 않았습니다."),
    MAX_FILE_SIZE_2MB(400, "Max file size 2MB"),
    USER_NOT_LOGIN(400, "User is not LoginResponseDto"),
    USER_NOT_FOUND(404, "User not found"),
    ID_NOT_EXIST(404, "ID is not exist"),
    USER_EXISTS(409, "User exists"),
    USERID_EXISTS(409, "User_id exists"),
    TEAMID_EXISTS(409, "Team_id exists"),
    USER_NOT_CREATED(403, "USER Not Created"),
    USER_NOT_PATCHED(403, "USER not patched"),
    USER_NOT_DELETED(403, "USER not deleted"),

    /*
    * Content(게시글) 예외 처리
    */
    CONTENT_NOT_CREATED(403, "Content not created"),
    CONTENT_NOT_PATCHED(403, "Content not patched"),
    CONTENT_NOT_RECRUITING(403, "Content status not recruiting"),
    INVALID_CONTENT_STATUS(403, "Invalid Content Status"),
    CONTENT_NOT_DELETED(404, "Content Not Deleted"),
    CONTENT_NOT_FOUND(404, "Content Not Found"),
    CONTENT_EXISTS(409, "Content exists"),
    CONTENT_CHECK_EXISTS(409, "Content Check exists"),
    CONTENT_REQUEST_EXISTS(409, "Content Request exists"),
    CONTENT_MEMBER_EXISTS(409, "Content Member exists"),

    /*
    * Comment(댓글) 예외 처리
    */
    COMMENT_NOT_CREATED(403, "Comment not created"),
    COMMENT_NOT_PATCHED(403, "Comment not patched"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    COMMENT_CHECK_EXISTS(409, "Comment Check exists"),

    /*
     * TEAM(팀) 예외 처리
     */
    TEAM_NOT_CREATED(403, "TEAM Not Created"),
    TEAM_NOT_PATCHED(403, "TEAM not patched"),
    TEAM_NOT_DELETED(403, "TEAM not deleted"),
    TEAM_NOT_FOUND(404, "TEAM Not Found"),
    TEAM_EXISTS(409, "TEAM exists"),

    /*
     * MATCH(경기) 예외 처리
     */
    MATCH_NOT_CREATED(403, "MATCH Not Created"),
    MATCH_NOT_PATCHED(403, "MATCH not patched"),
    MATCH_NOT_DELETED(403, "MATCH not deleted"),
    MATCH_NOT_FOUND(404, "MATCH Not Found"),
    MATCH_EXISTS(409, "MATCH exists"),

    /*
     * LEAGUE(리그) 예외 처리
     */
    LEAGUE_NOT_CREATED(403, "LEAGUE Not Created"),
    LEAGUE_NOT_PATCHED(403, "LEAGUE not patched"),
    LEAGUE_NOT_DELETED(403, "LEAGUE not deleted"),
    LEAGUE_NOT_FOUND(404, "LEAGUE Not Found"),
    LEAGUE_EXISTS(409, "LEAGUE exists"),

    /*
     * LEAGUE_MATCH(리그) 예외 처리
     */
    LEAGUE_MATCH_NOT_CREATED(403, "LEAGUE_MATCH Not Created"),
    LEAGUE_MATCH_NOT_PATCHED(403, "LEAGUE_MATCH not patched"),
    LEAGUE_MATCH_NOT_DELETED(403, "LEAGUE_MATCH not deleted"),
    LEAGUE_MATCH_NOT_FOUND(404, "LEAGUE_MATCH Not Found"),
    LEAGUE_MATCH_EXISTS(409, "LEAGUE_MATCH exists"),

    /*
     * LeagueApply(경기 or 팀 or 리그 신청) 예외 처리
     */
    TEAM_APPLY_NOT_CREATED(403, "TEAM_APPLY not created"),
    MATCH_APPLY_NOT_CREATED(403, "MATCH_APPLY not created"),
    LEAGUE_APPLY_NOT_CREATED(403, "LEAGUE_APPLY not created"),
    LEAGUE_APPLY_NOT_DELETED(403, "LEAGUE_APPLY not deleted"),
    MATCH_APPLY_NOT_DELETED(403, "LEAGUE_APPLY not deleted"),
    TEAM_APPLY_NOT_DELETED(403, "LEAGUE_APPLY not deleted"),
    TEAM_APPLY_NOT_FOUND(404, "LeagueApply Not Found"),
    MATCH_APPLY_NOT_FOUND(404, "LeagueApply Not Found"),
    LEAGUE_APPLY_NOT_FOUND(404, "LeagueApply Not Found"),
    LEAGUE_APPLY_CHECK_EXISTS(409, "LeagueApply Check exists"),
    TEAM_APPLY_CHECK_EXISTS(409, "LeagueApply Check exists"),
    MATCH_APPLY_CHECK_EXISTS(409, "LeagueApply Check exists"),

    /*
     * PARTICIPANTS(리그 참가팀) 예외 처리
     */
    PARTICIPANTS_NOT_CREATED(403, "PARTICIPANTS Not Created"),
    PARTICIPANTS_NOT_PATCHED(403, "PARTICIPANTS not patched"),
    PARTICIPANTS_NOT_DELETED(403, "PARTICIPANTS not deleted"),
    PARTICIPANTS_NOT_FOUND(404, "PARTICIPANTS Not Found"),
    PARTICIPANTS_EXISTS(409, "PARTICIPANTS exists"),

    /*
     * SQUAD_MATCH(리그 경기) 예외 처리
     */
    SQUAD_NOT_CREATED(403, "SQUAD_MATCH Not Created"),
    SQUAD_NOT_PATCHED(403, "SQUAD_MATCH not patched"),
    SQUAD_NOT_DELETED(403, "SQUAD_MATCH not deleted"),
    SQUAD_NOT_FOUND(404, "SQUAD_MATCH Not Found"),
    SQUAD_EXISTS(409, "SQUAD_MATCH exists"),

    /*
     * SCHEDULE(경기 일정) 예외 처리
     */
    SCHEDULE_NOT_CREATED(403, "SCHEDULE Not Created"),
    SCHEDULE_NOT_PATCHED(403, "SCHEDULE not patched"),
    SCHEDULE_NOT_DELETED(403, "SCHEDULE not deleted"),
    SCHEDULE_NOT_FOUND(404, "SCHEDULE Not Found"),
    SCHEDULE_EXISTS(409, "SCHEDULE exists"),

    /*
    * 파일 업로드 예외 처리
    */
    CONTENT_FILE_NOT_CREATED(403, "Content_File Not Created"),
    CONTENT_FILE_NOT_FOUND(404, "Content_File Not Found"),
    CONTENT_FILE_CHECK_ERROR(409, "Content_File Check ERROR");

    @Getter
    private int status;

    @Getter
    private String message;

    Exceptions(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
