package src.FindMyHome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private Integer streetNo;
    private String roadName;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private double latitude;
    private double longitude;
    private long zone;

//    @OneToOne
//    @JsonIgnore
//    private Node node;

    @OneToOne
    @JsonIgnore
    private Property property;

    public String toOneLineAddress() {
        StringBuilder bd = new StringBuilder();
        bd.append(streetNo).append(" ")
                .append(roadName).append(", ")
                .append(city).append(", ")
                .append(state).append(" ")
                .append(zipCode);

        return bd.toString();
    }

    public SimpleAddress toSimpleAddress() {
        return SimpleAddress.builder()
                .address(this.toOneLineAddress())
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }

}
