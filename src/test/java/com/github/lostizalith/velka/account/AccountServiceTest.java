package com.github.lostizalith.velka.account;

import com.github.lostizalith.velka.AbstractIntegrationTest;
import com.github.lostizalith.velka.account.entity.AccountCurrency;
import com.github.lostizalith.velka.account.entity.AccountEntity;
import com.github.lostizalith.velka.account.entity.AccountType;
import com.github.lostizalith.velka.account.repository.AccountRepository;
import com.github.lostizalith.velka.account.service.AccountService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class AccountServiceTest extends AbstractIntegrationTest {

    private AccountEntity accountEntity;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        accountEntity = new AccountEntity();
        accountEntity.setDisplayName("Test account");
        accountEntity.setShortDescription("Very test account");
        accountEntity.setAccountType(AccountType.CASH);
        accountEntity.setCurrentBalance(100.0);
        accountEntity.setCurrency(AccountCurrency.EUR);
    }

    @Test
    public void createTest_checkIdType() {
        final AccountEntity saved = accountService.createAccount(accountEntity);

        assertThat(saved.getId(), CoreMatchers.isA(UUID.class));
    }

    @Test
    public void createTest_checkAccountType() {
        final AccountEntity saved = accountService.createAccount(accountEntity);

        assertThat(saved.getAccountType(), is(AccountType.CASH));
    }

    @Test
    public void findAccountsTest_checkBySize() {
        final AccountEntity saved = accountService.createAccount(accountEntity);
        final UUID id = saved.getId();

        final List<AccountEntity> accounts = accountService.findAccounts().stream()
            .filter(accountEntity -> {
                assert accountEntity.getId() != null;
                return accountEntity.getId().equals(id);
            })
            .collect(Collectors.toList());

        assertThat(accounts.size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAccountByIdTest_expectException_ifNotExists() {
        accountService.findAccountById(UUID.randomUUID());
    }

    @Test
    public void findAccountByIdTest_expectExists_checkType() {
        final AccountEntity saved = accountService.createAccount(accountEntity);
        final UUID id = saved.getId();

        final AccountEntity founded = accountService.findAccountById(id);

        assertThat(founded.getAccountType(), is(AccountType.CASH));
    }

    @Test
    public void findAccountByIdTest_expectExists_checkDescription() {
        final String shortDescription = "Find by id";
        accountEntity.setShortDescription(shortDescription);
        final AccountEntity saved = accountService.createAccount(accountEntity);
        final UUID id = saved.getId();

        final AccountEntity founded = accountService.findAccountById(id);

        assertThat(founded.getShortDescription(), is(shortDescription));
    }

    @Test
    public void updateTest_checkAccount() {
        final AccountEntity saved = accountService.createAccount(accountEntity);
        final UUID savedId = saved.getId();

        accountEntity.setCurrency(AccountCurrency.EUR);

        final AccountEntity updatedAccount = accountService.updateAccount(savedId, this.accountEntity);

        assertThat(updatedAccount.getId(), is((savedId)));
        assertThat(updatedAccount.getCurrency(), is(AccountCurrency.EUR));
    }

    @Test
    public void updateTest_checkAccountByFindMethod() {
        final String shortDescription = "Find by id";

        final AccountEntity saved = accountService.createAccount(accountEntity);
        final UUID savedId = saved.getId();

        accountEntity.setCurrency(AccountCurrency.EUR);
        accountEntity.setShortDescription(shortDescription);

        accountService.updateAccount(savedId, this.accountEntity);
        final AccountEntity updatedAccount = accountService.findAccountById(savedId);

        assertThat(updatedAccount.getCurrency(), is(AccountCurrency.EUR));
        assertThat(updatedAccount.getShortDescription(), is(shortDescription));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteAccountTest_expectIllegalArgument_afterDelete() {
        final AccountEntity saved = accountService.createAccount(accountEntity);
        final UUID savedId = saved.getId();

        accountService.deleteAccount(savedId);
        accountService.findAccountById(savedId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteAccountNegativeTest() {
        accountService.deleteAccount(UUID.randomUUID());
    }

    @Test
    public void test() {
        assertTrue(POSTGRES.isRunning());
    }
}
