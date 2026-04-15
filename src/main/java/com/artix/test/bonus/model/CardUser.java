package com.artix.test.bonus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CardUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String firstName;

    @Column
    String secondName;

    @Column
    String lastName;

    @Column
    String role;
}
