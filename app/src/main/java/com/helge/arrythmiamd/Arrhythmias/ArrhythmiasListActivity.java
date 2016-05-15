package com.helge.arrythmiamd.Arrhythmias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.helge.arrythmiamd.ECG.ECGViewActivity;
import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.R;

public class ArrhythmiasListActivity extends AppCompatActivity {
    /*
        ListView which display all detected arrhythmias grouped by ECG recordings.
        Uses the ArrhythmiaAdapter for loading and displaying the objects.
        Redirects to ECGViewActivity (signal diplay) when pressing individual arrhytmias.
     */

    RadioGroup mButtonsGroup;
    ArrhythmiaAdapter listAdapter;
    ExpandableListView expListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrhythmias_list_view);

        mButtonsGroup = (RadioGroup) findViewById(R.id.radioGroup);
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);


        //Set the ListAdapter which retrives and processes the data to be displayed
        listAdapter = new ArrhythmiaAdapter(this);
        expListView.setAdapter(listAdapter);


        // Event handler for showing ECG signal when tapping a specific arrhythmia
        // on the list
        mButtonsGroup.setOnCheckedChangeListener(ToggleListener);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Arrhythmia arrhythmia = listAdapter.getChild(groupPosition, childPosition);

                Intent i = new Intent(ArrhythmiasListActivity.this, ECGViewActivity.class);
                i.putExtra("status", "arrhythmia");
                // Include the ID of the arrhythmia in the intent such that the graph
                // can display the signal around this particular arrhythmia
                i.putExtra("arrhythmiaId", arrhythmia.getObjectId());
                startActivity(i);

                return false;
            }
        });

    }

    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int i) {
            for (int j = 0; j < group.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) group.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };
}