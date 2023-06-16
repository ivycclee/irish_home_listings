package com.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Archive {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "street", nullable = false, length = 50)
    private String street;
    @Basic
    @Column(name = "city", nullable = false, length = 25)
    private String city;
    @Basic
    @Column(name = "listingNum", nullable = false)
    private Integer listingNum;
    @Basic
    @Column(name = "styleId", nullable = false)
    private int styleId;
    @Basic
    @Column(name = "typeId", nullable = false)
    private int typeId;
    @Basic
    @Column(name = "bedrooms", nullable = true)
    private int bedrooms;
    @Basic
    @Column(name = "bathrooms", nullable = true)
    private int bathrooms;
    @Basic
    @Column(name = "squarefeet", nullable = true)
    private Integer squarefeet;
    @Basic
    @Column(name = "berRating", nullable = false, length = 2)
    private String berRating;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "lotsize", nullable = true, length = 25)
    private String lotsize;
    @Basic
    @Column(name = "garagesize", nullable = true)
    private int garagesize;
    @Basic
    @Column(name = "garageId", nullable = false)
    private int garageId;
    @Basic
    @Column(name = "agentId", nullable = false)
    private int agentId;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private Double price;
    @Basic
    @Column(name = "dateAdded", nullable = false)
    private Date dateAdded;
    @Basic
    @Column(name = "vendorID", nullable = false)
    private int vendorId;

}
