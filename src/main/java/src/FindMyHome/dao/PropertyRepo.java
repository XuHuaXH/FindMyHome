package src.FindMyHome.dao;

import src.FindMyHome.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    Set<Property> findAllByZone(Long Zone);

    @Query("SELECT p FROM Property p " +
            "WHERE p.address.latitude >= :minLat" +
            " AND p.address.latitude <= :maxLat" +
            " AND p.address.longitude >= :minLng" +
            " AND p.address.longitude <= :maxLng ")
    Set<Property> selectPropertyCaseA(double minLat, double maxLat, double minLng, double maxLng);

    @Query("SELECT p FROM Property p " +
            "WHERE p.address.latitude >= :minLat" +
            " AND p.address.latitude <= :maxLat" +
            " AND (p.address.longitude >= :minLng" +
            " OR p.address.longitude <= :maxLng) ")
    Set<Property> selectPropertyCaseB(double minLat, double maxLat, double minLng, double maxLng);
}
