package FLAGCamp.FindMyHome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Node> nodes;

    @ManyToOne
    @JsonIgnore
    private Viewer viewer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Set<String> getDays() {
        return days;
    }

    public void setDays(Set<String> days) {
        this.days = days;
    }

    public List<String> getTravelModes() {
        return travelModes;
    }

    public void setTravelModes(List<String> travelModes) {
        this.travelModes = travelModes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
}
