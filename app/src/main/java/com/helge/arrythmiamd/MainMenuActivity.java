package com.helge.arrythmiamd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.helge.arrythmiamd.Arrhythmias.ArrhythmiasListView;
import com.helge.arrythmiamd.ECG.ECGListActivity;
import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.Notes.PatientNotesListActivity;

public class MainMenuActivity extends AppCompatActivity {
    /*
        Main menu consisting of 4 buttons. Tapping them simply starts the corresponding
        activity.
     */

    Button patientinfoButton;
    Button patientnotesButton;
    Button arrhythmiasButton;
    Button ECGButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        patientinfoButton = (Button) findViewById(R.id.patientinfo_button);
        patientnotesButton = (Button) findViewById(R.id.patientnotes_button);
        arrhythmiasButton = (Button) findViewById(R.id.arrhythmias_button);
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
                Intent i = new Intent(MainMenuActivity.this, PatientNotesListActivity.class);
                startActivity(i);
            }
        });

        arrhythmiasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, ArrhythmiasListView.class);
                startActivity(i);
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
