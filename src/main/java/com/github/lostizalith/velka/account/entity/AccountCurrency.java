package com.github.lostizalith.velka.account.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum AccountCurrency {

    BYN("BYN"),
    USD("USD"),
    EUR("EUR");

    private final String displayName;

    public static final List<AccountCurrency> ENUMS = List.of(values());
}
