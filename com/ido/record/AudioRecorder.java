package com.ido.record;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioRecorder {
    public static final short CHANNELS = 1;
    public static final int DEFAULT_AUDIO_SOURCE = 6;
    public static final int DEFAULT_SAMPLE_RATE = 16000;
    public static final short RESOLUTION_IN_BYTES = 2;

    public enum State {
        READY,
        RECORDING,
        ERROR,
        STOPPED
    }

    byte[] consumeRecording();

    byte[] consumeRecordingAndTruncate();

    float getRmsdb();

    State getState();

    String getWsArgs();

    boolean isPausing();

    void release();

    void start();
}