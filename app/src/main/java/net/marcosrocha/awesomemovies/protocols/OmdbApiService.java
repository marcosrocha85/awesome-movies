package net.marcosrocha.awesomemovies.protocols;

import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by marcos.rocha on 9/28/16.
 */
public interface OmdbApiService {
    @GET("/")
    Observable<MovieSearch> search(@Query("s") String title);

    @GET("/")
    Observable<Movie> details(@Query("i") String imdbID);
}
