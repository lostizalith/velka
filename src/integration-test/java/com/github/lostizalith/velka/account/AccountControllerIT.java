package com.github.lostizalith.velka.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lostizalith.velka.AbstractIntegrationTest;
import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.vo.AccountRequest;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static com.github.lostizalith.velka.common.json.JsonMediaType.JSON_PATCH_UTF8;
import static com.github.lostizalith.velka.common.json.JsonMediaType.MERGE_PATCH_UTF8;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
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
        final MvcResult mvcResult = performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final AccountEntity accountEntity = objectMapper.readValue(contentAsString, AccountEntity.class);

        final String savedId = accountEntity.getId().toString();

        mockMvc.perform(request(GET, String.format("/api/v1/accounts/%s", savedId)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")));
    }

    @Test
    public void createAccount_withNonExcitingCurrency_expectError() throws Exception {
        accountRequest.setCurrency("BYN1");

        performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status", is(400)))
            .andExpect(jsonPath("$.message", is("There's no such currency")));
    }

    @Test
    public void getAllAccount_checkCountBeforeAndAfter() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(request(GET, "/api/v1/accounts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();

        final int length = JsonPath
            .parse(contentAsString)
            .read("$.length()");

        performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        mockMvc.perform(request(GET, "/api/v1/accounts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(length + 1)));
    }

    @Test
    public void getAccountById_expectAccount() throws Exception {
        final MvcResult mvcResult = performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final AccountEntity accountEntity = objectMapper.readValue(contentAsString, AccountEntity.class);

        final String savedId = accountEntity.getId().toString();

        mockMvc.perform(request(GET, String.format("/api/v1/accounts/%s", savedId)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")));
    }

    @Test
    public void getAccountByNonExistsId_expectErrorResponse() throws Exception {
        final String randomId = UUID.randomUUID().toString();

        mockMvc.perform(request(GET, String.format("/api/v1/accounts/%s", randomId)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is(String.format("Could not find such account %s", randomId))));
    }

    @Test
    public void putAccount_expectUpdatedEntity() throws Exception {
        final MvcResult mvcResult = performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final AccountEntity accountEntity = objectMapper.readValue(contentAsString, AccountEntity.class);

        final String savedId = accountEntity.getId().toString();

        accountRequest.setCurrency("USD");
        accountRequest.setAccountType("CASH");

        mockMvc.perform(request(PUT, String.format("/api/v1/accounts/%s", savedId))
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accountType", is("CASH")))
            .andExpect(jsonPath("$.currency", is("USD")));
    }

    @Test
    public void putAccount_expectUpdatedEntity_byGetByIdEndpoint() throws Exception {
        final MvcResult mvcResult = performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final AccountEntity accountEntity = objectMapper.readValue(contentAsString, AccountEntity.class);

        final String savedId = accountEntity.getId().toString();

        accountRequest.setCurrency("USD");
        accountRequest.setAccountType("CASH");

        mockMvc.perform(request(PUT, String.format("/api/v1/accounts/%s", savedId))
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(savedId)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("CASH")))
            .andExpect(jsonPath("$.currency", is("USD")));

        mockMvc.perform(request(GET, String.format("/api/v1/accounts/%s", savedId)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(savedId)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("CASH")))
            .andExpect(jsonPath("$.currency", is("USD")));
    }

    @Test
    public void putAccount_randomId_expectErrorResponse() throws Exception {
        final String randomId = UUID.randomUUID().toString();

        mockMvc.perform(request(PUT, String.format("/api/v1/accounts/%s", randomId))
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is(String.format("Could not find such account %s", randomId))))
            .andExpect(jsonPath("$.status", is(400)));
    }

    @Test
    public void deleteAccount_randomId() throws Exception {
        final String randomId = UUID.randomUUID().toString();

        mockMvc.perform(request(DELETE, String.format("/api/v1/accounts/%s", randomId)))
            .andDo(print())
            .andExpect(jsonPath("$.message", is(String.format("Could not find such account %s", randomId))))
            .andExpect(jsonPath("$.status", is(400)));
    }

    @Test
    public void deleteAccount_expectDeletedAccount() throws Exception {
        final MvcResult mvcResult = performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final AccountEntity accountEntity = objectMapper.readValue(contentAsString, AccountEntity.class);

        final String savedId = accountEntity.getId().toString();

        mockMvc.perform(request(DELETE, String.format("/api/v1/accounts/%s", savedId)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(savedId)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")));
    }

    @Test
    public void deleteAccount_expectErrorResponseWhenGetById() throws Exception {
        final MvcResult mvcResult = performPostMockMvcRequest()
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", isA(String.class)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")))
            .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final AccountEntity accountEntity = objectMapper.readValue(contentAsString, AccountEntity.class);

        final String savedId = accountEntity.getId().toString();

        mockMvc.perform(request(DELETE, String.format("/api/v1/accounts/%s", savedId)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(savedId)))
            .andExpect(jsonPath("$.displayName", is("Prior Bank")))
            .andExpect(jsonPath("$.accountType", is("DEBIT_CARD")))
            .andExpect(jsonPath("$.currency", is("BYN")));

        mockMvc.perform(request(GET, String.format("/api/v1/accounts/%s", savedId)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is(String.format("Could not find such account %s", savedId))));
    }

    @Test
    public void patchAccount_expectNotImplemented() throws Exception {
        final String randomId = UUID.randomUUID().toString();

        mockMvc.perform(request(PATCH, String.format("/api/v1/accounts/%s", randomId))
            .contentType(JSON_PATCH_UTF8)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isNotImplemented())
            .andExpect(jsonPath("$.message", is("Not implemented yet")));
    }

    @Test
    public void mergeAccount_expectNotImplemented() throws Exception {
        final String randomId = UUID.randomUUID().toString();

        mockMvc.perform(request(PATCH, String.format("/api/v1/accounts/%s", randomId))
            .contentType(MERGE_PATCH_UTF8)
            .content(objectMapper.writeValueAsBytes(accountRequest)))
            .andDo(print())
            .andExpect(status().isNotImplemented())
            .andExpect(jsonPath("$.message", is("Not implemented yet")));
    }

    @Test
    public void test() {
        assertTrue(POSTGRES.isRunning());
    }

    private ResultActions performPostMockMvcRequest() throws Exception {
        return mockMvc.perform(request(POST, "/api/v1/accounts")
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(objectMapper.writeValueAsBytes(accountRequest)));
    }
}
