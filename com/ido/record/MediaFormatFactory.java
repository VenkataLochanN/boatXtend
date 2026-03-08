package com.ido.record;

import android.media.MediaFormat;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public class MediaFormatFactory {

    public enum Type {
        AAC,
        AMR,
        FLAC
    }

    public static MediaFormat createMediaFormat(Type type, int i) {
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setInteger("sample-rate", i);
        mediaFormat.setInteger("channel-count", 1);
        if (type == Type.AAC) {
            mediaFormat.setString("mime", "audio/mp4a-latm");
            mediaFormat.setInteger("aac-profile", 2);
            mediaFormat.setInteger("bitrate", 64000);
        } else if (type == Type.FLAC) {
            mediaFormat.setString("mime", "audio/flac");
            mediaFormat.setInteger("bitrate", 64000);
        } else {
            mediaFormat.setString("mime", "audio/amr-wb");
            mediaFormat.setInteger("bitrate", 23050);
        }
        return mediaFormat;
    }
}