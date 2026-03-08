package com.ido.life.appachcodec;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseNCodec implements BinaryEncoder, BinaryDecoder {
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    static final int EOF = -1;
    protected static final int MASK_8BITS = 255;
    private static final int MAX_BUFFER_SIZE = 2147483639;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD_DEFAULT = 61;
    public static final int PEM_CHUNK_SIZE = 64;

    @Deprecated
    protected final byte PAD;
    private final int chunkSeparatorLength;
    private final int encodedBlockSize;
    protected final int lineLength;
    protected final byte pad;
    private final int unencodedBlockSize;

    protected static boolean isWhiteSpace(byte b2) {
        return b2 == 9 || b2 == 10 || b2 == 13 || b2 == 32;
    }

    abstract void decode(byte[] bArr, int i, int i2, Context context);

    abstract void encode(byte[] bArr, int i, int i2, Context context);

    protected int getDefaultBufferSize() {
        return 8192;
    }

    protected abstract boolean isInAlphabet(byte b2);

    protected BaseNCodec(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, PAD_DEFAULT);
    }

    protected BaseNCodec(int i, int i2, int i3, int i4, byte b2) {
        this.PAD = PAD_DEFAULT;
        this.unencodedBlockSize = i;
        this.encodedBlockSize = i2;
        this.lineLength = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.chunkSeparatorLength = i4;
        this.pad = b2;
    }

    boolean hasData(Context context) {
        return context.buffer != null;
    }

    int available(Context context) {
        if (context.buffer != null) {
            return context.pos - context.readPos;
        }
        return 0;
    }

    private static byte[] resizeBuffer(Context context, int i) {
        int length = context.buffer.length * 2;
        if (compareUnsigned(length, i) < 0) {
            length = i;
        }
        if (compareUnsigned(length, MAX_BUFFER_SIZE) > 0) {
            length = createPositiveCapacity(i);
        }
        byte[] bArr = new byte[length];
        System.arraycopy(context.buffer, 0, bArr, 0, context.buffer.length);
        context.buffer = bArr;
        return bArr;
    }

    private static int compareUnsigned(int i, int i2) {
        return Integer.compare(i - Integer.MIN_VALUE, i2 - Integer.MIN_VALUE);
    }

    private static int createPositiveCapacity(int i) {
        if (i >= 0) {
            return i > MAX_BUFFER_SIZE ? i : MAX_BUFFER_SIZE;
        }
        throw new OutOfMemoryError("Unable to allocate array size: " + (((long) i) & 4294967295L));
    }

    protected byte[] ensureBufferSize(int i, Context context) {
        if (context.buffer == null) {
            context.buffer = new byte[getDefaultBufferSize()];
            context.pos = 0;
            context.readPos = 0;
        } else if ((context.pos + i) - context.buffer.length > 0) {
            return resizeBuffer(context, context.pos + i);
        }
        return context.buffer;
    }

    int readResults(byte[] bArr, int i, int i2, Context context) {
        if (context.buffer == null) {
            return context.eof ? -1 : 0;
        }
        int iMin = Math.min(available(context), i2);
        System.arraycopy(context.buffer, context.readPos, bArr, i, iMin);
        context.readPos += iMin;
        if (context.readPos >= context.pos) {
            context.buffer = null;
        }
        return iMin;
    }

    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof byte[])) {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode(obj);
    }

    public String encodeToString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public String encodeAsString(byte[] bArr) {
        return StringUtils.newStringUtf8(encode(bArr));
    }

    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode(obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String str) {
        return decode(StringUtils.getBytesUtf8(str));
    }

    @Override // com.ido.life.appachcodec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Context context = new Context();
        decode(bArr, 0, bArr.length, context);
        decode(bArr, 0, -1, context);
        byte[] bArr2 = new byte[context.pos];
        readResults(bArr2, 0, bArr2.length, context);
        return bArr2;
    }

    @Override // com.ido.life.appachcodec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? bArr : encode(bArr, 0, bArr.length);
    }

    public byte[] encode(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Context context = new Context();
        encode(bArr, i, i2, context);
        encode(bArr, i, -1, context);
        byte[] bArr2 = new byte[context.pos - context.readPos];
        readResults(bArr2, 0, bArr2.length, context);
        return bArr2;
    }

    public boolean isInAlphabet(byte[] bArr, boolean z) {
        for (byte b2 : bArr) {
            if (!isInAlphabet(b2) && (!z || (b2 != this.pad && !isWhiteSpace(b2)))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInAlphabet(String str) {
        return isInAlphabet(StringUtils.getBytesUtf8(str), true);
    }

    protected boolean containsAlphabetOrPad(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b2 : bArr) {
            if (this.pad == b2 || isInAlphabet(b2)) {
                return true;
            }
        }
        return false;
    }

    public long getEncodedLength(byte[] bArr) {
        int length = bArr.length;
        long j = ((long) (((length + r0) - 1) / this.unencodedBlockSize)) * ((long) this.encodedBlockSize);
        int i = this.lineLength;
        return i > 0 ? j + ((((((long) i) + j) - 1) / ((long) i)) * ((long) this.chunkSeparatorLength)) : j;
    }

    static class Context {
        byte[] buffer;
        int currentLinePos;
        boolean eof;
        int ibitWorkArea;
        long lbitWorkArea;
        int modulus;
        int pos;
        int readPos;

        Context() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", getClass().getSimpleName(), Arrays.toString(this.buffer), Integer.valueOf(this.currentLinePos), Boolean.valueOf(this.eof), Integer.valueOf(this.ibitWorkArea), Long.valueOf(this.lbitWorkArea), Integer.valueOf(this.modulus), Integer.valueOf(this.pos), Integer.valueOf(this.readPos));
        }
    }
}