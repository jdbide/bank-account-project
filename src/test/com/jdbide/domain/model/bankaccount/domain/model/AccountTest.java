package com.jdbide.domain.model.bankaccount.domain.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author jdbide
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private Account account;

    @Mock
    private Statement statement;


    @Before
    public void init() {
        account = new Account.AccountBuilder().statement(statement).build();
    }

    @Test
    public void balance_after_one_deposit() {
        account.deposit(new Amount(12));
        assertEquals(12d, account.getCurrentBalance().getValue());
    }

    @Test
    public void balance_after_two_deposit() {
        account.deposit(new Amount(500000));
        account.deposit(new Amount(100000));
        assertEquals(600000d, account.getCurrentBalance().getValue());
    }

    @Test
    public void balance_after_one_deposit_and_one_withdraw() {
        account.deposit(new Amount(500000));
        account.withdraw(new Amount(100000));
        assertEquals(400000d, account.getCurrentBalance().getValue());
    }

    @Test
    public void balance_after_one_deposit_and_two_withdraw() {
        account.deposit(new Amount(500000));
        account.withdraw(new Amount(100000));
        account.withdraw(new Amount(200000));
        assertEquals(200000d, account.getCurrentBalance().getValue());
    }

    @Test
    public void call_printHistory() {
        account.printHistory();
        account.withdraw(new Amount(100000));
        account.withdraw(new Amount(200000));
        verify(statement, times(1)).printHistory();
    }

}
