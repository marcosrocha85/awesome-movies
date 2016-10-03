package net.marcosrocha.awesomemovies.presenters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.activities.MovieListFragment;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod.InstanceOfType;
import net.marcosrocha.awesomemovies.utils.OmdbService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marcos.rocha on 9/29/16.
 */
public class MovieListFragmentPresenter {
    private AppCompatActivity activity;
    private int idRecyclerView;
    private int idLayout;

    public MovieListFragmentPresenter(AppCompatActivity activity, int idRecyclerView, int idLayout) {
        this.activity = activity;
        this.idRecyclerView = idRecyclerView;
        this.idLayout = idLayout;
    }

    public MovieListFragment createFragment(InstanceOfType instanceOfType) {
        if (activity == null || idRecyclerView == 0 || idLayout == 0) {
            Log.e("MovieListFragPresenter", "Programador: Você precisa informar Activity, RecyclerView e Layout ao criar o Fragment Movie.");
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        MovieListFragment mFragment = (MovieListFragment) fragmentManager.findFragmentById(R.id.fragment_movie);
        if (mFragment == null) {
            mFragment = MovieListFragment.newInstance(activity, this.idLayout, instanceOfType);
            FragmentTransaction myTransaction = fragmentManager.beginTransaction();
            myTransaction.replace(idRecyclerView, mFragment, mFragment.getTag());
            myTransaction.commit();
        }

        return mFragment;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public int getIdRecyclerView() {
        return idRecyclerView;
    }

    public void setIdRecyclerView(int idRecyclerView) {
        this.idRecyclerView = idRecyclerView;
    }

    public int getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(int idLayout) {
        this.idLayout = idLayout;
    }

    public static boolean isMovieInRealm(Movie movie) {
        return MovieRealmPresenter.isMovieInRealm(movie);
    }

    public interface AddToDatabaseCallback {
        void addToDatabase(boolean movieAdded, Throwable error);
    }
    public interface RemoveFromDatabaseCallback {
        void removeFromDatabase(boolean removed, Throwable error);
    }

    public static void removeFromDatabase(Movie movie, RemoveFromDatabaseCallback callback) {
        if (MovieRealmPresenter.removeFromDatabase(movie)) {
            callback.removeFromDatabase(true, null);
        } else {
            callback.removeFromDatabase(false, new Exception("Erro ao remover do Realm"));
        }
    }

    public static void addToDatabase(final Movie movie, final AddToDatabaseCallback callback) {
        boolean canAddToDatabase = true;
        if (movie.getId() == null || TextUtils.isEmpty(movie.getId())) {
            canAddToDatabase = false;
            OmdbService.details(movie.getImdbId(), new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (!response.isSuccessful()) {
                        onFailure(call, new Exception(response.message()));
                    }

                    movie.assignTo(response.body());
                    movie.setFavorite(true);
                    MovieRealmPresenter.addToDatabase(movie);
                    callback.addToDatabase(true, null);
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    callback.addToDatabase(false, t);
                }
            });
        }

        if (canAddToDatabase) {
            MovieRealmPresenter.addToDatabase(movie);
        }
    }
}
