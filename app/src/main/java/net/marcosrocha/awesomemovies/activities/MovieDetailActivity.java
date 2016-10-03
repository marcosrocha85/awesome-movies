package net.marcosrocha.awesomemovies.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.presenters.MovieDetailPresenter;
import net.marcosrocha.awesomemovies.presenters.MovieRealmPresenter;
import net.marcosrocha.awesomemovies.protocols.OmdbApiService;
import net.marcosrocha.awesomemovies.utils.OmdbService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.Serializable;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE = "net.marcosrocha.awesomemovies.extraImage";
    private static final double MAX_RATING = 10;
    private Movie movie;
    private MovieDetailPresenter presenter;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.detail_movie_image)
    ImageView movieImage;
    @BindView(R.id.detail_movie_title)
    TextView movieTitle;
    @BindView(R.id.detail_movie_plot)
    TextView moviePlot;
    @BindView(R.id.detail_movie_rating_bar)
    RatingBar movieRatingBar;
    @BindView(R.id.detail_movie_actors)
    TextView movieActors;
    @BindView(R.id.detail_movie_writer)
    TextView movieWriter;
    @BindView(R.id.detail_movie_type)
    TextView movieType;
    @BindView(R.id.detail_movie_metascore)
    TextView movieMetascore;
    @BindView(R.id.detail_movie_runtime)
    TextView movieRuntime;
    @BindView(R.id.detail_movie_rating_label)
    TextView movieRatedLabel;
    @BindView(R.id.detail_movie_rated)
    TextView movieRated;
    @BindView(R.id.detail_movie_released)
    TextView movieReleased;
    @BindView(R.id.detail_movie_year)
    TextView movieYear;
    @BindView(R.id.detail_movie_director)
    TextView movieDirector;
    @BindView(R.id.detail_movie_genre)
    TextView movieGenre;
    @BindView(R.id.detail_movie_language)
    TextView movieLanguage;
    @BindView(R.id.detail_movie_country)
    TextView movieCountry;
    @BindView(R.id.detail_movie_awards)
    TextView movieAwards;
    @BindView(R.id.fab_movie_favorite)
    FloatingActionButton fabFavorite;
    @OnClick(R.id.fab_movie_favorite)
    public void fabFavoriteOnClick(View view) {
        Movie movieTemp = new Movie();
        movieTemp.assignTo(movie);
        if (MovieRealmPresenter.isMovieInRealm(movieTemp)) {
            MovieRealmPresenter.removeFromDatabase(movieTemp);
            movie = movieTemp;
            movie.setFavorite(false);
        } else {
            movieTemp.setFavorite(true);
            MovieRealmPresenter.addToDatabase(movieTemp);
            movie.setId(movie.getImdbId());
            movie.setFavorite(true);
        }
        updateFabButtonImage();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);

        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(
                getIntent().getIntExtra("position", -1),
                intent
        );
    }

    public boolean loadMovieFromIntent() {
        if (getIntent().hasExtra("movie")) {
            movie = MovieDetailPresenter.getBundleAsMovie(getIntent().getExtras());
        }
        return movie != null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(-1);
        initActivityTransitions();
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        initPresenter();
        if (!loadMovieFromIntent()) {
            return;
        }

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadMovieData();
    }

    private void initPresenter() {
        this.presenter = new MovieDetailPresenter();
    }

    private void fillMovieData() {
        String itemTitle = movie.getTitle();
        movieTitle.setText(itemTitle);
        moviePlot.setText(movie.getPlot());
        float rating = (float) ((movie.getImdbRating() / MAX_RATING) * movieRatingBar.getNumStars());
        movieRatingBar.setRating(rating);
        movieActors.setText(movie.getActors());
        movieWriter.setText(movie.getWriter());
        movieType.setText(movie.getType());
        movieMetascore.setText(movie.getMetascore());
        movieRuntime.setText(movie.getRuntime());
        movieRatedLabel.setText(getString(R.string.imdb_rating, movie.getImdbRating()));
        movieRated.setText(movie.getRated());
        movieReleased.setText(movie.getReleased());
        movieYear.setText(movie.getYear());
        movieDirector.setText(movie.getDirector());
        movieGenre.setText(movie.getGenre());
        movieLanguage.setText(movie.getLanguage());
        movieCountry.setText(movie.getCountry());
        movieAwards.setText(movie.getAwards());
        updateFabButtonImage();

        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Picasso.with(this).load(movie.getPoster()).into(movieImage, new Callback() {
            @Override public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) movieImage.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override public void onError() {

            }
        });
    }

    private void updateFabButtonImage() {
        FontAwesome.Icon fabIcon = FontAwesome.Icon.faw_star_o;
        if (movie.isFavorite()) {
            fabIcon = FontAwesome.Icon.faw_star;
        }
        fabFavorite.setImageDrawable(new IconicsDrawable(
                        this,
                        fabIcon
                )
                        .color(Color.WHITE)
                        .sizeDp(24)
        );
    }

    private void loadMovieData() {
        if (!this.presenter.hasMovieInDatabase(movie)) {
            final MovieDetailActivity self = this;
            OmdbService.details(movie.getImdbId(), new retrofit2.Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    movie = response.body();
                    fillMovieData();
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    Toast.makeText(self, "Erro ao carregar detalhes do filme.", Toast.LENGTH_SHORT).show();
                    Log.d("loadMovieData", "Erro ao carregar detalhes do filme.");
                    t.printStackTrace();
                }
            });
        } else {
            movie = MovieRealmPresenter.getMovieByImdbID(movie.getImdbId());
            fillMovieData();
        }
    }

    @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    public int getColorFromTheme(int attributeColor) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(attributeColor, typedValue, true);

        return typedValue.data;
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getColorFromTheme(R.attr.colorPrimaryDark);
        int primary = getColorFromTheme(R.attr.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton) findViewById(R.id.fab_movie_favorite), palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getColorFromTheme(R.attr.colorAccent));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
}
