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
@Table(name = "agents", schema = "irish_home_listings")
public class Agent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "agentId", nullable = false)
    private int agentId;
    @Basic
    @Column(name = "firstName", nullable = true, length = 50)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = true, length = 45)
    private String lastName;
    @Basic
    @Column(name = "phone", nullable = true, length = 12)
    private String phone;
    @Basic
    @Column(name = "fax", nullable = true, length = 12)
    private String fax;
    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    private String password;
    @OneToMany(mappedBy = "agentsByAgentId")
    private Collection<Property> propertiesByAgentId;
    @OneToMany(mappedBy = "agentsByAgentId")
    private Collection<Vendor> vendorsByAgentId;


}
