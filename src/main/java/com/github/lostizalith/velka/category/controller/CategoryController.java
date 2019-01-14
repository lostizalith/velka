package com.github.lostizalith.velka.category.controller;

import com.github.lostizalith.velka.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryController {

    private final CategoryService categoryService;
}
