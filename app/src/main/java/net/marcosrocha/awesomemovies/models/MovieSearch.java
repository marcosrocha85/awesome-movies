package net.marcosrocha.awesomemovies.models;

/**
 * Created by marcos.rocha on 9/28/16.
 */

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MovieSearch {
    @SerializedName("Search")
    private List<Movie> search;
    @SerializedName("Response")
    private boolean response;
    @SerializedName("Error")
    private String error;

    public boolean getResponse() {
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

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }
}
