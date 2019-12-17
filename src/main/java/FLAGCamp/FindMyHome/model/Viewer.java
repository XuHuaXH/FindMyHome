package FLAGCamp.FindMyHome.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    public Set<Route> getCommuteRoutes() {
        return commuteRoutes;
    }

    public void setCommuteRoutes(Set<Route> commuteRoutes) {
        this.commuteRoutes = commuteRoutes;
    }

    public Set<Property> getFavPropertyList() {
        return favPropertyList;
    }

    public void setFavPropertyList(Set<Property> favPropertyList) {
        this.favPropertyList = favPropertyList;
    }

    public Set<Property> getHistoryPropertyList() {
        return historyPropertyList;
    }

    public void setHistoryPropertyList(Set<Property> historyPropertyList) {
        this.historyPropertyList = historyPropertyList;
    }
}
