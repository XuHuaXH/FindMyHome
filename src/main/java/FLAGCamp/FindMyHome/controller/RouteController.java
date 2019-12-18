package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.model.Route;
import FLAGCamp.FindMyHome.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addOrUpdateRoute(@RequestBody Route route) {
        service.addOrUpdateRoute(route);
    }

    @DeleteMapping("/route")
    public void deleteRoute(@RequestBody Map<String, Integer> map) {
        int routeId = map.get("routeId");
        service.deleteRoute(routeId);
    }
}
