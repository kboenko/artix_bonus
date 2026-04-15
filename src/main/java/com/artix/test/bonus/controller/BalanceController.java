package com.artix.test.bonus.controller;

import com.artix.test.bonus.model.BonusHistory;
import com.artix.test.bonus.service.BonusHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/balance")
@Slf4j
@RequiredArgsConstructor
public class BalanceController {

    private final BonusHistoryService bonusHistoryService;

    /**
     * Получение истории операций по номеру карты
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Page<BonusHistory> getHistory(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "operationDate") String sortBy,
                                                       @RequestParam String cardNumber) {
        log.info("Запрос баланса по номеру карты {}", cardNumber);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return bonusHistoryService.getHistoryByCardNumber(cardNumber, pageable);
    }
}
