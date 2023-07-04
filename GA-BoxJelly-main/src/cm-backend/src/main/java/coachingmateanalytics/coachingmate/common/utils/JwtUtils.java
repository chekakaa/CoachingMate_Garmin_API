package coachingmateanalytics.coachingmate.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_HEADER="X-Token";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final Long EXPIRE = 24 * 3600 * 1000L; //设置一天时间
    private static final String SECRET = "wzomg"; //用于signature（签名）部分解密

    //生成token
    public static String createJwt(String userId, String username) {
        /*setIssuer：设置iss（发行方）索赔
        setSubject：设置sub（主题）声明
        setAudience：设置aud（受众群体）声明
        setExpiration：设置exp（到期时间）声明
        setNotBefore：设置nbf（不早于）声明
        setIssuedAt：设置iat（签发）声明
        setId：设置jti（JWT ID）声明*/
        //Assert.notNull(userId, "用户ID不能为空");
        //Assert.notNull(username, "用户名不能为空");
        return Jwts.builder().setSubject("userId")
                .claim("password", userId)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }

    //解析token
    public static Claims parseJwt(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {    System.out.println(parseJwt("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2MzQ2MjlhMzQ2YzM4MjA4Y2FhOGMxMjYiLCJ1c2VySWQiOiI2MzQ2MjlhMzQ2YzM4MjA4Y2FhOGMxMjYiLCJ1c2VybmFtZSI6IjEyMzQ1NjciLCJpYXQiOjE2NjU2MjQzODIsImp0aSI6IjU0YWQwYTUzLTQyOGYtNDBkOC05ODJhLTgwMGQ1MzQ0Nzg4OSIsImV4cCI6MTY2NTcxMDc4Mn0.ZCDeq4gTRWCBIjekCk6uEJ3MyOkRbl25oyA5gtl-PEc"));
    }
}
