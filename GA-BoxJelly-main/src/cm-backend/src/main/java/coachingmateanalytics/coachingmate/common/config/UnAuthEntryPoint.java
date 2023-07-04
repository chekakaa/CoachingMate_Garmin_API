package coachingmateanalytics.coachingmate.common.config;

import coachingmateanalytics.coachingmate.common.utils.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//未认证时执行这个类
public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String jsonResponse= JSON.toJSONString(Response.fail(2003,"Need Auth"));
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}

