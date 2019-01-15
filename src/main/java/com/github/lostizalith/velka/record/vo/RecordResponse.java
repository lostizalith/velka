package com.github.lostizalith.velka.record.vo;

import com.github.lostizalith.velka.category.vo.InternalCategoryResponse;
import lombok.Data;

@Data
public class RecordResponse {

    private String id;

    private String memo;

    private String recordType;

    private Double flow;

    private InternalCategoryResponse internalCategory;
}
