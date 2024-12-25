package com.swproj.SWProject.config.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String sayHi(HttpServletRequest request) {
        return "Hello to Ibrahim Project, this is the request Id "+request.getSession().getId();
    }
}
