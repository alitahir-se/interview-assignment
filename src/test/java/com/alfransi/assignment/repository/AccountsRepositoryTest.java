package com.alfransi.assignment.repository;


import com.alfransi.assignment.models.Account;
import com.alfransi.assignment.repositories.AccountsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AccountsRepositoryTest{

    @Autowired
    AccountsRepository accountsRepository;

    @Test
    public void fetchByAccountNumber_Success(){
        Optional<Account> account = accountsRepository.fetchByAccountNumberAndObtainLock("123456789123456");
        Assert.assertTrue(account.isPresent());
        Assert.assertEquals("123456789123456",account.get().getAccountNumber());

    }

    @Test
    public void findByAccountNumber_Success(){
        Optional<Account> account = accountsRepository.findByAccountNumber("123456789123456");
        Assert.assertTrue(account.isPresent());
        Assert.assertEquals("123456789123456",account.get().getAccountNumber());
    }

    @Test
    public void fetchByAccountNumber_DataNotFound(){
        Optional<Account> account = accountsRepository.fetchByAccountNumberAndObtainLock("123456789127456");
        Assert.assertTrue(account.isEmpty());
    }

    @Test
    public void findByAccountNumber_DataNotFound(){
        Optional<Account> account = accountsRepository.findByAccountNumber("123456789323456");
        Assert.assertTrue(account.isEmpty());
    }

}
