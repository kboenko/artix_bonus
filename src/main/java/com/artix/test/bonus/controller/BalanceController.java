package com.artix.test.bonus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/balance")
public class BalanceController {

    /**
     * Получение баланса по номеру карты
     */
    public void getByCardNumber() {

    }

    /**
     * Получение истории операций по номеру карты
     */
    public void getHistory() {

    }
}
