package company.board_project.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {
    @Getter
    private Exceptions exceptions;

    public BusinessLogicException(Exceptions exceptions) {
        super(exceptions.getMessage());
        this.exceptions = exceptions;
    }
}