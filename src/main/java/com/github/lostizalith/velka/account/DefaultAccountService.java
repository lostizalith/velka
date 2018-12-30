package com.github.lostizalith.velka.account;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultAccountService implements AccountService {

    @Override
    public List<AccountEntity> getRandomList() {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setDisplayName("Prior Bank");
        accountEntity.setShortDescription("My super debit card");
        accountEntity.setAccountType("DEBIT CARD");
        accountEntity.setCurrentBalance("100.00");
        accountEntity.setCurrency("BYN");

        final AccountEntity anotherAccountEntity = new AccountEntity();
        anotherAccountEntity.setDisplayName("Cash savings EUR");
        anotherAccountEntity.setShortDescription("My cash saving in eur");
        anotherAccountEntity.setAccountType("CASH");
        anotherAccountEntity.setCurrentBalance("400.00");
        anotherAccountEntity.setCurrency("EUR");

        return Arrays.asList(accountEntity, anotherAccountEntity);
    }
}
