package com.ido.record;

import android.media.AudioRecord;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public class SpeechRecord extends AudioRecord {
    public SpeechRecord(int i, int i2) throws IllegalArgumentException {
        this(6, i, 16, 2, i2, false, false, false);
    }

    public SpeechRecord(int i, int i2, boolean z, boolean z2, boolean z3) throws IllegalArgumentException {
        this(6, i, 16, 2, i2, z, z2, z3);
    }

    public SpeechRecord(int i, int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        this(i, i2, i3, i4, i5, false, false, false);
    }

    public SpeechRecord(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3) throws IllegalArgumentException {
        super(i, i2, i3, i4, i5);
    }

    public static boolean isNoiseSuppressorAvailable() {
        if (Build.VERSION.SDK_INT >= 16) {
            return NoiseSuppressor.isAvailable();
        }
        return false;
    }
}