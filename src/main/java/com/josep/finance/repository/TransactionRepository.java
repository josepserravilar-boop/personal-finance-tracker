package com.josep.finance.repository;

import com.josep.finance.model.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private static final String DATABASE_URL =
            "jdbc:sqlite:finance.db";

    // Initialize database

    public void initializeDatabase() {

        String sql = """
                CREATE TABLE IF NOT EXISTS transactions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT NOT NULL,
                    amount TEXT NOT NULL,
                    category TEXT NOT NULL,
                    description TEXT,
                    date TEXT NOT NULL
                )
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(DATABASE_URL);

                Statement statement =
                        connection.createStatement()
        ) {

            statement.execute(sql);

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Could not initialize database.",
                    exception
            );
        }
    }

    // Save transaction

    public void save(Transaction transaction) {

        String sql = """
                INSERT INTO transactions
                (type, amount, category, description, date)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(DATABASE_URL);

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    transaction.getType()
            );

            statement.setString(
                    2,
                    transaction.getAmount().toPlainString()
            );

            statement.setString(
                    3,
                    transaction.getCategory()
            );

            statement.setString(
                    4,
                    transaction.getDescription()
            );

            statement.setString(
                    5,
                    transaction.getDate().toString()
            );

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Could not save transaction.",
                    exception
            );
        }
    }

    // Find all transactions

    public List<Transaction> findAll() {

        List<Transaction> transactions =
                new ArrayList<>();

        String sql = """
                SELECT type, amount, category, description, date
                FROM transactions
                ORDER BY date DESC, id DESC
                """;

        try (
                Connection connection =
                        DriverManager.getConnection(DATABASE_URL);

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {

                Transaction transaction =
                        new Transaction(
                                resultSet.getString("type"),

                                new BigDecimal(
                                        resultSet.getString("amount")
                                ),

                                resultSet.getString("category"),

                                resultSet.getString("description"),

                                LocalDate.parse(
                                        resultSet.getString("date")
                                )
                        );

                transactions.add(transaction);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Could not load transactions.",
                    exception
            );
        }

        return transactions;
    }
}