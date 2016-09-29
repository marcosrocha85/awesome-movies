package net.marcosrocha.awesomemovies.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.protocols.OnClickListenerProtected;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MovieListCustomHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OnClickListenerProtected mClickListener;
    @BindView(R.id.fragment_movie_title)
    TextView title;
    @BindView(R.id.fragment_movie_favorite)
    ImageView favorite;
    @BindView(R.id.fragment_movie_thumb)
    ImageView thumb;

    public MovieListCustomHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setFavorite(boolean isFavorite) {
        FontAwesome.Icon icon;
        if (isFavorite) {
            icon = FontAwesome.Icon.faw_star;
        } else {
            icon = FontAwesome.Icon.faw_star_o;
        }
        favorite.setImageDrawable(
                new IconicsDrawable(
                        favorite.getContext(),
                        icon
                )
                        .colorRes(R.color.starColor)
                        .sizeDp(24)
        );
    }

    @Override
    public void onClick(View view) {
        if (this.mClickListener != null) {
            this.mClickListener.onClickListener(view, getAdapterPosition());
        }
    }

    public void setOnClickListener(OnClickListenerProtected clickListener) {
        this.mClickListener = clickListener;
    }

    public OnClickListenerProtected getOnClickListener() {
        return this.mClickListener;
    }

    public void setData(Movie movie) {}
}
