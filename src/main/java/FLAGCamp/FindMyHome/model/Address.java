package FLAGCamp.FindMyHome.model;

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
    private int id;
    private int streetNo;
    private String roadName;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private double latitude;
    private double longitude;
    private long zone;

    @OneToOne
    @JsonIgnore
    private Node node;

    @OneToOne
    @JsonIgnore
    private Property property;

}
