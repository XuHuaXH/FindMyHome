package FLAGCamp.FindMyHome.service;

import FLAGCamp.FindMyHome.DistanceMatrixClient;
import FLAGCamp.FindMyHome.dao.RouteRepo;
import FLAGCamp.FindMyHome.model.Address;
import FLAGCamp.FindMyHome.model.Node;
import FLAGCamp.FindMyHome.model.Route;
import FLAGCamp.FindMyHome.model.TravelTimeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteService {

    @Autowired RouteRepo repo;

    public List<Route> getRoutes() {
        return repo.findAll();
    }

    public void addOrUpdateRoute(Route route) {
        repo.save(route);
    }

    public void deleteRoute(int routeId) {
        repo.deleteById(routeId);
    }

    public List<TravelTimeResponse> getTravelTime(Address address) {
        List<TravelTimeResponse> response = new ArrayList<>();
        Node home = Node.builder().name("home").address(address).build();
        List<Route> routes = getRoutes(); // TODO: change to getting all routes of one user

        DistanceMatrixClient client = new DistanceMatrixClient();
        for (Route route : routes) {
            response.add(client.estimateTravelTime(route, home));
        }
        return response;
    }
}
