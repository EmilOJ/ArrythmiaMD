package com.helge.arrythmiamd.ECG;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.Models.ECGRecording;
import com.helge.arrythmiamd.R;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class ECGViewActivity extends AppCompatActivity {
    /*
        Container activity for the ECG-signal graph fragment. Handles incoming startActivity()
        calls such that the graph displays the appropriate data depending on what the user has
        requested (either entire signal or specific arrhythmias).
        Also contains function to load the ECG signal from the database.
     */

    public ECGRecording mEcgRecording;
    public Arrhythmia mArrhythmia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Handles incoming intents and checks for extras. This is used in graphFragment
        // and determines what should be plotted (all data or specific arrhythmia)
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

