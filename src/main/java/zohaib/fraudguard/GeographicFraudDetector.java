/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

import java.time.Duration;
import java.util.List;

public class GeographicFraudDetector {
    public static void main(String[] args) {

        TransactionDAO dao =
                new TransactionDAO();

        // Customer 3 = Michael Williams
        List<Transaction> transactions =
                dao.getTransactionsByCustomer(3);

        analyzeTransactions(transactions);
    }
    public static int getRiskForTransaction(
        List<Transaction> transactions,
        int transactionIndex) {

    if (transactionIndex <= 0
            || transactionIndex >= transactions.size()) {

        return 0;
    }

    Transaction previous =
            transactions.get(transactionIndex - 1);

    Transaction current =
            transactions.get(transactionIndex);

    double distance =
            calculateDistance(
                    previous.getLatitude(),
                    previous.getLongitude(),
                    current.getLatitude(),
                    current.getLongitude()
            );

    long minutesBetween =
            java.time.Duration.between(
                    previous.getTimestamp(),
                    current.getTimestamp()
            ).toMinutes();

    double hoursBetween =
            calculateHoursBetween(minutesBetween);

    double requiredSpeed =
            calculateRequiredSpeed(
                    distance,
                    hoursBetween
            );

    return calculateRiskPoints(requiredSpeed);
}

    private static final double EARTH_RADIUS_MILES = 3958.8;

    /*
     * Calculate the approximate straight-line distance
     * between two geographic coordinates.
     */
    public static double calculateDistance(
            double latitude1,
            double longitude1,
            double latitude2,
            double longitude2) {

        double lat1 = Math.toRadians(latitude1);
        double lon1 = Math.toRadians(longitude1);

        double lat2 = Math.toRadians(latitude2);
        double lon2 = Math.toRadians(longitude2);

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a =
                Math.sin(deltaLat / 2)
                * Math.sin(deltaLat / 2)
                + Math.cos(lat1)
                * Math.cos(lat2)
                * Math.sin(deltaLon / 2)
                * Math.sin(deltaLon / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS_MILES * c;
    }

    /*
     * Convert minutes into hours.
     */
    public static double calculateHoursBetween(long minutes) {

        return minutes / 60.0;
    }

    /*
     * Calculate the speed required to travel
     * the distance in the available time.
     */
    public static double calculateRequiredSpeed(
            double distanceMiles,
            double hoursBetween) {

        if (hoursBetween <= 0) {
            return Double.POSITIVE_INFINITY;
        }

        return distanceMiles / hoursBetween;
    }

    /*
     * Determine geographic risk points based
     * on the required travel speed.
     */
    public static int calculateRiskPoints(
            double requiredSpeed) {

        if (requiredSpeed < 100) {
            return 0;
        }

        if (requiredSpeed < 300) {
            return 5;
        }

        if (requiredSpeed < 500) {
            return 15;
        }

        if (requiredSpeed < 700) {
            return 25;
        }

        return 35;
    }

    /*
     * Analyze a customer's transactions
     * for geographic anomalies.
     */
    public static void analyzeTransactions(
            List<Transaction> transactions) {

        if (transactions.size() < 2) {

            System.out.println(
                    "Not enough transactions for geographic analysis."
            );

            return;
        }

        int totalRiskPoints = 0;

        for (int i = 1; i < transactions.size(); i++) {

            Transaction previous =
                    transactions.get(i - 1);

            Transaction current =
                    transactions.get(i);

            double distance =
                    calculateDistance(
                            previous.getLatitude(),
                            previous.getLongitude(),
                            current.getLatitude(),
                            current.getLongitude()
                    );

            long minutesBetween =
                    Duration.between(
                            previous.getTimestamp(),
                            current.getTimestamp()
                    ).toMinutes();

            double hoursBetween =
                    calculateHoursBetween(minutesBetween);

            double requiredSpeed =
                    calculateRequiredSpeed(
                            distance,
                            hoursBetween
                    );

            int riskPoints =
                    calculateRiskPoints(requiredSpeed);

            totalRiskPoints += riskPoints;

            System.out.println("--------------------------------");

            System.out.println(
                    "Previous Transaction: "
                    + previous.getTransactionId()
            );

            System.out.println(
                    "Current Transaction: "
                    + current.getTransactionId()
            );

            System.out.println(
                    "From: "
                    + previous.getCity()
                    + ", "
                    + previous.getState()
            );

            System.out.println(
                    "To: "
                    + current.getCity()
                    + ", "
                    + current.getState()
            );

            System.out.println(
                    "Time Between: "
                    + minutesBetween
                    + " minutes"
            );

            System.out.println(
                    "Distance: "
                    + String.format(
                            "%.2f",
                            distance
                    )
                    + " miles"
            );

            System.out.println(
                    "Required Speed: "
                    + String.format(
                            "%.2f",
                            requiredSpeed
                    )
                    + " mph"
            );

            System.out.println(
                    "Geographic Risk Points: "
                    + riskPoints
            );

            if (riskPoints > 0) {

                System.out.println(
                        "WARNING: Geographic anomaly detected."
                );
            }
        }

        System.out.println("--------------------------------");

        System.out.println(
                "Total Geographic Risk Points: "
                + totalRiskPoints
        );
    }

}