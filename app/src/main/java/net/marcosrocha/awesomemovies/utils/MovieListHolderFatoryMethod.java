package net.marcosrocha.awesomemovies.utils;

import android.view.View;
import net.marcosrocha.awesomemovies.activities.MovieListCustomHolder;
import net.marcosrocha.awesomemovies.activities.MovieListFavoriteHolder;
import net.marcosrocha.awesomemovies.activities.MovieListSearchHolder;

/**
 * Created by marcos.rocha on 9/29/16.
 */
public class MovieListHolderFatoryMethod {
    public enum InstanceOfType {
        FAVORITES,
        SEARCH
    }

    public static MovieListCustomHolder getInstance(InstanceOfType instanceOfType, View itemView) {
        switch (instanceOfType) {
            case FAVORITES: {
                return new MovieListFavoriteHolder(itemView);
            }
            case SEARCH: {
                return new MovieListSearchHolder(itemView);
            }
            default: {
                return null;
            }
        }
    }
}
