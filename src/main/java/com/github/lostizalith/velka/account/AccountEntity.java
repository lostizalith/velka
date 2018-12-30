package com.github.lostizalith.velka.account;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountEntity {

    private UUID id = UUID.randomUUID();

    private String displayName;

    private String shortDescription;

    // TODO: enum?
    private String accountType;

    // TODO: what type (float?)
    private String currentBalance;

    // TODO: enum?
    private String currency;

    // TODO: CashFlow?

    // TODO: records

    // TODO: accounter name
}
