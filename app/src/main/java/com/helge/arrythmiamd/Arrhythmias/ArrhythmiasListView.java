package com.helge.arrythmiamd.Arrhythmias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.helge.arrythmiamd.ECG.ECGViewActivity;
import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.R;

import java.util.ArrayList;
import java.util.List;

public class ArrhythmiasListView extends AppCompatActivity {
    ToggleButton mAFButton;
    ToggleButton mVTButton;
    RadioGroup mButtonsGroup;
    ArrhythmiaAdapter listAdapter;
    ExpandableListView expListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrhythmias_list_view);

        mAFButton = (ToggleButton) findViewById(R.id.AFButton);
        mVTButton = (ToggleButton) findViewById(R.id.VTButton);
        mButtonsGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mButtonsGroup.setOnCheckedChangeListener(ToggleListener);

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

    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int i) {
            for (int j = 0; j < group.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) group.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    private void updateList() {
        boolean AFchecked = mAFButton.isChecked();
        boolean VTchecked = mVTButton.isChecked();
        List<String> types = new ArrayList<>();
        if (!AFchecked && !VTchecked) {
            types.add("all");
        } else {
            if (AFchecked) {
                types.add("AF");
            }
            if (VTchecked) {
                types.add("VT");
            }
        }
        listAdapter.queryNewData(types);
    }

    public void onToggle(View view) {
        updateList();
    }
}