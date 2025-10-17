package com.currencyconverter;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter implements Converter {

    // Simulated exchange rates (1 unit of key currency = x units of value currency)
    private final Map<Currency, Map<Currency, Double>> exchangeRates;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();

        for (Currency from : Currency.values()) {
            exchangeRates.put(from, new HashMap<>());
            for (Currency to : Currency.values()) {
                if (from != to) {
                    exchangeRates.get(from).put(to, getMockExchangeRate(from, to));
                }
            }
        }
    }

    private double getMockExchangeRate(Currency from, Currency to) {
        int hash = Math.abs((from.name() + to.name()).hashCode());
        return (hash % 1000) / 10.0 + 0.5; // Randomish, stable pseudo-rates
    }

    @Override
    public double convert(double amount, Currency from, Currency to) throws InvalidCurrencyException {
        if (from == to) return amount;

        Map<Currency, Double> ratesFrom = exchangeRates.get(from);
        if (ratesFrom == null || !ratesFrom.containsKey(to)) {
            throw new InvalidCurrencyException("Exchange rate from " + from + " to " + to + " not available.");
        }

        double rate = ratesFrom.get(to);
        return amount * rate;
    }
}
