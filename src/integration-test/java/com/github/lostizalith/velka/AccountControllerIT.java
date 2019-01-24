package com.github.lostizalith.velka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import org.hamcrest.CoreMatchers;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webAppConfiguration;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();
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
}
