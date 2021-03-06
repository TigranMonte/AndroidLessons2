package ru.startandroid.p1281audiofocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    final static String LOG_TAG = "myLogs";

    AudioManager audioManager;
    AFListener afListenerMusic;
    AFListener afListenerSound;
    MediaPlayer mpMusic;
    MediaPlayer mpSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }
     public void onClickMusic(View view) {
        mpMusic = new MediaPlayer();
        try {
            mpMusic.setDataSource("/mnt/sdcard/Music/System Of A Down — Aerials.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mpMusic.setOnCompletionListener(this);

        afListenerMusic = new AFListener(mpMusic, "Music");
        int requestResult = audioManager.requestAudioFocus(afListenerMusic, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);
        Log.d(LOG_TAG, "Music request focus, result: " + requestResult);

        mpMusic.start();
     }
     public void onClickSound(View view) {
        int durationHint = AudioManager.AUDIOFOCUS_GAIN;
        switch (view.getId()) {
            case R.id.btnPlaySoundG:
                durationHint = AudioManager.AUDIOFOCUS_GAIN;
                break;
            case R.id.btnPlaySoundGT:
                durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
                break;
            case R.id.btnPlaySoundGTD:
                durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
                break;
        }
        mpSound = MediaPlayer.create(this, R.raw.explosion);
        mpSound.setOnCompletionListener(this);

        afListenerMusic = new AFListener(mpSound, "sound");
        int requestResult = audioManager.requestAudioFocus(afListenerSound, AudioManager.STREAM_MUSIC,
                durationHint);
        Log.d(LOG_TAG, "Sound request focus, result: " + requestResult);

        mpSound.start();
     }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mediaPlayer == mpMusic) {
            Log.d(LOG_TAG, "Music: abandon focus");
            audioManager.abandonAudioFocus(afListenerMusic);
        } else if (mediaPlayer == mpSound) {
            Log.d(LOG_TAG, "Sound: abandon focus");
            audioManager.abandonAudioFocus(afListenerSound);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpMusic != null) mpMusic.release();
        if (mpSound != null) mpSound.release();
        if (afListenerMusic != null) audioManager.abandonAudioFocus(afListenerMusic);
        if (afListenerSound != null) audioManager.abandonAudioFocus(afListenerSound);
    }

    class AFListener implements AudioManager.OnAudioFocusChangeListener {
        String label = "";
        MediaPlayer mp;

        public AFListener(MediaPlayer mp, String label) {
            this.label = label;
            this.mp = mp;
        }

        @Override
        public void onAudioFocusChange(int focusChange) {
            String event = "";
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    event = "AUDIOFOCUS_LOSS";
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    event = "AUDIOFOCUS_LOSS_TRANSIENT";
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    event = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    event = "AUDIOFOCUS_GAIN";
                    break;
            }
            Log.d(LOG_TAG, label + " onAudioFocusChange: " + event);
        }
    }
}