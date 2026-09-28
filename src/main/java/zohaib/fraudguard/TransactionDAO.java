/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    
    

    public List<Transaction> getTransactionsByCustomer(
            long customerId) {

        List<Transaction> transactions = new ArrayList<>();

        String sql =
                "SELECT " +
                "transaction_id, " +
                "customer_id, " +
                "transaction_timestamp, " +
                "amount, " +
                "transaction_type, " +
                "merchant_name, " +
                "merchant_category, " +
                "city, " +
                "state, " +
                "latitude, " +
                "longitude, " +
                "device_id, " +
                "ip_address, " +
                "status " +
                "FROM transactions " +
                "WHERE customer_id = ? " +
                "ORDER BY transaction_timestamp ASC";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setLong(1, customerId);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    Timestamp timestamp =
                            result.getTimestamp(
                                    "transaction_timestamp"
                            );

                    Transaction transaction =
                            new Transaction(
                                    result.getLong("transaction_id"),
                                    result.getLong("customer_id"),
                                    timestamp.toLocalDateTime(),
                                    result.getDouble("amount"),
                                    result.getString("transaction_type"),
                                    result.getString("merchant_name"),
                                    result.getString("merchant_category"),
                                    result.getString("city"),
                                    result.getString("state"),
                                    result.getDouble("latitude"),
                                    result.getDouble("longitude"),
                                    result.getString("device_id"),
                                    result.getString("ip_address"),
                                    result.getString("status")
                            );

                    transactions.add(transaction);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading transactions:"
            );

            e.printStackTrace();
        }

        return transactions;
    }
    public List<Transaction> getRecentTransactionsByCustomer(
        long customerId) {

    List<Transaction> transactions = new ArrayList<>();

    String sql =
            "SELECT " +
            "transaction_id, " +
            "customer_id, " +
            "transaction_timestamp, " +
            "amount, " +
            "transaction_type, " +
            "merchant_name, " +
            "merchant_category, " +
            "city, " +
            "state, " +
            "latitude, " +
            "longitude, " +
            "device_id, " +
            "ip_address, " +
            "status " +
            "FROM transactions " +
            "WHERE customer_id = ? " +
            "ORDER BY transaction_timestamp DESC " +
            "LIMIT 10";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {
        statement.setLong(1, customerId);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Timestamp timestamp =
                        result.getTimestamp(
                                "transaction_timestamp"
                        );

                Transaction transaction =
                        new Transaction(
                                result.getLong("transaction_id"),
                                result.getLong("customer_id"),
                                timestamp.toLocalDateTime(),
                                result.getDouble("amount"),
                                result.getString("transaction_type"),
                                result.getString("merchant_name"),
                                result.getString("merchant_category"),
                                result.getString("city"),
                                result.getString("state"),
                                result.getDouble("latitude"),
                                result.getDouble("longitude"),
                                result.getString("device_id"),
                                result.getString("ip_address"),
                                result.getString("status")
                        );

                transactions.add(transaction);
            }
        }

    } catch (SQLException e) {

        System.out.println(
                "Error loading recent transactions:"
        );

        e.printStackTrace();
    }

    return transactions;
}
    public List<Transaction> getAllTransactionsByCustomer(
        long customerId) {

    List<Transaction> transactions = new ArrayList<>();

    String sql =
            "SELECT " +
            "transaction_id, " +
            "customer_id, " +
            "transaction_timestamp, " +
            "amount, " +
            "transaction_type, " +
            "merchant_name, " +
            "merchant_category, " +
            "city, " +
            "state, " +
            "latitude, " +
            "longitude, " +
            "device_id, " +
            "ip_address, " +
            "status " +
            "FROM transactions " +
            "WHERE customer_id = ? " +
            "ORDER BY transaction_timestamp DESC";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setLong(1, customerId);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Timestamp timestamp =
                        result.getTimestamp(
                                "transaction_timestamp"
                        );

                Transaction transaction =
                        new Transaction(
                                result.getLong("transaction_id"),
                                result.getLong("customer_id"),
                                timestamp.toLocalDateTime(),
                                result.getDouble("amount"),
                                result.getString("transaction_type"),
                                result.getString("merchant_name"),
                                result.getString("merchant_category"),
                                result.getString("city"),
                                result.getString("state"),
                                result.getDouble("latitude"),
                                result.getDouble("longitude"),
                                result.getString("device_id"),
                                result.getString("ip_address"),
                                result.getString("status")
                        );

                transactions.add(transaction);
            }
        }

    } catch (SQLException e) {

        System.out.println(
                "Error loading all transactions:"
        );

        e.printStackTrace();
    }

    return transactions;
}
    
}