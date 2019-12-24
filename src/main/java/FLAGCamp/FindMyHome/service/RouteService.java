package FLAGCamp.FindMyHome.service;

import FLAGCamp.FindMyHome.DistanceMatrixClient;
import FLAGCamp.FindMyHome.dao.RouteRepo;
import FLAGCamp.FindMyHome.dao.UserRepo;
import FLAGCamp.FindMyHome.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RouteService {

    @Autowired RouteRepo routeRepo;
    @Autowired UserRepo userRepo;

    private User emailIdToUser(String emailId) {
        return userRepo.findByEmailId(emailId);
    }

    public List<Route> getRoutes(String emailId) {
        return routeRepo.findByUser(emailIdToUser(emailId));
    }

    public String addOrUpdateRoute(Route route, String emailId) {

        if (route.getId() == null) {
            // add a new route for the user
            User user = emailIdToUser(emailId);
            route.setUser(user);
            routeRepo.save(route);
            return "saved";
        } else if (!routeRepo.findById(route.getId()).isPresent()) {
            return "No such route";
        } else {
            // check if the route id is attached to the user
            Route original = routeRepo.findById(route.getId()).get();
            if (original.getUser().getEmailId().equals(emailId)) {
                route.setUser(emailIdToUser(emailId));
                routeRepo.save(route);
                return "updated";
            } else {
                return "Permission Denied";
            }
        }
    }

    public String deleteRoute(int routeId, String emailId) {
        Route route = routeRepo.findById(routeId);
        if (route.getUser() != null && !route.getUser().getEmailId().equals(emailId)) {
            // this user has no permission to update the route
            return "Permission Denied";
        }
        routeRepo.deleteById(routeId);
        return "OK";
    }

    public List<TravelTimeResponse> getTravelTime(Address address, String emailId) {
        List<TravelTimeResponse> response = new ArrayList<>();
        Node home = Node.builder().name("home").address(address).build();

        List<Route> routes = getRoutes(emailId);

        DistanceMatrixClient client = new DistanceMatrixClient();
        for (Route route : routes) {
            response.add(client.estimateTravelTime(route, home));
        }
        return response;
    }
}
