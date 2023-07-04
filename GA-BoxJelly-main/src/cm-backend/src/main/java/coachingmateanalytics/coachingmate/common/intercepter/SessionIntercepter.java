package coachingmateanalytics.coachingmate.common.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SessionIntercepter class implements HandlerInterceptor to perform user login checks.
 */
public class SessionIntercepter implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SessionIntercepter.class);

    /**
     * The preHandle method is executed before the actual request processing.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @param handler  The handler object.
     * @return boolean If true, the request will be processed further, otherwise, the request will be terminated.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        String token = request.getHeader("X-Token");

        // Configure the response character encoding and headers.
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Access-Control-Allow-Credentials", "True");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " +
                "x-token, User, UserId, token, Authorization");
        response.setContentType("application/json;charset=UTF-8");

        // Proceed with the request processing.
        return true;
    }
}
