package com.helge.arrythmiamd.ECG;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helge.arrythmiamd.Models.Arrhythmia;
import com.helge.arrythmiamd.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {
    /*
        Fragment which plots the ECG-signal using the GraphView library. It contains methods
        for dynamically highlighting arrhythmias and for setting the axis ranges appropriately
        based on the signal. The signal itself is stored in the containing ECGViewActivity
        and is referenced from there.
     */

    private DataPoint[] mDataPoints;
    private GraphView mECGGraph;
    private double mMaxValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_graph_fragment, container, false);
        mECGGraph = (GraphView) rootView.findViewById(R.id.graph);
        mDataPoints = ((ECGViewActivity) getActivity()).mECGRecording.asDataPoints();
        mMaxValue = ((ECGViewActivity) getActivity()).mECGRecording.getMax();

        setLayout();
        drawECG();
        drawArrhythmias();

        return rootView;
    }

    private void drawECG() {
        /*
            Draws ECG signal from the signal.
         */
        LineGraphSeries<DataPoint> ECGseries = new LineGraphSeries<DataPoint>(mDataPoints);
        ECGseries.setColor(Color.argb(255,0,0,0));
        ECGseries.setThickness(2);
        mECGGraph.addSeries(ECGseries);
    }

    private void drawArrhythmias() {
        /*
            Draws all arrhythmias associated with the ECG signal.
         */
        List<Arrhythmia> arrhythmias = ((ECGViewActivity) getActivity()).mECGRecording.getArrhythmias();
        List<LineGraphSeries> arrhythmiasSeries = new ArrayList<>();

        for (int iArrhythmia = 0; iArrhythmia < arrhythmias.size(); iArrhythmia++) {
            Arrhythmia arrhythmia = arrhythmias.get(iArrhythmia);
            try {
                arrhythmia.fetchIfNeeded();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            LineGraphSeries arrhythmiaSeries = new LineGraphSeries(new DataPoint[]{
                    new DataPoint(adjustTime(arrhythmia.getStart()), this.mMaxValue-0.1),
                    new DataPoint(adjustTime(arrhythmia.getStop()), this.mMaxValue-0.1),
            });

            arrhythmiaSeries.setColor(getColor(arrhythmia));
            arrhythmiaSeries.setDrawBackground(true);
            arrhythmiaSeries.setBackgroundColor(getColor(arrhythmia));

            arrhythmiasSeries.add(arrhythmiaSeries);
            mECGGraph.addSeries(arrhythmiasSeries.get(iArrhythmia));
        }
    }

    private int getColor(Arrhythmia arrhythmia) {
        /*
            Sets the highlighting color for the arrhythmia. Can easily be expanded to
            include more arrhythmia types.
         */
        int color;
        if ( arrhythmia.getType().equals("AF")) {
            color = Color.argb(150, 0, 102, 204);
        } else {
            color = Color.argb(0, 0, 0, 200);
        }

        return color;
    }

    private double adjustTime(int original_time) {
        /*
            Convert sample numbers to seconds to match the timeline of the plot.
         */
        double adjusted_time = original_time / ((ECGViewActivity) getActivity()).mECGRecording.getFs();

        return adjusted_time;
    }

    private void setLayout() {
        /*
            Set the layout for the ECG signal. Includes settings for color, thickness
            and axis ranges.
         */
        mECGGraph.getViewport().setScrollable(true);
        mECGGraph.getViewport().setScalable(true);

        mECGGraph.getViewport().setXAxisBoundsManual(true);

        setXLimits();

        mECGGraph.getViewport().setYAxisBoundsManual(true);
        mECGGraph.getViewport().setMinY(-1);
        mECGGraph.getViewport().setMaxY(mMaxValue);


        GridLabelRenderer xTitle = mECGGraph.getGridLabelRenderer();
        xTitle.setHorizontalAxisTitle("Time [s]");

        GridLabelRenderer yTitle = mECGGraph.getGridLabelRenderer();
        yTitle.setVerticalAxisTitle("Amplitude [mV]");

    }

    private void setXLimits() {
        /*
            Computes the X axis ranges for showing the arrhythmia. Includes offset such that
            the onset of the arrhythmia is visible as well as the normal signal preceding it.
         */
        Arrhythmia arrhythmia = null;
        try {
            arrhythmia = ((ECGViewActivity) getActivity()).mArrhythmia;
        } catch (Exception e) {
            e.printStackTrace();
        }

        double offset = adjustTime(360);
        double windows_size = adjustTime(1400);

        if (arrhythmia != null) {
            mECGGraph.getViewport().setMinX(adjustTime(arrhythmia.getStart())- offset);
            mECGGraph.getViewport().setMaxX(adjustTime(arrhythmia.getStart()) - offset + windows_size);
        } else {
            mECGGraph.getViewport().setMinX(0);
            mECGGraph.getViewport().setMaxX(windows_size);

        }

    }
}
