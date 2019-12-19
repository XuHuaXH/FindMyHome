package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.Property;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.acl.Group;

public interface PropertyRepo extends JpaRepository<Property, Long> {
    Property findById(String id);
    Property findAllByZone(Long Zone);
}
