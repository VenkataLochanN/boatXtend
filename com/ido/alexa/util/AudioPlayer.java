package com.ido.alexa.util;

import android.media.MediaPlayer;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.log.AlexaLogPathImpl;
import com.ido.alexa.log.AlexaLogWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AudioPlayer {
    private MediaPlayer mMediaPlayer;

    public void saveAudio(byte[] bArr) {
        String alexaPCMPath = AlexaLogPathImpl.getInstance().getAlexaPCMPath();
        String str = new SimpleDateFormat(AlexaLogWriter.MP3_NAME_PATTERN, Locale.CHINA).format(new Date()) + AlexaLogWriter.MP3_FILE_PREFIX_NAME;
        File file = new File(alexaPCMPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str2 = alexaPCMPath + str;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            AlexaApi.onMp3FileName(str2);
        } catch (IOException | IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public void play(String str) {
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setAudioStreamType(3);
        }
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.stop();
        }
        this.mMediaPlayer.reset();
        try {
            this.mMediaPlayer.setDataSource(str);
            this.mMediaPlayer.prepare();
            this.mMediaPlayer.start();
        } catch (IOException | IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public void stop() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mMediaPlayer.stop();
        this.mMediaPlayer.release();
    }
}