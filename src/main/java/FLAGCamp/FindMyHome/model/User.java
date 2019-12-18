package FLAGCamp.FindMyHome.model;

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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String emailId;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Viewer viewer;

    @OneToOne(cascade = CascadeType.ALL)
    private Manager manager;

}
