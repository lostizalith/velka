package com.github.lostizalith.velka.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "api/v1/accounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountEntity>> getRandomList() {
        return ResponseEntity.ok(accountService.getRandomList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountEntity> getRandomAccount(@PathVariable("id") final String id) {
        return ResponseEntity.ok(accountService.getRandomAccount(UUID.fromString(id)));
    }
}
