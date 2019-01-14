package com.github.lostizalith.velka;

import com.github.lostizalith.velka.account.service.AccountService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;
}
