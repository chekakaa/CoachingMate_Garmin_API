// Define the package for the BusinessException class
package coachingmateanalytics.coachingmate.common.exceptions;

// Import required libraries
import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import lombok.Data;

/**
 * BusinessException is a custom exception class that extends RuntimeException
 * and includes a status code and message for application-specific errors.
 */
@Data
public class BusinessException extends RuntimeException {
    private Integer statusCode;
    private String message;

    /**
     * Constructor for BusinessException that initializes the status code and message
     * based on the provided ResponseCode enumeration value.
     *
     * @param responseCode The ResponseCode enumeration value.
     */
    public BusinessException(ResponseCode responseCode) {
        this.statusCode = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
