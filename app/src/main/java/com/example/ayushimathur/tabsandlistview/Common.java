package com.example.ayushimathur.tabsandlistview;

import android.content.Context;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ayushimathur on 8/3/16.
 */
public class Common {

    ArrayList<HashMap<String, Object>> inboxList;
    public static final String TAG_NAME = "name";
    public static final String TAG_LOCATION = "location";
    public static final String TAG_STIME = "stime";
    public static final String TAG_IMG = "image";
    public static final String TAG_DURATION = "duration";

    public void AddToMap(Context context, JSONArray artistList) {
        inboxList = new ArrayList<HashMap<String, Object>>();
        int id2 = 0;
        String from = "", location = "", stime = "", img = "", duration = "", start_time = "";

        for (int i = 0; i < artistList.length(); i++) {
            try {
                JSONObject a = artistList.getJSONObject(i);
                // Storing each json item in variable
                from = a.getString("artist");
                location = a.getString("zone");
                start_time = a.getString("start_time");
                img = a.getString("big_image_url");
                img = "a" + img;
                id2 = context.getResources().getIdentifier(img, "raw", context.getPackageName());
                duration = a.getString("duration_minutes");
                String[] times = start_time.split(" ");
                stime = times[1];
                duration = duration + " mins";
            } catch (JSONException e) {

            }

            // creating new HashMap
            HashMap<String, Object> map = new HashMap<String, Object>();

            // adding each child node to HashMap key => value
            map.put(TAG_NAME, from);
            map.put(TAG_LOCATION, location);
            map.put(TAG_STIME, stime);
            map.put(TAG_IMG, id2);
            map.put(TAG_DURATION, duration);

            // adding HashList to ArrayList
            this.inboxList.add(map);
        }
    }

        public SimpleAdapter createAdapter(Context context){
        return new SimpleAdapter(
                context, this.inboxList,
                R.layout.inbox_list_item, new String[] { TAG_NAME, TAG_LOCATION, TAG_STIME, TAG_IMG, TAG_DURATION},
                new int[] { R.id.from, R.id.location, R.id.stime , R.id.icon, R.id.duration});
    }
}
