package com.currencyconverter;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();

        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║         ADVANCED CURRENCY CONVERTER    ║");
        System.out.println("╚═════════════════════════════════════════╝");

        while (true) {
            try {
                System.out.print("\nEnter amount to convert: ");
                double amount = getValidAmount();

                System.out.print("Enter source currency (e.g., USD): ");
                Currency fromCurrency = getValidCurrency();

                System.out.print("Enter target currency (e.g., INR): ");
                Currency toCurrency = getValidCurrency();

                double result = converter.convert(amount, fromCurrency, toCurrency);
                System.out.printf("\n✔ %.2f %s = %.2f %s\n", amount, fromCurrency, result, toCurrency);

                LoggerUtil.log(amount + " " + fromCurrency + " to " + result + " " + toCurrency);

                System.out.print("\nDo you want to convert another? (yes/no): ");
                String again = scanner.nextLine().trim().toLowerCase();
                if (!again.equals("yes")) break;

            } catch (InvalidCurrencyException e) {
                System.err.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.err.println("⚠ Unexpected error: " + e.getMessage());
            }
        }

        System.out.println("👋 Thank you for using Currency Converter!");
    }

    private static double getValidAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) throw new NumberFormatException();
                return amount;
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid amount. Please enter a positive number: ");
            }
        }
    }

    private static Currency getValidCurrency() throws InvalidCurrencyException {
        String input = scanner.nextLine().trim().toUpperCase();
        if (!Currency.isValid(input)) {
            throw new InvalidCurrencyException("Currency '" + input + "' is not supported.");
        }
        return Currency.valueOf(input);
    }
}
