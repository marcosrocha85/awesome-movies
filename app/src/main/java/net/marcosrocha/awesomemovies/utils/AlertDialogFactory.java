package net.marcosrocha.awesomemovies.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import net.marcosrocha.awesomemovies.R;

/**
 * Created by marcos.rocha on 8/30/16.
 */
public class AlertDialogFactory {
    public static AlertDialog.Builder make(Context context, int resIdTitle, int resIdMessage) {
        return make(context,
                context.getString(resIdTitle),
                context.getString(resIdMessage));
    }

    public static AlertDialog.Builder make(Context context, String title, String message) {
        AlertDialog.Builder thisDialog = new AlertDialog.Builder(context);
        thisDialog.setTitle(title);
        thisDialog.setMessage(message);

        return thisDialog;
    }

    public static void info(Context context, int resIdMessage) {
        info(context, context.getString(resIdMessage));
    }

    public static void info(Context context, String message) {
        AlertDialog.Builder dialog = make(context, context.getString(R.string.info), message);
        dialog.setNeutralButton(R.string.ok, null);
        dialog.show();
    }

    public static void confirmation(Context context,
                                    DialogInterface.OnClickListener positiveListener,
                                    DialogInterface.OnClickListener negativeListener,
                                    int resIdMessage, Object... params) {
        AlertDialog.Builder dialog = make(context,
                context.getString(R.string.confirmation),
                context.getString(resIdMessage, params)
        );
        dialog.setNegativeButton(R.string.no_button, negativeListener);
        dialog.setPositiveButton(R.string.yes_button, positiveListener);
        dialog.show();
    }
    public static void warning(Context context, String message) {
        AlertDialog.Builder dialog = make(context, context.getString(R.string.warning), message);
        dialog.setNeutralButton(R.string.ok, null);
        dialog.show();
    }
    public static void error(Context context, String message) {
        AlertDialog.Builder dialog = make(context, context.getString(R.string.error), message);
        dialog.setNeutralButton(R.string.ok, null);
        dialog.show();
    }
}
