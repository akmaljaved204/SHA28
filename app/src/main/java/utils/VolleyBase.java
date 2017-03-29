package utils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Tejinder on 7/22/2016.
 */
public class VolleyBase {
    String request;
    OnApihit api;
    public VolleyBase(OnApihit api){

        this.api=api;
    }
    public String main(final Map<String, String> params, String URL){

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        request=response;
                        api.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        api.error(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        AppController.getInstance().addToRequestQueue(postRequest);
       return request;
    }
}
