package net.marcosrocha.awesomemovies.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.presenters.MovieDetailPresenter;
import net.marcosrocha.awesomemovies.presenters.MovieRealmPresenter;
import net.marcosrocha.awesomemovies.protocols.OnClickListenerProtected;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod.InstanceOfType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MovieListFragment extends Fragment implements OnClickListenerProtected {
    private List<Movie> mList;
    private MovieListCustomAdapter mAdapter;
    private LinearLayoutManager mManager;
    private boolean canDisplayNoMovies = true;
    private int idLayout;
    private InstanceOfType instanceOfType;
    private AppCompatActivity activity;
    @BindView(R.id.main_tv_no_movies)
    TextView noMovies;
    @BindView(R.id.main_fav_list)
    RecyclerView mRecyclerView;

    public static final MovieListFragment newInstance(AppCompatActivity activity, int idLayout, InstanceOfType instanceOfType) {
        MovieListFragment fragment = new MovieListFragment();
        fragment.instanceOfType = instanceOfType;
        fragment.idLayout = idLayout;
        fragment.activity = activity;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, myView);

        setupAdapter();
        changeNoItemsVisibility();

        return myView;
    }

    private void changeNoItemsVisibility() {
        if (!canDisplayNoMovies) {
            noMovies.setVisibility(View.GONE);
            return;
        }

        if (mList != null && mList.size() > 0) {
            noMovies.setVisibility(View.GONE);
        } else {
            noMovies.setVisibility(View.VISIBLE);
        }
    }

    private void setupAdapter() {
        this.mList = new ArrayList<>();
        this.mRecyclerView.setHasFixedSize(true);

        this.mManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(this.mManager);

        this.mAdapter = new MovieListCustomAdapter(this.getContext(), this.instanceOfType, this.mList);
        this.mAdapter.setIdLayout(this.idLayout);
        this.mAdapter.setOnClickListener(this);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    @Override
    public void onClickListener(View view, int position) {
        MovieDetailPresenter.startActivity(this.activity, view, mList.get(position));
    }

    @Override
    public void onStarClickListener(View view, int position) {
        Movie movie = mList.get(position);
        if (MovieRealmPresenter.isMovieInRealm(movie)) {
            MovieRealmPresenter.removeFromDatabase(movie);
        } else {
            MovieRealmPresenter.addToDatabase(movie);
        }
    }

    public void setMovies(List<Movie> movies) {
        this.mList.clear();
        this.mList.addAll(movies);
        this.mAdapter.notifyDataSetChanged();
        changeNoItemsVisibility();
    }

    public boolean getCanDisplayNoMovies() {
        return canDisplayNoMovies;
    }

    public void setCanDisplayNoMovies(boolean canDisplayNoMovies) {
        this.canDisplayNoMovies = canDisplayNoMovies;
    }
}
