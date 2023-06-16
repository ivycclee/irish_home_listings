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
@Table(name = "propertytypes", schema = "irish_home_listings")
public class PropertyType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "typeId", nullable = false)
    private int typeId;
    @Basic
    @Column(name = "pType", nullable = true, length = 20)
    private String pType;
    @OneToMany(mappedBy = "propertytypesByTypeId")
    private Collection<Property> propertiesByTypeId;

}
