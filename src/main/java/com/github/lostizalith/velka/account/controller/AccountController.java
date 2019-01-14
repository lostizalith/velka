package com.github.lostizalith.velka.account.controller;

import com.github.lostizalith.velka.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "api/v1/accounts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {

    private final AccountService accountService;
}
