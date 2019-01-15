package com.github.lostizalith.velka.record.vo;

import lombok.Data;

@Data
public class RecordRequest {

    private String memo;

    private String recordType;

    private Double flow;

    private String internalCategory;
}
