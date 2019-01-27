package com.github.lostizalith.velka.record.mapper;

import com.github.lostizalith.velka.category.entity.InternalCategoryEntity;
import com.github.lostizalith.velka.category.vo.InternalCategoryResponse;
import com.github.lostizalith.velka.record.entity.RecordEntity;
import com.github.lostizalith.velka.record.vo.RecordRequest;
import com.github.lostizalith.velka.record.vo.RecordResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = RecordMapperDecorator.class)
public interface RecordMapper {

    @Mappings({
        @Mapping(target = "internalCategory", source = "recordRequest.internalCategory", ignore = true),
        @Mapping(target = "accountCurrency", source = "recordRequest.currency"),
    })
    RecordEntity recordRequestToRecordEntity(RecordRequest recordRequest);

    @Mappings({
        @Mapping(target = "id", expression = "java(recordEntity.getId().toString())"),
        @Mapping(target = "currency", source = "recordEntity.accountCurrency"),
    })
    RecordResponse recordEntityToRecordResponse(RecordEntity recordEntity);

    @Mappings({
        @Mapping(target = "id", expression = "java(entity.getId().toString())"),
    })
    InternalCategoryResponse internalCategoryEntityToInternalCategoryResponse(InternalCategoryEntity entity);
}
