package com.yezi.office.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 叶子
 * @Description 请设置
 * @PackageName com.yezi.office.utils
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2021/1/4 星期一 11:27
 */
public class TokenUtils {

    private static final long EXPIRE = 1000 * 60 * 60 * 24 ;//设置token过期时间
    private static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
    public static final String TOKEN_NAME = "admin_token";

    public static String getJwtToken(String id, String username){

        String JwtToken = Jwts.builder()
                //设置头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                //设置分组信息
                .setSubject("office-user")

                //设置过期时间
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))

                //设置用户主体信息
                .claim("id", id)
                .claim("name", username)

                //设置签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(org.springframework.util.StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(org.springframework.util.StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取用户id
     * @param request
     * @return
     */
    public static String getUserIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(org.springframework.util.StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }

    /**
     * 根据token获取用户名
     * @param jwtToken
     * @return
     */
    public static String getUserNameByJwtToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("name");
    }
}
