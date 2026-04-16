package com.artix.test.bonus.controller;

import com.artix.test.bonus.dto.response.GetBalanceResponse;
import com.artix.test.bonus.model.BonusHistory;
import com.artix.test.bonus.service.BonusHistoryService;
import com.artix.test.bonus.service.BonusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/balance")
@Slf4j
@RequiredArgsConstructor
public class BalanceController {

    private final BonusHistoryService bonusHistoryService;
    private final BonusService bonusService;

    /**
     * Получение истории операций по номеру карты
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Page<BonusHistory> getHistory(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "operationDate") String sortBy,
                                                       @RequestParam String cardNumber) {
        log.info("Запрос баланса по номеру карты {}", cardNumber);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return bonusHistoryService.getHistoryByCardNumber(cardNumber, pageable);
    }

    /**
     * Получение баланса бонусов на карте
     * @return
     */
    @RequestMapping(value = "/{cardNumber}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public @ResponseBody
    GetBalanceResponse getBalance(@PathVariable String cardNumber) {
        log.info("Запрос баланса по номеру карты {}",cardNumber);
        return bonusService.getBalanceByCardNumber(cardNumber);
    }
}
