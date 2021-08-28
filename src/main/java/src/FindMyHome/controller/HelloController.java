package src.FindMyHome.controller;


import src.FindMyHome.JWT.JWTUserAuthHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/hello/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }

    @GetMapping("/hello/name")
    public String name(HttpServletRequest request){
        return JWTUserAuthHelper.TokenToUser(request);
    }
}
