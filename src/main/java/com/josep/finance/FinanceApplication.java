package com.josep.finance;

import com.josep.finance.service.TransactionService;
import com.josep.finance.view.DashboardView;
import com.josep.finance.view.TransactionsView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinanceApplication extends Application {

    @Override
    public void start(Stage stage) {

        // Main layout

        BorderPane root = new BorderPane();

        // Shared service

        TransactionService transactionService =
                new TransactionService();

        DashboardView dashboardView =
                new DashboardView(transactionService);

        TransactionsView transactionsView =
                new TransactionsView(transactionService);

        // Sidebar

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

        dashboardButton.getStyleClass()
                .add("menu-button");

        transactionsButton.getStyleClass()
                .add("menu-button");

        reportsButton.getStyleClass()
                .add("menu-button");

        settingsButton.getStyleClass()
                .add("menu-button");

        sidebar.getChildren().addAll(
                logo,
                dashboardButton,
                transactionsButton,
                reportsButton,
                settingsButton
        );

        // Navigation

        dashboardButton.setOnAction(event ->
                root.setCenter(
                        dashboardView.createView()
                )
        );

        transactionsButton.setOnAction(event ->
                root.setCenter(
                        transactionsView.createView()
                )
        );

        // Build main window

        root.setLeft(sidebar);
        root.setCenter(
                dashboardView.createView()
        );

        Scene scene =
                new Scene(root, 1100, 700);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles.css")
                        .toExternalForm()
        );

        stage.setTitle(
                "Personal Finance Tracker"
        );

        stage.setScene(scene);
        stage.show();
    }

    // Create menu icon

    private ImageView createIcon(String fileName) {

        Image image = new Image(
                getClass().getResourceAsStream(
                        "/icons/" + fileName
                )
        );

        ImageView imageView =
                new ImageView(image);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    // Application entry point

    public static void main(String[] args) {
        launch(args);
    }
}