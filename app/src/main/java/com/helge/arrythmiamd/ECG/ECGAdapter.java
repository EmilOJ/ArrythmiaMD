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

public class ECGAdapter extends ParseQueryAdapter {
    /*  Loads ECGRecordings from Parse server and processes them for
        displaying in ECGListActivity.
        Extends the Parse API ParseQueryAdapter which makes getting
        and displaying data from the database very simple.
     */
    public ECGAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                // Query database for all ECG recordings
                ParseQuery query = new ParseQuery("ECGRecording");
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        });
    }
    // Set and inflate each item on the list and set custom text (timestamp)
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
