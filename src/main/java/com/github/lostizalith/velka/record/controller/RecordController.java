package com.github.lostizalith.velka.record.controller;

import com.github.lostizalith.velka.record.entity.RecordEntity;
import com.github.lostizalith.velka.record.mapper.RecordMapper;
import com.github.lostizalith.velka.record.service.RecordService;
import com.github.lostizalith.velka.record.vo.RecordRequest;
import com.github.lostizalith.velka.record.vo.RecordResponse;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecordController {

    private final RecordMapper recordMapper;
    private final RecordService recordService;

    @PostMapping(value = "/{id}/records")
    public ResponseEntity<RecordResponse> createRecord(
        @PathVariable("id") final String accountId,
        @RequestBody final RecordRequest recordRequest) {

        final RecordEntity recordEntity = recordMapper.recordRequestToRecordEntity(recordRequest);

        final RecordEntity created = recordService.createRecord(recordEntity, UUID.fromString(accountId));

        final RecordResponse response = recordMapper.recordEntityToRecordResponse(created);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}/records")
    public ResponseEntity<List<RecordResponse>> getAllRecordsByAccountId(
        @PathVariable("id") final String accountId) {

        final List<RecordEntity> allRecords = recordService.findAllRecords(UUID.fromString(accountId));

        final List<RecordResponse> responses = allRecords.parallelStream()
            .map(recordMapper::recordEntityToRecordResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
