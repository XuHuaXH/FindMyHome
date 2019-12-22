package FLAGCamp.FindMyHome.service;

import FLAGCamp.FindMyHome.dao.AddressRepo;
import FLAGCamp.FindMyHome.model.Address;
import FLAGCamp.FindMyHome.model.AddressData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDataService {
    @Autowired
    AddressRepo repo;

    public void save(AddressData data) {
        repo.save(convertToAddress(data));
    }

    private boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Address convertToAddress(AddressData data) {
        String[] arr = data.getAddress1().split(" ", 2);
        Integer streetNo = isNumeric(arr[0]) ? Integer.parseInt(arr[0]) : null;
        String roadName = streetNo == null ? data.getAddress1() : arr[1];

        String country = "US";
        return Address.builder()
                .streetNo(streetNo)
                .roadName(roadName)
                .city(data.getCity())
                .state(data.getState())
                .country(country)
                .zipCode(data.getPostalCode())
                .latitude(data.getCoordinates().getLat())
                .longitude(data.getCoordinates().getLng())
                .build();
    }

}
