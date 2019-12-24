package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.Property;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.acl.Group;
import java.util.List;
import java.util.Set;

public interface PropertyRepo extends JpaRepository<Property, Long> {
    Set<Property> findAllByZone(Long Zone);
}
