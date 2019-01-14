package com.github.lostizalith.velka.category.service;

import com.github.lostizalith.velka.category.entity.CategoryEntity;
import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;
import com.github.lostizalith.velka.category.repository.CategoryRepository;
import com.github.lostizalith.velka.category.repository.InternalCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final InternalCategoryRepository internalCategoryRepository;

    @Override
    public CategoryEntity createCategory(final CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public InternalCategoryEntity createInternalCategory(final InternalCategoryEntity internalCategory,
                                                         final UUID categoryId) {

        categoryRepository.findById(categoryId).
            ifPresentOrElse(internalCategory::setCategory,
                () -> new IllegalArgumentException(String.format("Could not find such category %s", categoryId)));

        return internalCategoryRepository.save(internalCategory);
    }

    @Override
    public List<CategoryEntity> getAllInternalCategories(final UUID categoryId) {
        throw new NotImplementedException("CategoryService#getAllInternalCategories");
    }
}
