package FLAGCamp.FindMyHome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double utilityCost;
    private double maintenanceCost;
    private String name;
    private String category;
    private double price;
    private String description;
    private String area;
    private int noBedroom;
    private int noBathroom;

    @ManyToOne
    @JsonIgnore
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "viewerId")
    private Viewer viewer;

    @ManyToOne
    @JoinColumn(name = "favById")
    private Viewer favBy;

    @OneToOne(mappedBy = "property")
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUtilityCost() {
        return utilityCost;
    }

    public void setUtilityCost(double utilityCost) {
        this.utilityCost = utilityCost;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(double maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNoBedroom() {
        return noBedroom;
    }

    public void setNoBedroom(int noBedroom) {
        this.noBedroom = noBedroom;
    }

    public int getNoBathroom() {
        return noBathroom;
    }

    public void setNoBathroom(int noBathroom) {
        this.noBathroom = noBathroom;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
