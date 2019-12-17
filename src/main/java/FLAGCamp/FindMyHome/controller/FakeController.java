package FLAGCamp.FindMyHome.controller;

import FLAGCamp.FindMyHome.model.Property;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/fake")
public class FakeController {
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String address ){
        return "{ \n" +
                "  \"totalResult\": 1,    \n" +
                "  \"SearchLat\": 37.802452, \n" +
                "  \"SearchLong\" :-122.417227,\n" +
                "  \"results\" : [   \n" +
                "    {\n" +
                "      \"uuid\" : \"1\",\n" +
                "      \"Address\" : \"950 Lombard St, San Francisco, CA 94133\",\n" +
                "      \"Lat\" : 37.802452,\n" +
                "      \"Long\" : -122.417227,\n" +
                "      \"Price\": 40500000,\n" +
                "      \"Bedroom\" : 6,\n" +
                "      \"Bathroom\" : 8,\n" +
                "      \"area\" : 9495,\n" +
                "      \"Like\": false\n" +
                "    },\n" +
                "  {\n" +
                "      \"uuid\" : \"2\",\n" +
                "      \"Address\" : \"2698 Pacific Ave, San Francisco, CA 94115\",\n" +
                "      \"Lat\" : 37.793122,\n" +
                "      \"Long\" : -122.439469,\n" +
                "      \"Price\": 26800000,\n" +
                "      \"Bedroom\" : 6,\n" +
                "      \"Bathroom\" : 8,\n" +
                "      \"area\" : 10734,\n" +
                "      \"Like\": false\n" +
                "    }\n" +
                "]\n" +
                "}\n";
    }

}
