/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

public class TransactionRiskResult {

    private final long transactionId;
    private final int geographicRisk;
    private final int spendingRisk;
    private final int velocityRisk;
    private final int riskScore;
    private final String riskLevel;

    public TransactionRiskResult(
            long transactionId,
            int geographicRisk,
            int spendingRisk,
            int velocityRisk,
            int riskScore,
            String riskLevel) {

        this.transactionId = transactionId;
        this.geographicRisk = geographicRisk;
        this.spendingRisk = spendingRisk;
        this.velocityRisk = velocityRisk;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public int getGeographicRisk() {
        return geographicRisk;
    }

    public int getSpendingRisk() {
        return spendingRisk;
    }

    public int getVelocityRisk() {
        return velocityRisk;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }
}