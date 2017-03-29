package com.fs.sha28;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import utils.Global;
import utils.OnApihit;
import utils.Progress;
import utils.VolleyBase;

/**
 * Created by admin on 3/5/2017.
 */

public class Login extends Activity {
    EditText user_id;
    ImageView login;
    String userid,KEY,status,EventID;
    SharedPreferences sp;
    Progress pd;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_login);
        user_id=(EditText)findViewById(R.id.editText1);
        //user_id.setText("31007");
        login=(ImageView)findViewById(R.id.login);
        sp=getSharedPreferences(Global.PREF_LOGIN, Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid=user_id.getText().toString();
                getDeals();
            }
        });
    }

    private void getDeals(){
        pd=new Progress(Login.this);
        pd.show();
        Map<String, String> params = new HashMap<>();
        params.put("userId",userid);
        new VolleyBase(new OnApihit() {
            @Override
            public void success(String Responce) {
                pd.dismiss();
               try{
                   JSONObject jarray = new JSONObject(Responce);
                   JSONObject user_and_event_data=jarray.optJSONObject("user_and_event_data");
                   KEY=jarray.optString("KEY");
                   status=jarray.optString("status");
                   if(status.equals("true"))
                   {
                       SharedPreferences.Editor ed=sp.edit();
                       ed.putString(Global.USER_ID,jarray.getString("KEY"));
                       ed.putString(Global.USER_EventID,user_and_event_data.getString("EventID"));
                       ed.putString(Global.USER_Floorplan,user_and_event_data.getString("floorplan"));
                       ed.commit();
                       Intent in = new Intent(getApplicationContext(),MainActivity.class);
                       startActivity(in);
                       overridePendingTransition(0,0);
                       finish();
                   }else{
                       Toast.makeText(getApplicationContext(),"Invalid User Id",Toast.LENGTH_LONG).show();
                   }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void error(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        }).main(params,"http://krecloud.com/api/v1/login");
    }
}