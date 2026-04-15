package com.artix.test.bonus.repository;

import com.artix.test.bonus.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> getByNumber(String number);
}
