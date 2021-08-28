package src.FindMyHome;

import src.FindMyHome.model.Address;
import src.FindMyHome.model.Property;
import src.FindMyHome.dao.PropertyRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final PropertyRepo repo;

    public DataLoader(PropertyRepo repo){this.repo = repo;}

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

        Property P1 = Property.builder().area(9495)
                .description("A simple description")
                .noBathroom(8)
                .noBedroom(6)
                .price(40500000)
                .address(A1)
                .build();

        repo.save(P1);


    }


}
