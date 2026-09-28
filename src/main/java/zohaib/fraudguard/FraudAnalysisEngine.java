/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zohaibakram
 */
package zohaib.fraudguard;

import java.util.List;

public class FraudAnalysisEngine {

    private final TransactionDAO transactionDAO;

    public FraudAnalysisEngine() {
        transactionDAO = new TransactionDAO();
    }

    public void analyzeCustomer(long customerId) {

        List<Transaction> transactions =
                transactionDAO.getTransactionsByCustomer(customerId);

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions found for customer "
                    + customerId
            );

            return;
        }

        System.out.println("========================================");
        System.out.println("FraudGuard Analysis");
        System.out.println("========================================");

        System.out.println(
                "Customer ID: " + customerId
        );

        System.out.println(
                "Transactions Found: "
                + transactions.size()
        );

        /*
         * Analyze each transaction individually.
         */
        for (int i = 0; i < transactions.size(); i++) {

            Transaction transaction =
                    transactions.get(i);

            int geographicRisk =
                    GeographicFraudDetector
                            .getRiskForTransaction(
                                    transactions,
                                    i
                            );

            int spendingRisk =
                    SpendingBaselineDetector
                            .getRiskForTransaction(
                                    transactions,
                                    i
                            );

            int velocityRisk =
                    TransactionVelocityDetector
                            .getRiskForTransaction(
                                    transactions,
                                    i
                            );

            int riskScore =
                    RiskScoreEngine.calculateScore(
                            geographicRisk,
                            spendingRisk,
                            velocityRisk
                    );

            String riskLevel =
                    RiskScoreEngine.determineRiskLevel(
                            riskScore
                    );

            System.out.println();
            System.out.println("----------------------------------------");

            System.out.println(
                    "Transaction ID: "
                    + transaction.getTransactionId()
            );

            System.out.println(
                    "Amount: $"
                    + String.format(
                            "%.2f",
                            transaction.getAmount()
                    )
            );

            System.out.println(
                    "Location: "
                    + transaction.getCity()
                    + ", "
                    + transaction.getState()
            );

            System.out.println();

            System.out.println(
                    "Geographic Risk: "
                    + geographicRisk
            );

            System.out.println(
                    "Spending Risk: "
                    + spendingRisk
            );

            System.out.println(
                    "Velocity Risk: "
                    + velocityRisk
            );

            System.out.println();

            System.out.println(
                    "Risk Score: "
                    + riskScore
                    + " / 100"
            );

            System.out.println(
                    "Risk Level: "
                    + riskLevel
            );

            if (riskScore >= 60) {

                System.out.println(
                        "WARNING: Potentially suspicious transaction."
                );
            }
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("Analysis Complete");
        System.out.println("========================================");
    }
    public List<TransactionRiskResult> analyzeCustomerResults(
        long customerId) {

    List<TransactionRiskResult> results =
            new java.util.ArrayList<>();

    List<Transaction> transactions =
            transactionDAO.getTransactionsByCustomer(customerId);

    for (int i = 0; i < transactions.size(); i++) {

        Transaction transaction =
                transactions.get(i);

        int geographicRisk =
                GeographicFraudDetector
                        .getRiskForTransaction(
                                transactions,
                                i
                        );

        int spendingRisk =
                SpendingBaselineDetector
                        .getRiskForTransaction(
                                transactions,
                                i
                        );

        int velocityRisk =
                TransactionVelocityDetector
                        .getRiskForTransaction(
                                transactions,
                                i
                        );

        int riskScore =
                RiskScoreEngine.calculateScore(
                        geographicRisk,
                        spendingRisk,
                        velocityRisk
                );

        String riskLevel =
                RiskScoreEngine.determineRiskLevel(
                        riskScore
                );

        TransactionRiskResult result =
                new TransactionRiskResult(
                        transaction.getTransactionId(),
                        geographicRisk,
                        spendingRisk,
                        velocityRisk,
                        riskScore,
                        riskLevel
                );

        results.add(result);
    }

    return results;
}

    public static void main(String[] args) {

        FraudAnalysisEngine engine =
                new FraudAnalysisEngine();

        /*
         * Customer 3 = Michael Williams
         */
        engine.analyzeCustomer(3);
    }
}