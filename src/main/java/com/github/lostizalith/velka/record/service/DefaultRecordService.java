package com.github.lostizalith.velka.record.service;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.repository.AccountRepository;
import com.github.lostizalith.velka.record.entity.RecordEntity;
import com.github.lostizalith.velka.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultRecordService implements RecordService {

    private final AccountRepository accountRepository;
    private final RecordRepository recordRepository;

    @Override
    public RecordEntity createRecord(final RecordEntity record, final UUID accountId) {
        accountRepository.findById(accountId)
            .ifPresentOrElse(record::setAccount,
                () -> new IllegalArgumentException(String.format("Could not find such account %s", accountId)));

        return recordRepository.save(record);
    }

    @Override
    public List<RecordEntity> findAllRecords(final UUID accountId) {
        return accountRepository.findById(accountId)
            .map(AccountEntity::getRecords)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Could not find such account %s", accountId)));
    }
}
