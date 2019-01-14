package com.github.lostizalith.velka.category.service;

import com.github.lostizalith.velka.category.entity.CategoryEntity;
import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryEntity createCategory(CategoryEntity category);

    List<CategoryEntity> getAllCategories();

    InternalCategoryEntity createInternalCategory(InternalCategoryEntity internalCategory, UUID categoryId);

    List<CategoryEntity> getAllInternalCategories(UUID categoryId);
}
