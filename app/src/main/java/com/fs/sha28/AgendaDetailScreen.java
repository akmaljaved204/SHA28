package com.fs.sha28;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import adapters.AdapterForDetailAgenda;
import model.Agenda;
import model.AgendaDetail;

/**
 * Created by AkmalJaved on 3/9/2017.
 */
public class AgendaDetailScreen extends Activity{

    private ListView lvDetail;
    private ArrayList<AgendaDetail> detailArray=new ArrayList<>();
    private Agenda agenda;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.agenda_detail_screen);
        agenda= (Agenda) getIntent().getExtras().getSerializable("agendaObject");
        lvDetail= (ListView) findViewById(R.id.lvAgendaDetail);
        loadData();
        lvDetail.setAdapter(new AdapterForDetailAgenda(AgendaDetailScreen.this,R.layout.agenda_detail_divider_item,detailArray));
    }
    private void loadData(){
        AgendaDetail agendaDetail=new AgendaDetail();
        agendaDetail.setAgenda(agenda);
        agendaDetail.setType(0);
        detailArray.add(agendaDetail);
        agendaDetail=new AgendaDetail();
        agendaDetail.setHeaderName("Chairmans");
        agendaDetail.setType(1);
        detailArray.add(agendaDetail);

        for(int i=0;i<agenda.getChairmans().size();i++){
            agendaDetail=new AgendaDetail();
            agendaDetail.setType(2);
            agendaDetail.setChairman(agenda.getChairmans().get(i));
            detailArray.add(agendaDetail);
        }
        agendaDetail=new AgendaDetail();
        agendaDetail.setHeaderName("Topics");
        agendaDetail.setType(1);
        detailArray.add(agendaDetail);

        for(int i=0;i<agenda.getTopics().size();i++){
            agendaDetail=new AgendaDetail();
            agendaDetail.setType(3);
            agendaDetail.setTopics(agenda.getTopics().get(i));
            detailArray.add(agendaDetail);
        }
    }

}
