package com.fs.sha28;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import utils.Global;
import utils.Progress;

public class SpeakersBiographyDetails extends Activity {

    ImageView main_inmg;
    Intent in;
    TextView  first,second,third,four,five;
    String FullName,Gender,Category,Designation,WorkingPlace,WorkAddress,BigImage;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.speakersbio_deatils);
        first=(TextView)findViewById(R.id.first);
        second=(TextView)findViewById(R.id.two);
        third=(TextView)findViewById(R.id.three);
        four=(TextView)findViewById(R.id.fourr);
        five=(TextView)findViewById(R.id.fivee);
        main_inmg=(ImageView)findViewById(R.id.progressBar);
        in=getIntent();
        FullName=in.getStringExtra("FullName");
        Gender=in.getStringExtra("Gender");
        Category=in.getStringExtra("Category");
        Designation=in.getStringExtra("Designation");
        WorkingPlace=in.getStringExtra("WorkingPlace");
        WorkAddress=in.getStringExtra("WorkAddress");
        BigImage=in.getStringExtra("BigImage");
        Picasso.with(this)
                .load(Global.BASE_URL_IMAGE+"Invitations/"+BigImage)
                .placeholder(R.mipmap.defualt_imag)
                .error(R.mipmap.defualt_imag)
                .into(main_inmg);

        first.setText(FullName);
        second.setText(Gender);
        third.setText("Category: " +Category);
        four.setText("Designation: " +Designation);
        five.setText(WorkingPlace +", "+WorkAddress);

    }

}
