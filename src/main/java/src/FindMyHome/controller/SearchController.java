package src.FindMyHome.controller;

import src.FindMyHome.model.Property;
import src.FindMyHome.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping
public class SearchController {

    @Autowired SearchService service;

    // assume the input range is in miles
    @GetMapping("/search")
    public Set<Property> searchProperty(@RequestParam String keyword, @RequestParam int range) {
        return service.searchProperty(keyword, range);
    }

    // for displaying search results
    @GetMapping("/property-details")
    public Property getPropertyDetails(@RequestParam long id) {
        return service.getPropertyDetails(id);
    }
}
