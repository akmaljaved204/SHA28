package com.fs.sha28;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import model.Sponsor;
import utils.Global;

public class SponsorDetailScreen extends Activity {

    private TextView txtHeader,txtCategory,txtDetail;
    private ImageView imgSponsor;
    private Sponsor sponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_detail_screen);
        sponsor= (Sponsor) getIntent().getExtras().getSerializable("sponsorObject");
        txtHeader= (TextView) findViewById(R.id.txtHeader);
        txtCategory= (TextView) findViewById(R.id.txtCategory);
        txtDetail= (TextView) findViewById(R.id.txtDetail);
        imgSponsor= (ImageView) findViewById(R.id.imgSponsorImage);
        txtHeader.setText(sponsor.getSponsorName());
        txtCategory.setText(sponsor.getCategoryName());
        txtDetail.setText(sponsor.getSponsorDesc());
        txtCategory.setBackgroundColor(Color.parseColor(sponsor.getCategoryColor()));
        Picasso.with(this)
                .load(Global.BASE_URL_IMAGE+sponsor.getSponsorLogo())
                .placeholder(R.mipmap.defualt_imag)
                .error(R.mipmap.defualt_imag)
                .into(imgSponsor);
    }
}
