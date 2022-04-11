package com.jdbide.domain.model.bankaccount.domain.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author jdbide
 */
public class AmountTest {

    private Amount amount;

    @Before
    public void setUp() throws Exception {
        amount = new Amount();
    }

    @Test
    public void amount_init_to_0() {
        assertEquals(0d, amount.getValue());
    }

    @Test
    public void add() {
        amount = Amount.add(amount,new Amount(10d));
        amount = Amount.add(amount,new Amount(15d));

        assertEquals(25d, amount.getValue());

    }

    @Test
    public void add_and_substract() {
        amount = Amount.add(amount,new Amount(10d));
        amount = Amount.substract(amount,new Amount(15d));
        assertEquals(-5d, amount.getValue());

    }
}