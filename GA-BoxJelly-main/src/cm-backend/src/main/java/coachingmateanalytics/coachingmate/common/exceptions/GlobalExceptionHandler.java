package coachingmateanalytics.coachingmate.common.exceptions;

import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import coachingmateanalytics.coachingmate.common.utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * GlobalExceptionHandler class is responsible for handling application-wide exceptions.
 * It defines custom exception handlers to return appropriate ErrorResponse objects.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Handles any Throwable exceptions and returns an ErrorResponse.
     *
     * @param e The Throwable exception.
     * @param request The HttpServletRequest object.
     * @return An ErrorResponse object.
     */
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResponse HandleThrowable(Throwable e, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.fail(ResponseCode.SYSTEM_ERROR, e);
        log.error("URL: {} Exception:", request.getRequestURI(), e );

        return errorResponse;
    }

    /**
     * Handles MethodArgumentNotValidException exceptions and returns an ErrorResponse.
     *
     * @param e The MethodArgumentNotValidException.
     * @param request The HttpServletRequest object.
     * @return An ErrorResponse object.
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse HandleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                               HttpServletRequest request){
        String message = this.Handle(e.getBindingResult().getFieldErrors());
        ErrorResponse errorResponse = ErrorResponse.fail(ResponseCode.PARAM_IS_INVALID, e, message);
        log.error("URL: {} Param Check Exception:", request.getRequestURI(), e );

        return errorResponse;
    }

    /**
     * Processes a list of FieldError objects and returns a formatted error message.
     *
     * @param fieldErrorList The list of FieldError objects.
     * @return A formatted error message.
     */
    private String Handle(List<FieldError> fieldErrorList){
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrorList){
            sb.append(fieldError.getField());
            sb.append("=[");
            sb.append(fieldError.getDefaultMessage());
            sb.append("] ");

        }
        return sb.toString();

    }

    /**
     * Handles IllegalArgumentException exceptions and returns an ErrorResponse.
     *
     * @param e The IllegalArgumentException.
     * @param request The HttpServletRequest object.
     * @return An ErrorResponse object.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse HandleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder().statusCode(4000)
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL: {} Assert Check Exception:", request.getRequestURI(), e );
        return errorResponse;
    }

    /**
     * Handles BusinessException exceptions and returns an ErrorResponse.
     *
     * @param e The BusinessException.
     * @param request The HttpServletRequest object.
     * @return An ErrorResponse object.
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorResponse HandleBusinessException(BusinessException e, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder().statusCode(e.getStatusCode())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL: {} Business Exception:", request.getRequestURI(), e );

        return errorResponse;
    }
}
