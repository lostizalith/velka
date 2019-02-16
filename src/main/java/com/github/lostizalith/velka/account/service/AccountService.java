package com.github.lostizalith.velka.account.service;

import com.github.lostizalith.velka.account.entity.AccountEntity;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountEntity createAccount(AccountEntity account);

    List<AccountEntity> findAccounts();

    AccountEntity findAccountById(final UUID id);

    AccountEntity updateAccount(UUID id, AccountEntity accountEntity);

    AccountEntity deleteAccount(UUID id);
}
