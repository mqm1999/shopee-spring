package com.example.codese_spring.filter;

import com.example.codese_spring.constant.Constant;
import com.example.codese_spring.dto.InvalidTokenDTO;
import com.example.codese_spring.helper.Jwt.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Order(1)
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("default filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        //exception handler khong co tac dung o tang nay, phai tu xu ly
        try {
            String userId = Jwt.verifyToken(token, Constant.SECRET_KEY);
            request.setAttribute("user_id", userId);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json");
            InvalidTokenDTO invalidTokenDTO = new InvalidTokenDTO("Invalid token");
            OutputStream out = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            out.flush();
        }
    }

    @Override
    public void destroy() {
        System.out.println("default filter destroy");
    }

}
