package com.github.lostizalith.velka.account;

import com.github.lostizalith.velka.record.RecordEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    @Override
    public AccountEntity getRandomAccount(final UUID id) {
        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setDisplayName("Prior Bank");
        accountEntity.setShortDescription("My super debit card");
        accountEntity.setAccountType(AccountType.DEBIT_CARD);
        accountEntity.setCurrentBalance(100.10);
        accountEntity.setCurrency(AccountCurrency.BYN);

        final RecordEntity record = new RecordEntity();
        record.setId(UUID.randomUUID());
        record.setDate(LocalDateTime.now().toString());
        record.setMemo("memo");
        record.setExpense("30.0");
        record.setIncome("0.0");
        record.setCategoryId(UUID.randomUUID());
        record.setCategoryName("Entertainment");
        record.setInternalCategoryName("Books");

        final RecordEntity record2 = new RecordEntity();
        record2.setId(UUID.randomUUID());
        record2.setDate(LocalDateTime.now().toString());
        record2.setMemo("memo2");
        record2.setExpense("0.0");
        record2.setIncome("10.0");
        record2.setCategoryId(UUID.randomUUID());
        record2.setCategoryName("Investment");
        record2.setInternalCategoryName("Investment");

        final List<RecordEntity> records = List.of(record, record2);

        accountEntity.setRecords(records);

        return accountEntity;
    }
}
