package com.artix.test.bonus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bonus")
@Slf4j
public class BonusController {

    /**
     * Начисление бонусов
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add() {
        log.info("Начисление бонусов");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Списание бонусов
     */
    @RequestMapping(value = "/off", method = RequestMethod.POST)
    public ResponseEntity<?> writeOff() {
        log.info("Списание бонусов");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Возврат списанных бонусов
     */
    @RequestMapping(value = "/back", method = RequestMethod.POST)
    public ResponseEntity<?> returnBack() {
        log.info("Возврат списанных бонусов");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
