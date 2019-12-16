package FLAGCamp.FindMyHome.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Property> ownedPropertyList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Property> getOwnedPropertyList() {
        return ownedPropertyList;
    }

    public void setOwnedPropertyList(List<Property> ownedPropertyList) {
        this.ownedPropertyList = ownedPropertyList;
    }
}
