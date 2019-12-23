package FLAGCamp.FindMyHome;

import FLAGCamp.FindMyHome.model.Node;
import FLAGCamp.FindMyHome.model.Route;
import FLAGCamp.FindMyHome.model.TravelTimeResponse;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DistanceMatrixClient {
    private static final String API_KEY = "AIzaSyDVhmSs_lIao49lTSN2LMOXdKQrkU5xHGo";
    private static final String TIME_ZONE_ID = "America/Los_Angeles";
    private Map<String, DayOfWeek> dayOfWeekMap;
    private GeoApiContext context;


    public DistanceMatrixClient() {
        this.context = new GeoApiContext.Builder().apiKey(API_KEY).build();

        dayOfWeekMap = new HashMap<>();
        dayOfWeekMap.put("Monday", DayOfWeek.MONDAY);
        dayOfWeekMap.put("Tuesday", DayOfWeek.TUESDAY);
        dayOfWeekMap.put("Wednesday", DayOfWeek.WEDNESDAY);
        dayOfWeekMap.put("Thursday", DayOfWeek.THURSDAY);
        dayOfWeekMap.put("Friday", DayOfWeek.FRIDAY);
        dayOfWeekMap.put("Saturday", DayOfWeek.SATURDAY);
        dayOfWeekMap.put("Sunday", DayOfWeek.SUNDAY);
    }

    private static LocalDate findNext(DayOfWeek dayOfWeek) {
        LocalDate ld = LocalDate.now();
        return ld.with(TemporalAdjusters.next(dayOfWeek));
    }

    private String toReadableTime(long numOfSeconds) {
        final long SECONDS_IN_HOUR = 3600;
        final long SECONDS_IN_MINUTE = 60;
        StringBuilder bd = new StringBuilder();
        long numOfHours = numOfSeconds/SECONDS_IN_HOUR;
        if (numOfHours > 0) {
            bd.append(numOfHours).append(' ').append('h').append(' ');
        }
        numOfSeconds -= numOfHours * SECONDS_IN_HOUR;
        long numOfMinutes = numOfSeconds/SECONDS_IN_MINUTE;
        bd.append(numOfMinutes).append(' ').append("min");
        return bd.toString();
    }

    private long getAverage(List<Long> nums) {
        long average = 0;
        for (int i = 0; i < nums.size(); ++i) {
            average = (average * i + nums.get(i)) / (i + 1);
        }
        return average;
    }


    public TravelTimeResponse estimateTravelTime(Route route, Node home) {

        List<Node> nodes = new ArrayList<>();
        List<String> optimisticTimeAvg = new ArrayList<>();
        List<String> pessimisticTimeAvg = new ArrayList<>();
        List<BigDecimal> fare = new ArrayList<>();
        long lastOptimisticDuration = 0;
        long lastPessimisticDuration = 0;

        int numOfNodes = route.getNodes().size();
        for (int i = 0; i < numOfNodes - 1; ++i) {

            // set up origins and destinations
            Node node = route.getNodes().get(i);
            Node originNode = node.getName().equals("home") ? home : node;
            nodes.add(originNode);
            node = route.getNodes().get(i + 1);
            Node destinationNode = node.getName().equals("home") ? home : node;
            LatLng originLatLng = new LatLng(originNode.getAddress().getLatitude(), originNode.getAddress().getLongitude());
            LatLng destinationLatLng = new LatLng(destinationNode.getAddress().getLatitude(), destinationNode.getAddress().getLongitude());


            //set up travel_mode
            String travelModeStr = route.getTravelModes().get(i);
            TravelMode travelMode;
            switch (travelModeStr) {
                case "transit":
                    travelMode = TravelMode.TRANSIT;
                    break;
                case "bicycling":
                    travelMode = TravelMode.BICYCLING;
                    break;
                case "walking":
                    travelMode = TravelMode.WALKING;
                    break;
                default:
                    travelMode = TravelMode.DRIVING;
            }

            // set up units
            // TODO: make this a choice later?
            Unit unit = Unit.METRIC;

            // set up and iterate over different departure_time instants
            // then calculate the average result
            List<Long> optimisticTime = new ArrayList<>(); // temporary list for computing average
            List<Long> pessimisticTime = new ArrayList<>(); // temporary list for computing average
            for (String day : route.getDays()) {
                LocalDate nextDate = findNext(dayOfWeekMap.get(day));
                LocalDateTime dateTime = nextDate.atTime(route.getDepartureTime());
                Instant optimisticInstant = dateTime.atZone(ZoneId.of(TIME_ZONE_ID)).toInstant().plusSeconds(lastOptimisticDuration);
                Instant pessimisticInstant = dateTime.atZone(ZoneId.of(TIME_ZONE_ID)).toInstant().plusSeconds(lastPessimisticDuration);

                List<TrafficModel> trafficModels = new ArrayList<>();
                trafficModels.add(TrafficModel.OPTIMISTIC);
                trafficModels.add(TrafficModel.PESSIMISTIC);

                for (TrafficModel trafficModel : trafficModels) {

                    // assemble the request to Distance Matrix API
                    DistanceMatrixApiRequest req = new DistanceMatrixApiRequest(this.context);
                    req.origins(originLatLng)
                            .destinations(destinationLatLng)
                            .mode(travelMode)
                            .trafficModel(trafficModel)
                            .units(unit);
                    if (trafficModel == TrafficModel.OPTIMISTIC) {
                        req.departureTime(optimisticInstant);
                    } else {
                        req.departureTime(pessimisticInstant);
                    }

                    try {
                        DistanceMatrix matrix = req.await();
                        for (DistanceMatrixRow row : matrix.rows) {
                            for (DistanceMatrixElement element : row.elements) {
                                Long duration = element.durationInTraffic.inSeconds;
                                if (trafficModel == TrafficModel.OPTIMISTIC) {
                                    optimisticTime.add(duration);
                                    fare.add((element.fare == null) ? new BigDecimal(0) : element.fare.value);
                                } else {
                                    pessimisticTime.add(duration);
                                }
                            }
                        }
                    } catch (ApiException | InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // compute the average duration in traffic
            optimisticTimeAvg.add(toReadableTime(getAverage(optimisticTime)));
            pessimisticTimeAvg.add(toReadableTime(getAverage(pessimisticTime)));

            // update the duration of the last trip
            lastOptimisticDuration = getAverage(optimisticTime);
            lastPessimisticDuration = getAverage(pessimisticTime);
        }
        this.context.shutdown();

        // add in the last node of the route
        Node lastNode = route.getNodes().get(numOfNodes - 1);
        lastNode = lastNode.getName().equals("home") ? home : lastNode;
        nodes.add(lastNode);

        // assemble the response object
        return TravelTimeResponse.builder()
                .nodes(nodes)
                .travelModes(route.getTravelModes())
                .optimisticTime(optimisticTimeAvg)
                .pessimisticTime(pessimisticTimeAvg)
                .fare(fare)
                .build();
    }

}
