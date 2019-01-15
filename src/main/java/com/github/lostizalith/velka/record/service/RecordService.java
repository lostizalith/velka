package com.github.lostizalith.velka.record.service;

import com.github.lostizalith.velka.record.entity.RecordEntity;

import java.util.List;
import java.util.UUID;

public interface RecordService {

    RecordEntity createRecord(RecordEntity record, UUID accountId);

    List<RecordEntity> findAllRecords(UUID accountId);
}
