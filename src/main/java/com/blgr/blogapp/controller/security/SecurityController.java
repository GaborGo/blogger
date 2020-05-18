package com.blgr.blogapp.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
    @RequestMapping("login")
    public String login(){
        return "login";
    }


    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        System.out.println(" ment a login /default keresi");
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/welcomeadmin";
        }
        return "redirect:/blogger/welcome";
    }
}

