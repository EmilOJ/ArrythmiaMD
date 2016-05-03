package com.helge.arrythmiamd.Arrhythmias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.helge.arrythmiamd.ECG.ECGViewActivity;
import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.R;

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
                Arrhythmia arrhythmia = listAdapter.getChild(groupPosition, childPosition);

                Intent i = new Intent(ArrhythmiasListView.this, ECGViewActivity.class);
                i.putExtra("status", "arrhythmia");
                i.putExtra("arrhythmiaId", arrhythmia.getObjectId());
                startActivity(i);

                return false;
            }
        });

    }
}