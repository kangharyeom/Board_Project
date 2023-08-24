package company.board_project.exception;

import lombok.Getter;

public enum Exceptions {

    // 기본 예외 처리
    INVALID_DATE(400, "Invalid Date"),
    INVALID_VALUES(400, "Invalid Values"),
    UNAUTHORIZED(401, "Unauthorized"),
    ACCESS_FORBIDDEN(403, "Access forbidden"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    EMAIL_EXISTS(409, "Email exists"),
    LOGINID_EXISTS(409, "Login Id exists"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),

    // Member 예외 처리
    INVALID_MEMBER_STATUS(400, "Invalid User status"),
    INVALID_PASSWORD (400, "Invalid Password"),
    MEMBER_NOT_LOGIN(400, "User is not LoginResponseDto"),
    USER_NOT_FOUND(404, "User not found"),
    ID_NOT_EXIST(404, "ID is not exist"),
    MEMBER_EXISTS(409, "User exists"),

    // 게시글 예외 처리
    CONTENT_NOT_PATCHED(403, "Content not patched"),
    CONTENT_NOT_RECRUITING(403, "Content status not recruiting"),
    INVALID_CONTENT_STATUS(403, "Invalid Content Status"),
    CONTENT_NOT_FOUND(404, "Content Not Found"),
    CONTENT_EXISTS(409, "Content exists"),
    CONTENT_CHECK_EXISTS(409, "Content Check exists"),
    CONTENT_REQUEST_EXISTS(409, "Content Request exists"),
    CONTENT_MEMBER_EXISTS(409, "Content Member exists"),

    // 댓글 예외 처리
    COMMENT_NOT_PATCHED(403, "Comment not patched"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),
    COMMENT_CHECK_EXISTS(409, "Comment Check exists"),

    // 파일 업로드 예외 처리
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
