package com.jdbide.domain.model.bankaccount.domain.model;

import com.jdbide.domain.model.bankaccount.domain.model.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.reverseOrder;

/**
 * @author jdbide
 */
public class Statement {

    private List<AStatement> statements;

    public void addStatement(BankOperation bankOperation) {
        statements.add(new AStatement.AStatementBuilder().bankOperation(bankOperation).build());
    }

    public List<AStatement> getStatements() {
        return statements;
    }

    public void printHistory() {
        //headers
        StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append(Utils.fixedLengthString("Date", 20) + " | ");
        headerBuilder.append(Utils.fixedLengthString("Operation", 20) + " | ");
        headerBuilder.append(Utils.fixedLengthString("Montant", 20) + " | ");
        headerBuilder.append(Utils.fixedLengthString("Solde", 20) + " | ");
        System.out.println(headerBuilder.toString());

        statements.stream().sorted(Comparator.comparing(aStatement -> aStatement.getBankOperation().getDate(), reverseOrder()))
                .forEach(aStatement -> aStatement.print());
    }

    public static class Builder {

        private List<AStatement> statements;

        public Builder() {
            statements = new ArrayList<>();
            statements.add(new AStatement.AStatementBuilder().build());
        }

        Builder(List<AStatement> statements) {
            this.statements = statements;
        }

        public Builder statements(List<AStatement> statements) {
            this.statements = statements;
            return Builder.this;
        }

        public Builder addStatements(AStatement statements) {
            this.statements.add(statements);
            return Builder.this;
        }

        public Statement build() {
            if (this.statements == null) {
                throw new NullPointerException("The property \"statements\" is null. "
                        + "Please set the value by \"statements()\". ");
            }

            return new Statement(this);
        }
    }

    private Statement(Builder builder) {
        this.statements = builder.statements;
    }

}


