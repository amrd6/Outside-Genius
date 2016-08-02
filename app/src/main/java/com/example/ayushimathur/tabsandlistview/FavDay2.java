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


public class FavDay2 extends ListActivity {
	// Progress Dialog
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, Object>> inboxList;
	public static final String TAG_NAME = "name";
	public static final String TAG_LOCATION = "location";
	public static final String TAG_STIME = "stime";
	public static final String TAG_IMG = "image";
	public static final String TAG_DURATION = "duration";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outbox_list);

		// Hashmap for ListView
		inboxList = new ArrayList<HashMap<String, Object>>();

		// Loading INBOX in Background Thread
		new LoadInbox().execute();
	}

	/**
	 * Background Async Task to Load all INBOX messages by making HTTP Request
	 */
	class LoadInbox extends AsyncTask<String, String, String> {


		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(FavDay2.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Inbox JSON
		 */
		protected String doInBackground(String... args) {

			String from ="", location="", stime="", img="", duration="", zone_color="";

			for (int i = 0; i < SelectArtist.lovedArtists2.length()-1 ; i++) {

				try {
					JSONObject a = SelectArtist.lovedArtists2.getJSONObject(i);
					// Storing each json item in variable
					from = a.getString("artist");
					location = a.getString("zone");
					stime = a.getString("start_time");
					img = a.getString("big_image_url");
					img = "a" + img;
					duration = a.getString("duration_minutes");
					zone_color = a.getString("zone_color");
				} catch (JSONException e){

				}
				// creating new HashMap
				HashMap<String, Object> map = new HashMap<String, Object>();

				// adding each child node to HashMap key => value
				map.put(TAG_NAME, from);
				map.put(TAG_LOCATION, location);
				map.put(TAG_STIME, stime);
				map.put(TAG_IMG, img);
				map.put(TAG_DURATION, duration);

				// adding HashList to ArrayList
				inboxList.add(map);
			}


			return null;
		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					ListAdapter adapter = new SimpleAdapter(
							FavDay2.this, inboxList,
							R.layout.inbox_list_item, new String[] { TAG_NAME, TAG_LOCATION, TAG_STIME, TAG_DURATION, TAG_IMG},
							new int[] { R.id.from, R.id.location, R.id.stime , R.id.etime, R.id.icon});
					// updating listview
					setListAdapter(adapter);
				}
			});

		}
	}
}