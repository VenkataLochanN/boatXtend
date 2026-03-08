package com.king.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class BeepManager implements MediaPlayer.OnErrorListener, Closeable {
    private static final float BEEP_VOLUME = 0.1f;
    private static final String TAG = BeepManager.class.getSimpleName();
    private static final long VIBRATE_DURATION = 200;
    private final Activity activity;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean vibrate;

    BeepManager(Activity activity) {
        this.activity = activity;
        updatePrefs();
    }

    public void setVibrate(boolean z) {
        this.vibrate = z;
    }

    public void setPlayBeep(boolean z) {
        this.playBeep = z;
    }

    synchronized void updatePrefs() {
        shouldBeep(PreferenceManager.getDefaultSharedPreferences(this.activity), this.activity);
        if (this.playBeep && this.mediaPlayer == null) {
            this.activity.setVolumeControlStream(3);
            this.mediaPlayer = buildMediaPlayer(this.activity);
        }
    }

    synchronized void playBeepSoundAndVibrate() {
        if (this.playBeep && this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
        if (this.vibrate) {
            ((Vibrator) this.activity.getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    private static boolean shouldBeep(SharedPreferences sharedPreferences, Context context) {
        boolean z = sharedPreferences.getBoolean(Preferences.KEY_PLAY_BEEP, false);
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    private MediaPlayer buildMediaPlayer(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(R.raw.zxl_beep);
            try {
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                mediaPlayer.setOnErrorListener(this);
                mediaPlayer.setAudioStreamType(3);
                mediaPlayer.setLooping(false);
                mediaPlayer.setVolume(0.1f, 0.1f);
                mediaPlayer.prepare();
                if (assetFileDescriptorOpenRawResourceFd != null) {
                    assetFileDescriptorOpenRawResourceFd.close();
                }
                return mediaPlayer;
            } finally {
            }
        } catch (IOException e2) {
            Log.w(TAG, e2);
            mediaPlayer.release();
            return null;
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            this.activity.finish();
        } else {
            close();
            updatePrefs();
        }
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }
}