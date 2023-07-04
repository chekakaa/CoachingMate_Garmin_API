package coachingmateanalytics.coachingmate.common.utils;

import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class representing a generic response with data, status code, and message.
 *
 * @param <T> The type of the data this response is associated with.
 */
@Data
public class Response<T> {
    private static final Logger log = LoggerFactory.getLogger(Response.class);
    private static final long serialVersionUID = -1802122468331526708L;

    private int statusCode = -1;          // The status code for this response
    private String message = "Waiting";    // The message for this response
    private T data;                        // The data associated with this response

    public Response() {
    }

    public static Response success() {
        Response response = new Response();
        response.setResponseCode(ResponseCode.SUCCESS);
        return response;
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.setResponseCode(ResponseCode.SUCCESS);
        response.setData(data);
        return response;
    }

    public static Response fail(Integer code, String message) {
        Response response = new Response();
        response.setStatusCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response fail(ResponseCode responseCode) {
        Response response = new Response();
        response.setResponseCode(responseCode);
        return response;
    }

    private void setResponseCode(ResponseCode responseCode) {
        this.statusCode = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
