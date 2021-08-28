package src.FindMyHome.model;

import lombok.*;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double utilityCost;
    private double maintenanceCost;
    private String name;
    private String category;
    private double price;
    private String description;
    private int area;
    private int noBedroom;
    private int noBathroom;
    private long zone;

    @ManyToOne
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "favById")
    private User favBy;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

}
