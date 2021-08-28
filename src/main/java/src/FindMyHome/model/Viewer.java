package src.FindMyHome.model;

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

//    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private Set<Route> commuteRoutes;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private Set<Property> favPropertyList;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private Set<Property> historyPropertyList;

}
