package coachingmateanalytics.coachingmate.common.utils;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * A utility class that handles responses in a consistent format.
 */
@ControllerAdvice(basePackages = "coachingmateanalytics.coachingmate")
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * Handles the formatting of the response before it's written to the body.
     *
     * @param o The object to be included in the response body
     * @param methodParameter The method parameter
     * @param mediaType The media type of the response
     * @param aClass The class of the HttpMessageConverter
     * @param serverHttpRequest The server HTTP request
     * @param serverHttpResponse The server HTTP response
     * @return The formatted response object
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) o;
            return Response.fail(errorResponse.getStatusCode(), errorResponse.getMessage());
        } else if (o instanceof String) {
            return JsonUtil.object2Json(Response.success(o));
        }

        return Response.success(o);
    }
}
