package com.artix.test.bonus.repository;

import com.artix.test.bonus.model.BonusHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusHistoryRepository extends PagingAndSortingRepository<BonusHistory, Long> {

    @Query("SELECT bh.id, bh.amount, bh.operationDate from BonusHistory bh JOIN bh.card c where c.number like :number")
    Page<BonusHistory> getByCardNumber(String number, Pageable pageable);
}
