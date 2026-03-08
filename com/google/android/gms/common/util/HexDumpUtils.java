package com.google.android.gms.common.util;

import kotlin.UByte;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public final class HexDumpUtils {
    public static String dump(byte[] bArr, int i, int i2, boolean z) {
        if (bArr == null || bArr.length == 0 || i < 0 || i2 <= 0 || i + i2 > bArr.length) {
            return null;
        }
        StringBuilder sb = new StringBuilder((z ? 75 : 57) * (((i2 + 16) - 1) / 16));
        int i3 = i;
        int i4 = i2;
        int i5 = 0;
        int i6 = 0;
        while (i4 > 0) {
            if (i5 == 0) {
                if (i2 < 65536) {
                    sb.append(String.format("%04X:", Integer.valueOf(i3)));
                } else {
                    sb.append(String.format("%08X:", Integer.valueOf(i3)));
                }
                i6 = i3;
            } else if (i5 == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", Integer.valueOf(bArr[i3] & UByte.MAX_VALUE)));
            i4--;
            i5++;
            if (z && (i5 == 16 || i4 == 0)) {
                int i7 = 16 - i5;
                if (i7 > 0) {
                    for (int i8 = 0; i8 < i7; i8++) {
                        sb.append("   ");
                    }
                }
                if (i7 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int i9 = 0; i9 < i5; i9++) {
                    char c2 = (char) bArr[i6 + i9];
                    if (c2 < ' ' || c2 > '~') {
                        c2 = FilenameUtils.EXTENSION_SEPARATOR;
                    }
                    sb.append(c2);
                }
            }
            if (i5 == 16 || i4 == 0) {
                sb.append('\n');
                i5 = 0;
            }
            i3++;
        }
        return sb.toString();
    }
}