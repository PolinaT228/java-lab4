package ua.edu.sumdu.j2se.pr4;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Клас для управління підключенням до БД та збереження об'єктів ієрархії.
 */
public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String configFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Помилка завантаження конфігураційного файлу: " + e.getMessage());
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Збереження об'єкта одягу (або його нащадків) у єдину таблицю БД.
     */
    public void insertClothes(Clothes item) {
        String sql = "INSERT INTO clothes (type, name, size, price, quantity, category, has_belt, sleeve_length, has_hood, material) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String type = "Clothes";
            Boolean hasBelt = null;
            String sleeveLength = null;
            Boolean hasHood = null;
            String material = null;

            // Визначаємо конкретний тип об'єкта та специфічні поля
            if (item instanceof Pants) {
                type = "Pants";
                hasBelt = ((Pants) item).isHasBelt();
            } else if (item instanceof Shirts) {
                type = "Shirts";
                sleeveLength = ((Shirts) item).getSleeveLength();
            } else if (item instanceof Jacket) {
                type = "Jacket";
                hasHood = ((Jacket) item).isHasHood();
            } else if (item instanceof Socks) {
                type = "Socks";
                material = ((Socks) item).getMaterial();
            }

            pstmt.setString(1, type);
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getSize());
            pstmt.setDouble(4, item.getPrice());
            pstmt.setInt(5, item.getQuantity());
            pstmt.setString(6, item.getCategory().name());

            // Встановлюємо значення або NULL для специфічних полів
            if (hasBelt != null) {
                pstmt.setBoolean(7, hasBelt);
            } else {
                pstmt.setNull(7, Types.BOOLEAN);
            }

            if (sleeveLength != null) {
                pstmt.setString(8, sleeveLength);
            } else {
                pstmt.setNull(8, Types.VARCHAR);
            }

            if (hasHood != null) {
                pstmt.setBoolean(9, hasHood);
            } else {
                pstmt.setNull(9, Types.BOOLEAN);
            }

            if (material != null) {
                pstmt.setString(10, material);
            } else {
                pstmt.setNull(10, Types.VARCHAR);
            }

            pstmt.executeUpdate();
            System.out.println("Дані успішно збережено в базі даних (таблиця clothes).");
        } catch (SQLException e) {
            System.out.println("Помилка виконання SQL-запиту: " + e.getMessage());
        }
    }
}