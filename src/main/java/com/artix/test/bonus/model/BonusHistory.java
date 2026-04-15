package com.artix.test.bonus.model;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
public class BonusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Long amount;

    @Column
    OffsetDateTime operationDate;

    @ManyToOne
    Card card;
}
