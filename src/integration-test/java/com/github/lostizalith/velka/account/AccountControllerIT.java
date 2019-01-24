package com.github.lostizalith.velka.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountControllerIT {

    private static final Integer ORIGINAL_PORT = 5432;

    private MockMvc mockMvc;

    private GenericContainer postgresql = new GenericContainer("postgres:9.5-alpine")
        .withExposedPorts(ORIGINAL_PORT)
        .withEnv("POSTGRES_DB", "velka")
        .withEnv("POSTGRES_USER", "velka_user")
        .withEnv("POSTGRES_PASSWORD", "testpwd");

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webAppConfiguration;

    @Before
    public void setUp() {
        postgresql.start();
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();
    }

    // TODO: use Jupiter extension
    @After
    public void after() {
        postgresql.stop();
    }

    @Test
    public void createAccount_withNonExcitingCurrency_expectError() throws Exception {
        final AccountRequest accountRequest = new AccountRequest();
        accountRequest.setDisplayName("Prior Bank");
        accountRequest.setShortDescription("Prior Bank salary card");
        accountRequest.setAccountType("DEBIT_CARD");
        accountRequest.setCurrentBalance(100.1);
        accountRequest.setCurrency("BYN1");

        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/api/v1/accounts")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(400)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is("There's no such currency")));
    }

    @Test
    public void test() {
        assertTrue(postgresql.isRunning());
    }
}
