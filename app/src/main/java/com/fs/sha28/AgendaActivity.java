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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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


public class AgendaActivity extends Activity implements AdapterView.OnItemClickListener {
    private TextView txtDate,txtDay,txtSession;
    private List<Agenda> agendaList=new ArrayList<>();
    private List<Agenda> agendaSearchList=new ArrayList<>();
    private ListView list_view;
    private EditText edtSearch;
    private Progress pd;
    private SharedPreferences sp;
    private int selectedTab=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_test);
        txtDate=(TextView)findViewById(R.id.txtDate);
        txtDay=(TextView)findViewById(R.id.txtDay);
        txtSession=(TextView)findViewById(R.id.txtSession);
        edtSearch=(EditText) findViewById(R.id.edtSearch);
        list_view=(ListView) findViewById(R.id.list_view);
        list_view.setOnItemClickListener(this);
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
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab!=0){
                    selectedTab=0;
                    onChangeTab();
                }
            }
        });
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab!=1){
                    selectedTab=1;
                    onChangeTab();
                }
            }
        });
        txtSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab!=2){
                    selectedTab=2;
                    onChangeTab();
                }
            }
        });
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

    private void onChangeTab(){
        txtDate.setBackgroundResource(R.drawable.tabs_date_bg);
        txtDay.setBackgroundResource(R.drawable.tabs_day_bg);
        txtSession.setBackgroundResource(R.drawable.tabs_session_bg);
        txtDate.setTextColor(Color.WHITE);
        txtDay.setTextColor(Color.WHITE);
        txtSession.setTextColor(Color.WHITE);
        if(selectedTab==0){
            txtDate.setBackgroundResource(R.drawable.tabs_date_bg_sel);
            txtDate.setTextColor(Color.parseColor("#ef0d10"));
        }else if(selectedTab==1){
            txtDay.setBackgroundResource(R.drawable.tabs_day_bg_sel);
            txtDay.setTextColor(Color.parseColor("#ef0d10"));
        }else if(selectedTab==2){
            txtSession.setBackgroundResource(R.drawable.tabs_session_bg_sel);
            txtSession.setTextColor(Color.parseColor("#ef0d10"));
        }
        filterData();
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
}

