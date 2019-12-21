package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.model.Address;
import FLAGCamp.FindMyHome.model.Route;
import FLAGCamp.FindMyHome.model.TravelTimeResponse;
import FLAGCamp.FindMyHome.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RouteController {

    @Autowired
    RouteService service;

    @GetMapping("/route")
    public List<Route> getRoutes() {
        return service.getRoutes();
    }

    @PostMapping("/route")
    public Map<String, String> addOrUpdateRoute(@RequestBody Route route) {
        service.addOrUpdateRoute(route);
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

    @DeleteMapping("/route")
    public Map<String, String> deleteRoute(@RequestBody Map<String, Integer> map) {
        int routeId = map.get("id");
        service.deleteRoute(routeId);
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

    @PostMapping("/estimate-travel-time")
    public List<TravelTimeResponse> getTravelTime(@RequestBody Address address) {
        return service.getTravelTime(address);
    }
}
