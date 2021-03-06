package net.marcosrocha.awesomemovies.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class Movie extends RealmObject implements Serializable {
    private String id;
    private boolean favorite;
    private Date released_date;
    private int run_time;
    private long imdb_votes;

    @PrimaryKey
    @SerializedName("imdbID")
    private String imdbId;
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("Rated")
    private String rated;
    @SerializedName("Released")
    private String released;
    @SerializedName("Runtime")
    private String runtime;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Director")
    private String director;
    @SerializedName("Writer")
    private String writer;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Language")
    private String language;
    @SerializedName("Country")
    private String country;
    @SerializedName("Awards")
    private String awards;
    @SerializedName("Poster")
    private String poster;
    @SerializedName("Metascore")
    private String metaScore;
    @SerializedName("imdbRating")
    private double imdbRating;
    @SerializedName("imdbVotes")
    private String imdbVotes;
    @SerializedName("Type")
    private String type;
    @SerializedName("Response")
    private boolean response;
    @SerializedName("Error")
    private String error;

    public Movie() {}

    public Movie(String title, String year, String imdbId, String type, String poster) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.type = type;
        this.poster = poster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Date getReleased_date() {
        return released_date;
    }

    public void setReleased_date(Date released_date) {
        this.released_date = released_date;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
        if (released == null || TextUtils.isEmpty(released)) {
            return;
        }
        String[] months = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int[] intMonths = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int i = 0; i < months.length; i++) {
            released = released.replace(" " + months[i] + " ", "/" + Integer.toString(intMonths[i]) + "/");
        }

        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            setReleased_date(df.parse(released));
        } catch (Exception e) {

        }
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;

        runtime = runtime.replaceAll("\\D", "");
        setRun_time(Integer.parseInt(runtime));
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metaScore;
    }

    public void setMetascore(String metaScore) {
        this.metaScore = metaScore;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
        imdbVotes = imdbVotes.replace(",", "");
        setImdb_votes(Long.parseLong(imdbVotes));
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getRun_time() {
        return run_time;
    }

    public void setRun_time(int run_time) {
        this.run_time = run_time;
    }

    public boolean isFavorite() {
        return favorite && (id != null);
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public long getImdb_votes() {
        return imdb_votes;
    }

    public void setImdb_votes(long imdb_votes) {
        this.imdb_votes = imdb_votes;
    }

    public static List<Movie> findAll() {
        Realm realm = Realm.getDefaultInstance();
        List<Movie> listAux = new ArrayList<>();

        RealmResults<Movie> items = realm.where(Movie.class).findAll();
        for (Movie itemRow : items) {
            Movie item = new Movie();
            item.assignTo(itemRow);

            listAux.add(item);
        }

        return listAux;
    }

    public void assignTo(Movie sourceMovie) {
        this.setId(sourceMovie.getId());
        this.setTitle(sourceMovie.getTitle());
        this.setYear(sourceMovie.getYear());
        this.setRated(sourceMovie.getRated());
        this.setReleased(sourceMovie.getReleased());
        this.setRuntime(sourceMovie.getRuntime());
        this.setGenre(sourceMovie.getGenre());
        this.setDirector(sourceMovie.getDirector());
        this.setWriter(sourceMovie.getWriter());
        this.setActors(sourceMovie.getActors());
        this.setPlot(sourceMovie.getPlot());
        this.setLanguage(sourceMovie.getLanguage());
        this.setCountry(sourceMovie.getCountry());
        this.setAwards(sourceMovie.getAwards());
        this.setPoster(sourceMovie.getPoster());
        this.setMetascore(sourceMovie.getMetascore());
        this.setImdbRating(sourceMovie.getImdbRating());
        this.setImdbVotes(sourceMovie.getImdbVotes());
        this.setImdbId(sourceMovie.getImdbId());
        this.setType(sourceMovie.getType());
        this.setFavorite(sourceMovie.isFavorite());
    }
}
