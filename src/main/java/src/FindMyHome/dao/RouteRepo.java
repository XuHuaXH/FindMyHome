package src.FindMyHome.dao;

import src.FindMyHome.model.Route;
import src.FindMyHome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {
    Route findById(int id);
    List<Route> findByUser(User user);
}
