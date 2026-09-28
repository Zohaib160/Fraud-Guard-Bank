/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

public class RiskScoreEngine {

    private static final int MAX_RAW_SCORE = 95;

    /*
     * Combine the individual detector scores
     * into one 0-100 risk score.
     */
    public static int calculateScore(
            int geographicRisk,
            int spendingRisk,
            int velocityRisk) {

        int rawScore =
                geographicRisk
                + spendingRisk
                + velocityRisk;

        double normalizedScore =
                (rawScore / (double) MAX_RAW_SCORE) * 100;

        return (int) Math.round(normalizedScore);
    }

    /*
     * Convert the numerical score into
     * an understandable risk level.
     */
    public static String determineRiskLevel(
            int score) {

        if (score < 30) {
            return "NORMAL";
        }

        if (score < 60) {
            return "LOW";
        }

        if (score < 80) {
            return "HIGH";
        }

        return "CRITICAL";
    }

    public static void analyzeTransaction(
            long transactionId,
            int geographicRisk,
            int spendingRisk,
            int velocityRisk) {

        int score =
                calculateScore(
                        geographicRisk,
                        spendingRisk,
                        velocityRisk
                );

        String riskLevel =
                determineRiskLevel(score);

        System.out.println("--------------------------------");
        System.out.println(
                "FraudGuard Transaction Analysis"
        );
        System.out.println("--------------------------------");

        System.out.println(
                "Transaction ID: "
                + transactionId
        );

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

        System.out.println("--------------------------------");

        System.out.println(
                "Risk Score: "
                + score
                + " / 100"
        );

        System.out.println(
                "Risk Level: "
                + riskLevel
        );

        if (score >= 60) {

            System.out.println(
                    "WARNING: Potentially suspicious transaction."
            );

        } else {

            System.out.println(
                    "Transaction does not currently show "
                    + "a high level of risk."
            );
        }
    }

    public static void main(String[] args) {

        /*
         * Test transaction 208.
         *
         * Geographic = 35
         * Spending   = 30
         * Velocity   = 0
         */
        analyzeTransaction(
                208,
                35,
                30,
                0
        );
    }
}
