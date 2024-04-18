package br.com.fiap.activityrewards.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
        public String  loginForm(){
        return "auth/login";
    }

    @GetMapping("/logout")
        public String  logoutpage(){
        return "auth/logout";
    }
    
}
