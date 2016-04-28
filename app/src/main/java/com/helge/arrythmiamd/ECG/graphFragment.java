package com.helge.arrythmiamd.ECG;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helge.arrythmiamd.R;
import com.helge.arrythmiamd.Models.Arrhythmia;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class graphFragment extends Fragment {
    private static DataPoint[] mDataPoints;
    private static GraphView mECGgraph;
//    TODO: Compute ymax from ECG signal
    private static int ymax = 2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_graph_fragment, container, false);

        mECGgraph = (GraphView) rootView.findViewById(R.id.graph);

        mDataPoints = ((ECGViewActivity) getActivity()).mEcgRecording.asDataPoints();

        setLayout();
        drawECG();
        drawArrhythmias();


        return rootView;
    }

    private void drawECG() {
        LineGraphSeries<DataPoint> ECGseries = new LineGraphSeries<DataPoint>(mDataPoints);
        ECGseries.setThickness(2);
        mECGgraph.addSeries(ECGseries);
    }

    private void drawArrhythmias() {
        List<Arrhythmia> arrhythmias = ((ECGViewActivity) getActivity()).mEcgRecording.getArrhythmias();
        List<LineGraphSeries> arrhythmiasSeries = new ArrayList<>();

        for (int iArrhythmia = 0; iArrhythmia < arrhythmias.size(); iArrhythmia++) {
            Arrhythmia arrhythmia = arrhythmias.get(iArrhythmia);
            try {
                arrhythmia.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            LineGraphSeries arrhythmiaSeries = new LineGraphSeries(new DataPoint[]{
                    new DataPoint(adjustTime(arrhythmia.getStart()), this.ymax),
                    new DataPoint(adjustTime(arrhythmia.getStop()), this.ymax),
            });

            arrhythmiaSeries.setColor(getColor(arrhythmia));
            arrhythmiaSeries.setDrawBackground(true);
            arrhythmiaSeries.setBackgroundColor(getColor(arrhythmia));

            arrhythmiasSeries.add(arrhythmiaSeries);
            mECGgraph.addSeries(arrhythmiasSeries.get(iArrhythmia));
        }
    }

    private int getColor(Arrhythmia arrhythmia) {
        int color;
        if ( arrhythmia.getType().equals("AF")) {
            color = Color.argb(50, 200, 0, 200);
        } else if (arrhythmia.getType().equals("VF")) {
            color = Color.argb(50, 0, 200, 200);
        } else {
            color = Color.argb(0, 0, 0, 200);
        }

        return color;
    }

    private int adjustTime(int original_time) {
        int downSamplingRate = ((ECGViewActivity) getActivity()).mEcgRecording.getDownSamplingRate();
        int adjusted_time = (int) Math.floor(original_time / downSamplingRate);

        return adjusted_time;

    }

    private void setLayout() {
        mECGgraph.getLegendRenderer().setVisible(true);
        mECGgraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        //ECGgraph.getLegendRenderer().setFixedPosition(1300,20);
        mECGgraph.getLegendRenderer().setSpacing(15);
        mECGgraph.getLegendRenderer().setPadding(30);
        mECGgraph.getLegendRenderer().setMargin(15);
        //ECGgraph.getLegendRenderer().setBackgroundColor(fff3f3f3);

        mECGgraph.getViewport().setScrollable(true);
        mECGgraph.getViewport().setScalable(true);

        mECGgraph.getViewport().setXAxisBoundsManual(true);
        mECGgraph.getViewport().setMinX(1);
        mECGgraph.getViewport().setMaxX(140);

        mECGgraph.getViewport().setYAxisBoundsManual(true);
        mECGgraph.getViewport().setMinY(-2);
        mECGgraph.getViewport().setMaxY(2);

        GridLabelRenderer xTitle = mECGgraph.getGridLabelRenderer();
        xTitle.setHorizontalAxisTitle("Time [s]");
        //xTitle.setTextSize(20);

        GridLabelRenderer yTitle = mECGgraph.getGridLabelRenderer();
        yTitle.setVerticalAxisTitle("Amplitude [mV]");

    }

    private void testArrhythmias() {
        LineGraphSeries<DataPoint> AF_series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(11, 2),
                new DataPoint(29, 2)
        });
        mECGgraph.addSeries(AF_series);

        LineGraphSeries<DataPoint> VT_series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(76, 2),
                new DataPoint(95, 2)
        });
        mECGgraph.addSeries(VT_series);


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
    }

}
