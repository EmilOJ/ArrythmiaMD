package com.helge.arrythmiamd.ECG;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helge.arrythmiamd.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by emil on 25/04/16.
 */
public class ECGAdapter extends ParseQueryAdapter {
    public ECGAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("ECG");

                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.ecgitem, null);
        }
        super.getItemView(object, v, parent);

        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);

        Date timestamp = object.getCreatedAt();
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        timestampView.setText(df.format(timestamp));

        return v;
    }
}
