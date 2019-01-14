package com.github.lostizalith.velka.category.controller;

import com.github.lostizalith.velka.category.entity.CategoryEntity;
import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;
import com.github.lostizalith.velka.category.mapper.CategoryMapper;
import com.github.lostizalith.velka.category.service.CategoryService;
import com.github.lostizalith.velka.category.vo.CategoryRequest;
import com.github.lostizalith.velka.category.vo.CategoryResponse;
import com.github.lostizalith.velka.category.vo.InternalCategoryRequest;
import com.github.lostizalith.velka.category.vo.InternalCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody final CategoryRequest request) {
        final CategoryEntity category = categoryMapper.categoryRequestToCategoryEntity(request);

        final CategoryEntity created = categoryService.createCategory(category);

        final CategoryResponse response = categoryMapper.categoryEntityToCategoryResponse(created);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        final List<CategoryEntity> allCategories = categoryService.getAllCategories();

        final List<CategoryResponse> responses = allCategories.parallelStream()
            .map(categoryMapper::categoryEntityToCategoryResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PostMapping(value = "{id}/internal")
    public ResponseEntity<InternalCategoryResponse> createInternalCategory(
        @RequestBody final InternalCategoryRequest request,
        @PathVariable("id") final String categoryId) {

        final InternalCategoryEntity entity = categoryMapper.internalCategoryRequestToInternalCategoryEntity(request);

        final InternalCategoryEntity created = categoryService
            .createInternalCategory(entity, UUID.fromString(categoryId));

        final InternalCategoryResponse response = categoryMapper
            .internalCategoryEntityToInternalCategoryResponse(created);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "{id}/internal")
    public ResponseEntity<CategoryResponse> getAllInternalCategories(@PathVariable("id") final String categoryId) {
        throw new NotImplementedException("CategoryController#getAllInternalCategories");
    }
}
