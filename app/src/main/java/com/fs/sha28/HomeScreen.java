package com.fs.sha28;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adapters.AgendaAdapter;
import model.GenratClasses;
import utils.Global;
import utils.OnApihit;
import utils.Progress;
import utils.VolleyBase;

public class HomeScreen extends Activity implements View.OnClickListener{

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private RelativeLayout btnAgenda,btnSpeaker,btnExhibitor,btnFloorPlan;
    private ImageView btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        mNavigationDrawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),(RelativeLayout)findViewById(R.id.container),HomeScreen.this);
        btnAgenda= (RelativeLayout) findViewById(R.id.rlAgenda);
        btnSpeaker= (RelativeLayout) findViewById(R.id.rlSpeakerBios);
        btnExhibitor= (RelativeLayout) findViewById(R.id.rlExhibitor);
        btnFloorPlan= (RelativeLayout) findViewById(R.id.rlFloorPlan);
        btnMenu= (ImageView) findViewById(R.id.imgMenu);
        btnAgenda.setOnClickListener(this);
        btnSpeaker.setOnClickListener(this);
        btnExhibitor.setOnClickListener(this);
        btnFloorPlan.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
        getDeals();
    }

    @Override
    public void onClick(View v) {
        if(btnMenu==v){
            mNavigationDrawerFragment.openDrawer();
        }else if(btnAgenda==v){
            Intent intent = new Intent(HomeScreen.this,AgendaActivity.class);
            startActivity(intent);
        }else if(btnSpeaker==v){
            Intent intent = new Intent(HomeScreen.this,SpeakersBiography.class);
            startActivity(intent);
        }else if(btnExhibitor==v){
            Intent intent = new Intent(HomeScreen.this,SponsorScreen.class);
            startActivity(intent);
        }else if(btnFloorPlan==v){
            Intent intent = new Intent(HomeScreen.this,FloorActivity.class);
            startActivity(intent);
        }
    }
    private void getDeals(){
        Map<String, String> params = new HashMap<>();
        params.put("eventId", Global.EVENT_ID);
        new VolleyBase(new OnApihit() {
            @Override
            public void success(String Responce)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(Responce);
                    Global.EVENT_DAYS=new GenratClasses().getEventDays(jsonObject.getJSONArray("agenda_days").toString());
                    System.out.print(jsonObject);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void error(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        }).main(params,Global.BASE_URL+"agendaDays");
    }
}
