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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.popularmovies.jsonUtils.parseMovieJson;

public class MainActivity extends AppCompatActivity implements MainActivity.MoviesAdapter.movieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private static MoviesAdapter.movieAdapterOnClickHandler mClickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_mainGrid);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this, (MoviesAdapter.movieAdapterOnClickHandler) this);
        mRecyclerView.setAdapter(mAdapter);
        new apiQueryTask().execute();

    }

    @Override
    public void onClick() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView imageView;
        public TextView mMovieTextView;

        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick();
        }
    }

    public static class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
        private movieAdapterOnClickHandler mClickHandler;
        private List<Movie> mMovieList;
        private LayoutInflater mInflater;
        private Context mContext;

        public MoviesAdapter(Context context, movieAdapterOnClickHandler mClickHandler)
        {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
            this.mMovieList = new ArrayList<>();
            this.mClickHandler = mClickHandler;
        }

        public MoviesAdapter(MainActivity mainActivity) {

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

        public interface movieAdapterOnClickHandler {
            void onClick();
            //Intent intent = new Intent(MainActivity.this, detail.class);

        }

        public void setMovieList(List<Movie> movieList)
        {
            this.mMovieList.clear();
            this.mMovieList.addAll(movieList);
            notifyDataSetChanged();
        }
    }
}
