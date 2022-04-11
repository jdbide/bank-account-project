package com.jdbide.domain.model.bankaccount.domain.model;

import static com.jdbide.domain.model.bankaccount.domain.model.BankOperationType.DEPOSIT;
import static com.jdbide.domain.model.bankaccount.domain.model.BankOperationType.WITHDRAW;

/**
 * @author jdbide
 */
public class Account {

    private Statement statement;
    private Amount currentBalance;

    public void deposit(Amount amount) {
        statement.addStatement(BankOperation.Builder.createNew()
                .bankOperationType(DEPOSIT)
                .operationAmount(amount)
                .balanceBeforeOperation(new Amount(currentBalance.getValue()))
                .build());
        currentBalance = Amount.add(currentBalance,amount);

    }

    public void withdraw(Amount amount) {
        statement.addStatement(BankOperation.Builder.createNew()
                .bankOperationType(WITHDRAW)
                .operationAmount(amount)
                .balanceBeforeOperation(new Amount(currentBalance.getValue()))
                .build());
        currentBalance = Amount.substract(currentBalance,amount);

    }

    public void printHistory() {
        statement.printHistory();
    }

    public Amount getCurrentBalance() {
        return currentBalance;
    }

    public static class AccountBuilder {

        private Statement statement;
        private Amount currentBalance;

        public AccountBuilder() {
            statement = new Statement.Builder().build();
            currentBalance = new Amount();
        }

        public AccountBuilder statement(Statement statement) {
            this.statement = statement;
            return AccountBuilder.this;
        }

        public Account build() {
            if (this.statement == null) {
                throw new NullPointerException("The property \"statement\" is null. "
                        + "Please set the value by \"statement()\". "
                        + "The properties \"statement\", \"balance\" are required.");
            }
            if (this.currentBalance == null) {
                throw new NullPointerException("The property \"currentBalance\" is null. "
                        + "Please set the value by \"currentBalance()\". "
                        + "The properties \"statement\", \"currentBalance\" are required.");
            }

            return new Account(this);
        }
    }

    private Account(AccountBuilder builder) {
        this.statement = builder.statement;
        this.currentBalance = builder.currentBalance;
    }

}
