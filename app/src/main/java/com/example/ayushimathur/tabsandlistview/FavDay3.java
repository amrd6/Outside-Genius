package com.example.ayushimathur.tabsandlistview;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;


public class FavDay3 extends ListActivity {
	private ProgressDialog pDialog;
	public static final String TAG_NAME = "name";
	public static final String TAG_LOCATION = "location";
	public static final String TAG_STIME = "stime";
	public static final String TAG_IMG = "image";
	public static final String TAG_DURATION = "duration";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inbox_list);

		new LoadInbox().execute();
	}

	class LoadInbox extends AsyncTask<String, String, String> {

        Common commonApis = new Common();
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FavDay3.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
            commonApis.AddToMap(FavDay3.this, SelectArtist.lovedArtists3);
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
				public void run() {
					ListAdapter adapter = commonApis.createAdapter(FavDay3.this);
					setListAdapter(adapter);
				}
			});

		}
	}
}