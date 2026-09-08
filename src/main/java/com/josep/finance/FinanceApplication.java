package com.josep.finance;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinanceApplication extends Application {

    @Override
    public void start(Stage stage) {

        // =========================
        // MAIN LAYOUT
        // =========================

        BorderPane root = new BorderPane();


        // =========================
        // SIDEBAR
        // =========================

        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(30));
        sidebar.setPrefWidth(220);
        sidebar.getStyleClass().add("sidebar");

        Label logo = new Label("💰 Finance");
        logo.getStyleClass().add("logo");

        Button dashboardButton = new Button("🏠 Dashboard");
        Button transactionsButton = new Button("💳 Transactions");
        Button reportsButton = new Button("📊 Reports");
        Button settingsButton = new Button("⚙ Settings");

        dashboardButton.getStyleClass().add("menu-button");
        transactionsButton.getStyleClass().add("menu-button");
        reportsButton.getStyleClass().add("menu-button");
        settingsButton.getStyleClass().add("menu-button");

        sidebar.getChildren().addAll(
                logo,
                dashboardButton,
                transactionsButton,
                reportsButton,
                settingsButton
        );


        // =========================
        // MAIN CONTENT
        // =========================

        VBox content = new VBox(25);
        content.setPadding(new Insets(35));
        content.getStyleClass().add("content");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("page-title");


        // =========================
        // DASHBOARD CARDS
        // =========================

        HBox cards = new HBox(20);

        VBox balanceCard = createCard(
                "Current Balance",
                "2,450.00 €"
        );

        VBox incomeCard = createCard(
                "Income",
                "1,800.00 €"
        );

        VBox expensesCard = createCard(
                "Expenses",
                "950.00 €"
        );

        VBox savingsCard = createCard(
                "Savings",
                "850.00 €"
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


        // =========================
        // BUILD MAIN WINDOW
        // =========================

        root.setLeft(sidebar);
        root.setCenter(content);

        Scene scene = new Scene(root, 1100, 700);

        // Load CSS
        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles.css")
                        .toExternalForm()
        );

        stage.setTitle("Personal Finance Tracker");
        stage.setScene(scene);
        stage.show();
    }


    // =========================
    // CREATE DASHBOARD CARD
    // =========================

    private VBox createCard(String title, String value) {

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


    // =========================
    // APPLICATION ENTRY POINT
    // =========================

    public static void main(String[] args) {
        launch(args);
    }
}