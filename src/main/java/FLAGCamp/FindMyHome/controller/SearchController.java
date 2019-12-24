package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.dao.PropertyRepo;
import FLAGCamp.FindMyHome.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping
public class SearchController {

    @Autowired PropertyRepo repo;

    @GetMapping("/search")
    public Set<Property> searchProperty(@RequestParam long zone) {
        // TODO
        return repo.findAllByZone(zone);
    }

    // for displaying search results
    @GetMapping("/property-details")
    public Property getPropertyDetails(@RequestParam long id) {
        if (!repo.findById(id).isPresent()) {
            return null;
        }
        return repo.findById(id).get();
    }
}
