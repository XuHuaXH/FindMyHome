package FLAGCamp.FindMyHome;

import FLAGCamp.FindMyHome.dao.RouteRepo;
import FLAGCamp.FindMyHome.model.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RouteDataLoader implements CommandLineRunner {
    private final RouteRepo routeRepo;

    public RouteDataLoader(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    public void run(String... strings){
        Address A1 = Address.builder().streetNo(950)
                .roadName("Lombard St")
                .city("San Francisco")
                .state("CA")
                .zipCode("94133")
                .country("US")
                .latitude(37.802452)
                .longitude(-122.417227)
                .build();


        Set<String> days = new HashSet<>();
        days.add("Monday");
        days.add("Wednesday");
        days.add("Friday");
        List<String> travelModes = new ArrayList<>();
        travelModes.add("driving");
        List<Node> nodes = new ArrayList<>();
        Node home = Node.builder().name("home").build();
        Node node = Node.builder().name("work").address(A1).build();
        nodes.add(home);
        nodes.add(node);
        Route route = Route.builder().name("preloaded route").departureTime(LocalTime.of(7, 30)).days(days).travelModes(travelModes).nodes(nodes).build();
        routeRepo.save(route);
    }
}
