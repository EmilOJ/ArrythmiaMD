package com.helge.arrythmiamd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.helge.arrythmiamd.ECG.ECGListActivity;
import com.helge.arrythmiamd.ECG.ECGViewActivity;

public class MainMenuActivity extends AppCompatActivity {

    Button patientinfoButton;
    Button patientnotesButton;
    Button arrhythmiasButton;
    Button statisticsButton;
    Button ECGButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        patientinfoButton = (Button) findViewById(R.id.patientinfo_button);
        patientnotesButton = (Button) findViewById(R.id.patientnotes_button);
        arrhythmiasButton = (Button) findViewById(R.id.arrhythmias_button);
        statisticsButton = (Button) findViewById(R.id.statistics_button);
        ECGButton = (Button) findViewById(R.id.ECGButton);



        patientinfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, PatientInfoActivity.class);
                startActivity(i);
            }
        });

        patientnotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, PatientNotesActivity.class);
                startActivity(i);
            }
        });

        arrhythmiasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ECGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, ECGListActivity.class);
                startActivity(i);
            }
        });




    }
}
