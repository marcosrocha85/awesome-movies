package net.marcosrocha.awesomemovies.utils;

import android.content.Context;

/**
 * Created by marcos.rocha on 9/26/16.
 */
public class __n {
    public static String get(Context context, int number, int resIdSingular, int resIdPlural) {
        if (number < 2) {
            return context.getString(resIdSingular, number);
        } else {
            return context.getString(resIdPlural, number);
        }
    }
}
