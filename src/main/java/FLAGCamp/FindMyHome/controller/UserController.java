package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.dao.UserRepo;
import FLAGCamp.FindMyHome.jackson.LoginForm;
import FLAGCamp.FindMyHome.jackson.RegisterationForm;
import FLAGCamp.FindMyHome.model.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Builder
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserRepo repo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepo repo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repo = repo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterationForm regForm) {

        if (regForm.validate()) {
            User newUser = User.builder().emailId(regForm.emailId)
                    .password(bCryptPasswordEncoder.encode(regForm.password))
                    .build();
            repo.save(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm){
        return null;
    }

    @GetMapping("/listusers")
    Collection<User> listUsers(){
        Collection<User> res = repo.findAll();
        return res;
    }


}
