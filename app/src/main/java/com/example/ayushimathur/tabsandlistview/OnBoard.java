package com.example.ayushimathur.tabsandlistview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ayushimathur.tabsandlistview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class OnBoard extends AppCompatActivity {
    private ProgressDialog pDialog;
    public static JSONArray artistList = new JSONArray();


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
        new LoadArtist().execute();
        getsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SelectArtist.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class LoadArtist extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(OnBoard.this);
            pDialog.setMessage("Loading Artist Information...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting Inbox JSON
         */
        protected String doInBackground(String... args) {
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                artistList = obj.getJSONArray("artists");
            } catch (JSONException e) {
                e.printStackTrace();

            }
             return null;
            }

        public String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getResources().openRawResource(R.raw.schedule);
                int size = is.available();
                Log.d("filesize", String.valueOf(size));
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer);
            } catch (IOException ex) {
            }
            Log.d("JSON","HII");
            //Log.d("JSON",json);
            return json;
        }



    }
}


