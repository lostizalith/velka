package com.github.lostizalith.velka.account.service;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.mapper.AccountMapper;
import com.github.lostizalith.velka.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AccountEntity createAccount(final AccountEntity account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AccountEntity> findAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public AccountEntity findAccountById(final UUID id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Could not find such account %s", id)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AccountEntity updateAccount(final UUID id, final AccountEntity accountEntity) {
        accountRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Could not find such account %s", id)));

        accountEntity.setId(id);

        return accountRepository.save(accountEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AccountEntity deleteAccount(final UUID id) {
        final AccountEntity deleted = accountRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Could not find such account %s", id)));

        accountRepository.deleteById(id);

        return deleted;
    }
}
