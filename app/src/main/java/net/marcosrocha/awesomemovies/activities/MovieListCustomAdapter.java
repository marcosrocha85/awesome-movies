package net.marcosrocha.awesomemovies.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.marcosrocha.awesomemovies.models.Movie;
import net.marcosrocha.awesomemovies.protocols.OnClickListenerProtected;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod;
import net.marcosrocha.awesomemovies.utils.MovieListHolderFatoryMethod.InstanceOfType;

import java.util.List;

/**
 * Created by marcos.rocha on 9/29/16.
 */
public class MovieListCustomAdapter extends RecyclerView.Adapter<MovieListCustomHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private MovieListCustomHolder mViewHolder;
    private List<Movie> mList;
    private OnClickListenerProtected mOnclickListener;
    private InstanceOfType instanceOfType;
    private int idLayout;

    public MovieListCustomAdapter(Context context, InstanceOfType instanceOfType, List<Movie> mList) {
        this.instanceOfType = instanceOfType;
        this.mContext = context;
        this.mList = mList;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MovieListCustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = mLayoutInflater.inflate(this.idLayout, parent, false);
        this.mViewHolder = MovieListHolderFatoryMethod.getInstance(this.instanceOfType, myView);
        this.mViewHolder.setOnClickListener(this.mOnclickListener);

        return this.mViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListCustomHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnClickListener(OnClickListenerProtected onClickListener) {
        this.mOnclickListener = onClickListener;
        if (this.mViewHolder != null) {
            this.mViewHolder.setOnClickListener(this.mOnclickListener);
        }
    }

    public OnClickListenerProtected getOnClickListener() {
        return this.mViewHolder.getOnClickListener();
    }

    public List<Movie> getList() {
        return mList;
    }

    public Context getContext() {
        return mContext;
    }

    public int getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(int idLayout) {
        this.idLayout = idLayout;
    }
}
