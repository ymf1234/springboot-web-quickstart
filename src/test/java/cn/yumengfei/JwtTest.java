package cn.yumengfei;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class JwtTest {

    // 生成Jwt
    @Test
    public void genJwt() {
        HashMap<String, Object> claims = new HashMap<>();

        claims.put("id", 1);
        claims.put("username", 1);

        String jwt = Jwts.builder()
                .setClaims(claims) // 自定义内容(载荷)
                .setIssuedAt(new Date())
                .setIssuer("JWT")
                .signWith(SignatureAlgorithm.HS256, "genJwt") // 签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000)) // 有效期
                .compact();

        System.out.println(jwt);
    }

    // 解析jwt
    @Test
    public void parseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("genJwt") // 指定签名秘钥（必须保证和生成令牌时使用相同的签名秘钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKV1QiLCJpZCI6MSwiZXhwIjoxNjgyMTU1MTU4LCJpYXQiOjE2ODIwNjg3NTgsInVzZXJuYW1lIjoxfQ.mB48y2a6Kj46RWsfjONLBWFtSWPQptTQ-M0Rpgcb9CI")
                .getBody();

        System.out.println(claims);
    }

}
