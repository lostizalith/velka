package com.github.lostizalith.velka.category.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private String id;

    private String displayName;

    private String icon;

    private List<InternalCategoryResponse> internalCategories;
}
