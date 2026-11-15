package ua.edu.sumdu.j2se.pr4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.UUID;

/**
 * JavaFX додаток для управління магазином одягу та пошуку за UUID.
 */
public class MainApp extends Application {
    private Store store = new Store("Модний Стиль (GUI)");
    private ListView<String> listView = new ListView<>();
    private TextArea resultArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Магазин одягу - Управління та UUID пошук");

        // Головний контейнер
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // --- Блок 1: Форма додавання товару ---
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        TextField nameField = new TextField();
        TextField sizeField = new TextField();
        TextField priceField = new TextField();
        TextField qtyField = new TextField();

        ComboBox<ClothingCategory> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll(ClothingCategory.values());
        categoryBox.setValue(ClothingCategory.OUTERWEAR);

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Clothes", "Pants", "Shirts", "Jacket", "Socks");
        typeBox.setValue("Clothes");

        formGrid.add(new Label("Тип:"), 0, 0);
        formGrid.add(typeBox, 1, 0);
        formGrid.add(new Label("Назва:"), 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(new Label("Розмір:"), 0, 2);
        formGrid.add(sizeField, 1, 2);
        formGrid.add(new Label("Ціна:"), 0, 3);
        formGrid.add(priceField, 1, 3);
        formGrid.add(new Label("Кількість:"), 0, 4);
        formGrid.add(qtyField, 1, 4);
        formGrid.add(new Label("Категорія:"), 0, 5);
        formGrid.add(categoryBox, 1, 5);

        Button addButton = new Button("Додати товар");
        formGrid.add(addButton, 1, 6);

        // --- Блок 2: Список всіх товарів (коротко) ---
        root.getChildren().add(new Label("Каталог товарів (Назва | UUID):"));
        listView.setPrefHeight(150);
        root.getChildren().add(formGrid);
        root.getChildren().add(listView);

        // --- Блок 3: Пошук за UUID ---
        VBox searchBox = new VBox(5);
        TextField uuidSearchField = new TextField();
        uuidSearchField.setPromptText("Введіть UUID для пошуку...");
        Button searchButton = new Button("Знайти за UUID");

        resultArea.setEditable(false);
        resultArea.setPrefHeight(100);

        searchBox.getChildren().addAll(new Label("Пошук об'єкта за UUID:"), uuidSearchField, searchButton, new Label("Результат пошуку:"), resultArea);
        root.getChildren().add(searchBox);

        // Обробник події кнопки "Додати"
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String size = sizeField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qty = Integer.parseInt(qtyField.getText());
                ClothingCategory cat = categoryBox.getValue();
                String type = typeBox.getValue();

                Clothes item = null;
                switch (type) {
                    case "Pants":
                        item = new Pants(name, size, price, 1, cat, true);
                        break;
                    case "Shirts":
                        item = new Shirts(name, size, price, 1, cat, "довгий");
                        break;
                    case "Jacket":
                        item = new Jacket(name, size, price, 1, cat, true);
                        break;
                    case "Socks":
                        item = new Socks(name, size, price, 1, cat, "бавовна");
                        break;
                    default:
                        item = new Clothes(name, size, price, 1, cat) {};
                        break;
                }

                store.addNewClothes(item, qty);
                refreshListView();

                // Очищення полів
                nameField.clear();
                sizeField.clear();
                priceField.clear();
                qtyField.clear();
            } catch (Exception ex) {
                showAlert("Помилка вводу", "Перевірте коректність заповнених полів: " + ex.getMessage());
            }
        });

        // Обробник події кнопки "Знайти"
        searchButton.setOnAction(e -> {
            String uuidText = uuidSearchField.getText().trim();
            if (uuidText.isEmpty()) {
                resultArea.setText("Поле введення UUID порожнє.");
                return;
            }
            try {
                UUID targetUuid = UUID.fromString(uuidText);
                Clothes found = store.searchByUuid(targetUuid);
                if (found != null) {
                    resultArea.setText("Знайдено об'єкт:\n" + found.toString());
                } else {
                    resultArea.setText("Об'єкт із таким UUID не знайдено.");
                }
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Помилка: некоректний формат UUID! Перевірте правильність рядка.");
            }
        });

        Scene scene = new Scene(root, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshListView() {
        listView.getItems().clear();
        for (Clothes item : store.getClothesList()) {
            listView.getItems().add(item.getShortInfo());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}