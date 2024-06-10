package company.board_project.security.utils;
import com.google.gson.Gson;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response,
                                         HttpStatus status) throws IOException {

        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(status);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }

    public static void sendCustomErrorResponse(HttpServletResponse response,
                                               BusinessLogicException businessLogicException) throws IOException {

        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(businessLogicException.getExceptions());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(businessLogicException.getExceptions().getStatus());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}