/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

import java.util.List;

public class SpendingBaselineDetector {

    /*
     * Calculate the average transaction amount.
     */
    public static double calculateAverage(
            List<Transaction> transactions) {

        if (transactions.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }

        return total / transactions.size();
    }

    /*
     * Calculate how many times larger the current
     * transaction is compared to the customer's average.
     */
    public static double calculateDeviationMultiplier(
            double currentAmount,
            double averageAmount) {

        if (averageAmount <= 0) {
            return 0;
        }

        return currentAmount / averageAmount;
    }

    /*
     * Assign risk points based on how far the
     * transaction is from the customer's normal spending.
     */
    public static int calculateRiskPoints(
            double multiplier) {

        if (multiplier < 2) {
            return 0;
        }

        if (multiplier < 5) {
            return 5;
        }

        if (multiplier < 10) {
            return 10;
        }

        if (multiplier < 20) {
            return 20;
        }

        return 30;
    }

    /*
     * Analyze a customer's transactions.
     */
    public static void analyzeTransactions(
        List<Transaction> transactions) {

    if (transactions.size() < 2) {

        System.out.println(
                "Not enough transactions for spending analysis."
        );

        return;
    }

    int totalRiskPoints = 0;

    for (int i = 0; i < transactions.size(); i++) {

        Transaction current = transactions.get(i);

        // We need previous transactions to establish a baseline.
        if (i == 0) {

            System.out.println("--------------------------------");

            System.out.println(
                    "Transaction ID: "
                    + current.getTransactionId()
            );

            System.out.println(
                    "Transaction Amount: $"
                    + String.format(
                            "%.2f",
                            current.getAmount()
                    )
            );

            System.out.println(
                    "Not enough historical data for baseline."
            );

            continue;
        }

        /*
         * Only use transactions BEFORE the current
         * transaction to calculate the baseline.
         */
        double previousTotal = 0;

        for (int j = 0; j < i; j++) {

            previousTotal +=
                    transactions.get(j).getAmount();
        }

        double averageAmount =
                previousTotal / i;

        double multiplier =
                calculateDeviationMultiplier(
                        current.getAmount(),
                        averageAmount
                );

        int riskPoints =
                calculateRiskPoints(multiplier);

        totalRiskPoints += riskPoints;

        System.out.println("--------------------------------");

        System.out.println(
                "Transaction ID: "
                + current.getTransactionId()
        );

        System.out.println(
                "Transaction Amount: $"
                + String.format(
                        "%.2f",
                        current.getAmount()
                )
        );

        System.out.println(
                "Previous Average: $"
                + String.format(
                        "%.2f",
                        averageAmount
                )
        );

        System.out.println(
                "Spending Multiplier: "
                + String.format(
                        "%.2f",
                        multiplier
                )
                + "x"
        );

        System.out.println(
                "Spending Risk Points: "
                + riskPoints
        );

        if (riskPoints > 0) {

            System.out.println(
                    "WARNING: Unusual spending amount detected."
            );
        }
    }

    System.out.println("--------------------------------");

    System.out.println(
            "Total Spending Risk Points: "
            + totalRiskPoints
    );
}
    public static int getRiskForTransaction(
        List<Transaction> transactions,
        int transactionIndex) {

    if (transactionIndex < 0
            || transactionIndex >= transactions.size()) {

        return 0;
    }

    // First transaction has no historical baseline.
    if (transactionIndex == 0) {
        return 0;
    }

    Transaction current =
            transactions.get(transactionIndex);

    double previousTotal = 0;

    /*
     * Calculate the average using only transactions
     * that happened before the current transaction.
     */
    for (int i = 0; i < transactionIndex; i++) {

        previousTotal +=
                transactions.get(i).getAmount();
    }

    double averageAmount =
            previousTotal / transactionIndex;

    double multiplier =
            calculateDeviationMultiplier(
                    current.getAmount(),
                    averageAmount
            );

    return calculateRiskPoints(multiplier);
}

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        /*
         * Customer 3 = Michael Williams
         */
        List<Transaction> transactions =
                dao.getTransactionsByCustomer(3);

        analyzeTransactions(transactions);
    }
}
