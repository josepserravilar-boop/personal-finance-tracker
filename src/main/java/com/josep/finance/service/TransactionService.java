package com.josep.finance.service;

import com.josep.finance.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class TransactionService {

    private final ObservableList<Transaction> transactions =
            FXCollections.observableArrayList();

    // Get transactions

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    // Add transaction

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // Calculate total income

    public BigDecimal getTotalIncome() {

        return transactions.stream()
                .filter(transaction ->
                        transaction.getType().equals("Income"))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Calculate total expenses

    public BigDecimal getTotalExpenses() {

        return transactions.stream()
                .filter(transaction ->
                        transaction.getType().equals("Expense"))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Calculate current balance

    public BigDecimal getBalance() {
        return getTotalIncome()
                .subtract(getTotalExpenses());
    }
}