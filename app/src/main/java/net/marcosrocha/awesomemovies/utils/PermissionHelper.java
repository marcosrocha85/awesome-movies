package net.marcosrocha.awesomemovies.utils;

import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * Created by marcos.rocha on 9/20/16.
 */
public class PermissionHelper {

    public static String[] checkSelfPermissions(AppCompatActivity activity, String[] requestedPermissions) {
        ArrayList<String> returnPermission = new ArrayList<>();
        for (String permission : requestedPermissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                returnPermission.add(permission);
            }
        }

        String[] returnArray = new String[returnPermission.size()];
        returnArray = returnPermission.toArray(returnArray);
        return returnArray;
    }

    public static boolean checkAllGranted(String[] permissions, int[] grantResults) {
        if (permissions.length > grantResults.length) {
            return false;
        }

        for (int position = 0; position < permissions.length; position++) {
            if (grantResults[position] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }
}
