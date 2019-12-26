package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.JWT.JWTUserAuthHelper;
import FLAGCamp.FindMyHome.model.Address;
import FLAGCamp.FindMyHome.model.Route;
import FLAGCamp.FindMyHome.model.TravelTimeResponse;
import FLAGCamp.FindMyHome.model.User;
import FLAGCamp.FindMyHome.service.RouteService;
import io.jsonwebtoken.JwtHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RouteController {

    @Autowired
    RouteService service;

    @PostMapping("/test-route")
    public int testRoute(@RequestBody Route route) {
        return route.getId();
    }

    @GetMapping("/route")
    public List<Route> getRoutes(HttpServletRequest request) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        return service.getRoutes(emailId);
    }

    @PostMapping("/route")
    public Map<String, String> addOrUpdateRoute(@RequestBody Route route, HttpServletRequest request) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        String status = service.addOrUpdateRoute(route, emailId);
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return response;
    }

    @DeleteMapping("/route")
    public Map<String, String> deleteRoute(@RequestBody Map<String, Integer> map, HttpServletRequest request) {
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        int routeId = map.get("id");
        String status = service.deleteRoute(routeId, emailId);
        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return response;
    }

    @PostMapping("/estimate-travel-time")
    public List<TravelTimeResponse> getTravelTime(@RequestBody Map<String, Long> map, HttpServletRequest request) {
        long routeId = map.get("id");
        String emailId = JWTUserAuthHelper.TokenToUser(request);
        return service.getTravelTime(routeId, emailId);
    }
}
