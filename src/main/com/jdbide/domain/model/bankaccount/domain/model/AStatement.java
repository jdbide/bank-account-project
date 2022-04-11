package com.jdbide.domain.model.bankaccount.domain.model;

import java.text.SimpleDateFormat;
import java.util.StringJoiner;

import static com.jdbide.domain.model.bankaccount.domain.model.utils.Utils.fixedLengthString;


/**
 * @author jdbide
 */
public class AStatement {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    private BankOperation bankOperation;

    public BankOperation getBankOperation() {
        return bankOperation;
    }

    public void print() {
        System.out.println(formatToString());
    }

    public String formatToString() {
        StringJoiner builder = new StringJoiner(" | ");
        builder.add(fixedLengthString(simpleDateFormat.format(bankOperation.getDate()), 20));
        builder.add(fixedLengthString(bankOperation.getBankOperationType().getLibelle(), 20));
        builder.add(fixedLengthString( bankOperation.getOperationAmountWithPrefix(), 20));
        builder.add(fixedLengthString(bankOperation.getBalanceAfterOperation().getValue() + "", 20));
        return builder.toString();
    }

    public static class AStatementBuilder {

        private BankOperation bankOperation;

        public AStatementBuilder() {
            this.bankOperation = new BankOperation.Builder().createNewInitOperation();
        }

        public AStatementBuilder bankOperation(BankOperation bankOperation) {
            this.bankOperation = bankOperation;
            return AStatementBuilder.this;
        }

        public AStatement build() {
            if (this.bankOperation == null) {
                throw new NullPointerException("The property \"bankOperation\" is null. "
                        + "Please set the value by \"bankOperation()\". "
                        + "The properties \"bankOperation\", \"amount\" are required.");
            }

            return new AStatement(this);
        }
    }

    private AStatement(AStatementBuilder builder) {
        this.bankOperation = builder.bankOperation;
    }

}
