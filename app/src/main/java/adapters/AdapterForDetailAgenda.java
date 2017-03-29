package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fs.sha28.R;
import java.util.List;
import model.AgendaDetail;

public class AdapterForDetailAgenda extends ArrayAdapter<AgendaDetail>{

	private LayoutInflater inflater;
	private Context context;
	public AdapterForDetailAgenda(Context contextL, int textViewResourceId, List<AgendaDetail> objects) {
		super(contextL, textViewResourceId, objects);
		context=contextL;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		View row;
		AgendaDetail item = getItem(position);
		if(item.getType()==0){
			row = inflater.inflate(R.layout.agenda_detail_main_item, parent, false);
			TextView txtAgendaTitle= (TextView) row.findViewById(R.id.agendaTitle);
			TextView txtAgendaHallname= (TextView) row.findViewById(R.id.agendaHallname);
			TextView txtAgendaSession= (TextView) row.findViewById(R.id.agendaSession);
			TextView txtAgendaSessionType= (TextView) row.findViewById(R.id.agendaSessionType);
			txtAgendaTitle.setText(item.getAgenda().getTitle());
			txtAgendaHallname.setText("Hall name"+item.getAgenda().getHallsname());
			txtAgendaSession.setText(item.getAgenda().getSessionid()+" "+item.getAgenda().getDay()+" "+item.getAgenda().getDated()+"/"+
					item.getAgenda().getDatem()+"/"+item.getAgenda().getDatey()+" @"+item.getAgenda().getTimein()+" - "+item.getAgenda().getTimeout());
			txtAgendaSessionType.setText("Session Type: "+item.getAgenda().getType());
		}else if(item.getType()==1){
			row = inflater.inflate(R.layout.agenda_detail_divider_item, parent, false);
			TextView txtHeader= (TextView) row.findViewById(R.id.txtDivider);
			txtHeader.setText(item.getHeaderName());
		}else if(item.getType()==2){
			row = inflater.inflate(R.layout.agenda_detail_chairman_item, parent, false);
			TextView txtChairmanName= (TextView) row.findViewById(R.id.txtChaimanName);
			txtChairmanName.setText(item.getChairman().getFullName());
		}else{
			row = inflater.inflate(R.layout.agenda_detail_topic_item, parent, false);
			TextView txtTopicTitle= (TextView) row.findViewById(R.id.topicTitle);
			TextView txtTimeFrom= (TextView) row.findViewById(R.id.txtTimeFrom);
			TextView txtTimeTo= (TextView) row.findViewById(R.id.txtTimeTo);
			TextView txtName= (TextView) row.findViewById(R.id.txtName);
			txtTopicTitle.setText(item.getTopics().getTopic());
			txtTimeFrom.setText("Time From: "+item.getTopics().getTimeFrom());
			txtTimeTo.setText("Time To: "+item.getTopics().getTimeTo());
			txtName.setText(item.getTopics().getSpeakerName());
		}
		return row;
	}
}
