package com.helge.arrythmiamd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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
    String patientCPR;
    Button choose;
    EditText current_patientEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_patient);

        current_patientEditText = (EditText) findViewById(R.id.current_patient);
        current_patientEditText.setText("111100-1111");

        choose = (Button) findViewById(R.id.choose_done);




        // limit cpr-number input to 10 characters (11 with "-")
        InputFilter[] fa= new InputFilter[1];
        fa[0] = new InputFilter.LengthFilter(11);
        current_patientEditText.setFilters(fa);

        // place dash in cpr-number before the last 4 digits
        current_patientEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str =  s.toString();
                // 14 in line below should be removed
                if(s.length() == 6 || s.length() == 14){
                    str += "-";
                    current_patientEditText.setText(str);
                    current_patientEditText.setSelection(str.length());
                }
                patientCPR = current_patientEditText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                assert current_patientEditText != null;
                patientCPR = current_patientEditText.getText().toString();

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("CPR", patientCPR);

                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            // The query was successful.
                            if (objects.size() != 0) {
                                ArrythmiaMDApplication.gCurrent_patient = objects.get(0);
                                Intent i = new Intent(ChoosePatientActivity.this, MainMenuActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(ChoosePatientActivity.this, "Patient not found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Something went wrong.
                            Toast.makeText(ChoosePatientActivity.this, "Could not connect to database", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}

