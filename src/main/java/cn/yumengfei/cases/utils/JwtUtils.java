package cn.yumengfei.cases.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String singKey = "genJwt"; // 签名秘钥
    private static Long expire = 43200000L; // 有效时间

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分载荷 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims) // 自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, singKey) // 签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + expire)) // 过期时间
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(singKey) // 指定签名秘钥
                .parseClaimsJws(jwt) // 指定令牌Token
                .getBody();
        return claims;
    }
}
