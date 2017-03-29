package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fs.sha28.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Global;

/**
 * Created by  10/21/2016.
 */
public class HomeGridAdapter extends BaseAdapter {
    Context c;
    ArrayList<HashMap<String,String>> dealsList;
    public HomeGridAdapter(Context c,ArrayList<HashMap<String,String>> dealsList){
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
        View row=inflater.inflate(R.layout.header, parent, false);
        TextView title=(TextView)row.findViewById(R.id.email);
        TextView deaname=(TextView)row.findViewById(R.id.second);
        TextView dearate=(TextView)row.findViewById(R.id.third);
       final ImageView circleView=(ImageView)row.findViewById(R.id.circleView);
        title.setText(dealsList.get(position).get("FullName"));
        deaname.setText(dealsList.get(position).get("Designation"));
        dearate.setText(dealsList.get(position).get("CountryName"));

       /* Glide.with(c).load("http://krecloud.com/Invitations/"+dealsList.get(position).get("ThumbImage")).asBitmap().centerCrop().into(new BitmapImageViewTarget(circleView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(c.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                circleView.setImageDrawable(circularBitmapDrawable);
            }
        });*/
        Picasso.with(c)
                .load(Global.BASE_URL_IMAGE+"Invitations/"+dealsList.get(position).get("ThumbImage"))
                .placeholder(R.mipmap.defualt_imag)
                .error(R.mipmap.defualt_imag)
                .into(circleView);
        return row;
    }

}
