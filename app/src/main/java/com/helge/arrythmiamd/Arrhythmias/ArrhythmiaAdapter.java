package com.helge.arrythmiamd.Arrhythmias;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.Models.ECGRecording;
import com.helge.arrythmiamd.R;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by emil on 29/04/16.
 */
public class ArrhythmiaAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ECGRecording> mRecordings;
    private HashMap<String, List<Arrhythmia>> mListDataChild = new HashMap<>();

    public ArrhythmiaAdapter(Context context) {
        this.mContext = context;

        loadObjects();
    }

    private void loadObjects() {

        ParseQuery<ECGRecording> query = new ParseQuery(ECGRecording.class);
        query.fromLocalDatastore();
        try {
            mRecordings = query.find();
            for (ECGRecording recording : mRecordings) {
                String ecgID = recording.getObjectId();
                ParseQuery<Arrhythmia> aQuery = new ParseQuery(Arrhythmia.class);
                aQuery.fromLocalDatastore();
                aQuery.orderByAscending("start");
                aQuery.whereEqualTo("recordingId", ecgID);
                List<Arrhythmia> arrhythmias = aQuery.find();
                mListDataChild.put(ecgID, arrhythmias);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    };

    @Override
    public Arrhythmia getChild(int groupPosition, int childPosititon) {
        String ecgid = this.mRecordings.get(groupPosition).getObjectId();
        return this.mListDataChild.get(ecgid).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Arrhythmia arrhythmia = getChild(groupPosition, childPosition);
        DecimalFormat df = new DecimalFormat("#.##");

        final String childText = arrhythmia.getType() + " at " + df.format(arrhythmia.getStartTime()) + "s";

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.arrhythmia_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.arrhythmia_item);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListDataChild.get(this.mRecordings.get(groupPosition).getObjectId())
                .size();
    }

    @Override
    public ECGRecording getGroup(int groupPosition) {
        return this.mRecordings.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mRecordings.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Date timestamp = getGroup(groupPosition).getCreatedAt();
        DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");


        String headerTitle = "Recording at " + df.format(timestamp);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.arrhythmia_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
