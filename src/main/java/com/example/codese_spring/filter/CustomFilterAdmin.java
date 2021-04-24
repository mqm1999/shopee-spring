package com.example.codese_spring.filter;

import com.example.codese_spring.constant.Constant;
import com.example.codese_spring.dto.InvalidTokenDTO;
import com.example.codese_spring.helper.Jwt.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
public class CustomFilterAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        Claims claims = Jwt.getClaimFromToken(token, Constant.SECRET_KEY);
        String role = (String) claims.get("role");
        if (role.equals("ADMIN")) {
            request.setAttribute("role", role);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setStatus(403);
        }
    }

    @Override
    public void destroy() {

    }
}
