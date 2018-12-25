package com.github.lostizalith.velka.record;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/records", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecordController {

    @GetMapping
    public ResponseEntity<List<Record>> getRecords() {
        final Record record = new Record();
        record.setId(UUID.randomUUID().toString());
        record.setDate(LocalDateTime.now().toString());
        record.setMemo("memo");
        record.setExpense("30.0");
        record.setIncome("0.0");

        final Record record2 = new Record();
        record2.setId(UUID.randomUUID().toString());
        record2.setDate(LocalDateTime.now().toString());
        record2.setMemo("memo2");
        record2.setExpense("0.0");
        record2.setIncome("10.0");

        final List<Record> records = Arrays.asList(record, record2);

        return ResponseEntity.ok(records);
    }
}
