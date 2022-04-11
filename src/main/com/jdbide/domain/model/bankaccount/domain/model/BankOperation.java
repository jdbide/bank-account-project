package com.jdbide.domain.model.bankaccount.domain.model;

import java.util.Date;

import static com.jdbide.domain.model.bankaccount.domain.model.BankOperationType.*;
import static java.util.Objects.isNull;

/**
 * @author jdbide
 */
public class BankOperation {

    private BankOperationType bankOperationType;
    private Amount operationAmount;
    private Amount balanceAfterOperation;

    private Date date;

    public String getOperationPrefix() {
        return (DEPOSIT.equals(getBankOperationType()) ? "+" : (WITHDRAW.equals(getBankOperationType()) ? "-" : ""));
    }

    public Amount getBalanceAfterOperation() {
        return balanceAfterOperation;
    }

    public BankOperationType getBankOperationType() {
        return bankOperationType;
    }

    public Amount getOperationAmount() {
        return operationAmount;
    }

    public Date getDate() {
        return date;
    }

    public String getOperationAmountWithPrefix() {
        return this.getOperationPrefix() + (!isNull(getOperationAmount()) ? getOperationAmount().getValue():"");
    }

    public static class Builder {

        private Amount operationAmount;
        private Amount balanceBeforeOperation = new Amount();
        private Amount balanceAfterOperation = new Amount();
        private BankOperationType bankOperationType;
        private Date date = new Date();

        public Builder() {
        }

        public static Builder createNew() {
            return new Builder();
        }

        public BankOperation createNewInitOperation() {
            return new Builder().bankOperationType(INIT).build();
        }

        public Builder operationAmount(Amount value) {
            this.operationAmount = value;
            return Builder.this;
        }

        public Builder balanceBeforeOperation(Amount balanceBeforeOperation) {
            this.balanceBeforeOperation = balanceBeforeOperation;
            return Builder.this;
        }

        public Builder bankOperationType(BankOperationType bankOperationType) {
            this.bankOperationType = bankOperationType;
            return Builder.this;
        }

        public BankOperation build() {

            if (!INIT.equals(this.bankOperationType) && this.operationAmount == null) {
                throw new NullPointerException("The property \"value\" is null. "
                        + "Please set the value by \"value()\". "
                        + "The properties \"value\", \"date\", bankOperationType are required.");
            }

            if (this.bankOperationType == null) {
                throw new NullPointerException("The property \"bankOperationType\" is null. "
                        + "Please set the value by \"bankOperationType()\". "
                        + "The properties \"value\", \"date\",bankOperationType are required.");
            }

            if (this.date == null) {
                throw new NullPointerException("The property \"date\" is null. "
                        + "Please set the value by \"date()\". "
                        + "The properties \"value\", \"date\",bankOperationType are required.");
            }

            if (DEPOSIT.equals(bankOperationType))
                balanceAfterOperation = Amount.add(balanceBeforeOperation,operationAmount);
            else if (WITHDRAW.equals(bankOperationType))
                balanceAfterOperation = Amount.substract(balanceBeforeOperation,operationAmount);;

            return new BankOperation(this);
        }

    }

    private BankOperation(Builder builder) {
        this.operationAmount = builder.operationAmount;
        this.date = builder.date;
        this.bankOperationType = builder.bankOperationType;
        this.balanceAfterOperation = builder.balanceAfterOperation;
    }

}
