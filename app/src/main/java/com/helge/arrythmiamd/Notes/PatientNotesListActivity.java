package com.helge.arrythmiamd.Notes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.helge.arrythmiamd.R;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class PatientNotesListActivity extends AppCompatActivity {
    /*
        ListView displaying all Notes associated with the current patient. Uses the
        NotesAdapter for fetching and displaying the notes.
        Includes a BroadcastReceiver for live-updating the list when new notes arrive.
     */

    private ParseQueryAdapter<ParseObject> notesAdapter;
    ListView notesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_notes_list);
        notesListView = (ListView) findViewById(R.id.notesListView);

        //Set the ListAdapter which retrives and processes the data to be displayed
        notesAdapter = new NotesAdapter(this);
        notesListView.setAdapter(notesAdapter);
        notesAdapter.loadObjects();

        // Set BroadcastReceiver for live-updating the list when new notes arrive
        LocalBroadcastManager.getInstance(this).registerReceiver(mFetchtDataReceiver, new IntentFilter("doneFetchingData"));
    }

    // Reloads the entire list when "doneFetchingData" broadcast is received
    private BroadcastReceiver mFetchtDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            notesAdapter.loadObjects();
        }
    };


    // Unregisters the BroadcastReceiver on activity desctruction.
    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mFetchtDataReceiver);
        super.onDestroy();
    }
}
