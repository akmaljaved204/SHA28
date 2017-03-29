package utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.fs.sha28.R;

import java.text.DateFormatSymbols;

/**
 * Created by WebCom on 10/20/2016.
 */
public class CommonUtils {

    public static void showToast(Context c, String toastline){
        Toast.makeText(c,toastline, Toast.LENGTH_LONG).show();
    }

    public static boolean checkConnection(Context c) {
        boolean b;
        ConnectivityManager cm = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null) && (wifi.isConnected() | datac.isConnected())) {
            return true;
        }else{
            return false;
        }
    }
    public static void setAlertDialogBox(Context c, String error)
    {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(c);
        builder1.setMessage(error);
        builder1.setNegativeButton("oK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.getWindow().getAttributes().windowAnimations = R.style.Animations_SmileWindow;
        alert11.show();
    }
    public static String ordinal(int date, int month)
    {
        String[] suffix = {"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        int m = date % 100;
        String result;
        String montha=new DateFormatSymbols().getMonths()[month];
        result= String.valueOf(date) + suffix[(m > 10 && m < 20) ? 0 : (m % 10)]+" "+montha.substring(0, 3);
        return  result;
    }
}
