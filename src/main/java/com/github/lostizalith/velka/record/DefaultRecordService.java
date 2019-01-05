package com.github.lostizalith.velka.record;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultRecordService implements RecordService {

    @Override
    public List<RecordEntity> getRandomList() {
        final RecordEntity record = new RecordEntity();
        record.setId(UUID.randomUUID());
        record.setDate(LocalDateTime.now().toString());
        record.setMemo("memo");
        record.setExpense("30.0");
        record.setIncome("0.0");

        final RecordEntity record2 = new RecordEntity();
        record2.setId(UUID.randomUUID());
        record2.setDate(LocalDateTime.now().toString());
        record2.setMemo("memo2");
        record2.setExpense("0.0");
        record2.setIncome("10.0");

        return List.of(record, record2);
    }
}
