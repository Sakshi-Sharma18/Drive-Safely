package com.sakshi.myemergencyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView Appname= (ImageView) findViewById(R.id.Appname);

        CountDownTimer ctd= new CountDownTimer(2000, 20) {
            @Override
            public void onTick(long l) {
                Appname.animate().translationYBy(-500f);
            }

            @Override
            public void onFinish() {

                Appname.animate().alpha(0f);
                Intent intent= new Intent(getApplicationContext(), loginActivity.class);
                startActivity(intent);

            }
        }.start();
    }
}