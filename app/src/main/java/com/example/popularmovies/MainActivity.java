package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.popularmovies.jsonUtils.parseMovieJson;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_mainGrid);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        new apiQueryTask().execute();
        setContentView(R.layout.movie_detail);
        ImageView imageView = findViewById(R.id.image_movie);
        populateUI(movie);
        Picasso.with(this)
                .load(movie.getImage())
                .into(imageView);
    }

    private void populateUI(Movie movie) {
        TextView textViewOriginalTitle = findViewById(R.id.original_title);
        textViewOriginalTitle.setText(movie.getMovieName());

        TextView textViewDescription = findViewById(R.id.movie_overview);
        textViewDescription.setText(movie.getMovieOverview());

        ImageView imageView = findViewById(R.id.image_movie);
        String image = movie.getPosterUrl();
        int resID = getResources().getIdentifier(image, "drawable", getPackageName());
        imageView.setImageResource(resID);

        TextView textViewRating = findViewById(R.id.movie_rating);
        textViewRating.setText(movie.getRating());

        TextView textViewReleaseDate = findViewById(R.id.release_date);
        textViewReleaseDate.setText(movie.getReleaseDate());
    }

    class apiQueryTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params){

            URL movieRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);
                Log.e(getClass().getName(),"json response = " + jsonMovieResponse);
                return parseMovieJson(jsonMovieResponse);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mAdapter.setMovieList(movies);
        }
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public static class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
        private List<Movie> mMovieList;
        private LayoutInflater mInflater;
        private Context mContext;

        public MoviesAdapter(Context context)
        {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
            this.mMovieList = new ArrayList<>();
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = mInflater.inflate(R.layout.movie_layout, parent, false);
            MovieViewHolder viewHolder = new MovieViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position)
        {
            Movie movie = mMovieList.get(position);

            // This is how we use Picasso to load images from the internet.
            Picasso.get()
                    .load(movie.getPosterUrl())
                    .into(holder.imageView);
            Log.e("CHECK URL", "image url = " + movie.getPosterUrl() );
        }

        @Override
        public int getItemCount()
        {
            return (mMovieList == null) ? 0 : mMovieList.size();
        }

        public void setMovieList(List<Movie> movieList)
        {
            this.mMovieList.clear();
            this.mMovieList.addAll(movieList);
            notifyDataSetChanged();
        }
    }
}
