package com.stomatology.service;

import com.stomatology.entity.enums.RoleName;
import com.stomatology.entity.user.Account;
import com.stomatology.repository.AccountRepository;
import com.stomatology.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account create(String email, String password, RoleName role) {
        Account account = new Account(email, passwordEncoder.encode(password), roleRepository.findByName(role));
        return accountRepository.save(account);
    }
}
