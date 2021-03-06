package com.smartroom.springServer.business_services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smartroom.springServer.exceptions.JwtException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//jwt授权服务器颁发的令牌，包含关于用户或者客户的元数据和声明(claims)通过检查签名，期望的颁发者(issuer)，期望的接收人aud(audience)，或者scope，
// 资源服务器可以在本地校验令牌 通常实现为签名的JSON Web Tokens(JWT)

//postman请求：http://localhost:8080/auth/token请求头aContent-Type需要配置


@Service
public class JwtService {
    private static final String BEARER = "Bearer ";
    private static final String USER = "user";
    private static final String NAME = "name";
    private static final String ISSUER = "smartroom";
    private static final int EXPIRES_IN_MILLISECOND = 3600000;
    private static final String SECRET = "secret-password-test";

    //JwtService通过generateToken生成token，通过validateToken校验token
    public String createToken(String email, String userName) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_IN_MILLISECOND))
                .withClaim(USER, email)
                .withClaim(NAME, userName)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean isBearer(String authorization) {
        return authorization != null && authorization.startsWith(BEARER) && authorization.split("\\.").length == 3;
    }

    public String user(String authorization) {
        return this.verify(authorization).getClaim(USER).asString();
    }

    private DecodedJWT verify(String authorization) {
        if (!this.isBearer(authorization)) {
            throw new JwtException("It is not Bearer");
        }
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.length()));
        } catch (Exception exception) {
            throw new JwtException("JWT is wrong. " + exception.getMessage());
        }

    }
}
