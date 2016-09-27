package net.marcosrocha.awesomemovies.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.models.Movie;

import java.util.List;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityCustomHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Movie> mList;

    public MainActivityAdapter(Context context, List<Movie> mList) {
        this.mContext = context;
        this.mList = mList;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MainActivityCustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = mLayoutInflater.inflate(R.layout.fragment_movies, parent, false);
        MainActivityCustomHolder myViewHolder = new MainActivityCustomHolder(myView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MainActivityCustomHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
