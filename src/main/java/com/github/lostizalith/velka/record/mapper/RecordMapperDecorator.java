package com.github.lostizalith.velka.record.mapper;

import com.github.lostizalith.velka.category.repository.InternalCategoryRepository;
import com.github.lostizalith.velka.record.entity.RecordEntity;
import com.github.lostizalith.velka.record.vo.RecordRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class RecordMapperDecorator {

    @Autowired
    private InternalCategoryRepository internalCategoryRepository;

    @AfterMapping
    public void recordRequestToRecordEntity(final RecordRequest recordRequest,
                                            @MappingTarget final RecordEntity recordEntity) {

        final UUID internalCategoryId = UUID.fromString(recordRequest.getInternalCategory());
        internalCategoryRepository.findById(internalCategoryId)
            .ifPresentOrElse(recordEntity::setInternalCategory,
                () -> new IllegalArgumentException(String.format("Could not find such int category %s", internalCategoryId)));

    }
}
