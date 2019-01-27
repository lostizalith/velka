package com.github.lostizalith.velka.account;

import com.github.lostizalith.velka.AbstractIntegrationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class AccountServiceTest extends AbstractIntegrationTest {

    @Test
    public void test() {
        assertTrue(POSTGRES.isRunning());
    }
}
