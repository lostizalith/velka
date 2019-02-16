package com.github.lostizalith.velka.account.controller;

import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.mapper.AccountMapper;
import com.github.lostizalith.velka.account.service.AccountService;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import com.github.lostizalith.velka.account.vo.AccountResponse;
import com.github.lostizalith.velka.common.error.ErrorResponse;
import com.github.lostizalith.velka.common.json.JsonMediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "api/v1/accounts",
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    private final AccountMapper accountMapper;
    private final AccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid final AccountRequest request) {

        final AccountEntity account = accountMapper.accountRequestToAccountEntity(request);

        final AccountEntity saved = accountService.createAccount(account);

        final AccountResponse response = accountMapper.accountEntityToAccountResponse(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        final List<AccountEntity> accounts = accountService.findAccounts();

        final List<AccountResponse> responses = accounts.parallelStream()
            .map(accountMapper::accountEntityToAccountResponse)
            .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable("id") final String id) {
        final UUID uuid = UUID.fromString(id);

        final AccountEntity account = accountService.findAccountById(uuid);

        final AccountResponse response = accountMapper.accountEntityToAccountResponse(account);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AccountResponse> updateAccount(
        @PathVariable("id") final String id,
        @RequestBody final AccountRequest request) {

        final UUID uuid = UUID.fromString(id);

        final AccountEntity toUpdate = accountMapper.accountRequestToAccountEntity(request);

        final AccountEntity updated = accountService.updateAccount(uuid, toUpdate);

        final AccountResponse accountResponse = accountMapper.accountEntityToAccountResponse(updated);

        return ResponseEntity.ok(accountResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccountResponse> deleteAccountById(@PathVariable("id") final String id) {

        final UUID uuid = UUID.fromString(id);

        final AccountEntity deleted = accountService.deleteAccount(uuid);

        final AccountResponse accountResponse = accountMapper.accountEntityToAccountResponse(deleted);

        return ResponseEntity.ok(accountResponse);
    }

    @PatchMapping(value = "/{id}", consumes = JsonMediaType.JSON_PATCH_UTF8)
    public ResponseEntity<ErrorResponse> patchAccount(
        @PathVariable("id") final String id,
        @RequestBody final String jsonPatch) {

        final ErrorResponse errorResponse = ErrorResponse
            .aErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(), "Not implemented yet");

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
    }

    @PatchMapping(value = "/{id}", consumes = JsonMediaType.MERGE_PATCH_UTF8)
    public ResponseEntity<ErrorResponse> mergeAccount(
        @PathVariable("id") final String id,
        @RequestBody final String jsonPatch) {

        final ErrorResponse errorResponse = ErrorResponse
            .aErrorResponse(HttpStatus.NOT_IMPLEMENTED.value(), "Not implemented yet");

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(errorResponse);
    }
}
