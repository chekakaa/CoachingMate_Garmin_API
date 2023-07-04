// Define the package for the configuration
package coachingmateanalytics.coachingmate.common.config;

import coachingmateanalytics.coachingmate.common.intercepter.JWTAuthorizationFilter;
import coachingmateanalytics.coachingmate.common.intercepter.JwtLoginAuthFilter;
import coachingmateanalytics.coachingmate.common.utils.Response;
import coachingmateanalytics.coachingmate.service.serviceImpl.UserDetailsServiceImpl;
import coachingmateanalytics.coachingmate.service.serviceImpl.UserServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static coachingmateanalytics.coachingmate.common.utils.Consts.URL_WHITELIST;

/**
 * SecurityConfig is a class that contains security configurations for
 * the application. Currently, it is an empty class, but it can be
 * extended to include any security-related settings.
 */

 @Configuration
 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true)
 public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;



     @Override
     protected void configure(HttpSecurity http) throws Exception {
         //super.configure(http);
         http.cors().and().csrf().disable()
                 // don't need session
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and().authorizeRequests()
                 //whiteList URL can visit without auth
                 .antMatchers(URL_WHITELIST).permitAll()
                 .anyRequest().authenticated()
                 .and()
                 //set filter (order is important)
                 .addFilter(new JwtLoginAuthFilter(authenticationManager()))
                 .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                 .httpBasic().authenticationEntryPoint(new UnAuthEntryPoint()); //response of no auth


     }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // should change to frontend url;
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
                //.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
 }
