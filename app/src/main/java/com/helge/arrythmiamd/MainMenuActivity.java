package com.helge.arrythmiamd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button patientinfoButton = (Button) findViewById(R.id.patientinfo_button);
        Button patientnotesButton = (Button) findViewById(R.id.patientnotes_button);
        Button arrhythmiasButton = (Button) findViewById(R.id.arrhythmias_button);
        Button statisticsButton = (Button) findViewById(R.id.statistics_button);

        assert patientinfoButton != null;
        assert patientnotesButton != null;
        assert arrhythmiasButton != null;
        assert statisticsButton != null;

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




    }
}
