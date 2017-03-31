package view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fs.sha28.R;

import model.EventDay;

/**
 * Created by AkmalJaved on 3/31/2017.
 */

public class DayView extends RelativeLayout {
    private EventDay eventDay;
    private RelativeLayout rlSelection;
    private LayoutInflater inflater;

    public DayView(Context context, EventDay eventDay) {
        super(context);
        this.eventDay=eventDay;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
    }

    private void init(){
        View view;
        view = inflater.inflate(R.layout.agenda_day_view, null, false);
        rlSelection=(RelativeLayout) view.findViewById(R.id.rlDaySelector);
        TextView txtDayName= (TextView) view.findViewById(R.id.txtDayName);
        txtDayName.setText(eventDay.getDay());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        p.weight = 1;
        setLayoutParams(p);
        if(eventDay.isSelected()){
            rlSelection.setBackgroundColor(Color.WHITE);
        }else{
            rlSelection.setBackgroundColor(Color.parseColor("#003e52"));
        }
        addView(view);

    }

    public void setSelected(){
        rlSelection.setBackgroundColor(Color.WHITE);
        eventDay.setSelected(true);
        invalidate();
    }
    public void setUnSelected(){
        rlSelection.setBackgroundColor(Color.parseColor("#003e52"));
        eventDay.setSelected(false);
        invalidate();
    }
    public boolean isSelected(){
        return eventDay.isSelected();
    }
    public EventDay getEventDay(){
        return eventDay;
    }
}
