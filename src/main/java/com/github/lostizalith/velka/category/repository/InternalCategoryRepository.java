package com.github.lostizalith.velka.category.repository;

import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InternalCategoryRepository extends CrudRepository<InternalCategoryEntity, UUID> {

}
