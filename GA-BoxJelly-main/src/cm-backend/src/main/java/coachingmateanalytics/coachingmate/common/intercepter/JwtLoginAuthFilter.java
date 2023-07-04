package coachingmateanalytics.coachingmate.common.intercepter;

import coachingmateanalytics.coachingmate.common.utils.BeanUtils;
import coachingmateanalytics.coachingmate.common.utils.JwtUtils;
import coachingmateanalytics.coachingmate.common.utils.Response;
import coachingmateanalytics.coachingmate.controller.LoginController;
import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.dao.UserDaoImpl;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import coachingmateanalytics.coachingmate.service.UserService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



public class JwtLoginAuthFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;




    public JwtLoginAuthFilter(AuthenticationManager authenticationManager) {
        //super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.setFilterProcessesUrl("/login");
    }

    //login step
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserPartner loginUser = new ObjectMapper().readValue(request.getInputStream(), UserPartner.class);
            System.out.println("user："+loginUser.toString());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //return super.attemptAuthentication(request, response);
    }

    //if username and password is correct
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserPartner jwtUser = (UserPartner) authResult.getPrincipal();

        String token = JwtUtils.createJwt(jwtUser.getPassword(),jwtUser.getUsername());

        // according to the jwt, the prefix should be  `Bearer token`
        response.setHeader("token", JwtUtils.TOKEN_PREFIX + token);
        jwtUser.setToken(token);
        response.setContentType("application/json");
        // update in database
        // after we use redis, we don't need to store it in database
        UserService userService= BeanUtils.getBean(UserService.class);
        userService.updateUser(jwtUser);



        String jsonUser = JSON.toJSONString(Response.success(jwtUser));

        response.getWriter().write(jsonUser);
    }

    // if username or password is incorrect
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //response.getWriter().write("authentication failed, reason: " + failed.getMessage());
        String jsonResponse= JSON.toJSONString(Response.fail(2003,"User is not existed or password is incorrect!!"));
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

}

