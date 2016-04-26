package com.helge.arrythmiamd.ECG;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.helge.arrythmiamd.R;
import com.jjoe64.graphview.series.DataPoint;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ECGViewActivity extends AppCompatActivity {
    private ParseObject mECGObject;
    private ParseFile mECGdata_csv;
    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private File mCSVfile;
    private String mDataString;
    private String mECG_ID = "";

    private DataPoint[] mDataPoints;
    InputStream is;


    public DataPoint[] getmDataPoints() {
        return mDataPoints;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mECG_ID = extras.getString("ECG_ID");
        }

        loadECGData();
        setContentView(R.layout.activity_ecgview);


    }

    public DataPoint[] loadECGData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ECG");
        query.fromLocalDatastore();

        try {
            mECGObject = query.get(mECG_ID);
            mECGdata_csv = mECGObject.getParseFile("data");
            mDataString = new String (mECGdata_csv.getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<String> items = Arrays.asList(mDataString.split("\n"));
        List<DataPoint> mDataPointsList = new ArrayList<>();
        int counter = 0;

        for (int i=1; i < items.size(); i = i + 5) {
            String[] dataPointString = items.get(i).split(",");
            mDataPointsList.add(new DataPoint(counter, Double.parseDouble(dataPointString[1])));
            counter++;
        }

        mDataPoints = mDataPointsList.toArray(new DataPoint[mDataPointsList.size()]);
        return mDataPoints;
    }
}

