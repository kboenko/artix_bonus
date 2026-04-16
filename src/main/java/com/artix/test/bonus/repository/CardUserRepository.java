package com.artix.test.bonus.repository;

import com.artix.test.bonus.model.CardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardUserRepository extends JpaRepository<CardUser, Long> {
    Optional<CardUser> findByLogin(String login);
}
