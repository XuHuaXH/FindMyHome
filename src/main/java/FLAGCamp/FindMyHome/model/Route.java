package FLAGCamp.FindMyHome.model;

import FLAGCamp.FindMyHome.Util.AddressCoordinateHelper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalTime departureTime;
    @ElementCollection
    private List<String> days;
    @ElementCollection
    private List<String> travelModes;

    @Embedded
    @OneToMany(cascade = CascadeType.ALL)
    private List<Node> nodes;

    @ManyToOne
    @JsonIgnore
    private User user;

    public void addNodeCoordinates() {
        for (Node node : this.nodes) {
            if (node.getName().equals("home")) {
                return;
            }
            SimpleAddress address = node.getSimpleAddress();
            String keyword = address.getAddress();
            double[] coordinates = AddressCoordinateHelper.AddressToLatLong(keyword);
            if (coordinates != null) {
                address.setLatitude(coordinates[0]);
                address.setLongitude(coordinates[1]);
                node.setSimpleAddress(address);
            }
        }
    }
}
