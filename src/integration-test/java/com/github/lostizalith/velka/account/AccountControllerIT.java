package com.github.lostizalith.velka.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lostizalith.velka.AbstractIntegrationTest;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AccountControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    private AccountRequest accountRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webAppConfiguration;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();

        accountRequest = new AccountRequest();
        accountRequest.setDisplayName("Prior Bank");
        accountRequest.setShortDescription("Prior Bank salary card");
        accountRequest.setAccountType("DEBIT_CARD");
        accountRequest.setCurrentBalance(100.1);
        accountRequest.setCurrency("BYN");
    }

    @Test
    public void createAccount_expectCreated() throws Exception {
        mockMvc.perform(request(POST, "/api/v1/accounts")
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")));

        mockMvc.perform(request(GET, "/api/v1/accounts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id", isA(String.class)))
            .andExpect(jsonPath("$.[0].displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.[0].accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.[0].currency", is("BYN")));
    }

    @Test
    public void getAllAccounts_fromEmptyDB_expectOk_emptyList() throws Exception {
        mockMvc.perform(request(GET, "/api/v1/accounts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(Collections.EMPTY_LIST)));
    }

    @Test
    public void createAccount_withNonExcitingCurrency_expectError() throws Exception {
        accountRequest.setCurrency("BYN1");

        mockMvc.perform(request(POST, "/api/v1/accounts")
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status", is(400)))
            .andExpect(jsonPath("$.message", is("There's no such currency")));
    }

    @Test
    public void test() {
        assertTrue(POSTGRES.isRunning());
    }
}
