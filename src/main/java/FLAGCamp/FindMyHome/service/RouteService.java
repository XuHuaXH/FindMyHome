package FLAGCamp.FindMyHome.service;

import FLAGCamp.FindMyHome.dao.RouteRepo;
import FLAGCamp.FindMyHome.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
