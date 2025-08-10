package com.example.Leave.management.system.Controller;

import com.example.Leave.management.system.Model.LeaveSignup;
import com.example.Leave.management.system.Service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Security {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @GetMapping("/GetId")
    public String GetSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }
    @GetMapping("/WellCome")
    public String Welcome(){
        return "WellCome to my page";
    }
    @GetMapping("/CSRF_Id")
    public CsrfToken Csrf_Token(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
