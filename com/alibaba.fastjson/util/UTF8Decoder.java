package com.alibaba.fastjson.util;

import com.alibaba.fastjson.asm.Opcodes;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.bumptech.glide.load.Key;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class UTF8Decoder extends CharsetDecoder {
    private static final Charset charset = Charset.forName(Key.STRING_CHARSET_NAME);

    private static boolean isMalformed2(int i, int i2) {
        return (i & 30) == 0 || (i2 & Opcodes.CHECKCAST) != 128;
    }

    private static boolean isMalformed3(int i, int i2, int i3) {
        return ((i != -32 || (i2 & 224) != 128) && (i2 & Opcodes.CHECKCAST) == 128 && (i3 & Opcodes.CHECKCAST) == 128) ? false : true;
    }

    private static boolean isMalformed4(int i, int i2, int i3) {
        return ((i & Opcodes.CHECKCAST) == 128 && (i2 & Opcodes.CHECKCAST) == 128 && (i3 & Opcodes.CHECKCAST) == 128) ? false : true;
    }

    private static boolean isNotContinuation(int i) {
        return (i & Opcodes.CHECKCAST) != 128;
    }

    public UTF8Decoder() {
        super(charset, 1.0f, 1.0f);
    }

    private static CoderResult lookupN(ByteBuffer byteBuffer, int i) {
        for (int i2 = 1; i2 < i; i2++) {
            if (isNotContinuation(byteBuffer.get())) {
                return CoderResult.malformedForLength(i2);
            }
        }
        return CoderResult.malformedForLength(i);
    }

    public static CoderResult malformedN(ByteBuffer byteBuffer, int i) {
        int i2 = 1;
        if (i == 1) {
            byte b2 = byteBuffer.get();
            if ((b2 >> 2) == -2) {
                return byteBuffer.remaining() < 4 ? CoderResult.UNDERFLOW : lookupN(byteBuffer, 5);
            }
            if ((b2 >> 1) == -2) {
                if (byteBuffer.remaining() < 5) {
                    return CoderResult.UNDERFLOW;
                }
                return lookupN(byteBuffer, 6);
            }
            return CoderResult.malformedForLength(1);
        }
        if (i == 2) {
            return CoderResult.malformedForLength(1);
        }
        if (i == 3) {
            byte b3 = byteBuffer.get();
            byte b4 = byteBuffer.get();
            if ((b3 != -32 || (b4 & 224) != 128) && !isNotContinuation(b4)) {
                i2 = 2;
            }
            return CoderResult.malformedForLength(i2);
        }
        if (i == 4) {
            int i3 = byteBuffer.get() & UByte.MAX_VALUE;
            int i4 = byteBuffer.get() & UByte.MAX_VALUE;
            if (i3 > 244 || ((i3 == 240 && (i4 < 144 || i4 > 191)) || ((i3 == 244 && (i4 & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) != 128) || isNotContinuation(i4)))) {
                return CoderResult.malformedForLength(1);
            }
            return isNotContinuation(byteBuffer.get()) ? CoderResult.malformedForLength(2) : CoderResult.malformedForLength(3);
        }
        throw new IllegalStateException();
    }

    private static CoderResult malformed(ByteBuffer byteBuffer, int i, CharBuffer charBuffer, int i2, int i3) {
        byteBuffer.position(i - byteBuffer.arrayOffset());
        CoderResult coderResultMalformedN = malformedN(byteBuffer, i3);
        updatePositions(byteBuffer, i, charBuffer, i2);
        return coderResultMalformedN;
    }

    private static CoderResult xflow(Buffer buffer, int i, int i2, Buffer buffer2, int i3, int i4) {
        updatePositions(buffer, i, buffer2, i3);
        return (i4 == 0 || i2 - i < i4) ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0093, code lost:
    
        return xflow(r13, r5, r6, r14, r8, 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ca, code lost:
    
        return xflow(r13, r5, r6, r14, r8, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0123, code lost:
    
        return xflow(r13, r5, r6, r14, r8, 4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.nio.charset.CoderResult decodeArrayLoop(java.nio.ByteBuffer r13, java.nio.CharBuffer r14) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.UTF8Decoder.decodeArrayLoop(java.nio.ByteBuffer, java.nio.CharBuffer):java.nio.charset.CoderResult");
    }

    @Override // java.nio.charset.CharsetDecoder
    protected CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        return decodeArrayLoop(byteBuffer, charBuffer);
    }

    static void updatePositions(Buffer buffer, int i, Buffer buffer2, int i2) {
        buffer.position(i);
        buffer2.position(i2);
    }

    private static class Surrogate {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final int UCS4_MAX = 1114111;
        public static final int UCS4_MIN = 65536;

        public static char high(int i) {
            return (char) ((((i - 65536) >> 10) & 1023) | 55296);
        }

        public static char low(int i) {
            return (char) (((i - 65536) & 1023) | 56320);
        }

        public static boolean neededFor(int i) {
            return i >= 65536 && i <= 1114111;
        }

        private Surrogate() {
        }
    }
}