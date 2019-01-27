package com.github.lostizalith.velka.account.service;

import com.github.lostizalith.velka.account.entity.AccountEntity;

import java.util.List;

public interface AccountService {

    AccountEntity createAccount(AccountEntity account);

    List<AccountEntity> findAccounts();
}
