package org.example.movierating.model;

public class MovieRating {
    private String title;
    private String description;
    private int rating;

    @Override
    public String toString() {
        return "MovieRating{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }



    public MovieRating(String title, String description, int rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }


}
