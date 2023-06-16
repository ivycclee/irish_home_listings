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
@Table(name = "properties", schema = "irish_home_listings")
public class Property {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "bathrooms", nullable = true, precision = 0)
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
    @ManyToOne
    @JoinColumn(name = "styleId", referencedColumnName = "styleId", nullable = false, insertable=false, updatable = false)
    private Style stylesByStyleId;
    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "typeId", nullable = false, insertable=false, updatable = false)
    private PropertyType propertytypesByTypeId;
    @ManyToOne
    @JoinColumn(name = "garageId", referencedColumnName = "garageId", nullable = false, insertable=false, updatable = false)
    private GarageType garagetypesByGarageId;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "agentId", referencedColumnName = "agentId", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "agentId", referencedColumnName = "agentId", nullable = false)})
    private Agent agentsByAgentId;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "vendorID", referencedColumnName = "vendorID", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "vendorID", referencedColumnName = "vendorID", nullable = false)})
    private Vendor vendorsByVendorId;
    @ManyToOne
    @JoinColumn(name = "listingNum", referencedColumnName = "listingNum", nullable = false, insertable=false, updatable = false)
    private Note notesByListingNum;

    public Property(String street, String city, Integer listingNum, int styleId, int typeId, int bedrooms, int bathrooms, Integer squarefeet, String berRating, String description, String lotsize, int garagesize, int garageId, int agentId, Double price, Date dateAdded, int vendorId) {
        this.street = street;
        this.city = city;
        this.listingNum = listingNum;
        this.styleId = styleId;
        this.typeId = typeId;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squarefeet = squarefeet;
        this.berRating = berRating;
        this.description = description;
        this.lotsize = lotsize;
        this.garagesize = garagesize;
        this.garageId = garageId;
        this.agentId = agentId;
        this.price = price;
        this.dateAdded = dateAdded;
        this.vendorId = vendorId;
    }

}
