package com.hzk.backend.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControl {
    @GetMapping("/adminLogin")
    public void login(HttpServletRequest request) {
        System.out.println("---进入Login方法---");
        System.out.println(request.getMethod());
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
    }
}
