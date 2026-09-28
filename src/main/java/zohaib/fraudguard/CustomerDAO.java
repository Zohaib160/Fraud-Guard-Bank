/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

/**
 *
 * @author zohaibakram
 */
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public Customer findBySSN(String ssn) {

        String sql =
                "SELECT * FROM customers " +
                "WHERE ssn_token = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, ssn);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return mapCustomer(result);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
    public List<Customer> searchByName(
        String firstName,
        String lastName) {

    List<Customer> customers =
            new ArrayList<>();

    String sql =
            "SELECT * FROM customers " +
            "WHERE LOWER(first_name) = LOWER(?) " +
            "AND LOWER(last_name) = LOWER(?) " +
            "ORDER BY last_name, first_name";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setString(1, firstName);
        statement.setString(2, lastName);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                customers.add(
                        mapCustomer(result)
                );
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return customers;
}
    public List<Customer> searchByPhone(
        String phone) {

    List<Customer> customers =
            new ArrayList<>();

    String sql =
            "SELECT * FROM customers " +
            "WHERE phone = ? " +
            "ORDER BY last_name, first_name";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setString(1, phone);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                customers.add(
                        mapCustomer(result)
                );
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return customers;
}
    public List<Customer> searchBySSN(
        String ssn) {

    List<Customer> customers =
            new ArrayList<>();

    String sql =
            "SELECT * FROM customers " +
            "WHERE ssn_token = ?";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setString(1, ssn);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                customers.add(
                        mapCustomer(result)
                );
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return customers;
}
    public List<Customer> searchByAccount(
        String accountNumber,
        String accountType) {

    List<Customer> customers =
            new ArrayList<>();

    String sql =
            "SELECT * FROM customers " +
            "WHERE account_number = ? " +
            "AND account_type = ?";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setString(1, accountNumber);
        statement.setString(2, accountType);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                customers.add(
                        mapCustomer(result)
                );
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return customers;
}
    public List<Customer> searchByEmail(
        String email) {

    List<Customer> customers =
            new ArrayList<>();

    String sql =
            "SELECT * FROM customers " +
            "WHERE LOWER(email) = LOWER(?) " +
            "ORDER BY last_name, first_name";

    try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
    ) {

        statement.setString(1, email);

        try (ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                customers.add(
                        mapCustomer(result)
                );
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return customers;
}
    

    public Customer findByName(
            String firstName,
            String lastName) {

        String sql =
                "SELECT * FROM customers " +
                "WHERE LOWER(first_name) = LOWER(?) " +
                "AND LOWER(last_name) = LOWER(?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return mapCustomer(result);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public Customer findByAccount(
            String accountNumber,
            String accountType) {

        String sql =
                "SELECT * FROM customers " +
                "WHERE account_number = ? " +
                "AND account_type = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, accountNumber);
            statement.setString(2, accountType);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return mapCustomer(result);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public Customer findByPhone(String phone) {

        String sql =
                "SELECT * FROM customers " +
                "WHERE phone = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, phone);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return mapCustomer(result);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public Customer findByEmail(String email) {

        String sql =
                "SELECT * FROM customers " +
                "WHERE LOWER(email) = LOWER(?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return mapCustomer(result);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    private Customer mapCustomer(ResultSet result)
            throws SQLException {

        return new Customer(
                result.getLong("customer_id"),
                result.getString("first_name"),
                result.getString("middle_name"),
                result.getString("last_name"),
                result.getDate("date_of_birth")
                        .toLocalDate(),
                result.getString("ssn_token"),
                result.getString("account_number"),
                result.getString("account_type"),
                result.getString("phone"),
                result.getString("email"),
                result.getString("street"),
                result.getString("city"),
                result.getString("state"),
                result.getString("zip"),
                result.getDate("account_opening_date")
                        .toLocalDate()
        );
    }
}
