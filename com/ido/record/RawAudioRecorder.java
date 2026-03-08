package com.ido.record;

import com.ido.record.AudioRecorder;

/* JADX INFO: loaded from: classes3.dex */
public class RawAudioRecorder extends AbstractAudioRecorder {
    public RawAudioRecorder(int i, int i2) {
        super(i, i2);
        try {
            int bufferSize = getBufferSize();
            createRecorder(i, i2, bufferSize);
            createBuffer(bufferSize / 4);
            setState(AudioRecorder.State.READY);
        } catch (Exception e2) {
            if (e2.getMessage() == null) {
                handleError("Unknown error occurred while initializing recorder");
            } else {
                handleError(e2.getMessage());
            }
        }
    }

    public RawAudioRecorder(int i) {
        this(6, i);
    }

    public RawAudioRecorder() {
        this(6, AudioRecorder.DEFAULT_SAMPLE_RATE);
    }

    @Override // com.ido.record.AudioRecorder
    public String getWsArgs() {
        return "?content-type=audio/x-raw,+layout=(string)interleaved,+rate=(int)" + getSampleRate() + ",+format=(string)S16LE,+channels=(int)1";
    }
}