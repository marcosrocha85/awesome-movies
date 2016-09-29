package net.marcosrocha.awesomemovies.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by marcos.rocha on 9/15/16.
 */
public class ProgressDialogHelper {
    public static ProgressDialog.Builder builder(Context context, int titleId, int messageId) {
        ProgressDialog.Builder builderInstance = new ProgressDialog.Builder(context);
        builderInstance.setTitle(titleId);
        builderInstance.setMessage(messageId);

        return builderInstance;
    }

    public static ProgressDialog.Builder builder(Context context, String title, String message) {
        ProgressDialog.Builder builderInstance = new ProgressDialog.Builder(context);
        builderInstance.setTitle(title);
        builderInstance.setMessage(message);

        return builderInstance;
    }

    public static android.app.AlertDialog show(Context context, int titleId, int messageId) {
        ProgressDialog.Builder builder = builder(context, titleId, messageId);

        return builder.show();
    }

    public static android.app.AlertDialog show(Context context, String title, String message) {
        ProgressDialog.Builder builder = builder(context, title, message);

        return builder.show();
    }
}
