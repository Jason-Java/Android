package com.jason.system.util;

import com.jason.system.constant.Constants;
import com.jason.system.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";//用户
    private static final String CLAIM_KEY_CREATED = "created";//创建时间

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 获取用户携带的token
     *
     * @param request
     * @return
     */
    public String getUserNameFromToken(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        return getUsernameFromToken(token);
    }

    /**
     * 从Token中获取用户名
     *
     * @param token
     * @return
     */
    private String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();//获取用户名
        } catch (Exception exception) {
            username = null;
        }
        return username;
    }


    /**
     * 根据token获取载荷（claim）  **************************
     *
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return claims;
    }

    /**
     * 根据用户名生成Token
     *
     * @param userName
     * @return
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userName);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000*60))//失效时间
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 从请求体中提取token
     *
     * @param request
     * @return
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }


    /**
     * 判断token是否过期
     *
     * @param request
     * @return true 过期 false 未过期
     * <p>
     * 失效时间时间在当前时间之前就是失效了
     */
    public boolean isTokenExpired(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        Date expiredDate;
        try {
            expiredDate = getExpiredDateFromToken(token);
        } catch (Exception e) {
            return true;
        }
        return expiredDate.before(new Date());   //expiredDate < new Date()  true
    }

    /**
     * token 中获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        Date expiration = claims.getExpiration();
        return expiration;
    }


    /**
     * 刷新token 就是修改创建时间
     *
     * @param request
     * @param response
     * @return
     */
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String token = getTokenFromRequest(request);
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        response.setHeader(header, generateToken(claims));
    }


}
