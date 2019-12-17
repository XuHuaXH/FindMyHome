package FLAGCamp.FindMyHome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fake")
public class FakeController {
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword ){
        return "{\"totalResult\":3,\"SearchLat\":37.802452,\"SearchLong\":-122.417227,\"SearchKeyWord\": \"" + keyword + "\",\"results\":[{\"uuid\":\"1\",\"Address\":\"950 Lombard St, San Francisco, CA 94133\",\"Lat\":37.802452,\"Long\":-122.417227,\"Price\":40500000,\"Bedroom\":6,\"Bathroom\":8,\"area\":9495,\"Like\":true},{\"uuid\":\"2\",\"Address\":\"2698 Pacific Ave, San Francisco, CA 94115\",\"Lat\":37.793122,\"Long\":-122.439469,\"Price\":26800000,\"Bedroom\":6,\"Bathroom\":8,\"area\":10734,\"Like\":false},{\"uuid\":\"3\",\"Address\":\"870 Harrison St UNIT 305, San Francisco, CA 94107\",\"Lat\":37.779807,\"Long\":-122.401351,\"Price\":466345,\"Bedroom\":2,\"Bathroom\":1,\"area\":889,\"Like\":false}]}";
    }

    @GetMapping("/liked")
    public String liked(){
        return "{\"totalResult\":3,\"SearchLat\":37.802452,\"SearchLong\":-122.417227,\"SearchKeyWord\":\"San Francisco\",\"results\":[{\"uuid\":\"1\",\"Address\":\"950 Lombard St, San Francisco, CA 94133\",\"Lat\":37.802452,\"Long\":-122.417227,\"Price\":40500000,\"Bedroom\":6,\"Bathroom\":8,\"area\":9495,\"Like\":true},{\"uuid\":\"2\",\"Address\":\"2698 Pacific Ave, San Francisco, CA 94115\",\"Lat\":37.793122,\"Long\":-122.439469,\"Price\":26800000,\"Bedroom\":6,\"Bathroom\":8,\"area\":10734,\"Like\":false},{\"uuid\":\"3\",\"Address\":\"870 Harrison St UNIT 305, San Francisco, CA 94107\",\"Lat\":37.779807,\"Long\":-122.401351,\"Price\":466345,\"Bedroom\":2,\"Bathroom\":1,\"area\":889,\"Like\":false}]}";
    }

    @GetMapping("/history")
    public String history(){
        return "{\"totalResult\":1,\"mode\":\"History\",\"results\":[{\"uuid\":\"2\",\"Address\":\"2698 Pacific Ave, San Francisco, CA 94115\",\"Lat\":37.793122,\"Long\":-122.439469,\"Price\":26800000,\"MainPic\":\"2.jpg\",\"Bedroom\":6,\"Bathroom\":8,\"area\":10734,\"Like\":false}]}";
    }

    @GetMapping("/owned")
    public String owned(){
        return "{\"totalResult\":1,\"mode\":\"Owned\",\"results\":[{\"uuid\":\"3\",\"Address\":\"870 Harrison St UNIT 305, San Francisco, CA 94107\",\"Lat\":37.779807,\"Long\":-122.401351,\"Price\":466345,\"MainPic\":\"3.jpg\",\"Bedroom\":2,\"Bathroom\":1,\"area\":889,\"Like\":false}]}";
    }

}
