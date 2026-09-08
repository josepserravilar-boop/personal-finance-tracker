package com.josep.finance.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TransactionsView {

    public VBox createView() {

        // Main content

        VBox content = new VBox(25);
        content.setPadding(new Insets(35));
        content.getStyleClass().add("content");

        // Header

        Label title = new Label("Transactions");
        title.getStyleClass().add("page-title");

        Button addTransactionButton =
                new Button("+ Add Transaction");

        addTransactionButton
                .getStyleClass()
                .add("primary-button");

        HBox header = new HBox();

        HBox.setHgrow(title, Priority.ALWAYS);

        header.getChildren().addAll(
                title,
                addTransactionButton
        );

        // Transactions table

        TableView<TransactionRow> table =
                new TableView<>();

        TableColumn<TransactionRow, String> dateColumn =
                new TableColumn<>("Date");

        TableColumn<TransactionRow, String> typeColumn =
                new TableColumn<>("Type");

        TableColumn<TransactionRow, String> categoryColumn =
                new TableColumn<>("Category");

        TableColumn<TransactionRow, String> descriptionColumn =
                new TableColumn<>("Description");

        TableColumn<TransactionRow, String> amountColumn =
                new TableColumn<>("Amount");

        dateColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().date()
                        )
        );

        typeColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().type()
                        )
        );

        categoryColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().category()
                        )
        );

        descriptionColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().description()
                        )
        );

        amountColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().amount()
                        )
        );

        table.getColumns().addAll(
                dateColumn,
                typeColumn,
                categoryColumn,
                descriptionColumn,
                amountColumn
        );

        // Temporary data

        ObservableList<TransactionRow> transactions =
                FXCollections.observableArrayList(
                        new TransactionRow(
                                "08/09/2026",
                                "Expense",
                                "Food",
                                "Supermarket",
                                "-45.90 €"
                        ),
                        new TransactionRow(
                                "07/09/2026",
                                "Income",
                                "Salary",
                                "Monthly salary",
                                "+1800.00 €"
                        ),
                        new TransactionRow(
                                "06/09/2026",
                                "Expense",
                                "Transport",
                                "Train ticket",
                                "-25.00 €"
                        )
                );

        table.setItems(transactions);

        // Layout

        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(
                header,
                table
        );

        return content;
    }


    // Temporary table model

    private record TransactionRow(
            String date,
            String type,
            String category,
            String description,
            String amount
    ) {
    }
}