package com.example.android.myappportfolio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button[] button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;


        button = new Button[6];

        button[0] = (Button) findViewById(R.id.spotify_streamer);
        button[1] = (Button) findViewById(R.id.scores_app);
        button[2] = (Button) findViewById(R.id.library_app);
        button[3] = (Button) findViewById(R.id.build_it_bigger);
        button[4] = (Button) findViewById(R.id.xyz_reader);
        button[5] = (Button) findViewById(R.id.capstone);

        button[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast = Toast.makeText(context, Constants.prefix + Constants.message[0], duration);
                toast.show();
            }
        });

        button[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast = Toast.makeText(context, Constants.prefix + Constants.message[1], duration);
                toast.show();
            }
        });

        button[2].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast = Toast.makeText(context, Constants.prefix + Constants.message[2], duration);
                toast.show();
            }
        });

        button[3].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast = Toast.makeText(context, Constants.prefix + Constants.message[3], duration);
                toast.show();
            }
        });

        button[4].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast = Toast.makeText(context, Constants.prefix + Constants.message[4], duration);
                toast.show();
            }
        });

        button[5].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast toast = Toast.makeText(context, Constants.prefix + Constants.message[5], duration);
                toast.show();
            }
        });
    }
}
