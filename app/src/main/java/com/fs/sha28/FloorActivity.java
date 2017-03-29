package com.fs.sha28;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import utils.Global;
import utils.Progress;

/**
 * Created by admin on 3/7/2017.
 */

public class FloorActivity extends Activity {
    EditText user_id;
    ImageView imgFloorImage;
    SharedPreferences sp;
    String floorplan;
    private Progress pd;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.floor_plan);
        pd=new Progress(this);
        pd.show();
        imgFloorImage=(ImageView)findViewById(R.id.imge);
        sp=getSharedPreferences(Global.PREF_LOGIN, Context.MODE_PRIVATE);
        floorplan=sp.getString(Global.USER_Floorplan,"");
        Picasso.with(this)
                .load(Global.BASE_URL_IMAGE+floorplan)
                .into(imgFloorImage, new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {
                        pd.dismiss();
                    }

                    @Override
                    public void onError() {
                        pd.dismiss();
                    }
                });
    }
}
