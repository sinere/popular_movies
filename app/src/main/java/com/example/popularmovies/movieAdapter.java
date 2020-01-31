package com.example.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.MovieAdapteViewHolder> {

    private String[] mMovieData;

    private final movieAdapterOnClickHandler mClickHandler;

    @NonNull
    @Override
    public MovieAdapteViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_detail;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapteViewHolder(view);
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

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param forecastAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MovieAdapteViewHolder forecastAdapterViewHolder, int position) {
        String movieData = mMovieData[position];
        forecastAdapterViewHolder.mMovieTextView.setText(movieData);

        populateUI(movie);
        Picasso.get(this)
                .load(movie.getImage())
                .into(imageView);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mMovieData.length;
    }

    public interface movieAdapterOnClickHandler {
        void onClick(String movieNumber);
    }

    public movieAdapter(movieAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    public class MovieAdapteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mMovieTextView;

        public MovieAdapteViewHolder(View view) {
            super(view);
            mMovieTextView = (TextView) view.findViewById(R.id.);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String movieNumber = mMovieData[adapterPosition];
            mClickHandler.onClick(movieNumber);
        }
    }
}
