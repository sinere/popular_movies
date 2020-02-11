package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView movieIv = findViewById(R.id.image_poster);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("Movie");

        String movieName = movie.getMovieName();
        String posterURL = movie.getPosterUrl();
        String movieOverview = movie.getMovieOverview();
        String releaseDate = movie.getReleaseDate();
        Integer rating = movie.getRating();

        TextView textViewName = findViewById(R.id.movie_name);
        textViewName.setText(movieName);

        ImageView imageView = findViewById(R.id.image_poster);
        int resID = getResources().getIdentifier(posterURL, "drawable", getPackageName());
        imageView.setImageResource(resID);

        TextView textViewOverview = findViewById(R.id.movie_overview);
        textViewOverview.setText(movieOverview);

        TextView textViewRelease = findViewById(R.id.release_date);
        textViewRelease.setText("Release Date: " + releaseDate);

        TextView textViewRating = findViewById(R.id.movie_rating);
        textViewRating.setText("User rating: " + rating.toString());

        Picasso.get()
                .load(movie.getPosterUrl())
                .into(movieIv);

        setTitle(movie.getMovieName());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
