package net.marcosrocha.awesomemovies.presenters;

import io.realm.Realm;
import io.realm.RealmObject;
import net.marcosrocha.awesomemovies.models.Movie;

/**
 * Created by marcos.rocha on 9/30/16.
 */
public class MovieRealmPresenter {
    public static boolean isMovieInRealm(Movie movie) {
        return getMovieByImdbID(movie.getImdbId()) != null;
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

    public static Movie getMovieByImdbID(String imdbID) {
        return getRealm().where(Movie.class).equalTo("id", imdbID).findFirst();
    }

    public static boolean removeFromDatabase(Movie movie) {
        Realm realm = getRealm();

        if (!movie.isValid() || !isMovieInRealm(movie)) {
            movie = getMovieByImdbID(movie.getImdbId());
        }

        try {
            realm.beginTransaction();

            movie.deleteFromRealm();

            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
        }

        return false;
    }
}
