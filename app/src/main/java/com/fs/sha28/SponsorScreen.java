package com.fs.sha28;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.SectionedGridRecyclerViewAdapter;
import adapters.SimpleAdapter;
import model.GenratClasses;
import model.Sponsor;
import utils.Global;
import utils.OnApihit;
import utils.Progress;
import utils.VolleyBase;

public class SponsorScreen extends Activity {

    private Progress pd;
    private SharedPreferences sp;
    private List<Sponsor> sponsorList;
    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_screen);
        sp=getSharedPreferences(Global.PREF_LOGIN, Context.MODE_PRIVATE);
        mRecyclerView = (RecyclerView)findViewById(R.id.sponsor_recyle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        getDeals();
    }

    private void getDeals(){
        pd=new Progress(SponsorScreen.this);
        pd.show();
        Map<String, String> params = new HashMap<>();
        params.put("eventId",Global.EVENT_ID);
        new VolleyBase(new OnApihit() {
            @Override
            public void success(String Responce)
            {
                sponsorList=new ArrayList<>();
                try
                {
                    JSONObject jarray = new JSONObject(Responce);
                    JSONArray user_speakers=jarray.optJSONArray("sponsors");
                    sponsorList=new GenratClasses().getspossors(user_speakers.toString());
                    mAdapter = new SimpleAdapter(SponsorScreen.this,sponsorList);
                    List<SectionedGridRecyclerViewAdapter.Section> sections = new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
                    String tempCat="";
                    for(int i=0;i<sponsorList.size();i++){
                        if(!sponsorList.get(i).getCategoryName().equals(tempCat)){
                            sections.add(new SectionedGridRecyclerViewAdapter.Section(i,sponsorList.get(i).getCategoryName(),sponsorList.get(i).getCategoryColor()));
                            tempCat=sponsorList.get(i).getCategoryName();
                        }
                    }
                    SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
                    SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                            SectionedGridRecyclerViewAdapter(SponsorScreen.this,R.layout.section,R.id.section_text,mRecyclerView,mAdapter);
                    mSectionedAdapter.setSections(sections.toArray(dummy));
                    mRecyclerView.setAdapter(mSectionedAdapter);
                }
                catch (JSONException e) {
                    pd.dismiss();
                    e.printStackTrace();
                }
                pd.dismiss();
            }
            @Override
            public void error(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        }).main(params,Global.BASE_URL+"get_sponsors");
    }
    public void onItemClick(Sponsor sponsor){
        Intent intent=new Intent(SponsorScreen.this,SponsorDetailScreen.class);
        intent.putExtra("sponsorObject",sponsor);
        startActivity(intent);
    }
}
