package com.example.codese_spring.filter;

import com.example.codese_spring.constant.Constant;
import com.example.codese_spring.dto.InvalidTokenDTO;
import com.example.codese_spring.helper.Jwt.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Order(1)
public class CustomFilterId implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        String responseToken = Jwt.verifyToken(token, Constant.SECRET_KEY);
        Claims claims = Jwt.getClaimFromToken(token, Constant.SECRET_KEY);
        if(responseToken != null) {
            String userId = (String) claims.get("userId");
            String role = (String) claims.get("role");
            request.setAttribute("user_id", userId);
            request.setAttribute("role", role);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setStatus(401);
        }
    }

    @Override
    public void destroy() {

    }
}
