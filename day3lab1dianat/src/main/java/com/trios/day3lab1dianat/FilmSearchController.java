package com.trios.day3lab1dianat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FilmSearchController {
    @FXML
    private TextField titleInput;
    @FXML
    private Button searchButton;
    @FXML
    private Label resultLabel;
//database connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sakila1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "MySQLServer";

    @FXML
    protected void searchFilm() {
        String title = titleInput.getText();
        String query = "SELECT title, language_id, rental_rate, length, rating, special_features FROM film WHERE title = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String language = getLanguage(rs.getInt("language_id"));
                resultLabel.setText(String.format("Title: %s\nLanguage: %s\nRental Rate: $%.2f\nLength: %d min\nRating: %s\nFeatures: %s",
                        rs.getString("title"), language, rs.getDouble("rental_rate"),
                        rs.getInt("length"), rs.getString("rating"), rs.getString("special_features")));
            } else {
                resultLabel.setText("Film not found.");
            }
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }
    private String getLanguage(int id) {
        return switch (id) {
            case 1 -> "English";
            case 2 -> "Italian";
            case 3 -> "Japanese";
            case 4 -> "Mandarin";
            case 5 -> "French";
            case 6 -> "German";
            default -> "Unknown";
        };
    }
}
