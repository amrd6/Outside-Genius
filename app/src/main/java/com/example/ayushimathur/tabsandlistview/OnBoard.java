package com.example.ayushimathur.tabsandlistview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ayushimathur.tabsandlistview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class OnBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        TextView welcome = (TextView) findViewById(R.id.welcomeText);
        TextView outsideLand = (TextView) findViewById(R.id.outsideLands);
        Button getsStart = (Button) findViewById(R.id.letsGo);

        outsideLand.setText("Welcome To Outside Lands");
        welcome.setText("Listen Love And Let Artists become part of your schedule");
        getsStart.setText("Got it!");
        getsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SelectArtist.class);
                startActivity(intent);
                finish();
            }
        });
    }

}


