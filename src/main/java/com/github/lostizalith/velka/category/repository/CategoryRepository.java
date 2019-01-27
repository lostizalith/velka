package com.github.lostizalith.velka.category.repository;

import com.github.lostizalith.velka.category.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {

    List<CategoryEntity> findAll();
}
