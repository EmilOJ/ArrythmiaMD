package com.helge.arrythmiamd.ECG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helge.arrythmiamd.Models.ECGRecording;
import com.helge.arrythmiamd.R;
import com.helge.arrythmiamd.Models.Arrhythmia;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class graphFragment extends Fragment {
    private static DataPoint[] mDataPoints;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_graph_fragment, container, false);

        GraphView ECGgraph = (GraphView) rootView.findViewById(R.id.graph);

        mDataPoints = ((ECGViewActivity) getActivity()).mEcgRecording.asDataPoints();

        LineGraphSeries<DataPoint> AF_series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(11, 2),
                new DataPoint(29, 2)
        });
        ECGgraph.addSeries(AF_series);

        LineGraphSeries<DataPoint> VT_series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(76, 2),
                new DataPoint(95, 2)
        });
        ECGgraph.addSeries(VT_series);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(mDataPoints);

        series.setThickness(2);
        ECGgraph.addSeries(series);

        //AF_series.setColor(Color.argb(0, 0, 0, 0));
        AF_series.setColor(Color.argb(50, 200, 0, 200));
        AF_series.setDrawBackground(true); // activate the background feature
        AF_series.setBackgroundColor(Color.argb(50, 200, 0, 200));

        //VT_series.setColor(Color.argb(0, 0, 0, 0));
        VT_series.setColor(Color.argb(50, 0, 200, 200));
        VT_series.setDrawBackground(true); // activate the background feature
        VT_series.setBackgroundColor(Color.argb(50, 0, 200, 200));

        AF_series.setTitle("AF");
        VT_series.setTitle("VT");
        ECGgraph.getLegendRenderer().setVisible(true);
        ECGgraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        //ECGgraph.getLegendRenderer().setFixedPosition(1300,20);
        ECGgraph.getLegendRenderer().setSpacing(15);
        ECGgraph.getLegendRenderer().setPadding(30);
        ECGgraph.getLegendRenderer().setMargin(15);
        //ECGgraph.getLegendRenderer().setBackgroundColor(fff3f3f3);

        ECGgraph.getViewport().setScrollable(true);
        ECGgraph.getViewport().setScalable(true);

        ECGgraph.getViewport().setXAxisBoundsManual(true);
        ECGgraph.getViewport().setMinX(1);
        ECGgraph.getViewport().setMaxX(140);

        ECGgraph.getViewport().setYAxisBoundsManual(true);
        ECGgraph.getViewport().setMinY(-2);
        ECGgraph.getViewport().setMaxY(2);

        GridLabelRenderer xTitle = ECGgraph.getGridLabelRenderer();
        xTitle.setHorizontalAxisTitle("Time [s]");
        //xTitle.setTextSize(20);

        GridLabelRenderer yTitle = ECGgraph.getGridLabelRenderer();
        yTitle.setVerticalAxisTitle("Amplitude [mV]");




        return rootView;
    }

}
