package net.marcosrocha.awesomemovies.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.protocols.OnClickListenerProtected;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MovieListFavoriteHolder extends MovieListCustomHolder {
    @BindView(R.id.fragment_movie_plot)
    TextView plot;
    @BindView(R.id.fragment_movie_rating)
    TextView rating;

    public MovieListFavoriteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Movie movie) {
        super.setData(movie);

        this.title.setText(movie.getTitle());
        this.plot.setText(movie.getPlot());
        this.rating.setText(Double.toString(movie.getImdbRating()));
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
