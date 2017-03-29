package com.fs.sha28;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapters.HomeGridAdapter;
import utils.Global;
import utils.OnApihit;
import utils.Progress;
import utils.VolleyBase;

/**
 * Created by admin on 3/7/2017.
 */

public class SpeakersBiography extends Activity {
  ListView list_view;
    Progress pd;
    SharedPreferences sp;
    HashMap<String, String> map;
    String event_id,key;
    HomeGridAdapter adapter;
    ArrayList<HashMap<String,String>> dealsList;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.speakersbio);
        list_view=(ListView)findViewById(R.id.list_view);
        sp=getSharedPreferences(Global.PREF_LOGIN, Context.MODE_PRIVATE);
        key=sp.getString(Global.USER_ID,"");
        event_id=sp.getString(Global.USER_EventID,"");
        getDeals();


    }

    private void getDeals(){
        pd=new Progress(SpeakersBiography.this);
        pd.show();
        Map<String, String> params = new HashMap<>();
        params.put("eventId",event_id);
        params.put("key",key);

        new VolleyBase(new OnApihit() {
            @Override
            public void success(String Responce) {
                pd.dismiss();
                dealsList=new ArrayList<HashMap<String,String>> ();
                try{
                    JSONObject jarray = new JSONObject(Responce);
                    JSONArray user_speakers=jarray.optJSONArray("speakers");
                    for(int i=0;i<user_speakers.length();i++) {
                        JSONObject obj = new JSONObject(user_speakers.optString(i));
                        map = new HashMap<String, String>();
                        map.put("PresenterType",obj.optString("PresenterType"));
                        map.put("CountryName",obj.optString("CountryName"));
                        map.put("FullName",obj.optString("FullName"));
                        map.put("Designation",obj.optString("Designation"));
                        map.put("WorkingPlace",obj.optString("WorkingPlace"));
                        map.put("Gender",obj.optString("Gender"));
                        map.put("WorkAddress",obj.optString("WorkAddress"));
                        map.put("Category",obj.optString("Category"));
                        map.put("BigImage",obj.optString("BigImage"));
                        map.put("ThumbImage",obj.optString("ThumbImage"));
                        map.put("City",obj.optString("City"));
                        dealsList.add(map);

                    }
                    adapter=new HomeGridAdapter(getApplicationContext(),dealsList);
                    list_view.setAdapter(adapter);
                    list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> par, View parent,
                                                int position, long arg3) {
                          Intent intent = new Intent(getApplicationContext(),SpeakersBiographyDetails.class);
                            intent.putExtra("FullName", dealsList.get(position).get("FullName"));
                            intent.putExtra("Gender", dealsList.get(position).get("Gender"));
                            intent.putExtra("Category", dealsList.get(position).get("Category"));
                            intent.putExtra("Designation", dealsList.get(position).get("Designation"));
                            intent.putExtra("WorkingPlace", dealsList.get(position).get("WorkingPlace"));
                            intent.putExtra("WorkAddress", dealsList.get(position).get("WorkAddress"));
                            intent.putExtra("BigImage", dealsList.get(position).get("BigImage"));
                            startActivity(intent);
                            overridePendingTransition(0,0);


                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void error(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        }).main(params,"http://krecloud.com/api/v1/get_speakers");
    }
}