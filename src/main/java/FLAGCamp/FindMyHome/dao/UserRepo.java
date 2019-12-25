package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
}
