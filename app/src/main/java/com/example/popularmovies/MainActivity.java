package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    class apiQueryTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params){

            URL movieRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
