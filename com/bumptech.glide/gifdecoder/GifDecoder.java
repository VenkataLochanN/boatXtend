package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class GifDecoder {
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    public static final int TOTAL_ITERATION_COUNT_FOREVER = 0;
    private int[] act;
    private BitmapProvider bitmapProvider;
    private byte[] data;
    private int framePointer;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;
    private static final String TAG = GifDecoder.class.getSimpleName();
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private final int[] pct = new int[256];
    private final byte[] block = new byte[256];
    private GifHeader header = new GifHeader();

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider bitmapProvider) {
        this.bitmapProvider = bitmapProvider;
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        int i;
        if (this.header.frameCount <= 0 || (i = this.framePointer) < 0) {
            return -1;
        }
        return getDelay(i);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        if (this.status != 1 && this.status != 2) {
            this.status = 0;
            GifFrame gifFrame = this.header.frames.get(this.framePointer);
            int i = this.framePointer - 1;
            GifFrame gifFrame2 = i >= 0 ? this.header.frames.get(i) : null;
            this.act = gifFrame.lct != null ? gifFrame.lct : this.header.gct;
            if (this.act == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "No Valid Color Table");
                }
                this.status = 1;
                return null;
            }
            if (gifFrame.transparency) {
                System.arraycopy(this.act, 0, this.pct, 0, this.act.length);
                this.act = this.pct;
                this.act[gifFrame.transIndex] = 0;
            }
            return setPixels(gifFrame, gifFrame2);
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to decode frame, status=" + this.status);
        }
        return null;
    }

    public int read(InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, bArr.length);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e2) {
                Log.w(TAG, "Error reading data from stream", e2);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                Log.w(TAG, "Error closing stream", e3);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        this.header = gifHeader;
        this.data = bArr;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(bArr);
        this.rawData.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.mainPixels = new byte[gifHeader.width * gifHeader.height];
        this.mainScratch = new int[gifHeader.width * gifHeader.height];
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public int read(byte[] bArr) {
        this.data = bArr;
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[this.header.width * this.header.height];
            this.mainScratch = new int[this.header.width * this.header.height];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
        }
        return this.status;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap setPixels(com.bumptech.glide.gifdecoder.GifFrame r18, com.bumptech.glide.gifdecoder.GifFrame r19) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.setPixels(com.bumptech.glide.gifdecoder.GifFrame, com.bumptech.glide.gifdecoder.GifFrame):android.graphics.Bitmap");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v23, types: [short] */
    /* JADX WARN: Type inference failed for: r2v26 */
    private void decodeBitmapData(GifFrame gifFrame) {
        int i;
        short s;
        if (gifFrame != null) {
            this.rawData.position(gifFrame.bufferFrameStart);
        }
        int i2 = gifFrame == null ? this.header.width * this.header.height : gifFrame.ih * gifFrame.iw;
        byte[] bArr = this.mainPixels;
        if (bArr == null || bArr.length < i2) {
            this.mainPixels = new byte[i2];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[4097];
        }
        int i3 = read();
        int i4 = 1;
        int i5 = 1 << i3;
        int i6 = i5 + 1;
        int i7 = i5 + 2;
        int i8 = i3 + 1;
        int i9 = (1 << i8) - 1;
        for (int i10 = 0; i10 < i5; i10++) {
            this.prefix[i10] = 0;
            this.suffix[i10] = (byte) i10;
        }
        int i11 = -1;
        int i12 = i8;
        int i13 = i7;
        int i14 = i9;
        int i15 = 0;
        int block = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = -1;
        while (true) {
            if (i15 >= i2) {
                break;
            }
            int i23 = 3;
            if (block == 0) {
                block = readBlock();
                if (block <= 0) {
                    this.status = 3;
                    break;
                }
                i17 = 0;
            }
            i16 += (this.block[i17] & UByte.MAX_VALUE) << i18;
            i18 += 8;
            i17 += i4;
            block += i11;
            int i24 = i20;
            int i25 = i12;
            int i26 = i22;
            int i27 = i19;
            int i28 = i15;
            int i29 = i13;
            int i30 = i21;
            int i31 = i27;
            while (i18 >= i25) {
                int i32 = i16 & i14;
                i16 >>= i25;
                i18 -= i25;
                if (i32 != i5) {
                    if (i32 > i29) {
                        this.status = i23;
                    } else if (i32 != i6) {
                        if (i26 == -1) {
                            this.pixelStack[i30] = this.suffix[i32];
                            i26 = i32;
                            i24 = i26;
                            i30++;
                            i23 = 3;
                        } else {
                            if (i32 >= i29) {
                                i = i8;
                                this.pixelStack[i30] = (byte) i24;
                                s = i26;
                                i30++;
                            } else {
                                i = i8;
                                s = i32;
                            }
                            while (s >= i5) {
                                this.pixelStack[i30] = this.suffix[s];
                                s = this.prefix[s];
                                i30++;
                                i5 = i5;
                            }
                            int i33 = i5;
                            byte[] bArr2 = this.suffix;
                            int i34 = bArr2[s] & UByte.MAX_VALUE;
                            int i35 = i30 + 1;
                            int i36 = i6;
                            byte b2 = (byte) i34;
                            this.pixelStack[i30] = b2;
                            if (i29 < 4096) {
                                this.prefix[i29] = (short) i26;
                                bArr2[i29] = b2;
                                i29++;
                                if ((i29 & i14) == 0 && i29 < 4096) {
                                    i25++;
                                    i14 += i29;
                                }
                            }
                            i30 = i35;
                            while (i30 > 0) {
                                i30--;
                                this.mainPixels[i31] = this.pixelStack[i30];
                                i28++;
                                i31++;
                            }
                            i26 = i32;
                            i5 = i33;
                            i6 = i36;
                            i23 = 3;
                            i11 = -1;
                            i24 = i34;
                            i8 = i;
                        }
                    }
                    i12 = i25;
                    i22 = i26;
                    i20 = i24;
                    i4 = 1;
                    i11 = -1;
                    break;
                }
                i25 = i8;
                i29 = i7;
                i14 = i9;
                i26 = -1;
                i11 = -1;
            }
            i20 = i24;
            i12 = i25;
            i22 = i26;
            i8 = i8;
            i4 = 1;
            int i37 = i30;
            i13 = i29;
            i15 = i28;
            i19 = i31;
            i21 = i37;
        }
        for (int i38 = i19; i38 < i2; i38++) {
            this.mainPixels[i38] = 0;
        }
    }

    private int read() {
        try {
            return this.rawData.get() & UByte.MAX_VALUE;
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int i = read();
        int i2 = 0;
        if (i > 0) {
            while (i2 < i) {
                int i3 = i - i2;
                try {
                    this.rawData.get(this.block, i2, i3);
                    i2 += i3;
                } catch (Exception e2) {
                    Log.w(TAG, "Error Reading Block", e2);
                    this.status = 1;
                }
            }
        }
        return i2;
    }

    private Bitmap getNextBitmap() {
        Bitmap bitmapObtain = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (bitmapObtain == null) {
            bitmapObtain = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(bitmapObtain);
        return bitmapObtain;
    }

    private static void setAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}