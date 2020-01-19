package com.example.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class jsonUtils {

    public static Movie parseMovieJson(String json) {

        Movie movie = new Movie();

        try {
            JSONObject movieJson = new JSONObject(json);
            JSONArray resultJSONArray = movieJson.optJSONArray("results");
            if(resultJSONArray != null) {
                for (int i = 0; i < resultJSONArray.length(); i++) {
                    JSONObject movieObject = resultJSONArray.getJSONObject(i);
                    movie.setMovieName(movieObject.getString("title"));
                    movie.setPosterUrl(movieObject.getString("poster_path"));
                }
            }
            Log.e("CHECK NAME", "name = " + movie.getMovieName() );

        } catch (JSONException e) {
            System.out.println("Something went wrong.");
        }

        return movie;
    }
}
