package com.currencyconverter;

public enum Currency {
    USD, INR, EUR, GBP, JPY, AUD, CAD, CNY, CHF, AED;

    public static boolean isValid(String currency) {
        for (Currency c : Currency.values()) {
            if (c.name().equalsIgnoreCase(currency)) {
                return true;
            }
        }
        return false;
    }
}
