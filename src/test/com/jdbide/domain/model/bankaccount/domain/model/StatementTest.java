package com.jdbide.domain.model.bankaccount.domain.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static com.jdbide.domain.model.bankaccount.domain.model.BankOperationType.DEPOSIT;
import static junit.framework.TestCase.assertEquals;


/**
 * @author jdbide
 */
public class StatementTest {

    private Statement statement;


    @Before
    public void init(){
        statement = new Statement.Builder().build();
    }

    @Test
    public void statement_after_two_deposits(){
        statement.addStatement(BankOperation.Builder.createNew()
                .bankOperationType(DEPOSIT)
                .operationAmount(new Amount(10))
                .balanceBeforeOperation(new Amount())
                .build());

        statement.addStatement(BankOperation.Builder.createNew()
                .bankOperationType(DEPOSIT)
                .operationAmount(new Amount(20))
                .balanceBeforeOperation(statement.getStatements().get(1).getBankOperation().getBalanceAfterOperation())
                .build());

        assertEquals(10d,statement.getStatements().get(1).getBankOperation().getBalanceAfterOperation().getValue());
        assertEquals(30d,statement.getStatements().get(2).getBankOperation().getBalanceAfterOperation().getValue());
    }


}