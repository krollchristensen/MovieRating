package org.example.movierating.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.movierating.model.MovieRating;

public class MovieRatingDAO {
    private static final String URL = "jdbc:mysql://localhost/feedback?user=root&password=root";

    public void save(MovieRating movieRating) {
        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO feedback.movierating VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, movieRating.getTitle());
            preparedStatement.setString(2, movieRating.getDescription());
            preparedStatement.setInt(3, movieRating.getRating());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save movie rating", e);
        }
    }

    public ResultSet getMoviesRatings() {
        try {
            Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM feedback.movierating");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve movie ratings", e);
        }
    }

    public void updateMovie(String title, String newDescription, int newRating) {
        String updateQuery = "UPDATE feedback.movierating SET description = ?, rating = ? WHERE title = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, newRating);
            preparedStatement.setString(3, title);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No movie found with the given title: " + title);
            } else {
                System.out.println("Movie updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update movie: " + title, e);
        }
    }
    public void deleteMovie(String title) {
        String deleteQuery = "DELETE FROM feedback.movierating WHERE title = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, title);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No movie found with the given title: " + title);
            } else {
                System.out.println("Movie deleted successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete movie: " + title, e);
        }
    }


}
