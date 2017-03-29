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
import model.SponsorObject;

public class AdapterForSponsor extends ArrayAdapter<SponsorObject>{

	private LayoutInflater inflater;
	private Context context;
	public AdapterForSponsor(Context contextL, int textViewResourceId, List<SponsorObject> objects) {
		super(contextL, textViewResourceId, objects);
		context=contextL;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		View row;
		if(position%5==0){
			row = inflater.inflate(R.layout.sponsor_header_item, parent, false);
		}else{
			row = inflater.inflate(R.layout.sponsor_main_item, parent, false);
		}
		return row;
	}
}
