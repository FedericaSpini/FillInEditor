package it.uniroma1.fillineditor.util;

import android.os.SystemClock;

public class Chronometer {

    private final long real_start_time;

    public Chronometer() {
        this.real_start_time = SystemClock.elapsedRealtime();
    }


    public long getElapsedTime() {
        return SystemClock.elapsedRealtime() - real_start_time;
    }

}
