package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.JWT.JWTUserAuthHelper;
import FLAGCamp.FindMyHome.dao.UserRepo;
import FLAGCamp.FindMyHome.model.Property;
import FLAGCamp.FindMyHome.dao.PropertyRepo;
import FLAGCamp.FindMyHome.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PropertyController {

    @Autowired private PropertyRepo propertyRepo;
    @Autowired private UserRepo userRepo;

    public PropertyController(PropertyRepo propertyRepo){
        this.propertyRepo = propertyRepo;
    }

    @GetMapping("/properties")
    Collection<Property> groups(){
        return propertyRepo.findAll();
    }

    @GetMapping("/liked")
    public Set<Property> likedProperties(HttpServletRequest request) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        User user = userRepo.findByEmailId(emailId);
        return user != null ? user.getFavPropertyList() : new HashSet<>();
    }

    @PostMapping("/like")
    public Map<String, String> likeProperty(HttpServletRequest request, @RequestBody Map<String, Long> body) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        User user = userRepo.findByEmailId(emailId);
        long id = body.get("id");
        String status;

        if (user == null) {
            return new HashMap<>();
        }

        // check if the given property id is valid
        if (!propertyRepo.findById(id).isPresent()) {
            status = "no such property";
        } else {
            Property property = propertyRepo.findById(id).get();
            Set<Property> liked = user.getFavPropertyList();
            liked.add(property);
            user.setFavPropertyList(liked);
            userRepo.save(user);
            status = "OK";
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return response;
    }

    @PostMapping("/unlike")
    public Map<String, String> unlikeProperty(HttpServletRequest request, @RequestBody Map<String, Integer> body) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        User user = userRepo.findByEmailId(emailId);
        long id = body.get("id");
        String status;

        if (user == null) {
            return new HashMap<>();
        }

        // check if the given property id is valid
        if (!propertyRepo.findById(id).isPresent()) {
            status = "no such property";
        } else {
            Property property = propertyRepo.findById(id).get();
            Set<Property> liked = user.getFavPropertyList();
            liked.remove(property);
            user.setFavPropertyList(liked);
            userRepo.save(user);
            status = "OK";
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return response;
    }

    @GetMapping("/history")
    public Set<Property> historyProperties(HttpServletRequest request) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        User user = userRepo.findByEmailId(emailId);
        return user != null ? user.getHistoryPropertyList() : new HashSet<>();
    }

    @PostMapping("/history")
    public Map<String, String> addHistory(HttpServletRequest request, @RequestBody Map<String, Integer> body) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        User user = userRepo.findByEmailId(emailId);
        long id = body.get("id");
        String status;

        if (user == null) {
            return new HashMap<>();
        }

        // check if the given property id is valid
        if (!propertyRepo.findById(id).isPresent()) {
            status = "no such property";
        } else {
            Property property = propertyRepo.findById(id).get();
            Set<Property> history = user.getHistoryPropertyList();
            history.add(property);
            user.setHistoryPropertyList(history);
            userRepo.save(user);
            status = "OK";
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return response;
    }
}
