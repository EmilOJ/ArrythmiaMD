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
    /*
        Shows info about the current patient. Uses the global gCurrent_patient variable
        which was set in the ChoosePatientActivity when entering the CPR number.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        // Get patient name and CPR and set it in the appropriate TextViews
        // Name of patient
        String patientName = ArrythmiaMDApplication.gCurrent_patient.get("name").toString();
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setText(patientName);
        // CPR-number
        String patientCPR = ArrythmiaMDApplication.gCurrent_patient.get("CPR").toString();
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setText(patientCPR);


        // Medicine that patient takes
        // Currently hardcoded. It should be implemented as a database object.
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

