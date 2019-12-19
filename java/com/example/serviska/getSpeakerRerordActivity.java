package com.example.serviska;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.serviska.engine.BitMapHolder;

import java.io.IOException;

public class getSpeakerRerordActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";

    private ImageButton btnOK;
    private ImageButton btnClose;
    private ImageButton btnPlay;
    private ImageButton btnRetry;
    private ImageButton btnSpeaker;
    private ProgressBar progressBar;

    private boolean hasPermision=false;

    private MediaRecorder recorder = null;
    private MediaPlayer player = null;

    private boolean startRecord=true;
    private boolean startPlay=true;
    private String fileName;
    private static final int MY_SPEAKER_PERMISSION_CODE = 911;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get_speaker_rerord);

        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";

        getPermision();
        createContent();
    }

    private void createContent(){
        btnOK=this.findViewById(R.id.btnOKSpeaker);
        btnClose=this.findViewById(R.id.btnCloseSpeaker);
        btnPlay=this.findViewById(R.id.btnPlay);
        btnRetry=this.findViewById(R.id.btnRetry);
        btnSpeaker=this.findViewById(R.id.btnSpeaker);

        progressBar=this.findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        btnSpeaker.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    if(!hasPermision)return false;
                    if(!startPlay){
                        Toast.makeText(getApplicationContext(), "Cant record while playing!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if(startRecord){
                        btnSpeaker.setColorFilter(Color.GREEN);
                    }else{
                        btnSpeaker.setColorFilter(Color.RED);
                    }
                    onRecord(startRecord);
                    startRecord=!startRecord;
                }
                return true;
            }
        });

        btnPlay.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    if(!hasPermision)return false;
                    if(!startRecord){
                        Toast.makeText(getApplicationContext(), "Cant record while record!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if(startPlay){
                        btnPlay.setColorFilter(Color.GREEN);
                    }else{
                        btnPlay.setColorFilter(Color.RED);
                    }
                    onPlay(startPlay);
                    startPlay=!startPlay;
                }
                return true;
            }
        });

        btnClose.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    onStop();
                    finish();
                }
                return true;
            }
        });

        btnRetry.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    onStop();
                    Toast.makeText(getApplicationContext(), "Clearing...", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        btnOK.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    onStop();
                   finish();
                }
                return true;
            }
        });
    }

    private void getPermision(){
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, MY_SPEAKER_PERMISSION_CODE);
        }
        else hasPermision=true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_SPEAKER_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Speaker permission granted", Toast.LENGTH_LONG).show();
                hasPermision=true;
            }
            else
            {
                Toast.makeText(this, "Speaker permission denied", Toast.LENGTH_LONG).show();
                hasPermision=false;
            }
        }
    }

    private void playBeep(boolean mode){
        final MediaPlayer mp;

        if(mode) mp=MediaPlayer.create(getApplicationContext(), R.raw.beep);
        else mp=MediaPlayer.create(getApplicationContext(),R.raw.beepok);
        mp.start();
        /*try {
            mp.prepare();
            mp.start();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }


    private void onRecord(boolean start) {
        if (start) {
            stopPlaying();
            playBeep(true);
            startRecording();
        } else {
            stopRecording();
            playBeep(false);
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            stopRecording();
            playBeep(true);
            startPlaying();
        } else {
            stopPlaying();
            playBeep(false);
        }
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        if(player==null)return;
        player.release();
        player = null;
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {
        if(recorder==null)return;
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
    }
}
