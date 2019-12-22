package FLAGCamp.FindMyHome.dao;

import FLAGCamp.FindMyHome.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
