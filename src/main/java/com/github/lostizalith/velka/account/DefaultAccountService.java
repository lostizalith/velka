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
        accountEntity.setAccountType(AccountType.DEBIT_CARD);
        accountEntity.setCurrentBalance(100.10);
        accountEntity.setCurrency(AccountCurrency.BYN);

        final AccountEntity anotherAccountEntity = new AccountEntity();
        anotherAccountEntity.setDisplayName("Cash savings EUR");
        anotherAccountEntity.setShortDescription("My cash saving in eur");
        anotherAccountEntity.setAccountType(AccountType.CASH);
        anotherAccountEntity.setCurrentBalance(400.00);
        anotherAccountEntity.setCurrency(AccountCurrency.EUR);

        return Arrays.asList(accountEntity, anotherAccountEntity);
    }
}
