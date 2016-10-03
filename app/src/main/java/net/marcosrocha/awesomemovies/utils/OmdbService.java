package net.marcosrocha.awesomemovies.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.models.MovieSearch;
import net.marcosrocha.awesomemovies.protocols.OmdbApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(OmdbApiService.class);
    }

    public static Subscription search(String title, Observer<MovieSearch> searchCallback) {
        OmdbApiService service = buildRetrofit();
        Observable<MovieSearch> search = service.search(title);
        return search
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchCallback);
    }

    public static Subscription details(String imdbID, Observer<Movie> movieCallback) {
        OmdbApiService service = buildRetrofit();
        Observable<Movie> movieCall = service.details(imdbID);
        return movieCall
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieCallback);
    }
}
