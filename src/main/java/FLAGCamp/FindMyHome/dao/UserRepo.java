package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmailId(String emailId);
}
