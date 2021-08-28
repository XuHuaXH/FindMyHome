package src.FindMyHome.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String emailId;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Route> commuteRoutes;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Property> favPropertyList;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Property> historyPropertyList;

    @OneToOne(cascade = CascadeType.ALL)
    private Viewer viewer;

    @OneToOne(cascade = CascadeType.ALL)
    private Manager manager;

}
