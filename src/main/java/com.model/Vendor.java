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
@Table(name = "vendors", schema = "irish_home_listings")
public class Vendor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vendorID", nullable = false)
    private int vendorId;
    @Basic
    @Column(name = "agentID", nullable = false)
    private int agentId;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @OneToMany(mappedBy = "vendorsByVendorId")
    private Collection<Property> propertiesByVendorId;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "agentID", referencedColumnName = "agentId", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "agentID", referencedColumnName = "agentId", nullable = false)})
    private Agent agentsByAgentId;
    @Basic
    @Column(name = "phone", nullable = true)
    private String phone;

}
