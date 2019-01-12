package com.mesh.metronome_standalone;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mesh.metronome_standalone.util.Metronome;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    Metronome metronome;
    Button buttonPlay;
    EditText bpmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPlay = findViewById(R.id.buttonPlay);
        bpmText = findViewById(R.id.bpmText);
        metronome = new Metronome(this);
    }

    public void click(View v){
        if(metronome.isPlaying()){
            metronome.stop();
            buttonPlay.setText("TOCAR");
        }else {
            metronome.play(Integer.valueOf(bpmText.getText().toString()));
            buttonPlay.setText("PARAR");
        }
    }


}
