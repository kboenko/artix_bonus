package com.artix.test.bonus.service.impl;

import com.artix.test.bonus.model.BonusHistory;
import com.artix.test.bonus.service.BonusHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusHistoryServiceImpl implements BonusHistoryService {

    @Override
    public Page<BonusHistory> getHistoryByCardNumber(String cardNumber, Pageable pageable) {

        return null;
    }
}
