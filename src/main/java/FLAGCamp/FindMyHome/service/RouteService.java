package FLAGCamp.FindMyHome.service;

import FLAGCamp.FindMyHome.DistanceMatrixClient;
import FLAGCamp.FindMyHome.dao.PropertyRepo;
import FLAGCamp.FindMyHome.dao.RouteRepo;
import FLAGCamp.FindMyHome.dao.UserRepo;
import FLAGCamp.FindMyHome.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteService {

    @Autowired RouteRepo routeRepo;
    @Autowired PropertyRepo propertyRepo;
    @Autowired UserRepo userRepo;

    public List<Route> getRoutes(String emailId) {
        return routeRepo.findByUser(userRepo.findByEmailId(emailId));
    }

    public String addOrUpdateRoute(Route route, String emailId) {
        User user = userRepo.findByEmailId(emailId);
        if (route.getId() == null) {
            // add a new route for the user
            route.setUser(user);
            routeRepo.save(route);
            return "saved";
        } else if (!routeRepo.findById(route.getId()).isPresent()) {
            return "No such route";
        } else {
            // check if the route id is attached to the user
            Route original = routeRepo.findById(route.getId()).get();
            if (original.getUser().equals(user)) {
                route.setUser(user);
                routeRepo.save(route);
                return "updated";
            } else {
                return "Permission Denied";
            }
        }
    }

    public String deleteRoute(int routeId, String emailId) {
        User user = userRepo.findByEmailId(emailId);
        Route route = routeRepo.findById(routeId);
        if (route.getUser() != null && !route.getUser().equals(user)) {
            // this user has no permission to update the route
            return "Permission Denied";
        }
        routeRepo.deleteById(routeId);
        return "OK";
    }

    public List<TravelTimeResponse> getTravelTime(long propertyId, String emailId) {
        List<TravelTimeResponse> response = new ArrayList<>();
        if (!propertyRepo.findById(propertyId).isPresent()) {
            return response;
        }

        Property property = propertyRepo.findById(propertyId).get();
        Node home = Node.builder().name("home").address(property.getAddress()).build();

        List<Route> routes = getRoutes(emailId);

        DistanceMatrixClient client = new DistanceMatrixClient();
        for (Route route : routes) {
            response.add(client.estimateTravelTime(route, home));
        }
        return response;
    }
}
