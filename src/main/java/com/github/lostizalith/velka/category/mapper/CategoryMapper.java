package com.github.lostizalith.velka.category.mapper;

import com.github.lostizalith.velka.category.entity.CategoryEntity;
import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;
import com.github.lostizalith.velka.category.vo.CategoryRequest;
import com.github.lostizalith.velka.category.vo.CategoryResponse;
import com.github.lostizalith.velka.category.vo.InternalCategoryRequest;
import com.github.lostizalith.velka.category.vo.InternalCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

// shortify names
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity categoryRequestToCategoryEntity(CategoryRequest categoryRequest);

    @Mappings({
        @Mapping(target = "id", expression = "java(categoryEntity.getId().toString())"),
    })
    CategoryResponse categoryEntityToCategoryResponse(CategoryEntity categoryEntity);

    InternalCategoryEntity internalCategoryRequestToInternalCategoryEntity(InternalCategoryRequest request);

    @Mappings({
        @Mapping(target = "id", expression = "java(entity.getId().toString())"),
    })
    InternalCategoryResponse internalCategoryEntityToInternalCategoryResponse(InternalCategoryEntity entity);
}
