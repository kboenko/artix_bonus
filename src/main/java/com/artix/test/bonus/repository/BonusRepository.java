package com.artix.test.bonus.repository;

import com.artix.test.bonus.model.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {

    @Query("SELECT b.id, b.amount from Bonus b JOIN b.card c where c.number like :number")
    Optional<Bonus> getBonusByCardNumber(String number);
}
