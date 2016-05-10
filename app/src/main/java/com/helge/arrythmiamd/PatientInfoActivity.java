package com.helge.arrythmiamd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PatientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("CPR", "1111001111");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

            }
        });
        String patientName = ArrythmiaMDApplication.gCurrent_patient.get("name").toString();
        String patientCPR = ArrythmiaMDApplication.gCurrent_patient.get("CPR").toString();
        String patientMedicin = ArrythmiaMDApplication.gCurrent_patient.get("medicin").toString();

        // Name of patient
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setText(patientName);

        // cpr-number
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setText(patientCPR);

        // Medicin that patient takes
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setText("ChlorTrimeton");
        //textView8.setText(patientMedicin);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        textView9.setText("(allergy and hay fever)");
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        textView10.setText("Ventolin");
        //textView8.setText(patientMedicin);
        TextView textView11 = (TextView) findViewById(R.id.textView11);
        textView11.setText("(asthma and obstructive pulmonary disease)");
    }
}

