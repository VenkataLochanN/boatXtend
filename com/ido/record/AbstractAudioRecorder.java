package com.ido.record;

import com.ido.alexa.util.AudioUtils;
import com.ido.record.AudioRecorder;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractAudioRecorder implements AudioRecorder {
    private static final int BUFFER_SIZE_MULTIPLIER = 4;
    private static final int DEFAULT_BUFFER_LENGTH_IN_MILLIS = 35000;
    private static final int RESOLUTION = 2;
    private final boolean mAlwaysListen;
    private double mAvgEnergy;
    private byte[] mBuffer;
    private int mConsumedLength;
    private AtomicLong mConsumedSessionId;
    private int mRecordedLength;
    private AtomicLong mRecordedSessionId;
    private SpeechRecord mRecorder;
    final byte[] mRecording;
    private boolean mRecordingBufferIsFullWithData;
    private final int mRecordingBufferLengthMillis;
    private final int mSampleRate;
    private final int mSamplesInOneMilliSec;
    private final int mSamplesInOneSec;
    private AudioRecorder.State mState;

    private static short getShort(byte b2, byte b3) {
        return (short) (b2 | (b3 << 8));
    }

    protected AbstractAudioRecorder(int i, int i2, int i3, boolean z) {
        this.mRecorder = null;
        this.mAvgEnergy = 0.0d;
        this.mRecordedLength = 0;
        this.mRecordedSessionId = new AtomicLong(0L);
        this.mRecordingBufferIsFullWithData = false;
        this.mConsumedLength = 0;
        this.mConsumedSessionId = new AtomicLong(0L);
        this.mSampleRate = i2;
        this.mSamplesInOneSec = this.mSampleRate * 2;
        this.mSamplesInOneMilliSec = (int) (((double) this.mSamplesInOneSec) / 1000.0d);
        this.mRecordingBufferLengthMillis = i3;
        this.mRecording = new byte[this.mSamplesInOneMilliSec * this.mRecordingBufferLengthMillis];
        this.mAlwaysListen = z;
    }

    protected AbstractAudioRecorder(int i, int i2) {
        this(i, i2, DEFAULT_BUFFER_LENGTH_IN_MILLIS, false);
    }

    protected SpeechRecord createRecorder(int i, int i2, int i3) {
        if (this.mRecorder != null) {
            release();
        }
        this.mRecorder = new SpeechRecord(i, i2, 16, 2, i3, false, false, false);
        if (getSpeechRecordState() != 1) {
            throw new IllegalStateException("SpeechRecord initialization failed");
        }
        return this.mRecorder;
    }

    protected void createBuffer(int i) {
        this.mBuffer = new byte[i * 2 * 1];
    }

    protected int getBufferSize() {
        int minBufferSize = SpeechRecord.getMinBufferSize(this.mSampleRate, 16, 2);
        if (minBufferSize == -2) {
            throw new IllegalArgumentException("SpeechRecord.getMinBufferSize: parameters not supported by hardware");
        }
        if (minBufferSize == -1) {
            minBufferSize = this.mSampleRate * 0 * 2 * 1;
        }
        return minBufferSize * 4;
    }

    @Override // com.ido.record.AudioRecorder
    public synchronized byte[] consumeRecordingAndTruncate() {
        byte[] currentRecording;
        currentRecording = getCurrentRecording(getConsumedLength());
        setRecordedLength(0);
        setConsumedLength(0);
        return currentRecording;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    protected int getNumOfSamplesIn(int i) {
        return Math.abs(i) * this.mSamplesInOneMilliSec;
    }

    protected boolean isRecordedSessionSameAsConsumedSession() {
        return this.mRecordedSessionId.get() == this.mConsumedSessionId.get();
    }

    protected int getStatus(int i, int i2) {
        if (i < 0) {
            return i;
        }
        if (i > i2) {
            return -100;
        }
        if (i == 0) {
            return -200;
        }
        return this.mRecording.length < this.mRecordedLength + i ? -300 : 0;
    }

    private boolean isConsumePointerCrossed(boolean z, int i) {
        if (i > 0 && this.mRecordingBufferIsFullWithData && isRecordedSessionSameAsConsumedSession()) {
            int i2 = this.mRecordedLength;
            int i3 = i2 - i;
            int i4 = this.mConsumedLength;
            if ((i3 < i4 && i2 >= i4) || (z && this.mConsumedLength < this.mRecordedLength)) {
                return true;
            }
        }
        return false;
    }

    public long markNewRecordingSession() {
        return this.mRecordedSessionId.incrementAndGet();
    }

    protected int read(SpeechRecord speechRecord, byte[] bArr) {
        int length = bArr.length;
        int i = speechRecord.read(bArr, 0, length);
        if (i == 0 && this.mAlwaysListen) {
            consumeRecordingAndTruncate();
            this.mBuffer = new byte[this.mBuffer.length];
            createRecorder(speechRecord.getAudioSource(), speechRecord.getSampleRate(), getBufferSize());
            start();
        }
        int status = getStatus(i, length);
        boolean z = true;
        if (this.mAlwaysListen && status == -300) {
            if (!this.mRecordingBufferIsFullWithData) {
                this.mRecordingBufferIsFullWithData = true;
            }
            status = 0;
        } else {
            z = false;
        }
        if (status == 0 && i >= 0) {
            if (!z) {
                System.arraycopy(bArr, 0, this.mRecording, this.mRecordedLength, i);
                this.mRecordedLength += i;
            } else {
                byte[] bArr2 = this.mRecording;
                int length2 = bArr2.length;
                int i2 = this.mRecordedLength;
                int i3 = length2 - i2;
                System.arraycopy(bArr, 0, bArr2, i2, i3);
                int i4 = i - i3;
                System.arraycopy(bArr, i3, this.mRecording, 0, i4);
                this.mRecordedLength = i4;
            }
            if (isConsumePointerCrossed(z, i)) {
                markNewRecordingSession();
            }
        }
        if (this.mAlwaysListen) {
            return 0;
        }
        return status;
    }

    @Override // com.ido.record.AudioRecorder
    public AudioRecorder.State getState() {
        return this.mState;
    }

    protected void setState(AudioRecorder.State state) {
        this.mState = state;
    }

    public byte[] getCompleteRecording() {
        return getCurrentRecording(0);
    }

    public byte[] getCompleteRecordingAsWav() {
        return getRecordingAsWav(getCompleteRecording(), this.mSampleRate);
    }

    public static byte[] getRecordingAsWav(byte[] bArr, int i) {
        return AudioUtils.getRecordingAsWav(bArr, i, (short) 2, (short) 1);
    }

    @Override // com.ido.record.AudioRecorder
    public synchronized byte[] consumeRecording() {
        byte[] currentRecording = getCurrentRecording(this.mConsumedLength);
        if (currentRecording == null) {
            return null;
        }
        this.mConsumedLength = this.mRecordedLength;
        this.mConsumedSessionId.set(this.mRecordedSessionId.get());
        return currentRecording;
    }

    protected byte[] getCurrentRecording(int i) {
        int length = getLength() - i;
        byte[] bArr = new byte[length];
        System.arraycopy(this.mRecording, i, bArr, 0, length);
        return bArr;
    }

    protected int getConsumedLength() {
        return this.mConsumedLength;
    }

    protected void setConsumedLength(int i) {
        this.mConsumedLength = i;
    }

    protected void setRecordedLength(int i) {
        this.mRecordedLength = i;
    }

    public int getLength() {
        return this.mRecordedLength;
    }

    @Override // com.ido.record.AudioRecorder
    public boolean isPausing() {
        return getPauseScore() > 7.0d;
    }

    @Override // com.ido.record.AudioRecorder
    public float getRmsdb() {
        double dSqrt = Math.sqrt(getRms(this.mRecordedLength, this.mBuffer.length) / ((long) (this.mBuffer.length / 2)));
        if (dSqrt > 1.0d) {
            return (float) (Math.log10(dSqrt) * 10.0d);
        }
        return 0.0f;
    }

    private double getPauseScore() {
        long rms = getRms(this.mRecordedLength, this.mSamplesInOneSec);
        if (rms == 0) {
            return 0.0d;
        }
        double d2 = this.mAvgEnergy;
        double d3 = rms;
        double d4 = d2 / d3;
        this.mAvgEnergy = ((d2 * 2.0d) + d3) / 3.0d;
        return d4;
    }

    @Override // com.ido.record.AudioRecorder
    public synchronized void release() {
        if (this.mRecorder != null) {
            if (this.mRecorder.getRecordingState() == 3) {
                stop();
            }
            this.mRecorder.release();
            this.mRecorder = null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.ido.record.AbstractAudioRecorder$1] */
    @Override // com.ido.record.AudioRecorder
    public void start() {
        if (getSpeechRecordState() == 1) {
            this.mRecorder.startRecording();
            if (this.mRecorder.getRecordingState() == 3) {
                setState(AudioRecorder.State.RECORDING);
                new Thread() { // from class: com.ido.record.AbstractAudioRecorder.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        AbstractAudioRecorder abstractAudioRecorder = AbstractAudioRecorder.this;
                        abstractAudioRecorder.recorderLoop(abstractAudioRecorder.mRecorder);
                    }
                }.start();
                return;
            } else {
                handleError("startRecording() failed");
                return;
            }
        }
        handleError("start() called on illegal state");
    }

    public void stop() {
        if (getSpeechRecordState() == 1 && this.mRecorder.getRecordingState() == 3) {
            try {
                this.mRecorder.stop();
                setState(AudioRecorder.State.STOPPED);
                return;
            } catch (IllegalStateException e2) {
                handleError("native stop() called in illegal state: " + e2.getMessage());
                return;
            }
        }
        handleError("stop() called in illegal state");
    }

    protected void recorderLoop(SpeechRecord speechRecord) {
        while (speechRecord.getRecordingState() == 3) {
            int i = read(speechRecord, this.mBuffer);
            if (i < 0) {
                handleError("status = " + i);
                return;
            }
        }
    }

    private long getRms(int i, int i2) {
        int i3 = i - i2;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 % 2 != 0) {
            i3++;
        }
        long j = 0;
        while (i3 < i) {
            byte[] bArr = this.mRecording;
            short s = getShort(bArr[i3], bArr[i3 + 1]);
            j += (long) (s * s);
            i3 += 2;
        }
        return j;
    }

    protected void handleError(String str) {
        release();
        setState(AudioRecorder.State.ERROR);
    }

    private int getSpeechRecordState() {
        SpeechRecord speechRecord = this.mRecorder;
        if (speechRecord == null) {
            return 0;
        }
        return speechRecord.getState();
    }
}