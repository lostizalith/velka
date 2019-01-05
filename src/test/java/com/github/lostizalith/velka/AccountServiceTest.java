package com.github.lostizalith.velka;

import com.github.lostizalith.velka.account.AccountEntity;
import com.github.lostizalith.velka.account.AccountService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void getRandomListTest() {
        final List<AccountEntity> randomList = accountService.getRandomList();

        Assert.assertThat(2, CoreMatchers.is(randomList.size()));
    }
}
