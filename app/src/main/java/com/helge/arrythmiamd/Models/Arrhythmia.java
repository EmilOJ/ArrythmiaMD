package com.helge.arrythmiamd.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by emil on 26/04/16.
 */
@ParseClassName("Arrythmia")
public class Arrhythmia extends ParseObject {
    //String id = "objectId"; //maybe it should be a float???
    String sStop = "stop";
    String sStart = "start";
    String sDuration = "duration";
    String sType = "type";

    public Arrhythmia() {
    }

    public Arrhythmia(int start, int stop, String type) {
        super("Arrythmia");
        this.put(sStart, start);
        this.put(sStop, stop);
        this.put(sType, type);
        this.put(sDuration, computeDuration());
    }

    private double computeDuration() {
        double duration;
        duration = this.getDouble(sStop) - this.getDouble(sStart);
        return duration;
    }

    public String getsStop() {
        return getString(sStop);
    }

    public int getStop() {
        return getInt(sStop);
    }

    public void setStop(double stop) {
        put(sStop, stop);
    }

    public int getStart() {
        return getInt(sStart);
    }

    public void setStart(double start) {
        put(sStart, start);
    }

    public int getDuration() {
        return getInt(sDuration);
    }

    public void setDuration(double duration) {
        put(sDuration, duration);
    }

    public String getType() {
        return getString(sType);
    }

    public void setType(String type) {
        put(sType, type);
    }
}
