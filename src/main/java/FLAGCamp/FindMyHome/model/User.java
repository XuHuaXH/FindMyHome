package FLAGCamp.FindMyHome.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String emailId;

    private String password;

    @OneToOne(mappedBy = "user")
    private Viewer viewer;

    @OneToOne(mappedBy = "user")
    private Manager manager;


}
