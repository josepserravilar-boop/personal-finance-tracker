package com.josep.finance;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.josep.finance.view.*;


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

        Label logo = new Label("Finance");
        logo.getStyleClass().add("logo");

        Button dashboardButton = new Button(
                "Dashboard",
                createIcon("dashboard.png")
        );

        Button transactionsButton = new Button(
                "Transactions",
                createIcon("transactions.png")
        );

        Button reportsButton = new Button(
                "Reports",
                createIcon("reports.png")
        );

        Button settingsButton = new Button(
                "Settings",
                createIcon("settings.png")
        );

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
        // DASHBOARD CONTENT
        // =========================

        VBox dashboardContent = new VBox(25);
        dashboardContent.setPadding(new Insets(35));
        dashboardContent.getStyleClass().add("content");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("page-title");

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

        dashboardContent.getChildren().addAll(
                title,
                cards
        );


        // =========================
        // TRANSACTIONS VIEW
        // =========================

        TransactionsView transactionsView =
                new TransactionsView();


        // =========================
        // NAVIGATION
        // =========================

        dashboardButton.setOnAction(event -> {
            root.setCenter(dashboardContent);
        });

        transactionsButton.setOnAction(event -> {
            root.setCenter(
                    transactionsView.createView()
            );
        });


        // =========================
        // BUILD MAIN WINDOW
        // =========================

        root.setLeft(sidebar);
        root.setCenter(dashboardContent);

        Scene scene = new Scene(root, 1100, 700);

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
    // CREATE MENU ICON
    // =========================

    private ImageView createIcon(String fileName) {

        Image image = new Image(
                getClass().getResourceAsStream(
                        "/icons/" + fileName
                )
        );

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);

        return imageView;
    }


    // =========================
    // APPLICATION ENTRY POINT
    // =========================

    public static void main(String[] args) {
        launch(args);
    }
}