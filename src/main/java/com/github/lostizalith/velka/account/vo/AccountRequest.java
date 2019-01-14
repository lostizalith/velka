package com.github.lostizalith.velka.account.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountRequest {

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

//    private List<RecordEntity> records;
}
