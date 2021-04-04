package com.example.codese_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/default")
public class DefaultController {
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return (String) request.getAttribute("user_id");
    }
}
