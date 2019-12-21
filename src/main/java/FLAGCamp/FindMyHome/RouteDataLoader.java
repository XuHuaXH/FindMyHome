package FLAGCamp.FindMyHome;

import FLAGCamp.FindMyHome.dao.RouteRepo;
import FLAGCamp.FindMyHome.model.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RouteDataLoader implements CommandLineRunner {
    private final RouteRepo routeRepo;

    public RouteDataLoader(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    public void run(String... strings){
        Address A2 = Address.builder().streetNo(1600)
                .roadName("Amphitheatre Parkway")
                .city("Mountain View")
                .state("CA")
                .zipCode("94043")
                .country("US")
                .latitude(37.422)
                .longitude(-122.084)
                .build();


        List<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Wednesday");
        days.add("Friday");
        List<String> travelModes = new ArrayList<>();
        travelModes.add("driving");
        List<Node> nodes = new ArrayList<>();
        Node home = Node.builder().name("home").build();
        Node node = Node.builder().name("work").address(A2).build();
        nodes.add(home);
        nodes.add(node);
        Route route = Route.builder().name("preloaded route").departureTime(LocalTime.of(7, 30)).days(days).travelModes(travelModes).nodes(nodes).build();
        routeRepo.save(route);
    }
}
