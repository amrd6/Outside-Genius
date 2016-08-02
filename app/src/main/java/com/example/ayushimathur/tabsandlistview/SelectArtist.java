package com.example.ayushimathur.tabsandlistview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.LocaleDisplayNames;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.example.ayushimathur.tabsandlistview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SelectArtist extends AppCompatActivity {
    JSONArray m_jArry = OnBoard.artistList;

    TextView artname;
    public static JSONArray lovedArtists1 = new JSONArray();
    public static JSONArray lovedArtists2 = new JSONArray();
    public static JSONArray lovedArtists3 = new JSONArray();
    public static JSONArray rejectedArtists1 = new JSONArray();
    public static JSONArray rejectedArtists2 = new JSONArray();
    public static JSONArray rejectedArtists3 = new JSONArray();
    MediaPlayer player = new MediaPlayer();
    int numTaps = 0;
    int songplaying = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    ImageView btnskipRight, btnheart,
            btnFavList, btnheartAfter,
            btnRejectList, btnCross,
            btnskipLeft, btnCrossAfter,
            btnpause, btnplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_artist);
        artname = (TextView) findViewById(R.id.artistName);

        btnplay = (ImageView) findViewById(R.id.btnplay);
        btnskipRight = (ImageView) findViewById(R.id.btnskipRight);
        btnFavList = (ImageView) findViewById(R.id.favList);
        btnRejectList = (ImageView)findViewById(R.id.rejectList);
        btnskipLeft = (ImageView) findViewById(R.id.btnskipLeft);
        btnpause = (ImageView) findViewById(R.id.btnpause);
        btnpause.setVisibility(View.GONE);
        btnheart = (ImageView) findViewById(R.id.btnheart);
        btnheartAfter = (ImageView) findViewById(R.id.btnheartAfter);
        btnCross = (ImageView) findViewById(R.id.btncross);
        btnCrossAfter = (ImageView) findViewById(R.id.btncrossAfter);
        btnCrossAfter.setVisibility(View.GONE);
        btnheartAfter.setVisibility(View.GONE);

        btnFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AndroidTabAndListView.class);
                player.stop();
                player.release();
                startActivity(intent);
                finish();
            }
        });

        btnRejectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RejectedArtistsLists.class);
                player.stop();
                player.release();
                startActivity(intent);
                finish();
            }
        });

        setUpImageAndSong();


        //   Collection
        btnheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jo_inside;
                String artist;
                String date = "";
                try {
                    jo_inside = m_jArry.getJSONObject(songplaying);
                    date = jo_inside.getString("start_time");
                    artist = jo_inside.getString("artist");
                if(date.contains("2016-08-05")) {
                    if (!userexists(lovedArtists1, artist)) {
                        lovedArtists1.put(jo_inside);
                    }
                    if (userexists(rejectedArtists1, artist)) {
                        rejectedArtists1.remove(songplaying);
                    }
                }

                if(date.contains("2016-08-06")) {
                    if (!userexists(lovedArtists2, artist)) {
                        lovedArtists2.put(jo_inside);
                    }
                    if (userexists(rejectedArtists2, artist)) {
                        rejectedArtists2.remove(songplaying);
                    }
                }

                if(date.contains("2016-08-07")) {
                    if (!userexists(lovedArtists3, artist)) {
                        lovedArtists3.put(jo_inside);
                    }
                    if (userexists(rejectedArtists3, artist)) {
                        rejectedArtists3.remove(songplaying);
                    }
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                btnheartAfter.setVisibility(View.VISIBLE);
                btnheart.setVisibility(View.GONE);

                btnCrossAfter.setVisibility(View.GONE);
                btnCross.setVisibility(View.VISIBLE);
            }
        });

        btnheartAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jo_inside;
                String artist;
                String date = "";
                try {
                    jo_inside = m_jArry.getJSONObject(songplaying);
                    date = jo_inside.getString("start_time");
                    artist = jo_inside.getString("artist");
                if(date.contains("2016-08-07")) {
                    if (userexists(lovedArtists3, artist)) {
                        lovedArtists3.remove(songplaying);
                    }
                }
                if(date.contains("2016-08-06")) {
                    if (userexists(lovedArtists2, artist)) {
                        lovedArtists2.remove(songplaying);
                    }
                }
                if(date.contains("2016-08-05")) {
                    if (userexists(lovedArtists1, artist)) {
                        lovedArtists1.remove(songplaying);
                    }
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                btnheartAfter.setVisibility(View.GONE);
                btnheart.setVisibility(View.VISIBLE);
            }
        });


        btnCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    JSONObject jo_inside;
                    String artist;
                    String date = "";
                    try {
                        jo_inside = m_jArry.getJSONObject(songplaying);
                        date = jo_inside.getString("start_time");
                        artist = jo_inside.getString("artist");
                if(date.contains("2016-08-07")) {
                    if (!userexists(rejectedArtists3, artist)) {
                        rejectedArtists3.put(jo_inside);
                    }
                    if (userexists(lovedArtists3, artist)) {
                        lovedArtists3.remove(songplaying);
                    }
                }

                if(date.contains("2016-08-06")) {
                    if (!userexists(rejectedArtists2, artist)) {
                        rejectedArtists2.put(jo_inside);
                    }
                    if (userexists(lovedArtists2, artist)) {
                        lovedArtists2.remove(songplaying);
                    }
                }

                if(date.contains("2016-08-05")) {
                    if (!userexists(rejectedArtists1, artist)) {
                        rejectedArtists1.put(jo_inside);
                    }
                    if (userexists(lovedArtists1, artist)) {
                        lovedArtists1.remove(songplaying);
                    }
                }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                btnCrossAfter.setVisibility(View.VISIBLE);
                btnCross.setVisibility(View.GONE);

                btnheart.setVisibility(View.VISIBLE);
                btnheartAfter.setVisibility(View.GONE);
            }
        });

        btnCrossAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jo_inside;
                String artist;
                String date = "";
                try {
                    jo_inside = m_jArry.getJSONObject(songplaying);
                    date = jo_inside.getString("start_time");
                    artist = jo_inside.getString("artist");
                if(date.contains("2016-08-07")) {
                    if (userexists(rejectedArtists3, artist)) {
                        rejectedArtists3.remove(songplaying);
                    }
                }
                if(date.contains("2016-08-06")) {
                    if (userexists(rejectedArtists2, artist)) {
                        rejectedArtists2.remove(songplaying);
                    }
                }
                if(date.contains("2016-08-05")) {
                    if (userexists(rejectedArtists1, artist)) {
                        rejectedArtists1.remove(songplaying);
                    }
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                btnCrossAfter.setVisibility(View.GONE);
                btnCross.setVisibility(View.VISIBLE);
            }
        });


        btnskipRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songplaying < m_jArry.length()-2) {
                    songplaying++;
                    setUpImageAndSong();
                }   else {
                    Intent intent = new Intent(view.getContext(), AndroidTabAndListView.class);
                    player.stop();
                    player.release();
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnskipLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songplaying > 1) {
                    songplaying--;
                    setUpImageAndSong();
                    btnheart.setVisibility(View.VISIBLE);
                    btnheartAfter.setVisibility(View.GONE);
                }else {
                    Intent intent = new Intent(view.getContext(), OnBoard.class);
                    player.stop();
                    player.release();
                    startActivity(intent);
                    finish();
                }

            }
        });

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()){
                    player.pause();
                    btnpause.setVisibility(View.GONE);
                    btnplay.setVisibility(View.VISIBLE);
                } else {
                    player.start();
                    btnpause.setVisibility(View.VISIBLE);
                    btnplay.setVisibility(View.GONE);
                }
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.pause();
                    btnpause.setVisibility(View.GONE);
                    btnplay.setVisibility(View.VISIBLE);
                } else {
                    player.start();
                    btnpause.setVisibility(View.VISIBLE);
                    btnplay.setVisibility(View.GONE);
                }
            }
        });

        ImageView img1 = (ImageView) findViewById(R.id.artistphoto);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        Bitmap conv_bm = getRoundedBitmap(bm);
        img1.setImageBitmap(conv_bm);
        artname.setText("");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private boolean userexists(JSONArray jsonArray, String usernameToFind){
        return jsonArray.toString().contains("\"username\":\""+usernameToFind+"\"");
    }

    private void setUpImageAndSong() {
        try {
            JSONObject jo_inside = m_jArry.getJSONObject(songplaying);
            String artistName = jo_inside.getString("artist");
            String imgName = jo_inside.getString("big_image_url");
            String song_url = jo_inside.getString("song_url");

            artname.setText(artistName);

            try {
                player.setDataSource(song_url);
                player.prepare();
            } catch (IOException e) {
                Log.e("", "prepare() failed");
            }

            ImageView imgv = (ImageView) findViewById(R.id.artistphoto);
            imgName = "a" + imgName ;
            int id2 =getResources().getIdentifier(imgName, "drawable", getPackageName());
            imgv.setImageResource(id2);
    }
        catch (JSONException e){

        }
        player.setLooping(true);
        player.start();

        btnpause.setVisibility(View.VISIBLE);
        btnplay.setVisibility(View.GONE);

        btnheart.setVisibility(View.VISIBLE);
        btnheartAfter.setVisibility(View.GONE);

        btnCross.setVisibility(View.VISIBLE);
        btnCrossAfter.setVisibility(View.GONE);
    }

    public static Bitmap getRoundedBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SelectArtist Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ayushimathur.tabsandlistview/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SelectArtist Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ayushimathur.tabsandlistview/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    
}




