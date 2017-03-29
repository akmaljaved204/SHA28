package utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WebCom on 10/24/2016.
 */
public class Validations {
    public static boolean match(String str)
    {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(str);
        boolean bs = ms.matches();
        return bs;
    }
    public static boolean registervalidate(Context context, String phone, String name, String lname, String password, String email) {

        if(name.length()==0 || phone.length()==0 || password.length()==0 || lname.length()==0|| email.length()==0)
        {
            CommonUtils.setAlertDialogBox(context, "Please fill all fields..!!");
            return false;
        }

        else if(!match(name))
        {
            CommonUtils.setAlertDialogBox(context, "Please enter valid First Name..!!");
            return false;
        }
        else if(!match(lname))
        {
            CommonUtils.setAlertDialogBox(context, "Please enter valid Last Name..!!");
            return false;
        }

        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            CommonUtils.setAlertDialogBox(context, "Please enter valid email..!!");
            return false;
        }

        else  if(password.length()<6 || password.length()>20)
        {
            CommonUtils.setAlertDialogBox(context, "Password must be 6 to 20 chars..!!");
            return false;
        }

        else  if(phone.length()<8 || phone.length()>14)
        {
            CommonUtils.setAlertDialogBox(context, "Phone no. no valid");
            return false;
        }
        else
        {
            return true;
        }

    }

    public static boolean Loginvalidate(Context context, String phone, String password) {

        if(phone.length()==0 || password.length()==0)
        {
            CommonUtils.setAlertDialogBox(context, "Please fill all fields..!!");
            return false;
        }
        else
        {
            return true;
        }
    }


}
