package com.helge.arrythmiamd.ECG;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helge.arrythmiamd.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class graphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_graph_fragment, container, false);

        GraphView ECGgraph = (GraphView) rootView.findViewById(R.id.graph);


        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 3),
                new DataPoint(1, 3),
                new DataPoint(2, 6),
                new DataPoint(3, 2),
                new DataPoint(4, 5),
                new DataPoint(5, 3),
                new DataPoint(6, 3),
                new DataPoint(7, 6),
                new DataPoint(8, 2),
                new DataPoint(9, 5),
                new DataPoint(10, 3),
                new DataPoint(11, 3),
                new DataPoint(12, 6),
                new DataPoint(13, 2),
                new DataPoint(14, 5),
                new DataPoint(15, 3),
                new DataPoint(16, 3),
                new DataPoint(17, 6),
                new DataPoint(18, 2),
                new DataPoint(19, 5),
                new DataPoint(20, 3),
                new DataPoint(21, 3),
                new DataPoint(22, 6),
                new DataPoint(23, 2),
                new DataPoint(24, 5),
                new DataPoint(25, 3),
                new DataPoint(26, 3),
                new DataPoint(27, 6),
                new DataPoint(28, 2),
                new DataPoint(29, 5),
                new DataPoint(30, 3),
                new DataPoint(31, 3),
                new DataPoint(32, 6),
                new DataPoint(33, 2),
                new DataPoint(34, 5),
                new DataPoint(35, 3),
                new DataPoint(36, 3),
                new DataPoint(37, 6),
                new DataPoint(38, 2),
                new DataPoint(39, 5)
        });


        ECGgraph.addSeries(series2);

        ECGgraph.getViewport().setScrollable(true);
        ECGgraph.getViewport().setScalable(true);

        ECGgraph.getViewport().setXAxisBoundsManual(true);
        ECGgraph.getViewport().setMinX(10);
        ECGgraph.getViewport().setMaxX(30);

        return rootView;
    }
}
