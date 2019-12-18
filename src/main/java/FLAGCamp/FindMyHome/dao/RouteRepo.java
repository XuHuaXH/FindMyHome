package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

}
