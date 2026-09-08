package com.josep.finance.view;

import com.josep.finance.model.Transaction;
import com.josep.finance.service.TransactionService;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionsView {

    private final TransactionService transactionService;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TransactionsView(
            TransactionService transactionService
    ) {
        this.transactionService = transactionService;
    }

    // Create transactions view

    public VBox createView() {

        VBox content = new VBox(25);
        content.setPadding(new Insets(35));
        content.getStyleClass().add("content");

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

        TableView<Transaction> table =
                createTransactionsTable();

        VBox.setVgrow(table, Priority.ALWAYS);

        addTransactionButton.setOnAction(event ->
                showAddTransactionDialog()
        );

        content.getChildren().addAll(
                header,
                table
        );

        return content;
    }

    // Create transactions table

    private TableView<Transaction> createTransactionsTable() {

        TableView<Transaction> table =
                new TableView<>();

        TableColumn<Transaction, String> dateColumn =
                new TableColumn<>("Date");

        TableColumn<Transaction, String> typeColumn =
                new TableColumn<>("Type");

        TableColumn<Transaction, String> categoryColumn =
                new TableColumn<>("Category");

        TableColumn<Transaction, String> descriptionColumn =
                new TableColumn<>("Description");

        TableColumn<Transaction, String> amountColumn =
                new TableColumn<>("Amount");

        dateColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getDate()
                                .format(dateFormatter)
                )
        );

        typeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getType()
                )
        );

        categoryColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCategory()
                )
        );

        descriptionColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDescription()
                )
        );

        amountColumn.setCellValueFactory(data -> {

            Transaction transaction =
                    data.getValue();

            String symbol =
                    transaction.getType().equals("Income")
                            ? "+"
                            : "-";

            return new SimpleStringProperty(
                    symbol
                            + transaction.getAmount()
                            + " €"
            );
        });

        table.getColumns().addAll(
                dateColumn,
                typeColumn,
                categoryColumn,
                descriptionColumn,
                amountColumn
        );

        table.setItems(
                transactionService.getTransactions()
        );

        return table;
    }

    // Add transaction dialog

    private void showAddTransactionDialog() {

        Dialog<Transaction> dialog =
                new Dialog<>();

        dialog.setTitle("Add Transaction");
        dialog.setHeaderText(
                "Create a new transaction"
        );

        ButtonType addButton =
                new ButtonType(
                        "Add",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        addButton,
                        ButtonType.CANCEL
                );

        ComboBox<String> typeBox =
                new ComboBox<>();

        typeBox.getItems().addAll(
                "Income",
                "Expense"
        );

        typeBox.setValue("Expense");

        TextField amountField =
                new TextField();

        amountField.setPromptText("Amount");

        ComboBox<String> categoryBox =
                new ComboBox<>();

        categoryBox.getItems().addAll(
                "Salary",
                "Food",
                "Transport",
                "Entertainment",
                "Shopping",
                "Other"
        );

        categoryBox.setValue("Food");

        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Description"
        );

        DatePicker datePicker =
                new DatePicker(LocalDate.now());

        VBox form = new VBox(12);
        form.setPadding(new Insets(10));

        form.getChildren().addAll(
                new Label("Type"),
                typeBox,

                new Label("Amount"),
                amountField,

                new Label("Category"),
                categoryBox,

                new Label("Description"),
                descriptionField,

                new Label("Date"),
                datePicker
        );

        dialog.getDialogPane()
                .setContent(form);

        dialog.setResultConverter(button -> {

            if (button != addButton) {
                return null;
            }

            try {

                BigDecimal amount =
                        new BigDecimal(
                                amountField.getText()
                        );

                if (amount.compareTo(
                        BigDecimal.ZERO) <= 0) {

                    showError(
                            "Amount must be greater than zero."
                    );

                    return null;
                }

                return new Transaction(
                        typeBox.getValue(),
                        amount,
                        categoryBox.getValue(),
                        descriptionField.getText(),
                        datePicker.getValue()
                );

            } catch (NumberFormatException exception) {

                showError(
                        "Please enter a valid amount."
                );

                return null;
            }
        });

        dialog.showAndWait()
                .ifPresent(
                        transactionService::addTransaction
                );
    }

    // Show validation error

    private void showError(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Invalid transaction");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}