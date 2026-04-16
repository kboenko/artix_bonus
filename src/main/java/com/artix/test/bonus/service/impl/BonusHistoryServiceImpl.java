package com.artix.test.bonus.service.impl;

import com.artix.test.bonus.model.BonusHistory;
import com.artix.test.bonus.repository.BonusHistoryRepository;
import com.artix.test.bonus.service.BonusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BonusHistoryServiceImpl implements BonusHistoryService {

    private final BonusHistoryRepository bonusHistoryRepository;

    @Override
    public Page<BonusHistory> getHistoryByCardNumber(String cardNumber, Pageable pageable) {

        return bonusHistoryRepository.getByCardNumber(cardNumber, pageable);
    }
}
