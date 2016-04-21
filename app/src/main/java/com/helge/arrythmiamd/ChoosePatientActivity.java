package com.helge.arrythmiamd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import com.parse.GetCallback;


import android.content.Intent;

import java.util.List;

public class ChoosePatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_patient);

        EditText current_patient = (EditText) findViewById(R.id.current_patient);

        assert current_patient != null;
        //final double patient = Double.parseDouble(current_patient.getText().toString());



        //double patient = 0;
        //final string patient = Double.parseDouble(current_patient.getText().toString().replace(",", ""));


        Button choose = (Button) findViewById(R.id.choose_done);

        assert choose != null;

        // Cheat button to move to Main Menu
        final Button goButton2 = (Button) findViewById(R.id.goButton2);
        assert goButton2 != null;

        goButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoosePatientActivity.this, MainMenuActivity.class);
                startActivity(i);
            }
        });

        // Should be moved just below onClick, as it is not "filled out" prior to this
        final String patient = current_patient.getText()+"";

        choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // "1111001111" will be replaced with the variable patient´
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Name");
                query.whereEqualTo("cpr-nr", patient);
                query.getFirstInBackground(new GetCallback<ParseObject>() {


                    @Override
                    public void done(ParseObject object, ParseException e) {

                    }

                    public void done(List<ParseUser> objects, ParseException e) {

                    if (e == null) {
                        // The query was successful.
                        Intent i = new Intent(ChoosePatientActivity.this, MainMenuActivity.class);
                        startActivity(i);
                        Toast.makeText(ChoosePatientActivity.this, "Patient found", Toast.LENGTH_SHORT).show();
                    } else {
                        // Something went wrong.
                        Toast.makeText(ChoosePatientActivity.this, "Patient not found", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    });
}
}
