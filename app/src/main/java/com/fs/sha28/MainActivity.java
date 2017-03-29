package com.fs.sha28;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by admin on 3/7/2017.
 */

public class MainActivity  extends Activity {
    LinearLayout real_time_linear,speaker_linear,sponser_layout,floor_layout,logout_linear;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
                real_time_linear=(LinearLayout)findViewById(R.id.real_time_linear);
                speaker_linear=(LinearLayout)findViewById(R.id.speaker_linear);
                sponser_layout=(LinearLayout)findViewById(R.id.sponser_layout);
                floor_layout=(LinearLayout)findViewById(R.id.floor_layout);
                logout_linear=(LinearLayout)findViewById(R.id.logout_linear);

        real_time_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),AgendaActivity.class);
                startActivity(in);
                overridePendingTransition(0,0);

            }
        });
        speaker_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),SpeakersBiography.class);
                startActivity(in);
                overridePendingTransition(0,0);
            }
        });

        sponser_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SponsorScreen.class);
                startActivity(intent);
            }
        });

        floor_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),FloorActivity.class);
                startActivity(in);
                overridePendingTransition(0,0);
            }
        });

        logout_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),Login.class);
                startActivity(in);
                finish();
                overridePendingTransition(0,0);
            }
        });


    }
}