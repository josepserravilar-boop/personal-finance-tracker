package com.josep.finance.view;

import com.josep.finance.service.TransactionService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;

public class DashboardView {

    private final TransactionService transactionService;

    public DashboardView(
            TransactionService transactionService
    ) {
        this.transactionService = transactionService;
    }

    // Create dashboard

    public VBox createView() {

        VBox content = new VBox(25);
        content.setPadding(new Insets(35));
        content.getStyleClass().add("content");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("page-title");

        HBox cards = new HBox(20);

        BigDecimal balance =
                transactionService.getBalance();

        BigDecimal income =
                transactionService.getTotalIncome();

        BigDecimal expenses =
                transactionService.getTotalExpenses();

        VBox balanceCard = createCard(
                "Current Balance",
                formatMoney(balance)
        );

        VBox incomeCard = createCard(
                "Income",
                formatMoney(income)
        );

        VBox expensesCard = createCard(
                "Expenses",
                formatMoney(expenses)
        );

        VBox savingsCard = createCard(
                "Savings",
                formatMoney(balance)
        );

        cards.getChildren().addAll(
                balanceCard,
                incomeCard,
                expensesCard,
                savingsCard
        );

        content.getChildren().addAll(
                title,
                cards
        );

        return content;
    }

    // Create dashboard card

    private VBox createCard(
            String title,
            String value
    ) {

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("card-title");

        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("card-value");

        VBox card = new VBox(10);

        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(20));
        card.setPrefWidth(180);
        card.setPrefHeight(120);

        card.getStyleClass().add("card");

        card.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return card;
    }

    // Format monetary values

    private String formatMoney(BigDecimal amount) {
        return String.format("%.2f €", amount);
    }
}