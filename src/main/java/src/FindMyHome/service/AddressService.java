package src.FindMyHome.service;

import src.FindMyHome.dao.AddressRepo;
import src.FindMyHome.model.Address;
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
