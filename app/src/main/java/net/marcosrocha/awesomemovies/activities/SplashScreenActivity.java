package net.marcosrocha.awesomemovies.activities;

import android.app.Application;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import net.marcosrocha.awesomemovies.BuildConfig;
import net.marcosrocha.awesomemovies.R;
import net.marcosrocha.awesomemovies.presenters.MainActivityPresenter;

/**
 * Created by marcos.rocha on 9/27/16.
 */
public class SplashScreenActivity extends AppCompatActivity {
    private long defaultSplashDelay;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        defaultSplashDelay = 3000;
        if (BuildConfig.DEBUG) {
            defaultSplashDelay = 100;
        }

        startCounter();
    }

    private void startCounter() {
        final AppCompatActivity self = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    MainActivityPresenter.startActivity(self);
                    self.finish();
                }
            },
            defaultSplashDelay);
    }
}
