package com.artix.test.bonus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
