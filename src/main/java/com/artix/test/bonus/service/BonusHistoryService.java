package com.artix.test.bonus.service;

import com.artix.test.bonus.model.BonusHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BonusHistoryService {

    Page<BonusHistory> getHistoryByCardNumber(String cardNumber, Pageable pageable);
}
