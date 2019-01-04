package net.marcosrocha.awesomemovies.protocols;

import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static net.marcosrocha.awesomemovies.utils.OmdbService.apiKey;

/**
 * Created by marcos.rocha on 9/28/16.
 */
public interface OmdbApiService {
    @GET("/?apiKey=" + apiKey)
    Observable<MovieSearch> search(@Query("s") String title);

    @GET("/?apiKey=" + apiKey)
    Observable<Movie> details(@Query("i") String imdbID);
}
