package ru.startandroid.p1261mediaplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.File;
import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {

    final String LOG_TAG = "myLogs";

    final String DATA_HTTP = "https://noisefx.ru/skachat-zvuk-silnogo-vzryva.html";
    final String DATA_STREAM = "https://europaplus.ru/live";
    final String DATA_SD = Environment.getExternalStorageState(new File(Environment.DIRECTORY_MUSIC)) +
            "/music.mp3";
    final Uri DATA_URI = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 13359);

    MediaPlayer mediaPlayer;
    AudioManager am;
    CheckBox chbLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        chbLoop = (CheckBox) findViewById(R.id.chbLoop);
        chbLoop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mediaPlayer != null)
                    mediaPlayer.setLooping(isChecked);
            }
        });
    }

    public void onClickStart(View view) {
        releaseMP();

        try {
            switch (view.getId()) {
                case R.id.btnStartHttp:
                    Log.d(LOG_TAG, "start HTTP");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_HTTP);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.d(LOG_TAG, "prepareAsync");
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                case R.id.btnStartStream:
                    Log.d(LOG_TAG, "start Stream");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.d(LOG_TAG, "prepareAsync");
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                case R.id.btnStartSD:
                    Log.d(LOG_TAG, "start SD");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_SD);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    break;
                case R.id.btnStartUri:
                    Log.d(LOG_TAG, "start Uri");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(this, DATA_URI);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    break;
                case R.id.btnStartRaw:
                    Log.d(LOG_TAG, "start Raw");
                    mediaPlayer = MediaPlayer.create(this, R.raw.explosion);
                    mediaPlayer.start();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null)
            return;
        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);
    }
    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void onClick (View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.btnPause:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.btnResume:
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                break;
            case R.id.btnBackward:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
                break;
            case R.id.btnForward:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
            case R.id.btnInfo:
                Log.d(LOG_TAG, "Playing " + mediaPlayer.isPlaying());
                Log.d(LOG_TAG, "Time " + mediaPlayer.getCurrentPosition() + " / "
                        + mediaPlayer.getDuration());
                Log.d(LOG_TAG, "Looping " + mediaPlayer.isLooping());
                Log.d(LOG_TAG,
                        "Volume " + am.getStreamVolume(AudioManager.STREAM_MUSIC));
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(LOG_TAG, "onCompletion");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(LOG_TAG, "onPrepared");
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}