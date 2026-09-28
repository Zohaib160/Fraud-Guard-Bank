/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

import java.time.Duration;
import java.util.List;

public class TransactionVelocityDetector {

    private static final long WINDOW_MINUTES = 5;

    public static int calculateRiskPoints(
            int transactionCount) {

        if (transactionCount <= 2) {
            return 0;
        }

        if (transactionCount == 3) {
            return 10;
        }

        if (transactionCount == 4) {
            return 20;
        }

        return 30;
    }

    public static void analyzeTransactions(
            List<Transaction> transactions) {

        if (transactions.size() < 2) {

            System.out.println(
                    "Not enough transactions for velocity analysis."
            );

            return;
        }

        int highestRiskPoints = 0;

        for (int i = 0; i < transactions.size(); i++) {

            Transaction current =
                    transactions.get(i);

            int transactionCount = 0;

            /*
             * Look backward from the current transaction
             * and count transactions within the time window.
             */
            for (int j = i; j >= 0; j--) {

                Transaction previous =
                        transactions.get(j);

                long minutesBetween =
                        Duration.between(
                                previous.getTimestamp(),
                                current.getTimestamp()
                        ).toMinutes();

                if (minutesBetween <= WINDOW_MINUTES) {

                    transactionCount++;

                } else {

                    break;
                }
            }

            int riskPoints =
                    calculateRiskPoints(
                            transactionCount
                    );

            if (riskPoints > highestRiskPoints) {
                highestRiskPoints = riskPoints;
            }

            System.out.println("--------------------------------");

            System.out.println(
                    "Current Transaction: "
                    + current.getTransactionId()
            );

            System.out.println(
                    "Transactions in last "
                    + WINDOW_MINUTES
                    + " minutes: "
                    + transactionCount
            );

            System.out.println(
                    "Velocity Risk Points: "
                    + riskPoints
            );

            if (riskPoints > 0) {

                System.out.println(
                        "WARNING: High transaction velocity detected."
                );
            }
        }

        System.out.println("--------------------------------");

        System.out.println(
                "Highest Velocity Risk Points: "
                + highestRiskPoints
        );
    }
    public static int getRiskForTransaction(
        List<Transaction> transactions,
        int transactionIndex) {

    if (transactionIndex < 0
            || transactionIndex >= transactions.size()) {

        return 0;
    }

    Transaction current =
            transactions.get(transactionIndex);

    int transactionCount = 0;

    /*
     * Count transactions that occurred within
     * the previous 5 minutes, including current.
     */
    for (int i = transactionIndex; i >= 0; i--) {

        Transaction previous =
                transactions.get(i);

        long minutesBetween =
                java.time.Duration.between(
                        previous.getTimestamp(),
                        current.getTimestamp()
                ).toMinutes();

        if (minutesBetween <= WINDOW_MINUTES) {

            transactionCount++;

        } else {

            break;
        }
    }

    return calculateRiskPoints(transactionCount);
}

    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        /*
         * Customer 2 = Sarah Johnson
         */
        List<Transaction> transactions =
                dao.getTransactionsByCustomer(2);

        analyzeTransactions(transactions);
    }
}