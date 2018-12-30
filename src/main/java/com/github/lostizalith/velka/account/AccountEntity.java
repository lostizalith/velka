package com.github.lostizalith.velka.account;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountEntity {

    private UUID id = UUID.randomUUID();

    private String displayName;

    private String shortDescription;

    private AccountType accountType;

    private Double currentBalance;

    private AccountCurrency currency;

    // TODO: CashFlow?

    // TODO: records

    // TODO: accounter name
}
