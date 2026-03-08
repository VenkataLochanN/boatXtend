package com.ido.alexa.util;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.text.TextUtils;
import android.view.Surface;
import com.ido.record.MediaFormatFactory;
import com.realsil.sdk.bbpro.core.protocol.params.Mmi;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AudioUtils {
    public static byte[] getWavHeader(int i, int i2, short s, short s2) {
        int i3 = i2 * s;
        int i4 = i + 44;
        return new byte[]{82, 73, 70, 70, (byte) (i4 & 255), (byte) ((i4 >> 8) & 255), (byte) ((i4 >> 16) & 255), (byte) ((i4 >> 24) & 255), 87, 65, 86, 69, 102, 109, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 32, 16, 0, 0, 0, 1, 0, (byte) s2, 0, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255), (byte) (i3 & 255), (byte) ((i3 >> 8) & 255), (byte) ((i3 >> 16) & 255), (byte) ((i3 >> 24) & 255), 4, 0, (byte) (s * 8), 0, 100, 97, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 97, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    private AudioUtils() {
    }

    public static void saveWavToFile(String str, byte[] bArr, boolean z) throws Throwable {
        try {
            FileUtils.writeByteArrayToFile(new File(str), bArr, z);
        } catch (IOException unused) {
        }
    }

    public static void saveWavHeaderToFile(String str, byte[] bArr) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.write(bArr);
            randomAccessFile.close();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static byte[] getRecordingAsWav(byte[] bArr, int i, short s, short s2) {
        byte[] wavHeader = getWavHeader(bArr.length, i, s, s2);
        byte[] bArr2 = new byte[wavHeader.length + bArr.length];
        System.arraycopy(wavHeader, 0, bArr2, 0, wavHeader.length);
        System.arraycopy(bArr, 0, bArr2, wavHeader.length, bArr.length);
        return bArr2;
    }

    public static List<String> getAvailableEncoders(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            MediaFormat mediaFormatCreateMediaFormat = MediaFormatFactory.createMediaFormat(MediaFormatFactory.Type.FLAC, i);
            MediaCodecList mediaCodecList = new MediaCodecList(0);
            String strFindEncoderForFormat = mediaCodecList.findEncoderForFormat(mediaFormatCreateMediaFormat);
            ArrayList arrayList = new ArrayList();
            for (MediaCodecInfo mediaCodecInfo : mediaCodecList.getCodecInfos()) {
                if (mediaCodecInfo.isEncoder()) {
                    if (mediaCodecInfo.getName().equals(strFindEncoderForFormat)) {
                        arrayList.add("*** " + mediaCodecInfo.getName() + ": " + TextUtils.join(", ", mediaCodecInfo.getSupportedTypes()));
                    } else {
                        arrayList.add(mediaCodecInfo.getName() + ": " + TextUtils.join(", ", mediaCodecInfo.getSupportedTypes()));
                    }
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public static List<String> getEncoderNamesForType(String str) {
        LinkedList linkedList = new LinkedList();
        if (Build.VERSION.SDK_INT >= 16) {
            int codecCount = MediaCodecList.getCodecCount();
            for (int i = 0; i < codecCount; i++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
                if (codecInfoAt.isEncoder() && codecInfoAt.getName().startsWith("OMX.")) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    int length = supportedTypes.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        if (supportedTypes[i2].equalsIgnoreCase(str)) {
                            linkedList.push(codecInfoAt.getName());
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return linkedList;
    }

    public static MediaCodec createCodec(String str, MediaFormat mediaFormat) {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(str);
                mediaCodecCreateByCodecName.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
                return mediaCodecCreateByCodecName;
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static void showMetrics(MediaFormat mediaFormat, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            mediaFormat.getInteger("sample-rate");
            mediaFormat.getInteger("channel-count");
            mediaFormat.getInteger("bitrate");
        }
    }

    public static byte[] concatenateBuffers(List<byte[]> list) {
        Iterator<byte[]> it = list.iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().length;
        }
        byte[] bArr = new byte[length];
        int length2 = 0;
        for (byte[] bArr2 : list) {
            System.arraycopy(bArr2, 0, bArr, length2, bArr2.length);
            length2 += bArr2.length;
        }
        return bArr;
    }

    public static void showSomeBytes(String str, byte[] bArr) {
        int length = bArr.length;
        if (length > 0) {
            String str2 = "";
            for (int i = 0; i < length && i < 5; i++) {
                str2 = str2 + Integer.toHexString(bArr[i]) + " ";
            }
        }
    }
}