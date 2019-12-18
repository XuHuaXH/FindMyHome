package FLAGCamp.FindMyHome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String departureTime;
    @ElementCollection
    private Set<String> days;
    @ElementCollection
    private List<String> travelModes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Node> nodes;

    @ManyToOne
    @JsonIgnore
    private Viewer viewer;

}
