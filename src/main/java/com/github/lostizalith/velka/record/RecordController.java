package com.github.lostizalith.velka.record;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/api/v1/records", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public ResponseEntity<List<RecordEntity>> getRecords() {
        return ResponseEntity.ok(recordService.getRandomList());
    }
}
