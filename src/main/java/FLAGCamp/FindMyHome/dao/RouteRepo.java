package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.Route;
import FLAGCamp.FindMyHome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {
    Route findById(int id);
    List<Route> findByUser(User user);
}
