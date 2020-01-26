package com.example.popularmovies;

import java.util.List;

public class Movie {

    public static final String TMDB_IMAGE_PATH = "https://image.tmdb.org/t/p/w500";
    private String movieName;
    private String posterUrl;
    private String movieOverview;
    private String releaseDate;
    private Integer popularity;
    private Integer rating;

    public Movie() {
        this.movieName = movieName;
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

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
