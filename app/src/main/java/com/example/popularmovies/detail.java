package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.popularmovies.movieRoomDatabase.databaseWriteExecutor;

public class detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView movieIv = findViewById(R.id.image_poster);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

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
        textViewOverview.setText("Plot Synopsis:\n" + movieOverview);

        TextView textViewRelease = findViewById(R.id.release_date);
        textViewRelease.setText("Release Date: " + releaseDate);

        TextView textViewRating = findViewById(R.id.movie_rating);
        textViewRating.setText("Average Vote: " + rating.toString());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
