package com.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "garagetypes", schema = "irish_home_listings")
public class GarageType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "garageId", nullable = false)
    private int garageId;
    @Basic
    @Column(name = "gType", nullable = true, length = 20)
    private String gType;
    @OneToMany(mappedBy = "garagetypesByGarageId")
    private Collection<Property> propertiesByGarageId;

}
