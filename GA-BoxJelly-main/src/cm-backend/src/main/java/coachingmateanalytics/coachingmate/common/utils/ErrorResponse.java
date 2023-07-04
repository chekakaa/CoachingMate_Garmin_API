package coachingmateanalytics.coachingmate.common.utils;

import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorResponse is a class representing an error response with status code, message, and exception.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private Integer statusCode;
    private String message;
    private String exception;

    /**
     * Creates an ErrorResponse object with a custom message.
     *
     * @param responseCode the response code
     * @param e           the exception
     * @param message     the custom error message
     * @return the ErrorResponse object
     */
    public static ErrorResponse fail(ResponseCode responseCode, Throwable e, String message) {
        ErrorResponse response = ErrorResponse.fail(responseCode, e);
        response.setMessage(message);

        return response;
    }

    /**
     * Creates an ErrorResponse object using a response code and exception.
     *
     * @param responseCode the response code
     * @param e           the exception
     * @return the ErrorResponse object
     */
    public static ErrorResponse fail(ResponseCode responseCode, Throwable e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(responseCode.getMessage());
        response.setStatusCode(responseCode.getCode());
        response.setException(e.getClass().getName());

        return response;
    }
}
