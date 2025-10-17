package com.currencyconverter;

public interface Converter {
    double convert(double amount, Currency from, Currency to) throws InvalidCurrencyException;
}
