package net.marcosrocha.awesomemovies.presenters;

import android.content.Context;
import android.content.Intent;
import net.marcosrocha.awesomemovies.activities.MovieDetailActivity;

/**
 * Created by marcos.rocha on 9/29/16.
 */
public class MovieDetailPresenter {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        context.startActivity(intent);
    }
}
