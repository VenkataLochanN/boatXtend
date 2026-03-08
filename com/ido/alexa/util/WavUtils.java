package com.ido.alexa.util;

import android.util.Log;
import com.realsil.sdk.bbpro.core.protocol.params.Mmi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes2.dex */
public class WavUtils {
    private static final String TAG = WavUtils.class.getSimpleName();

    public static byte[] generateWavFileHeader(int i, int i2, int i3, int i4) {
        return new WavHeader(i, i2, (short) i3, (short) i4).getHeader();
    }

    public static void writeHeader(File file, byte[] bArr) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    try {
                        randomAccessFile2.seek(0L);
                        randomAccessFile2.write(bArr);
                        randomAccessFile2.close();
                        randomAccessFile2.close();
                    } catch (Exception e2) {
                        e = e2;
                        randomAccessFile = randomAccessFile2;
                        Log.e(TAG, e.getMessage());
                        if (randomAccessFile == null) {
                        } else {
                            randomAccessFile.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e3) {
                                Log.e(TAG, e3.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (IOException e5) {
            Log.e(TAG, e5.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0062 -> B:62:0x0065). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void convertPcm2Wav(java.lang.String r11, java.lang.String r12, int r13, int r14, int r15) throws java.lang.Throwable {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            int r1 = r13 * r14
            int r1 = r1 * r15
            r15 = 0
            int r1 = r1 / 8
            long r9 = (long) r1     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            r1.<init>(r11)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4c
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L44
            java.nio.channels.FileChannel r12 = r1.getChannel()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            long r3 = r12.size()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r5 = 36
            long r5 = r5 + r3
            r2 = r11
            r7 = r13
            r8 = r14
            writeWaveFileHeader(r2, r3, r5, r7, r8, r9)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
        L26:
            int r12 = r1.read(r0)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r12 <= 0) goto L31
            r13 = 0
            r11.write(r0, r13, r12)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            goto L26
        L31:
            r1.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r12 = move-exception
            r12.printStackTrace()
        L39:
            r11.close()     // Catch: java.io.IOException -> L61
            goto L65
        L3d:
            r12 = move-exception
            goto L68
        L3f:
            r12 = move-exception
            goto L46
        L41:
            r12 = move-exception
            r11 = r15
            goto L68
        L44:
            r12 = move-exception
            r11 = r15
        L46:
            r15 = r1
            goto L4e
        L48:
            r12 = move-exception
            r11 = r15
            r1 = r11
            goto L68
        L4c:
            r12 = move-exception
            r11 = r15
        L4e:
            r12.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r15 == 0) goto L5b
            r15.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r12 = move-exception
            r12.printStackTrace()
        L5b:
            if (r11 == 0) goto L65
            r11.close()     // Catch: java.io.IOException -> L61
            goto L65
        L61:
            r11 = move-exception
            r11.printStackTrace()
        L65:
            return
        L66:
            r12 = move-exception
            r1 = r15
        L68:
            if (r1 == 0) goto L72
            r1.close()     // Catch: java.io.IOException -> L6e
            goto L72
        L6e:
            r13 = move-exception
            r13.printStackTrace()
        L72:
            if (r11 == 0) goto L7c
            r11.close()     // Catch: java.io.IOException -> L78
            goto L7c
        L78:
            r11 = move-exception
            r11.printStackTrace()
        L7c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.alexa.util.WavUtils.convertPcm2Wav(java.lang.String, java.lang.String, int, int, int):void");
    }

    private static void writeWaveFileHeader(FileOutputStream fileOutputStream, long j, long j2, int i, int i2, long j3) throws IOException {
        fileOutputStream.write(new byte[]{82, 73, 70, 70, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 87, 65, 86, 69, 102, 109, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 32, 16, 0, 0, 0, 1, 0, (byte) i2, 0, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), (byte) ((i2 * 16) / 8), 0, 16, 0, 100, 97, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 97, (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)}, 0, 44);
    }
}