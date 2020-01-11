package com.example.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jsonUtils {

    public static Movie parseMovieJson(String json) {

        Movie movie = new Movie();

        try {
            JSONObject movieJson = new JSONObject(json);
            JSONObject nameJSONObject = movieJson.getJSONObject("title");
            movie.setMovieName(nameJSONObject.optString("movieName"));

        } catch (JSONException e) {
            System.out.println("Something went wrong.");
        }


        return movie;
    }
}
