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
    private FrameLayout progressBarHolder;
    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private File mCSVfile;
    private String mDataString;
    private String mECG_ID = "";

    private DataPoint[] mDataPoints;
    InputStream is;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecgview);

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mECG_ID = extras.getString("ECG_ID");
        }

        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);

        loadECGData();
    }

    private void loadECGData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ECG");
        query.fromLocalDatastore();

        try {
            mECGObject = query.get(mECG_ID);
            mECGdata_csv = mECGObject.getParseFile("data");
            mDataString = new String (mECGdata_csv.getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);

        List<String> items = Arrays.asList(mDataString.split("\n"));

        mDataPoints = new DataPoint[items.size()];
        int counter = 0;
        for (String item : items) {
            if (counter > 0) {
                String[] dataPointString = item.split(",");
                mDataPoints[counter] = new DataPoint(counter, Double.parseDouble(dataPointString[1]));
            }
            counter++;
        }
        Toast.makeText(ECGViewActivity.this, "asd", Toast.LENGTH_SHORT).show();
    }

    public DataPoint[] getmDataPoints() {
        return mDataPoints;
    }
}

