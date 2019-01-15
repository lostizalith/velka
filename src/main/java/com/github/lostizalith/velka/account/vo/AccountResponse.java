package com.github.lostizalith.velka.account.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lostizalith.velka.record.vo.RecordResponse;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "displayName")
    private String displayName;

    @JsonProperty(value = "shortDescription")
    private String shortDescription;

    @JsonProperty(value = "accountType")
    private String accountType;

    @JsonProperty(value = "currentBalance")
    private Double currentBalance;

    @JsonProperty(value = "currency")
    private String currency;

    private List<RecordResponse> records;
}
