package com.github.lostizalith.velka.account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    List<AccountEntity> getRandomList();

    AccountEntity getRandomAccount(UUID id);
}
