package com.yznu.qinggua.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yznu.qinggua.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        // 获取请求中的token令牌
        String token = request.getHeader("token");

        if(token == null){
            map.put("data","");
            map.put("status", 500);
            map.put("message", "Header中无token!");
        }else{
            try {
                // 验证令牌
                JwtUtil.verifyToken(token);
                // 放行请求
                return true;
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                map.put("message", "Invalid signature!");
            } catch (TokenExpiredException e){
                e.printStackTrace();
                map.put("message", "Token expired!");
            }catch (ArithmeticException e){
                e.printStackTrace();
                map.put("message", "Token inconsistent algorithms!");
            }catch (Exception e){
                e.printStackTrace();
                map.put("message", "Token verification failed!");
            }
            // 失败状态
            map.put("status", 500);
            map.put("data","");
        }
        if((Integer) map.get("status") == 500){
            String text = new ObjectMapper().writeValueAsString(map);
            JSONObject responseJSONObject = JSONObject.parseObject(text);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(responseJSONObject);
            return false;
        }else{
            return true;
        }
    }
}