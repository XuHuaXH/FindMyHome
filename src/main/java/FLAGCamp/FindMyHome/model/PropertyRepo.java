package FLAGCamp.FindMyHome.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.security.acl.Group;

public interface PropertyRepo extends JpaRepository<Property, Long> {
    Property findById(String id);
}
