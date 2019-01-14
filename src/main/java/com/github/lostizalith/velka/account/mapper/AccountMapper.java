package com.github.lostizalith.velka.account.mapper;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import com.github.lostizalith.velka.account.vo.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface AccountMapper {

    @Mappings({
        @Mapping(target = "displayName", source = "accountRequest.displayName"),
        @Mapping(target = "shortDescription", source = "accountRequest.shortDescription"),
        @Mapping(target = "accountType", source = "accountRequest.accountType"),
        @Mapping(target = "currentBalance", source = "accountRequest.currentBalance")
    })
    AccountEntity accountRequestToAccountEntity(AccountRequest accountRequest);

    @Mappings({
        @Mapping(target = "id", expression = "java(accountEntity.getId().toString())"),
    })
    AccountResponse accountEntityToAccountResponse(AccountEntity accountEntity);
}
