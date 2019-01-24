package com.github.lostizalith.velka.account.controller;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.mapper.AccountMapper;
import com.github.lostizalith.velka.account.service.AccountService;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import com.github.lostizalith.velka.account.vo.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "api/v1/accounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    private final AccountMapper accountMapper;
    private final AccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid final AccountRequest request) {

        final AccountEntity account = accountMapper.accountRequestToAccountEntity(request);

        final AccountEntity saved = accountService.createAccount(account);

        final AccountResponse response = accountMapper.accountEntityToAccountResponse(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        final List<AccountEntity> accounts = accountService.findAccounts();

        final List<AccountResponse> responses = accounts.parallelStream()
            .map(accountMapper::accountEntityToAccountResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
