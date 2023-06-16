package com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes", schema = "irish_home_listings")
public class Note {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "userId", nullable = false)
    private int userId;
    @Basic
    @Column(name = "listingNum", nullable = false)
    private int listingNum;
    @Basic
    @Column(name = "note", nullable = false, length = -1)
    private String note;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "userId", referencedColumnName = "userID", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "userId", referencedColumnName = "userID", nullable = false)})
    private User usersByUserId;

    //don't delete this constructor, need for insert
    public Note(int userId, int listingNum, String note) {
        this.userId = userId;
        this.listingNum = listingNum;
        this.note = note;
    }
}
