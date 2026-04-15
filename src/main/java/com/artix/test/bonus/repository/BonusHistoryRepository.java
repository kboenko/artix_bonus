package com.artix.test.bonus.repository;

import com.artix.test.bonus.model.BonusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusHistoryRepository extends JpaRepository<BonusHistory, Long> {


}
