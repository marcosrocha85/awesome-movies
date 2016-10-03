package net.marcosrocha.awesomemovies.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.presenters.MainActivityPresenter;
import net.marcosrocha.awesomemovies.presenters.MovieListFragmentPresenter;
import net.marcosrocha.awesomemovies.presenters.SearchPresenter;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod.InstanceOfType;

import java.util.List;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MainActivity extends AppCompatActivity {
    private MainActivityPresenter presenter;
    private MovieListFragmentPresenter fragmentPresenter;
    private MovieListFragment mFragment;

    @BindView(R.id.main_fab_search)
    FloatingActionButton fabSearch;
    @OnClick(R.id.main_fab_search)
    protected void fabSearchOnClick(View view) {
        SearchPresenter.startActivity(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.presenter = new MainActivityPresenter();
        setupFloatingActionButton();
        createFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        List<Movie> movies = this.presenter.getFromRealm();
        this.mFragment.setMovies(movies);
    }

    private void createFragment() {
        this.fragmentPresenter = new MovieListFragmentPresenter(
                this,
                R.id.main_relative_movies,
                R.layout.fragment_movie_favorite
        );
        this.mFragment = this.fragmentPresenter.createFragment(InstanceOfType.FAVORITES);
        this.mFragment.setShouldRemoveItemFromList(true);
    }

    private void setupFloatingActionButton() {
        fabSearch.setImageDrawable(
                new IconicsDrawable(
                        this,
                        FontAwesome.Icon.faw_search_plus
                )
                        .color(Color.WHITE)
                        .sizeDp(24)
        );
    }
}
