package com.artix.test.bonus.service.impl;

import com.artix.test.bonus.dto.response.GetBalanceResponse;
import com.artix.test.bonus.exception.BonusNotFoundException;
import com.artix.test.bonus.exception.CardNotFoundException;
import com.artix.test.bonus.exception.NotEnoughBonusException;
import com.artix.test.bonus.model.Bonus;
import com.artix.test.bonus.model.BonusHistory;
import com.artix.test.bonus.model.Card;
import com.artix.test.bonus.repository.BonusHistoryRepository;
import com.artix.test.bonus.repository.BonusRepository;
import com.artix.test.bonus.repository.CardRepository;
import com.artix.test.bonus.service.BonusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;
    private final CardRepository cardRepository;
    private final BonusHistoryRepository bonusHistoryRepository;

    @Override
    public GetBalanceResponse getBalanceByCardNumber(String cardNumber) {
        Bonus bonus = bonusRepository.getBonusByCardNumber(cardNumber)
                .orElse(new Bonus());

        return GetBalanceResponse.builder()
                .cardNumber(cardNumber)
                .balance(bonus.getAmount())
                .build();
    }

    @Override
    @Transactional
    public void addBonus(String cardNumber, Long amount) {
        bonusRepository.getBonusByCardNumber(cardNumber)
                .ifPresentOrElse(b -> increaseAndSave(b, amount),
                        () -> createNewBonus(cardNumber, amount));
    }

    @Override
    @Transactional
    public void writeOffBonus(String cardNumber, Long amount) {
        bonusRepository.getBonusByCardNumber(cardNumber)
                .ifPresentOrElse(b -> decreaseAndSave(b, amount, false),
                        () -> throwBonusNotFoundException(String.format("Карта %s пуста, нечего списывать", cardNumber)));
    }

    @Override
    @Transactional
    public void returnBonus(String cardNumber, Long amount, boolean isNeedAdd) {
        if (isNeedAdd) {
            addBonus(cardNumber, amount);
        } else {
            subtractBonus(cardNumber, amount);
        }
    }

    private void subtractBonus(String cardNumber, Long amount) {
        bonusRepository.getBonusByCardNumber(cardNumber)
                .ifPresentOrElse(b -> decreaseAndSave(b, amount, true),
                        () -> throwBonusNotFoundException(String.format("На карту %s еще ни разу не начислялись бонусы. "
                                + "Свяжитеть с техподдержкой, чтобы выяснить причину списания", cardNumber)));
    }

    private synchronized void increaseAndSave(Bonus bonus, Long amount) {
        AtomicLong currentAmount = new AtomicLong(bonus.getAmount() + amount);
        bonus.setAmount(currentAmount.get());
        OffsetDateTime operationDate = OffsetDateTime.now();
        bonus.setOperationDate(operationDate);
        bonusRepository.save(bonus);

        // возможно, имело бы смысл вынести наполнение исторической таблицы в саму СУБД
        // (к примеру, написать для этого функцию и вызывать ее по триггеру on create или on update на таблице bonus)
        BonusHistory history = BonusHistory.builder()
                .card(bonus.getCard())
                .amount(currentAmount.get())
                .operationDate(operationDate)
                .build();
        bonusHistoryRepository.save(history);
    }

    private synchronized void decreaseAndSave(Bonus bonus, Long amount, boolean isDeptPossible) {
        AtomicLong currentAmount = new AtomicLong(bonus.getAmount() - amount);

        if (currentAmount.get() < 0 && Boolean.FALSE.equals(isDeptPossible)) {
            throw new NotEnoughBonusException("Недостаточно бонусов для списания");
        }

        bonus.setAmount(currentAmount.get());
        OffsetDateTime operationDate = OffsetDateTime.now();
        bonus.setOperationDate(operationDate);
        bonusRepository.save(bonus);

        // возможно, имело бы смысл вынести наполнение исторической таблицы в саму СУБД
        // (к примеру, написать для этого функцию и вызывать ее по триггеру on create или on update на таблице bonus)
        BonusHistory history = BonusHistory.builder()
                .card(bonus.getCard())
                .amount(currentAmount.get())
                .operationDate(operationDate)
                .build();
        bonusHistoryRepository.save(history);
    }

    private void createNewBonus(String cardNumber, Long amount) {
        Card card = cardRepository.getByNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException(String.format("Не найдена карта с номером %s", cardNumber)));

        OffsetDateTime operationDate = OffsetDateTime.now();

        Bonus bonus =  Bonus.builder()
                .card(card)
                .amount(amount)
                .operationDate(operationDate)
                .build();

        bonusRepository.save(bonus);

        // возможно, имело бы смысл вынести наполнение исторической таблицы в саму СУБД
        // (к примеру, написать для этого функцию и вызывать ее по триггеру on create или on update на таблице bonus)
        BonusHistory history = BonusHistory.builder()
                .card(bonus.getCard())
                .amount(amount)
                .operationDate(operationDate)
                .build();
        bonusHistoryRepository.save(history);
    }

    private void throwBonusNotFoundException(String message) {
        throw new BonusNotFoundException(message);
    }

}
