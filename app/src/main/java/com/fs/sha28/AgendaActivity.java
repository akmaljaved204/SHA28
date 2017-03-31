package com.fs.sha28;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import adapters.AgendaAdapter;
import model.Agenda;
import model.GenratClasses;
import utils.Global;
import utils.OnApihit;
import utils.Progress;
import utils.VolleyBase;
import view.DayView;

import static utils.Global.EVENT_DAYS;


public class AgendaActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private List<Agenda> agendaList=new ArrayList<>();
    private List<Agenda> agendaSearchList=new ArrayList<>();
    private RelativeLayout rlNormal,rlSearch;
    private ImageView imgClose,imgSearch;
    private LinearLayout llDayContainer;
    private ListView list_view;
    private EditText edtSearch;
    private Progress pd;
    private SharedPreferences sp;
    private int selectedTab=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_test);
        llDayContainer= (LinearLayout) findViewById(R.id.llDayContainer);
        llDayContainer.setWeightSum((float)Global.EVENT_DAYS.size());
        edtSearch=(EditText) findViewById(R.id.edtSearch);
        list_view=(ListView) findViewById(R.id.list_view);
        imgClose=(ImageView) findViewById(R.id.imgCrossSearch);
        imgSearch=(ImageView) findViewById(R.id.imgSearchIcon);
        rlNormal=(RelativeLayout) findViewById(R.id.rlNormalView);
        rlSearch=(RelativeLayout) findViewById(R.id.rlSearchView);
        list_view.setOnItemClickListener(this);
        imgClose.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        sp=getSharedPreferences(Global.PREF_LOGIN, Context.MODE_PRIVATE);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchActivity(editable.toString());
            }
        });
        getDeals();

        for(int i=0;i<Global.EVENT_DAYS.size();i++){
            if(i==0){
                Global.EVENT_DAYS.get(i).setSelected(true);
            }
            DayView dayView=new DayView(this,Global.EVENT_DAYS.get(i));
            dayView.setOnClickListener(this);
            llDayContainer.addView(dayView);
        }

    }

    private void getDeals(){
        pd=new Progress(AgendaActivity.this);
        pd.show();
        Map<String, String> params = new HashMap<>();
        params.put("eventId",Global.EVENT_ID);
        new VolleyBase(new OnApihit() {
            @Override
            public void success(String Responce)
            {
                pd.dismiss();
                try
                {
                    JSONObject jarray = new JSONObject(Responce);
                    JSONArray user_speakers=jarray.optJSONArray("agenda");
                    agendaList=new GenratClasses().getAgendas(user_speakers.toString());
                    loadAllData();
                    list_view.setAdapter(new AgendaAdapter(getApplicationContext(),agendaSearchList));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void error(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        }).main(params,Global.BASE_URL+"realtimeagenda");
    }



    private void filterData(){
        if(selectedTab==0){
            loadAllData();
        }else if(selectedTab==1){
            loadAllData();
            Collections.sort(agendaSearchList, new Comparator<Agenda>() {
                @Override
                public int compare(Agenda agenda1, Agenda agenda2) {
                    return agenda1.getDay().compareTo(agenda2.getDay());
                }
            });
        }else if(selectedTab==2){
            loadAllData();
            Collections.sort(agendaSearchList, new Comparator<Agenda>() {
                @Override
                public int compare(Agenda agenda1, Agenda agenda2) {
                    return agenda1.getSessionid().compareTo(agenda2.getSessionid());
                }
            });
        }
        ((AgendaAdapter)list_view.getAdapter()).notifyDataSetChanged();
    }
    private void searchActivity(String search){
        if(search.equals("")){
            loadAllData();
        }else{
            agendaSearchList.clear();
            for(int i=0;i<agendaList.size();i++){
                if(agendaList.get(i).getTitle().toLowerCase().contains(search.toLowerCase())){
                    agendaSearchList.add(agendaList.get(i));
                }
            }

        }
        ((AgendaAdapter)list_view.getAdapter()).notifyDataSetChanged();
    }


    private void loadAllData(){
        agendaSearchList.clear();
        for(int i=0;i<agendaList.size();i++){
            agendaSearchList.add(agendaList.get(i));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent=new Intent(AgendaActivity.this, AgendaDetailScreen.class);
        intent.putExtra("agendaObject",agendaSearchList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(imgClose==v){
            rlNormal.setVisibility(View.VISIBLE);
            rlSearch.setVisibility(View.GONE);
        }else if(imgSearch==v){
            rlNormal.setVisibility(View.GONE);
            rlSearch.setVisibility(View.VISIBLE);
        }else if(v instanceof DayView){
            DayView clickedDayView=(DayView)v;
            for(int i=0;i<llDayContainer.getChildCount();i++){
                DayView dayView=(DayView)llDayContainer.getChildAt(i);
                dayView.setUnSelected();
            }
            if(!clickedDayView.isSelected()){
                clickedDayView.setSelected();
            }

        }
    }
}

