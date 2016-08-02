package com.example.ayushimathur.tabsandlistview;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ayushimathur.tabsandlistview.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<HashMap<String, Object>> inboxList;

    public CustomListAdapter(Activity context, ArrayList<HashMap<String, Object>> inboxList) {
        super(context, R.layout.mylist);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.inboxList = inboxList;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.from);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView location = (TextView) rowView.findViewById(R.id.location);
        TextView time = (TextView) rowView.findViewById(R.id.date);

        HashMap<String,Object> info = inboxList.get(position);
        txtTitle.setText(info.get("from").toString());
        imageView.setImageResource(((int) info.get("image")));
        location.setText("Description "+info.get("location").toString());
        time.setText(info.get("date").toString());
        return rowView;
    };
}



