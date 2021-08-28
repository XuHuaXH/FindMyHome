package src.FindMyHome.service;

import src.FindMyHome.Util.AddressCoordinateHelper;
import src.FindMyHome.Util.GeoLocation;
import src.FindMyHome.dao.PropertyRepo;
import src.FindMyHome.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SearchService {

    @Autowired PropertyRepo repo;

    private static final double KILOMETER_IN_A_MILE = 1.60934;
    private double milesToKilometers(double miles) {
        return miles * KILOMETER_IN_A_MILE;
    }

    public Set<Property> searchProperty(String keyword, int range) {
        double[] coordinates = AddressCoordinateHelper.AddressToLatLong(keyword);
        if (coordinates == null || coordinates.length < 2) {
            return new HashSet<>();
        }

        GeoLocation location = GeoLocation.fromDegrees(coordinates[0], coordinates[1]);
        GeoLocation[] boundingBox = location.boundingCoordinates(milesToKilometers(range), GeoLocation.EARTH_RADIUS_IN_KM);

        GeoLocation min = boundingBox[0];
        GeoLocation max = boundingBox[1];
        double minLat = min.getLatitudeInDegrees();
        double minLng = min.getLongitudeInDegrees();
        double maxLat = max.getLatitudeInDegrees();
        double maxLng = max.getLongitudeInDegrees();
        if (min.getLongitudeInDegrees() <= max.getLongitudeInDegrees()) {
            // CASE A
            return repo.selectPropertyCaseA(minLat, maxLat, minLng, maxLng);
        } else {
            // CASE B
            return repo.selectPropertyCaseB(minLat, maxLat, minLng, maxLng);
        }

    }

    public Property getPropertyDetails(long id) {
        if (!repo.findById(id).isPresent()) {
            return null;
        }
        return repo.findById(id).get();
    }


}
