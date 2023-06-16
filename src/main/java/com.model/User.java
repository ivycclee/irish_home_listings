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
@Table(name = "users", schema = "irish_home_listings")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "firstName", nullable = false, length = 64)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = false, length = 64)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = false, length = 64)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 64)
    private String password;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<Note> notesByUserId;

    // do not delete below constructor
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}
