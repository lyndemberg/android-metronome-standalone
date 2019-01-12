package com.mesh.metronome_standalone.util;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Process;

import com.mesh.metronome_standalone.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metronome {
    private final Logger LOG = Logger.getLogger("Metronome");
    private ScheduledExecutorService executor;
    private Runnable task;
    private int bpm;
    private boolean isPlaying = false;
    private SoundPool sp;

    public Metronome(Context context) {
        sp = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        final int load = sp.load(context, R.raw.click,100);
        task = new Runnable() {
            @Override
            public void run() {
                sp.play(load,1,1,1,0,1);
            }
        };
    }

    public void play(int bpm){
        long timePerClick = 60000 / bpm;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(task,1000, timePerClick, TimeUnit.MILLISECONDS);
        isPlaying = true;
    }

    public void stop(){
        executor.shutdown();
        isPlaying = false;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public int getBpm() {
        return bpm;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
