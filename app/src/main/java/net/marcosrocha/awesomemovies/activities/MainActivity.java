package net.marcosrocha.awesomemovies.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.presenters.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MainActivity extends AppCompatActivity {
    private List<Movie> mList;
    private MainActivityPresenter presenter;
    private MainActivityAdapter mAdapter;
    private LinearLayoutManager mManager;

    @BindView(R.id.main_fab_search)
    FloatingActionButton fabSearch;
    @BindView(R.id.main_fav_list)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupFloatingActionButton();
        setupAdapter();
    }

    private void setupAdapter() {
        this.mList = new ArrayList<>();
        this.mList.add(new Movie("Back To The Future", "tt0088763", true));
        this.mList.add(new Movie("Terminator II", "tt0088764", true));
        this.mList.add(new Movie("X-Man Origins: Wolverine", "tt0088765", true));
        this.mRecyclerView.setHasFixedSize(true);

        this.mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(this.mManager);

        this.mAdapter = new MainActivityAdapter(this, this.mList);
        this.mRecyclerView.setAdapter(this.mAdapter);
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
