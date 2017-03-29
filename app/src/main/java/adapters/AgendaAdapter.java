package adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fs.sha28.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Agenda;

/**
 * Created by admin on 3/8/2017.
 */

public class AgendaAdapter extends BaseAdapter {
    Context c;
    List<Agenda> dealsList;
    public AgendaAdapter(Context c,List<Agenda> dealsList){
        this.c=c;
        this.dealsList=dealsList;


    }
    @Override
    public int getCount() {
        return dealsList.size();
    }

    @Override
    public Object getItem(int position) {
        return dealsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.agenda_date, parent, false);
        TextView title=(TextView)row.findViewById(R.id.first);
        TextView deaname=(TextView)row.findViewById(R.id.second);
        TextView dearate=(TextView)row.findViewById(R.id.third);
        title.setText(dealsList.get(position).getTitle());
        deaname.setText(dealsList.get(position).getHallsname());
        dearate.setText("Session"+" "+dealsList.get(position).getSessionid()+" "+dealsList.get(position).getDay()+" "+dealsList.get(position).getDated()+"/"+
                dealsList.get(position).getDatem()+"/"+dealsList.get(position).getDatey()+" @"+dealsList.get(position).getTimein());
        return row;
    }

}

