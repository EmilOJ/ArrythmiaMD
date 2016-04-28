package com.helge.arrythmiamd.Models;

import com.jjoe64.graphview.series.DataPoint;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by emil on 26/04/16.
 */

@ParseClassName("ECGRecording")
public class ECGRecording extends ParseObject {
    private List<Double> mData;
    String sData = "data";
    String sFs = "fs";
    String sStart = "start";
    String sStop = "stop";
    String sArrhythmias = "arrhythmias";
    String sDownSamplingRate = "downSamplingRate";

    public int getDownSamplingRate() {
        return getInt(sDownSamplingRate);
    }

    public void setDownSamplingRate(int downSamplingRate) {
        put(sDownSamplingRate, downSamplingRate);
    }

    public ECGRecording() {}

    public Double getFs() {
        return getDouble(sFs);
    }

    public void setFs(int Fs) {
        put(sFs, Fs);
    }

    public Double getStart() {
        return getDouble(sStart);
    }

    public void setStart(Double start) {
        put(sStart, start);
    }

    public Double getStop() {
        return getDouble(sStop);
    }

    public void setsStop(Double stop) {
        put(sStop, stop);
    }

    public List<Arrhythmia> getArrhythmias() {
        return getList(sArrhythmias);
    }

    public void setArrhythmias(List<Arrhythmia> arrhythmias) {
        put(sArrhythmias, arrhythmias);
    }

    public void setData(ParseFile data) {
        put(sData, data);
    }

    private void getAndConvertData() {
        String dataString = null;
        try {
            dataString = new String(this.getParseFile(sData).getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> dataPairs = Arrays.asList(dataString.split("\n"));
        mData = new ArrayList<>();

        for (int i=0; i < dataPairs.size(); i++) {
            try {
                mData.add(Double.parseDouble(dataPairs.get(i).split(",")[1]));
            } catch (Exception e) {

            }
        }
    }

    public List<Double> getData() {
        if (mData == null) {
            getAndConvertData();
        }

        return mData;
    }

    public DataPoint[] asDataPoints() {
        if (mData == null) {
            getAndConvertData();
        }
        DataPoint[] dataPointsArray = new DataPoint[(int) Math.floor(mData.size() / getDownSamplingRate())];
        int counter = 0;
        for (int i=0; i < mData.size(); i = i + getDownSamplingRate()) {
            double dataPoint = mData.get(i);
            try {
                dataPointsArray[counter] = new DataPoint(counter, dataPoint);
            } catch (Exception e) {
                int a = 1;
            }


            counter++;
        }

        return dataPointsArray;
    }
}