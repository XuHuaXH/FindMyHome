package FLAGCamp.FindMyHome.service;

import FLAGCamp.FindMyHome.dao.AddressRepo;
import FLAGCamp.FindMyHome.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressService {
    @Autowired AddressRepo repo;

    public List<Address> getAddresses() {
        return repo.findAll();
    }
}
