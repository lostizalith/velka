package com.github.lostizalith.velka.record;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum RecordType {

    INCOME("Income"),
    OUTCOME("Outcome"),
    TRANSFER("Transfer");

    private final String displayName;

    public static final List<RecordType> ENUMS = List.of(values());
}
