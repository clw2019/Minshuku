package com.clw.interceptor;

import com.clw.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/4/14 14:55
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        log.info("[{}]", request.getHeader("Host"));
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token != null && jwtTokenUtils.isTokenExpired(token)) {
            System.out.println("..... " + jwtTokenUtils.getClaimsFromToken(token).getExpiration());
            System.out.println("++++++++++++++++++++++++++++++++ token 过期了.....");
            return false;
        }
        return true;
    }
}
