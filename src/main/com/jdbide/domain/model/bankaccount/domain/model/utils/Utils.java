package com.jdbide.domain.model.bankaccount.domain.model.utils;

/**
 * @author jdbide
 */
public  class Utils {
    public static String fixedLengthString(String string, int length) {
        return String.format("%1$"+length+ "s", string);
    }
}
