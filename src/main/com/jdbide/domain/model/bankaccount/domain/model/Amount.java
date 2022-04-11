package com.jdbide.domain.model.bankaccount.domain.model;

/**
 * @author jdbide
 */
public class Amount {

    private double value;

    public double getValue() {
        return value;
    }

    public Amount() {
        this.value = 0d;
    }

    public Amount(double amount) {
        this.value = amount;
    }

    public static Amount add(Amount amount1,Amount amount2) {
        return new Amount(amount1.value + amount2.value);
    }

    public static Amount substract(Amount amount1,Amount amount2) {
        return new Amount(amount1.value - amount2.value);

    }

}
