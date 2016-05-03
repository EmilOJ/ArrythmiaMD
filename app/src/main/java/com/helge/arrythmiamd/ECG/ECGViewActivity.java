package com.helge.arrythmiamd.ECG;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.Models.ECGRecording;
import com.helge.arrythmiamd.R;
import com.jjoe64.graphview.series.DataPoint;
import com.parse.Parse;
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
    private List<Double> mECGdata = new ArrayList<Double>();
    private String mDataString;
    public ECGRecording mEcgRecording;
    public Arrhythmia mArrhythmia;
    private DataPoint[] mDataPoints;
    InputStream is;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String ECG_ID = "";
            String status = extras.getString("status");
            switch (status) {
                case "all":
                    ECG_ID = extras.getString("ECG_ID");
                    break;
                case "arrhythmia":
                    ParseQuery<Arrhythmia> query = new ParseQuery<Arrhythmia>(Arrhythmia.class);
                    query.fromLocalDatastore();
                    try {
                        mArrhythmia = query.get(extras.getString("arrhythmiaId"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ECG_ID = mArrhythmia.getRecordingID();
                    break;
            }

            loadECGData(ECG_ID);
        }

        setContentView(R.layout.activity_ecgview);
    }

    public void loadECGData(String id) {
        ParseQuery<ECGRecording> query = new ParseQuery<ECGRecording>(ECGRecording.class);
        query.fromLocalDatastore();
        try {
            mEcgRecording = query.get(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

