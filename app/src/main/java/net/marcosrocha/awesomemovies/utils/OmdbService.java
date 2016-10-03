package net.marcosrocha.awesomemovies.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;
import net.marcosrocha.awesomemovies.protocols.OmdbApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcos.rocha on 10/3/16.
 */
public class OmdbService {
    public static final String BASE_URL = "http://www.omdbapi.com/";

    private static OmdbApiService buildRetrofit() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(OmdbApiService.class);
    }

    public static void search(String title, Callback<MovieSearch> searchCallback) {
        OmdbApiService service = buildRetrofit();
        Call<MovieSearch> search = service.search(title);
        search.enqueue(searchCallback);
    }

    public static void details(String imdbID, Callback<Movie> movieCallback) {
        OmdbApiService service = buildRetrofit();
        Call<Movie> movieCall = service.details(imdbID);
        movieCall.enqueue(movieCallback);
    }
}
