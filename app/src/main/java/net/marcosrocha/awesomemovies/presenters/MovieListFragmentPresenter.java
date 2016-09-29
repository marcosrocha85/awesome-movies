package net.marcosrocha.awesomemovies.presenters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.activities.MovieListCustomAdapter;
import net.marcosrocha.awesomemovies.activities.MovieListFragment;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod.InstanceOfType;

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
}
