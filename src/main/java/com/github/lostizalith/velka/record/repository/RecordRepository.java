package com.github.lostizalith.velka.record.repository;

import com.github.lostizalith.velka.record.entity.RecordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository extends CrudRepository<RecordEntity, UUID> {

    List<RecordEntity> findAll();
}
