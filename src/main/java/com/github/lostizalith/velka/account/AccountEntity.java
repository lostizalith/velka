package com.github.lostizalith.velka.account;

import com.github.lostizalith.velka.record.RecordEntity;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AccountEntity {

    private UUID id = UUID.randomUUID();

    private String displayName;

    private String shortDescription;

    private AccountType accountType;

    private Double currentBalance;

    private AccountCurrency currency;

    private List<RecordEntity> records;

    // TODO: CashFlow?

    // TODO: accounter name
}
