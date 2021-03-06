package com.helge.arrythmiamd.ECG;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.helge.arrythmiamd.R;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class ECGListActivity extends AppCompatActivity {
    /*
        ListView displaying all ECG recordings in the database. Uses the ECGAdapter
        to load and display the data. Also starts the ECGViewActivity (ECG signal) when
        tapping either of the recordings on the list.
     */
    private ParseQueryAdapter<ParseObject> mECGAdapter;
    private ListView mECGListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecglist);

        mECGListView = (ListView) findViewById(R.id.ECGListView);


        //Set the ListAdapter which retrives and processes the data to be displayed
        mECGAdapter = new ECGAdapter(this);
        mECGListView.setAdapter(mECGAdapter);
        mECGAdapter.loadObjects();


        mECGListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ECGListActivity.this, ECGViewActivity.class);
                ParseObject ECGObject = mECGAdapter.getItem(position);
                ECGObject.pinInBackground();

                String ECG_ID = ECGObject.getObjectId();
                i.putExtra("ECG_ID", ECG_ID);
                i.putExtra("status", "all");

                startActivity(i);
            }
        });
    }
}
