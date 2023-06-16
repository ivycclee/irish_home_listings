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
@Table(name = "styles", schema = "irish_home_listings")
public class Style {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "styleId", nullable = false)
    private int styleId;
    @Basic
    @Column(name = "pStyle", nullable = true, length = 20)
    private String pStyle;
    @OneToMany(mappedBy = "stylesByStyleId")
    private Collection<Property> propertiesByStyleId;

}
