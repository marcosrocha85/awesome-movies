package net.marcosrocha.awesomemovies.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import net.marcosrocha.awesomemovies.activities.MovieDetailActivity;
import net.marcosrocha.awesomemovies.models.Movie;

import static net.marcosrocha.awesomemovies.activities.MovieDetailActivity.EXTRA_IMAGE;
import static net.marcosrocha.awesomemovies.activities.MovieListFragment.DETAILED_MOVIE;

/**
 * Created by marcos.rocha on 9/29/16.
 */
public class MovieDetailPresenter {
    public static void startActivity(AppCompatActivity activity, View transitionImage, int position, Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);
        bundle.putInt("position", position);

        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtras(bundle);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivityForResult(activity, intent, DETAILED_MOVIE, options.toBundle());
    }

    public static Movie getBundleAsMovie(Bundle bundle) {
        return (Movie) bundle.getSerializable("movie");
    }

    public boolean hasMovieInDatabase(Movie movie) {
        return MovieRealmPresenter.isMovieInRealm(movie);
    }
}
