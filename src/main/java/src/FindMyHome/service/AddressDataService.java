package src.FindMyHome.service;

import src.FindMyHome.dao.PropertyRepo;
import src.FindMyHome.model.Address;
import src.FindMyHome.model.AddressData;
import src.FindMyHome.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AddressDataService {

    @Autowired
    PropertyRepo repo;

    public void save(AddressData data) {
        Address address = convertToAddress(data);
        repo.save(buildProperty(address));
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

    private Address convertToAddress(AddressData data) {
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

    private static int randomInt(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min);
    }

    private static double randomPrice() {
        int num = randomInt(500, 8500);
        return num * 1000;
    }

    private static int randomArea() {
        return randomInt(700, 9800);
    }

    private Property buildProperty(Address address) {
        int noBathroom = randomInt(2, 10);
        int noBedroom = randomInt(noBathroom + 1, noBathroom + 3);
        double utilityCost = (double)randomInt(80, 300);
        double maintenanceCost = (double)randomInt(100, 400);
        double price = randomPrice();
        return Property.builder()
                .noBathroom(noBathroom)
                .noBedroom(noBedroom)
                .utilityCost(utilityCost)
                .maintenanceCost(maintenanceCost)
                .price(price)
                .area(randomArea())
                .description("A Simple Description")
                .name("Best property in town")
                .address(address).build();
    }

}
