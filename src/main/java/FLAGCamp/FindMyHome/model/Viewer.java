package FLAGCamp.FindMyHome.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "viewer")
public class Viewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Route> commuteRoutes;

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Property> favPropertyList;

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Property> historyPropertyList;

}
