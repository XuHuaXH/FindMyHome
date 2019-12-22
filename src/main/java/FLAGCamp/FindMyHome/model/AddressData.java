package FLAGCamp.FindMyHome.model;

import lombok.Data;

@Data
public class AddressData {

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private Coordinate coordinates;

}
