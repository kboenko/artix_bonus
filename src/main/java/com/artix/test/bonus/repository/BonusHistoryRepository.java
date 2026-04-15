package com.artix.test.bonus.repository;

import com.artix.test.bonus.model.BonusHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusHistoryRepository extends PagingAndSortingRepository<BonusHistory, Long> {


}
