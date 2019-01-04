package net.marcosrocha.awesomemovies.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;
import net.marcosrocha.awesomemovies.presenters.MovieDetailPresenter;
import net.marcosrocha.awesomemovies.presenters.MovieListFragmentPresenter;
import net.marcosrocha.awesomemovies.presenters.SearchPresenter;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod.InstanceOfType;
import net.marcosrocha.awesomemovies.utils.OmdbService;
import net.marcosrocha.awesomemovies.utils.ProgressDialogHelper;
import net.marcosrocha.awesomemovies.utils.__n;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static net.marcosrocha.awesomemovies.activities.MovieListFragment.DETAILED_MOVIE;

public class SearchActivity extends AppCompatActivity {
    private SearchPresenter presenter;
    private MovieListFragmentPresenter fragmentPresenter;
    private MovieListFragment mFragment;

    @BindView(R.id.search_text)
    TextInputLayout searchText;
    @BindView(R.id.search_button)
    ImageView searchButon;
    @OnClick(R.id.search_button)

    protected void searchButtonOnClick(View view) {
        searchText.setErrorEnabled(false);
        EditText searchEdit = searchText.getEditText();
        if (searchEdit != null && TextUtils.isEmpty(searchEdit.getText().toString())) {
            searchText.setErrorEnabled(true);
            return;
        }

        String searchTerm = searchEdit.getText().toString();
        final AppCompatActivity self = this;
        final AlertDialog progress = ProgressDialogHelper.show(this, R.string.search, R.string.searching_movies);
        OmdbService.search(searchTerm, new Observer<MovieSearch>() {
            @Override
            public void onError(Throwable e) {
                progress.dismiss();
            }

            @Override
            public void onComplete() {
                progress.dismiss();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieSearch movieSearch) {
                progress.dismiss();
                if (movieSearch.getResponse()) {
                    mFragment.setMovies(movieSearch.getSearch());
                    Toast.makeText(
                            self.getBaseContext(),
                            __n.get(self,
                                    movieSearch.getSearch().size(),
                                    R.string.found_one_movie,
                                    R.string.found_many_movies
                            ),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(self.getBaseContext(), movieSearch.getError(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        createFragment();
        setupSearchField();
        setupSearchButton();
    }

    private void createFragment() {
        this.fragmentPresenter = new MovieListFragmentPresenter(
                this,
                R.id.search_relative_movies,
                R.layout.fragment_movie_search
        );
        this.mFragment = this.fragmentPresenter.createFragment(InstanceOfType.SEARCH);
        this.mFragment.setCanDisplayNoMovies(false);
    }

    private void setupSearchButton() {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.colorAccent, typedValue, true);
        int color = typedValue.data;

        searchButon.setImageDrawable(
                new IconicsDrawable(
                        this,
                        FontAwesome.Icon.faw_search
                )
                        .color(color)
                        .sizeDp(24)
        );
    }

    private void setupSearchField() {
        searchText.setError(getString(R.string.search_criteria_required));
        searchText.setErrorEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAILED_MOVIE) {
            if (resultCode > -1) {
                Movie movie = MovieDetailPresenter.getBundleAsMovie(data.getExtras());
                this.mFragment.updateMovie(resultCode, movie);
            }
        }
    }
}
