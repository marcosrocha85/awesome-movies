package net.marcosrocha.awesomemovies.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;
import net.marcosrocha.awesomemovies.protocols.OmdbApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcos.rocha on 10/3/16.
 */
public class OmdbService {
    private static final String BASE_URL = "http://www.omdbapi.com";
    public static final String apiKey = "<insert your OMDBAPI Key here>"; // <------

    private static OmdbApiService buildRetrofit() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(OmdbApiService.class);
    }

    public static void search(String title, Observer<MovieSearch> searchCallback) {
        OmdbApiService service = buildRetrofit();
        Observable<MovieSearch> search = service.search(title);
        search
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(searchCallback);
    }

    public static void details(String imdbID, Observer<Movie> movieCallback) {
        OmdbApiService service = buildRetrofit();
        Observable<Movie> movieCall = service.details(imdbID);
        movieCall
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(movieCallback);
    }
}
