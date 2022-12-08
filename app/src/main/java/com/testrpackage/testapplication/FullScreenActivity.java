package com.testrpackage.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullScreenActivity extends AppCompatActivity {

    String url;
    ImageView fullImage;
    Double resol;
    int doubleToInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        init();
    }

    public void init() {
        fullImage = findViewById(R.id.fullScreenIm);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            url = null;
        } else {
            url = extras.getString("url");
            resol = extras.getDouble("resol");
            doubleToInt = (int) ((int) 4000*resol);
            Picasso.get().load(url).resize(doubleToInt, 4000).onlyScaleDown().into(fullImage);
        }
    }
}