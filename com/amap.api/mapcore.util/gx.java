package com.amap.api.mapcore.util;

import com.realsil.sdk.bbpro.core.protocol.params.Mmi;
import com.realsil.sdk.bbpro.core.protocol.params.Parameters;
import com.realsil.sdk.dfu.DfuConstants;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import kotlin.UByte;
import org.apache.commons.fileupload.MultipartStream;

/* JADX INFO: compiled from: Base64Util.java */
/* JADX INFO: loaded from: classes.dex */
public class gx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f1158a = !gx.class.desiredAssertionStatus();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f1159b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 117, 118, 119, 120, 121, 122, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, 52, 53, 54, 55, 56, 57, 43, 47};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final byte[] f1160c = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, DfuConstants.BANK_INFO_NOT_SUPPORTED, 16, 17, Parameters.RWS_CHANNEL_0, 19, Mmi.AU_MMI_UPDATE_INDICATOR, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, Parameters.RWS_CHANNEL_1, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, MultipartStream.DASH, 46, 47, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final byte[] f1161d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 117, 118, 119, 120, 121, 122, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, 52, 53, 54, 55, 56, 57, MultipartStream.DASH, 95};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final byte[] f1162e = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, DfuConstants.BANK_INFO_NOT_SUPPORTED, 16, 17, Parameters.RWS_CHANNEL_0, 19, Mmi.AU_MMI_UPDATE_INDICATOR, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, Parameters.RWS_CHANNEL_1, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, MultipartStream.DASH, 46, 47, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final byte[] f1163f = {MultipartStream.DASH, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 117, 118, 119, 120, 121, 122};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final byte[] f1164g = {-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, DfuConstants.BANK_INFO_NOT_SUPPORTED, 16, 17, Parameters.RWS_CHANNEL_0, 19, Mmi.AU_MMI_UPDATE_INDICATOR, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, Parameters.RWS_CHANNEL_1, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, MultipartStream.DASH, 46, 47, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9};

    private static final byte[] b(int i) {
        if ((i & 16) == 16) {
            return f1161d;
        }
        if ((i & 32) == 32) {
            return f1163f;
        }
        return f1159b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] c(int i) {
        if ((i & 16) == 16) {
            return f1162e;
        }
        if ((i & 32) == 32) {
            return f1164g;
        }
        return f1160c;
    }

    private gx() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        a(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] bArrB = b(i4);
        int i5 = (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0) | (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 2 ? (bArr[i + 2] << 24) >>> 24 : 0);
        if (i2 == 1) {
            bArr2[i3] = bArrB[i5 >>> 18];
            bArr2[i3 + 1] = bArrB[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = 61;
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 == 2) {
            bArr2[i3] = bArrB[i5 >>> 18];
            bArr2[i3 + 1] = bArrB[(i5 >>> 12) & 63];
            bArr2[i3 + 2] = bArrB[(i5 >>> 6) & 63];
            bArr2[i3 + 3] = 61;
            return bArr2;
        }
        if (i2 != 3) {
            return bArr2;
        }
        bArr2[i3] = bArrB[i5 >>> 18];
        bArr2[i3 + 1] = bArrB[(i5 >>> 12) & 63];
        bArr2[i3 + 2] = bArrB[(i5 >>> 6) & 63];
        bArr2[i3 + 3] = bArrB[i5 & 63];
        return bArr2;
    }

    public static String a(byte[] bArr) throws Throwable {
        String strA;
        try {
            strA = a(bArr, 0, bArr.length, 0);
        } catch (IOException e2) {
            if (!f1158a) {
                throw new AssertionError(e2.getMessage());
            }
            strA = null;
        }
        if (f1158a || strA != null) {
            return strA;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i, int i2, int i3) throws Throwable {
        byte[] bArrB = b(bArr, i, i2, i3);
        try {
            return new String(bArrB, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArrB);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] b(byte[] bArr, int i, int i2, int i3) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        a aVar;
        GZIPOutputStream gZIPOutputStream;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        }
        if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        }
        if ((i3 & 2) != 0) {
            GZIPOutputStream gZIPOutputStream2 = null;
            gZIPOutputStream2 = null;
            gZIPOutputStream2 = null;
            ByteArrayOutputStream byteArrayOutputStream2 = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    aVar = new a(byteArrayOutputStream, i3 | 1);
                } catch (IOException e2) {
                    e = e2;
                    aVar = null;
                    gZIPOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    aVar = null;
                }
            } catch (IOException e3) {
                e = e3;
                aVar = null;
                gZIPOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
                aVar = null;
            }
            try {
                gZIPOutputStream = new GZIPOutputStream(aVar);
                try {
                    gZIPOutputStream.write(bArr, i, i2);
                    gZIPOutputStream.close();
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception unused) {
                    }
                    try {
                        aVar.close();
                    } catch (Exception unused2) {
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception unused3) {
                    }
                    return byteArrayOutputStream.toByteArray();
                } catch (IOException e4) {
                    e = e4;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    try {
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        gZIPOutputStream2 = gZIPOutputStream;
                        try {
                            gZIPOutputStream2.close();
                        } catch (Exception unused4) {
                        }
                        try {
                            aVar.close();
                        } catch (Exception unused5) {
                        }
                        try {
                            byteArrayOutputStream.close();
                            throw th;
                        } catch (Exception unused6) {
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    gZIPOutputStream2 = gZIPOutputStream;
                    gZIPOutputStream2.close();
                    aVar.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                gZIPOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                gZIPOutputStream2.close();
                aVar.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } else {
            byte b2 = (i3 & 8) != 0;
            int i4 = ((i2 / 3) * 4) + (i2 % 3 > 0 ? 4 : 0);
            if (b2 != false) {
                i4 += i4 / 76;
            }
            byte[] bArr2 = new byte[i4];
            int i5 = i2 - 2;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i6 < i5) {
                a(bArr, i6 + i, 3, bArr2, i7, i3);
                int i9 = i8 + 4;
                if (!b2 == true || i9 < 76) {
                    i8 = i9;
                } else {
                    bArr2[i7 + 4] = 10;
                    i7++;
                    i8 = 0;
                }
                i6 += 3;
                i7 += 4;
            }
            if (i6 < i2) {
                a(bArr, i6 + i, i2 - i6, bArr2, i7, i3);
                i7 += 4;
            }
            int i10 = i7;
            if (i10 > bArr2.length - 1) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i10];
            System.arraycopy(bArr2, 0, bArr3, 0, i10);
            return bArr3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        }
        if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        }
        if (i < 0 || (i4 = i + 3) >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i)));
        }
        if (i2 < 0 || (i5 = i2 + 2) >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", Integer.valueOf(bArr2.length), Integer.valueOf(i2)));
        }
        byte[] bArrC = c(i3);
        int i6 = i + 2;
        if (bArr[i6] == 61) {
            bArr2[i2] = (byte) ((((bArrC[bArr[i + 1]] & UByte.MAX_VALUE) << 12) | ((bArrC[bArr[i]] & UByte.MAX_VALUE) << 18)) >>> 16);
            return 1;
        }
        if (bArr[i4] == 61) {
            int i7 = ((bArrC[bArr[i6]] & UByte.MAX_VALUE) << 6) | ((bArrC[bArr[i + 1]] & UByte.MAX_VALUE) << 12) | ((bArrC[bArr[i]] & UByte.MAX_VALUE) << 18);
            bArr2[i2] = (byte) (i7 >>> 16);
            bArr2[i2 + 1] = (byte) (i7 >>> 8);
            return 2;
        }
        int i8 = (bArrC[bArr[i4]] & UByte.MAX_VALUE) | ((bArrC[bArr[i + 1]] & UByte.MAX_VALUE) << 12) | ((bArrC[bArr[i]] & UByte.MAX_VALUE) << 18) | ((bArrC[bArr[i6]] & UByte.MAX_VALUE) << 6);
        bArr2[i2] = (byte) (i8 >> 16);
        bArr2[i2 + 1] = (byte) (i8 >> 8);
        bArr2[i5] = (byte) i8;
        return 3;
    }

    public static byte[] c(byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4;
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        }
        if (i < 0 || (i4 = i + i2) > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (i2 == 0) {
            return new byte[0];
        }
        if (i2 < 4) {
            throw new IllegalArgumentException("Base64Util-encoded string must have at least four characters, but length specified was " + i2);
        }
        byte[] bArrC = c(i3);
        byte[] bArr2 = new byte[(i2 * 3) / 4];
        byte[] bArr3 = new byte[4];
        int i5 = 0;
        int iB = 0;
        while (i < i4) {
            byte b2 = bArrC[bArr[i] & UByte.MAX_VALUE];
            if (b2 < -5) {
                throw new IOException(String.format("Bad Base64Util input character decimal %d in array position %d", Integer.valueOf(bArr[i] & UByte.MAX_VALUE), Integer.valueOf(i)));
            }
            if (b2 >= -1) {
                int i6 = i5 + 1;
                bArr3[i5] = bArr[i];
                if (i6 > 3) {
                    iB += b(bArr3, 0, bArr2, iB, i3);
                    if (bArr[i] == 61) {
                        break;
                    }
                    i5 = 0;
                } else {
                    i5 = i6;
                }
            }
            i++;
        }
        byte[] bArr4 = new byte[iB];
        System.arraycopy(bArr2, 0, bArr4, 0, iB);
        return bArr4;
    }

    public static byte[] a(String str) throws IOException {
        return a(str, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.ByteArrayInputStream] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:58:0x0059
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static byte[] a(java.lang.String r5, int r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto L8d
            java.lang.String r0 = "US-ASCII"
            byte[] r5 = r5.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L9
            goto Ld
        L9:
            byte[] r5 = r5.getBytes()
        Ld:
            int r0 = r5.length
            r1 = 0
            byte[] r5 = c(r5, r1, r0, r6)
            r0 = 4
            r6 = r6 & r0
            r2 = 1
            if (r6 == 0) goto L1a
            r6 = r2
            goto L1b
        L1a:
            r6 = r1
        L1b:
            if (r5 == 0) goto L8c
            int r3 = r5.length
            if (r3 < r0) goto L8c
            if (r6 != 0) goto L8c
            r6 = r5[r1]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r0 = r5[r2]
            int r0 = r0 << 8
            r2 = 65280(0xff00, float:9.1477E-41)
            r0 = r0 & r2
            r6 = r6 | r0
            r0 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r6) goto L8c
            r6 = 2048(0x800, float:2.87E-42)
            byte[] r6 = new byte[r6]
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L75
            r2.<init>()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L75
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6c
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L6c
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L66
        L48:
            int r0 = r4.read(r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            if (r0 < 0) goto L52
            r2.write(r6, r1, r0)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            goto L48
        L52:
            byte[] r5 = r2.toByteArray()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r2.close()     // Catch: java.lang.Exception -> L59
        L59:
            r4.close()     // Catch: java.lang.Exception -> L5c
        L5c:
            r3.close()     // Catch: java.lang.Exception -> L8c
            goto L8c
        L60:
            r5 = move-exception
            goto L81
        L62:
            r6 = move-exception
            goto L6f
        L64:
            r5 = move-exception
            goto L82
        L66:
            r6 = move-exception
            r4 = r0
            goto L6f
        L69:
            r5 = move-exception
            r3 = r0
            goto L82
        L6c:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L6f:
            r0 = r2
            goto L78
        L71:
            r5 = move-exception
            r2 = r0
            r3 = r2
            goto L82
        L75:
            r6 = move-exception
            r3 = r0
            r4 = r3
        L78:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L7f
            r0.close()     // Catch: java.lang.Exception -> L59
            goto L59
        L7f:
            r5 = move-exception
            r2 = r0
        L81:
            r0 = r4
        L82:
            r2.close()     // Catch: java.lang.Exception -> L85
        L85:
            r0.close()     // Catch: java.lang.Exception -> L88
        L88:
            r3.close()     // Catch: java.lang.Exception -> L8b
        L8b:
            throw r5
        L8c:
            return r5
        L8d:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "Input string was null."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.gx.a(java.lang.String, int):byte[]");
    }

    /* JADX INFO: compiled from: Base64Util.java */
    public static class a extends FilterOutputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1165a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1166b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private byte[] f1167c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f1168d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1169e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f1170f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private byte[] f1171g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f1172h;
        private int i;
        private byte[] j;

        public a(OutputStream outputStream, int i) {
            super(outputStream);
            this.f1170f = (i & 8) != 0;
            this.f1165a = (i & 1) != 0;
            this.f1168d = this.f1165a ? 3 : 4;
            this.f1167c = new byte[this.f1168d];
            this.f1166b = 0;
            this.f1169e = 0;
            this.f1172h = false;
            this.f1171g = new byte[4];
            this.i = i;
            this.j = gx.c(i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.f1172h) {
                this.out.write(i);
                return;
            }
            if (this.f1165a) {
                byte[] bArr = this.f1167c;
                int i2 = this.f1166b;
                this.f1166b = i2 + 1;
                bArr[i2] = (byte) i;
                if (this.f1166b >= this.f1168d) {
                    this.out.write(gx.b(this.f1171g, this.f1167c, this.f1168d, this.i));
                    this.f1169e += 4;
                    if (this.f1170f && this.f1169e >= 76) {
                        this.out.write(10);
                        this.f1169e = 0;
                    }
                    this.f1166b = 0;
                    return;
                }
                return;
            }
            byte[] bArr2 = this.j;
            int i3 = i & 127;
            if (bArr2[i3] > -5) {
                byte[] bArr3 = this.f1167c;
                int i4 = this.f1166b;
                this.f1166b = i4 + 1;
                bArr3[i4] = (byte) i;
                if (this.f1166b >= this.f1168d) {
                    this.out.write(this.f1171g, 0, gx.b(bArr3, 0, this.f1171g, 0, this.i));
                    this.f1166b = 0;
                    return;
                }
                return;
            }
            if (bArr2[i3] != -5) {
                throw new IOException("Invalid character in Base64Util data.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.f1172h) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void a() throws IOException {
            if (this.f1166b > 0) {
                if (this.f1165a) {
                    this.out.write(gx.b(this.f1171g, this.f1167c, this.f1166b, this.i));
                    this.f1166b = 0;
                    return;
                }
                throw new IOException("Base64Util input not properly padded.");
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a();
            super.close();
            this.f1167c = null;
            this.out = null;
        }
    }
}