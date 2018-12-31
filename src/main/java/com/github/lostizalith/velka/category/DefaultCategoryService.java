package com.github.lostizalith.velka.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {

    @Override
    public List<CategoryEntity> getRandomList() {
        final InternalCategoryEntity internalCategory = new InternalCategoryEntity();
        internalCategory.setDisplayName("Books");
        internalCategory.setIcon("Books icon path");

        final InternalCategoryEntity internalCategory2 = new InternalCategoryEntity();
        internalCategory2.setDisplayName("Music streaming service");
        internalCategory2.setIcon("Music streaming service icon path");

        final InternalCategoryEntity internalCategory3 = new InternalCategoryEntity();
        internalCategory3.setDisplayName("Investment");
        internalCategory3.setIcon("Investment icon path");

        final InternalCategoryEntity internalCategory4 = new InternalCategoryEntity();
        internalCategory4.setDisplayName("Savings");
        internalCategory4.setIcon("Savings icon path");

        final CategoryEntity category = new CategoryEntity();
        category.setDisplayName("Entertainment");
        category.setIcon("Entertainment icon path");
        category.setInternalCategories(List.of(internalCategory, internalCategory2));

        final CategoryEntity category2 = new CategoryEntity();
        category2.setDisplayName("Investment");
        category2.setIcon("Investment icon path");
        category2.setInternalCategories(List.of(internalCategory3, internalCategory4));

        return List.of(category, category2);
    }
}
