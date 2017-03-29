package utils;

import com.android.volley.VolleyError;

/**
 * Created by Tejinder on 7/22/2016.
 */
public interface OnApihit {
   void success(String Responce);
    void error(VolleyError error);
}
