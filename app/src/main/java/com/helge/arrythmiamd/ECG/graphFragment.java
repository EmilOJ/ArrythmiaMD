package com.helge.arrythmiamd.ECG;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helge.arrythmiamd.R;
import com.jjoe64.graphview.GraphView;
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

        mDataPoints = ((ECGViewActivity) getActivity()).getmDataPoints();

        LineGraphSeries<DataPoint> AF_series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(11, 2),
                new DataPoint(12, 2),
                new DataPoint(13, 2),
                new DataPoint(14, 2),
                new DataPoint(15, 2),
                new DataPoint(16, 2),
                new DataPoint(17, 2),
                new DataPoint(18, 2),
                new DataPoint(19, 2),
                new DataPoint(20, 2),
                new DataPoint(21, 2),
                new DataPoint(22, 2),
                new DataPoint(23, 2),
                new DataPoint(24, 2),
                new DataPoint(25, 2),
                new DataPoint(26, 2),
                new DataPoint(27, 2),
                new DataPoint(28, 2),
                new DataPoint(29, 2)
        });
        ECGgraph.addSeries(AF_series);

        LineGraphSeries<DataPoint> VT_series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(76, 2),
                new DataPoint(77, 2),
                new DataPoint(78, 2),
                new DataPoint(79, 2),
                new DataPoint(80, 2),
                new DataPoint(81, 2),
                new DataPoint(82, 2),
                new DataPoint(83, 2),
                new DataPoint(84, 2),
                new DataPoint(85, 2),
                new DataPoint(86, 2),
                new DataPoint(87, 2),
                new DataPoint(88, 2),
                new DataPoint(89, 2),
                new DataPoint(90, 2),
                new DataPoint(91, 2),
                new DataPoint(92, 2),
                new DataPoint(93, 2),
                new DataPoint(94, 2),
                new DataPoint(95, 2)
        });
        ECGgraph.addSeries(VT_series);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(mDataPoints);

        series.setThickness(1);
        ECGgraph.addSeries(series);

        AF_series.setDrawBackground(true); // activate the background feature
        AF_series.setBackgroundColor(Color.argb(50, 200, 0, 200));

        VT_series.setDrawBackground(true); // activate the background feature
        VT_series.setBackgroundColor(Color.argb(50, 0, 200, 200));



        ECGgraph.getViewport().setScrollable(true);
        ECGgraph.getViewport().setScalable(true);

        ECGgraph.getViewport().setXAxisBoundsManual(true);
        ECGgraph.getViewport().setMinX(1);
        ECGgraph.getViewport().setMaxX(140);

        ECGgraph.getViewport().setYAxisBoundsManual(true);
        ECGgraph.getViewport().setMinY(-2);
        ECGgraph.getViewport().setMaxY(2);

        return rootView;
    }
}
