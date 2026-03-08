package com.bumptech.glide.gifdecoder;

import android.util.Log;
import androidx.core.view.ViewCompat;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class GifHeaderParser {
    static final int DEFAULT_FRAME_DELAY = 10;
    private static final int MAX_BLOCK_SIZE = 256;
    static final int MIN_FRAME_DELAY = 3;
    public static final String TAG = "GifHeaderParser";
    private final byte[] block = new byte[256];
    private int blockSize = 0;
    private GifHeader header;
    private ByteBuffer rawData;

    public GifHeaderParser setData(byte[] bArr) {
        reset();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.rawData = null;
            this.header.status = 2;
        }
        return this;
    }

    public void clear() {
        this.rawData = null;
        this.header = null;
    }

    private void reset() {
        this.rawData = null;
        Arrays.fill(this.block, (byte) 0);
        this.header = new GifHeader();
        this.blockSize = 0;
    }

    public GifHeader parseHeader() {
        if (this.rawData == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (err()) {
            return this.header;
        }
        readHeader();
        if (!err()) {
            readContents();
            if (this.header.frameCount < 0) {
                this.header.status = 1;
            }
        }
        return this.header;
    }

    private void readContents() {
        boolean z = false;
        while (!z && !err()) {
            int i = read();
            if (i == 33) {
                int i2 = read();
                if (i2 == 1) {
                    skip();
                } else if (i2 == 249) {
                    this.header.currentFrame = new GifFrame();
                    readGraphicControlExt();
                } else if (i2 == 254) {
                    skip();
                } else if (i2 == 255) {
                    readBlock();
                    String str = "";
                    for (int i3 = 0; i3 < 11; i3++) {
                        str = str + ((char) this.block[i3]);
                    }
                    if (str.equals("NETSCAPE2.0")) {
                        readNetscapeExt();
                    } else {
                        skip();
                    }
                } else {
                    skip();
                }
            } else if (i == 44) {
                if (this.header.currentFrame == null) {
                    this.header.currentFrame = new GifFrame();
                }
                readBitmap();
            } else if (i != 59) {
                this.header.status = 1;
            } else {
                z = true;
            }
        }
    }

    private void readGraphicControlExt() {
        read();
        int i = read();
        this.header.currentFrame.dispose = (i & 28) >> 2;
        if (this.header.currentFrame.dispose == 0) {
            this.header.currentFrame.dispose = 1;
        }
        this.header.currentFrame.transparency = (i & 1) != 0;
        int i2 = readShort();
        if (i2 < 3) {
            i2 = 10;
        }
        this.header.currentFrame.delay = i2 * 10;
        this.header.currentFrame.transIndex = read();
        read();
    }

    private void readBitmap() {
        this.header.currentFrame.ix = readShort();
        this.header.currentFrame.iy = readShort();
        this.header.currentFrame.iw = readShort();
        this.header.currentFrame.ih = readShort();
        int i = read();
        boolean z = (i & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (i & 7) + 1);
        this.header.currentFrame.interlace = (i & 64) != 0;
        if (z) {
            this.header.currentFrame.lct = readColorTable(iPow);
        } else {
            this.header.currentFrame.lct = null;
        }
        this.header.currentFrame.bufferFrameStart = this.rawData.position();
        skipImageData();
        if (err()) {
            return;
        }
        this.header.frameCount++;
        this.header.frames.add(this.header.currentFrame);
    }

    private void readNetscapeExt() {
        do {
            readBlock();
            byte[] bArr = this.block;
            if (bArr[0] == 1) {
                this.header.loopCount = ((bArr[2] & UByte.MAX_VALUE) << 8) | (bArr[1] & UByte.MAX_VALUE);
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    private void readHeader() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) read());
        }
        if (!str.startsWith("GIF")) {
            this.header.status = 1;
            return;
        }
        readLSD();
        if (!this.header.gctFlag || err()) {
            return;
        }
        GifHeader gifHeader = this.header;
        gifHeader.gct = readColorTable(gifHeader.gctSize);
        GifHeader gifHeader2 = this.header;
        gifHeader2.bgColor = gifHeader2.gct[this.header.bgIndex];
    }

    private void readLSD() {
        this.header.width = readShort();
        this.header.height = readShort();
        int i = read();
        this.header.gctFlag = (i & 128) != 0;
        GifHeader gifHeader = this.header;
        gifHeader.gctSize = 2 << (i & 7);
        gifHeader.bgIndex = read();
        this.header.pixelAspect = read();
    }

    private int[] readColorTable(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.rawData.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i2 + 1;
                iArr[i2] = ((bArr[i3] & UByte.MAX_VALUE) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i4] & UByte.MAX_VALUE) << 8) | (bArr[i5] & UByte.MAX_VALUE);
                i3 = i6;
                i2 = i7;
            }
        } catch (BufferUnderflowException e2) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Format Error Reading Color Table", e2);
            }
            this.header.status = 1;
        }
        return iArr;
    }

    private void skipImageData() {
        read();
        skip();
    }

    private void skip() {
        int i;
        do {
            i = read();
            ByteBuffer byteBuffer = this.rawData;
            byteBuffer.position(byteBuffer.position() + i);
        } while (i > 0);
    }

    private int readBlock() {
        this.blockSize = read();
        int i = 0;
        if (this.blockSize > 0) {
            int i2 = 0;
            while (i < this.blockSize) {
                try {
                    i2 = this.blockSize - i;
                    this.rawData.get(this.block, i, i2);
                    i += i2;
                } catch (Exception e2) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Error Reading Block n: " + i + " count: " + i2 + " blockSize: " + this.blockSize, e2);
                    }
                    this.header.status = 1;
                }
            }
        }
        return i;
    }

    private int read() {
        try {
            return this.rawData.get() & UByte.MAX_VALUE;
        } catch (Exception unused) {
            this.header.status = 1;
            return 0;
        }
    }

    private int readShort() {
        return this.rawData.getShort();
    }

    private boolean err() {
        return this.header.status != 0;
    }
}