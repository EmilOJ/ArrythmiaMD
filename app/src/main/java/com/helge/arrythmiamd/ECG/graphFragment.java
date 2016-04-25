package com.helge.arrythmiamd.ECG;

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


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(mDataPoints);

        series.setThickness(1);
        ECGgraph.addSeries(series);

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
