package com.github.lostizalith.velka.account.service;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountEntity createAccount(final AccountEntity account) {
        return accountRepository.save(account);
    }

    @Override
    public List<AccountEntity> findAccounts() {
        return accountRepository.findAll();
    }
}
