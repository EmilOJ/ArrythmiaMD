package com.helge.arrythmiamd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    /*
        Lets the MD login with username and password using the ParseUser object
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final ProgressBar spinner = (ProgressBar) findViewById(R.id.progressBar);

        assert bLogin != null;
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert etUsername != null;
                assert etPassword != null;

                // Retrieve entered username and password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Showing loading spinner
                assert spinner != null;
                spinner.setVisibility(View.VISIBLE);
                bLogin.setEnabled(false);

                // Login in background and start the ChoosePatientActivity on success.
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Intent i = new Intent(LoginActivity.this, ChoosePatientActivity.class);
                            startActivity(i);
                        } else {
                            // Hide loading spinner if the login was unsuccessful and show a Toast.
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    spinner.setVisibility(View.GONE);
                                    bLogin.setEnabled(true);
                                }
                            });
                            Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
