package com.artix.test.bonus.service;

import com.artix.test.bonus.dto.response.GetBalanceResponse;

public interface BonusService {

    GetBalanceResponse getBalanceByCardNumber(String cardNumber);

    void addBonus(String cardNumber, Long amount);

    void writeOffBonus(String cardNumber, Long amount);

    void returnBonus(String cardNumber, Long amount, boolean isNeedAdd);
}
