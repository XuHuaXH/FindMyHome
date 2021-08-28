package src.FindMyHome.controller;

import src.FindMyHome.model.Address;
import src.FindMyHome.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService service;

    @GetMapping
    public List<Address> getAddresses() {
        return service.getAddresses();
    }

}
