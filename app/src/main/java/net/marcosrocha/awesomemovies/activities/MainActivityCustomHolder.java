package net.marcosrocha.awesomemovies.activities;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import net.marcosrocha.awesomemovies.R;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class MainActivityCustomHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.fragment_movie_title)
    TextView title;


    public MainActivityCustomHolder(Activity activity, View itemView) {
        super(itemView);
        ButterKnife.bind(this, activity);
    }

    @Override
    public void onClick(View view) {

    }
}
