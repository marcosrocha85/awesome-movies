package net.marcosrocha.awesomemovies.activities;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.presenters.MovieRealmPresenter;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MovieListSearchHolder extends MovieListCustomHolder {
    @BindView(R.id.fragment_movie_year)
    TextView year;

    public MovieListSearchHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Movie movie) {
        super.setData(movie);

        if (!movie.isFavorite()) {
            Movie realmMovie = MovieRealmPresenter.getMovieByImdbID(movie.getImdbId());
            if (realmMovie != null) {
                movie = realmMovie;
            }
        }

        this.title.setText(movie.getTitle());
        this.year.setText(movie.getYear());
        this.setFavorite(movie.isFavorite());
        int imageWidth = this.itemView.getResources().getDimensionPixelSize(R.dimen.movie_thumb_width);
        int imageHeight = this.itemView.getResources().getDimensionPixelSize(R.dimen.movie_thumb_height);

        Picasso.with(this.itemView.getContext())
                .load(movie.getPoster())
                .resize(imageWidth, imageHeight)
                .centerCrop()
                .into(this.thumb);
    }
}
