package com.github.lostizalith.velka.record;

import lombok.Data;

import java.util.UUID;

@Data
public class RecordEntity {

    private UUID id = UUID.randomUUID();

    private String memo;

    private String date;

    // TODO: record type instead and some field like value
    private String income;

    private String expense;
}
