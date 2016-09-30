package net.marcosrocha.awesomemovies.presenters;

import android.text.TextUtils;
import io.realm.Realm;
import io.realm.RealmObject;
import net.marcosrocha.awesomemovies.models.Movie;

/**
 * Created by marcos.rocha on 9/30/16.
 */
public class MovieRealmPresenter {
    public static boolean isMovieInRealm(Movie movie) {
        return !TextUtils.isEmpty(movie.getId());
    }

    public static void addToDatabase(Movie movie) {
        Realm realm = getRealm();

        try {
            realm.beginTransaction();

            movie.setId(movie.getImdbId());
            realm.copyToRealmOrUpdate(movie);

            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public static Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    public static void removeFromDatabase(Movie movie) {
        Realm realm = getRealm();

        if (!isMovieInRealm(movie)) {
            movie = realm.where(Movie.class).equalTo("id", movie.getImdbId()).findFirst();
        }

        try {
            realm.beginTransaction();

            movie.deleteFromRealm();

            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }
}
