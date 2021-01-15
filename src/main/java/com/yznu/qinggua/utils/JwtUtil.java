package com.yznu.qinggua.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    // 加密密钥
    private static final String SING = "~!@#$%^&*()_+<>?{}|";

    // 加密token
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        // 有效期为5小时
        instance.add(Calendar.SECOND, 18000);

        // 创建 JWT builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k, v)->{
            builder.withClaim(k, v);
        });

        // 生成token
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));

        return token;
    }

    // 验证token
    public static void verifyToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    // 获取token信息
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }

}