package net.marcosrocha.awesomemovies.protocols;

import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcos.rocha on 9/28/16.
 */
public interface OmdbApiService {
    @GET("/")
    Call<MovieSearch> search(@Query("s") String title);

    @GET("/")
    Call<Movie> details(@Query("t") String title);
}
