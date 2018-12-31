package com.github.lostizalith.velka.category;

import lombok.Data;

import java.util.UUID;

@Data
public class InternalCategoryEntity {

    private UUID id = UUID.randomUUID();

    private String displayName;

    private String icon;
}
