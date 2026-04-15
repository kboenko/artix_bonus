package com.artix.test.bonus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 20)
    String number;

    @OneToOne
    CardUser cardUser;
}
