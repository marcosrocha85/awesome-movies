package net.marcosrocha.awesomemovies.presenters;

import android.content.Context;
import android.content.Intent;
import net.marcosrocha.awesomemovies.activities.SearchActivity;

/**
 * Created by marcos.rocha on 9/28/16.
 */
public class SearchPresenter {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
