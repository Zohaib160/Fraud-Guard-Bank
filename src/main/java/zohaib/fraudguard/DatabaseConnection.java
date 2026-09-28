/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/fraudguard";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "YOUR_POSTGRES_PASSWORD";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    public static void main(String[] args) {

        try (Connection connection = getConnection()) {

            System.out.println(
                    "FraudGuard database connected successfully!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed!"
            );

            e.printStackTrace();
        }
    }
}