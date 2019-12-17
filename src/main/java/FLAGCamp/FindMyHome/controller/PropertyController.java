package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.model.Property;
import FLAGCamp.FindMyHome.model.PropertyRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class PropertyController {
    private PropertyRepo propertyRepo;
    public PropertyController(PropertyRepo propertyRepo){
        this.propertyRepo = propertyRepo;
    }

    @GetMapping("/properties")
    Collection<Property> groups(){
        return propertyRepo.findAll();
    }
}
