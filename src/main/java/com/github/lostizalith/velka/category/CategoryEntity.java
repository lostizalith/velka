package com.github.lostizalith.velka.category;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CategoryEntity {

    private UUID id = UUID.randomUUID();

    private String displayName;

    private String icon;

    private List<InternalCategoryEntity> internalCategories;
}
