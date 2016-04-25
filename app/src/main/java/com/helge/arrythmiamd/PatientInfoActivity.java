package com.helge.arrythmiamd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class PatientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Name");
        query.whereEqualTo("CPR", "1111001111");
        query.getFirstInBackground(new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject object, ParseException e) {

            }
        });

        // Name of patient
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setText("Lone");

        // cpr-number
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setText("1111001111");

        // Medicin that patient takes
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setText("Panodiler");
    }
}

