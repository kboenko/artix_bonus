package com.artix.test.bonus.security.service;

import com.artix.test.bonus.model.CardUser;
import com.artix.test.bonus.repository.CardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardUserDetailsService implements UserDetailsService {

    @Autowired
    private CardUserRepository cardUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CardUser> cardUser = cardUserRepository.findByLogin(username);

        return cardUser
                .map(CardUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден в БД!", username)));
    }
}
