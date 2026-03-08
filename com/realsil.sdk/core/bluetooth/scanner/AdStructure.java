package com.realsil.sdk.core.bluetooth.scanner;

import com.bumptech.glide.load.Key;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AdStructure {
    public AdStructure(int i, int i2, byte[] bArr) {
        try {
            new String(bArr, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        ZLogger.v("Length: " + i + " Type : " + i2 + " Data : " + DataConverter.bytes2Hex(bArr));
    }

    public static List<AdStructure> parseScanRecord(byte[] bArr) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            byte b3 = bArr[i];
            if (b3 == 0 || (b2 = bArr[i2]) == 0) {
                break;
            }
            int i3 = i2 + 1;
            int i4 = i2 + b3;
            arrayList.add(new AdStructure(b3, b2, Arrays.copyOfRange(bArr, i3, i4)));
            i = i4;
        }
        return arrayList;
    }
}