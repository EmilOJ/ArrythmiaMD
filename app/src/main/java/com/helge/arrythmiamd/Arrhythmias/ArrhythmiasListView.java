package com.helge.arrythmiamd.Arrhythmias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.helge.arrythmiamd.Models.ECGRecording;
import com.helge.arrythmiamd.R;

import java.util.HashMap;
import java.util.List;

public class ArrhythmiasListView extends AppCompatActivity {

    ArrhythmiaAdapter listAdapter;
    ExpandableListView expListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrhythmias_list_view);

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        listAdapter = new ArrhythmiaAdapter(this);
        expListView.setAdapter(listAdapter);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

    }





}
