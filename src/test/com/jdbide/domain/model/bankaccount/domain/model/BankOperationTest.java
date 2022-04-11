package com.jdbide.domain.model.bankaccount.domain.model;

import org.junit.Test;

import static com.jdbide.domain.model.bankaccount.domain.model.BankOperationType.*;
import static junit.framework.TestCase.assertEquals;

/**
 * @author jdbide
 */
public class BankOperationTest {

    private  BankOperation bankOperation;

    @Test
    public void prefix_when_operation_is_init() {
        bankOperation = new BankOperation.Builder().createNewInitOperation();
        assertEquals("", bankOperation.getOperationPrefix());
    }

    @Test
    public void prefix_when_operation_is_deposit() {
        bankOperation = new BankOperation.Builder().bankOperationType(DEPOSIT).operationAmount(new Amount(10)).build();
        assertEquals("+", bankOperation.getOperationPrefix());
    }

    @Test
    public void prefix_when_operation_is_withdraw() {
        bankOperation = new BankOperation.Builder().bankOperationType(WITHDRAW).operationAmount(new Amount(10)).build();
        assertEquals("-", bankOperation.getOperationPrefix());
    }
}