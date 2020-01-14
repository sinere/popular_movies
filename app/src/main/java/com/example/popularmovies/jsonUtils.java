package com.example.popularmovies;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class jsonUtils {

    public static Movie parseMovieJson(String json) {

        Movie movie = new Movie();

        try {
            JSONObject movieJson = new JSONObject(json);
            JSONObject nameJSONObject = movieJson.getJSONObject("title");
            movie.setMovieName(nameJSONObject.optString("movieName"));
            Log.e("CHECK NAME", "name = " + movie.getMovieName() );

        } catch (JSONException e) {
            System.out.println("Something went wrong.");
        }

        return movie;
    }
}
