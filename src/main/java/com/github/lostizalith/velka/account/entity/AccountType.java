package com.github.lostizalith.velka.account.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum AccountType {

    DEBIT_CARD("Debit Card"),
    CASH("Cash");

    private final String displayName;

    public static final List<AccountType> ENUMS = List.of(values());
}
