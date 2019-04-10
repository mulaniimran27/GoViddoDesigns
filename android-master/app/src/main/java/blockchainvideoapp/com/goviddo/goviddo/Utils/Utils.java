package blockchainvideoapp.com.goviddo.goviddo.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
