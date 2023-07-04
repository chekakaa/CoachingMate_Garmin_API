package coachingmateanalytics.coachingmate.common.intercepter;

import coachingmateanalytics.coachingmate.common.annotation.AuthCheck;
import coachingmateanalytics.coachingmate.common.enums.ResponseCode;
import coachingmateanalytics.coachingmate.common.exceptions.BusinessException;
import coachingmateanalytics.coachingmate.service.UserService;
import coachingmateanalytics.coachingmate.service.serviceImpl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * AuthInterceptor is responsible for validating user authentication tokens.
 */
@Configuration
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * Pre-handle method that intercepts incoming requests and validates authentication tokens.
     *
     * @param request  The incoming HttpServletRequest object.
     * @param response The HttpServletResponse object to send the response.
     * @param handler  The handler object.
     * @return A boolean value, true if the token is valid or AuthCheck is not required, false otherwise.
     * @throws Exception if an error occurs during token validation.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Token validation
        setResponseHeaders(response);

        AuthCheck authCheck;
        UserService userService = new UserServiceImpl();
        if (handler instanceof HandlerMethod) {
            authCheck = ((HandlerMethod) handler).getMethodAnnotation(AuthCheck.class);
        } else {
            return true;
        }

        String authToken = request.getHeader("X-Token");
        if (authCheck != null) {
            if (userService.tokenCheck(authToken)) {
                throw new BusinessException(ResponseCode.AUTHORITY_AUTHENTICATION_FAILED);
            }
        }

        return true;
    }

    /**
     * Sets the response headers for the HttpServletResponse object.
     *
     * @param response The HttpServletResponse object.
     */
    private void setResponseHeaders(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Access-Control-Allow-Credentials", "True");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " +
                "x-token, User, UserId, token, Authorization");
        response.setContentType("application/json;charset=UTF-8");
    }
}
