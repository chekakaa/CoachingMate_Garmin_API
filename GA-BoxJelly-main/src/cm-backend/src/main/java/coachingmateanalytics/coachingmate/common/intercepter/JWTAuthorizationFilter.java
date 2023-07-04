package coachingmateanalytics.coachingmate.common.intercepter;

import coachingmateanalytics.coachingmate.common.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
/**
 * developers can assign different role for the system In the future
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(JwtUtils.TOKEN_HEADER);
        // if the header doesn't include token
        if (tokenHeader == null /*|| !tokenHeader.startsWith(JwtUtils.TOKEN_PREFIX)*/) {
            chain.doFilter(request, response);
            logger.info("header doesn't include token ");
            return;
        }
        // if header includes token, parse it and get the auth info.
        logger.info("has token ");
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
        String username = JwtUtils.parseJwt(token).getSubject();
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }
}

