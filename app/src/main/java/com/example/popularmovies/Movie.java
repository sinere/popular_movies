package com.example.popularmovies;

import java.util.List;

public class Movie {
    private String movieName;
    private String movieOriName;
    private String posterUrl;
    private String movieOverview;
    private String releaseDate;
    private Integer popularity;
    private Integer rating;

    //Constructor
    public static Movie{}

    public Movie(String movieName, String movieOriName, String posterUrl, String movieOverview, String releaseDate, Integer popularity, Integer rating) {
        this.movieName = movieName;
        this.movieOriName = movieOriName;
        this.posterUrl = posterUrl;
        this.movieOverview = movieOverview;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieOriName() {
        return movieOriName;
    }

    public void setMovieOriName(String movieOriName) {
        this.movieOriName = movieOriName;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
