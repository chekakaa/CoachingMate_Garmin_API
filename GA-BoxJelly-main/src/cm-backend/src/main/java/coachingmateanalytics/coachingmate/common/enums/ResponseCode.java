// Define the package for the enumeration
package coachingmateanalytics.coachingmate.common.enums;

/**
 * ResponseCode is an enumeration that defines response codes and their
 * corresponding messages to be used throughout the application.
 */
public enum ResponseCode {
    SUCCESS(200, "Success"),
    EMAIL_HAS_EXISTED(20001, "Email is already existed!"),
    PARAM_IS_INVALID(20002, "Param is invalid."),
    USER_IS_NOT_EXISTED(20003, "User is not existed or password is incorrect"),
    USER_HAS_NOT_LOGIN(20004, "User is not login."),
    CHECK_TOKEN_FAIL(20004, "Token verification failed, please log in again."),
    AUTHORITY_AUTHENTICATION_FAILED(20004, "Authority authentication failed"),
    QUESTION_ALREADY_EXIST(30001, "This question is already existed"),
    SYSTEM_ERROR(10000, "System Error, Please Contact Admin."),

    EMAIL_IS_NOT_EXISTED(25164, "Email does not exists!"),

    SECURITY_CODE_EXPIRED_OR_INCORRECT(659812, "Security code expired or incorrect"),

    NOT_AUTHORISED(56413, "User has not been authorised"),

    AUTHORISATION_EXPIRED(49716,"Authorisation expired"),

    EMAIL_OR_USERNAME_IS_NOT_EXISTED(24164, "Email or username already exists!");

    private Integer code;
    private String message;

    /**
     * Constructor for the ResponseCode enum.
     *
     * @param code    The response code value.
     * @param message The corresponding message for the response code.
     */
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Retrieves the response code value.
     *
     * @return The response code value as an Integer.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Retrieves the message corresponding to the response code.
     *
     * @return The response code message as a String.
     */
    public String getMessage() {
        return message;
    }
}
