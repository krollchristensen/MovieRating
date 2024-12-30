package org.example.movierating.model;


import org.example.movierating.dal.MovieRatingDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
Denne klasse fungerer som en slags service eller forretningslogiklag, der styrer interaktionen mellem DAO (Data Access Object) og controlleren.
Den indeholder metoder til at oprette og vise filmanmeldelser.
I denne klasse håndteres logikken for at interagere med datakilden, som er databasen i dette tilfælde.

*/

public class MovieRatingManager {
    private  MovieRatingDAO movieRatingDAO = new MovieRatingDAO();

    public void create(String title, String description, int rating) {
        MovieRating movieRating = new MovieRating(title, description, rating);
        movieRatingDAO.save(movieRating);
    }

    public ArrayList<MovieRating> display() {
        ArrayList<MovieRating> movieRatingList = new ArrayList<>();
        try (ResultSet resultSet = movieRatingDAO.getMoviesRatings()) {
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int rating = resultSet.getInt("rating");
                movieRatingList.add(new MovieRating(title, description, rating));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to display movie ratings", e);
        }
        return movieRatingList;
    }

    public void update(MovieRating m) {

        movieRatingDAO.updateMovie(m.getTitle(), m.getDescription(), m.getRating());
    }
}




