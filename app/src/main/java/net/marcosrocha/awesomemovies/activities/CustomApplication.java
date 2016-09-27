package net.marcosrocha.awesomemovies.activities;

import android.app.Application;
import io.realm.BuildConfig;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import net.marcosrocha.awesomemovies.models.Migration;

/**
 * Created by marcos.rocha on 8/24/16.
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration.Builder realmConfiguration = new RealmConfiguration.Builder(this)
                .name("awesomemovies.realm")
                .schemaVersion(0)
                .migration(new Migration());

        if (BuildConfig.DEBUG) {
            realmConfiguration.deleteRealmIfMigrationNeeded();
        }

        Realm.setDefaultConfiguration(realmConfiguration.build());
    }
}
