package com.github.lostizalith.velka.account.repository;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, UUID> {

    List<AccountEntity> findAll();
}
