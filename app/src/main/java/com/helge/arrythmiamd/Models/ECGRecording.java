package com.helge.arrythmiamd.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emil on 26/04/16.
 */

@ParseClassName("ECGRecording")
public class ECGRecording extends ParseObject {
    String sData = "data";
    String sFs = "fs";
    String sStart = "start";
    String sStop = "stop";
    String sArrhythmias = "arrhythmias";

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

    public void setData(List<Double> data) {
        put(sData, data);
    }

    public List<Object> getData() {
        return getList(sData);
    }

    public static void saveECG() {

    }
}