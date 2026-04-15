package com.artix.test.bonus.controller;

import com.artix.test.bonus.dto.response.GetBalanceResponse;
import com.artix.test.bonus.service.BonusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bonus")
@Slf4j
@RequiredArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    /**
     * Начисление бонусов
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(String cardNumber, Long amount) {
        log.info("Начисление бонусов");
        bonusService.addBonus(cardNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Списание бонусов
     */
    @RequestMapping(value = "/off", method = RequestMethod.POST)
    public ResponseEntity<?> writeOff(String cardNumber, Long amount) {
        log.info("Списание бонусов");
        bonusService.writeOffBonus(cardNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Возврат списанных бонусов. Будем считать, что в общем случае логика возврата такова:
     * человек списал некоторое количество при покупке некоторого товара, затем через какое-то время товар вернул.
     * За это время он мог еще что-то покупать и использовать бонусы. Поэтому задача сводится к пополнению данной карты
     * на N потраченных в тот раз бонусов (т.е. операция аналогична начислению).
     * Если же речь идет о возврате начисленных бонусов, то по той же причине операция аналогична списанию - с той лишь
     * разницей, что при нехватке средств мы не бросаем эксепшен, а сохраняем отрицательное количество бонусов
     * (значит, он должник, и следующее начисление будет делаться уже с учетом этого долга).
     */
    @RequestMapping(value = "/back", method = RequestMethod.POST)
    public ResponseEntity<?> returnBack(String cardNumber, Long amount, boolean isNeedAdd) {
        log.info("Возврат списанных бонусов");
        bonusService.returnBonus(cardNumber, amount, isNeedAdd);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Получение баланса бонусов на карте
     * @return
     */
    @RequestMapping(value = "/balance/{cardNumber}", method = RequestMethod.GET)
    public @ResponseBody GetBalanceResponse getBalance(@PathVariable String cardNumber) {
        log.info("Запрос баланса по номеру карты {}",cardNumber);
        return bonusService.getBalanceByCardNumber(cardNumber);
    }
}
