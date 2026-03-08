package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: loaded from: classes2.dex */
final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    UPCEANExtension5Support() {
    }

    Result decodeRow(int i, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int iDecodeMiddle = decodeMiddle(bitArray, iArr, sb);
        String string = sb.toString();
        Map<ResultMetadataType, Object> extensionString = parseExtensionString(string);
        float f2 = i;
        Result result = new Result(string, null, new ResultPoint[]{new ResultPoint((iArr[0] + iArr[1]) / 2.0f, f2), new ResultPoint(iDecodeMiddle, f2)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (extensionString != null) {
            result.putAllMetadata(extensionString);
        }
        return result;
    }

    private int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int nextUnset = iArr[1];
        int i = 0;
        int i2 = 0;
        while (i < 5 && nextUnset < size) {
            int iDecodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, nextUnset, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((iDecodeDigit % 10) + 48));
            int i3 = nextUnset;
            for (int i4 : iArr2) {
                i3 += i4;
            }
            if (iDecodeDigit >= 10) {
                i2 |= 1 << (4 - i);
            }
            nextUnset = i != 4 ? bitArray.getNextUnset(bitArray.getNextSet(i3)) : i3;
            i++;
        }
        if (sb.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (extensionChecksum(sb.toString()) == determineCheckDigit(i2)) {
            return nextUnset;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(CharSequence charSequence) {
        int length = charSequence.length();
        int iCharAt = 0;
        for (int i = length - 2; i >= 0; i -= 2) {
            iCharAt += charSequence.charAt(i) - '0';
        }
        int iCharAt2 = iCharAt * 3;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            iCharAt2 += charSequence.charAt(i2) - '0';
        }
        return (iCharAt2 * 3) % 10;
    }

    private static int determineCheckDigit(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == CHECK_DIGIT_ENCODINGS[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        String extension5String;
        if (str.length() != 5 || (extension5String = parseExtension5String(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, extension5String);
        return enumMap;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static String parseExtension5String(String str) {
        byte b2 = 0;
        char cCharAt = str.charAt(0);
        String str2 = "";
        if (cCharAt == '0') {
            str2 = "£";
        } else if (cCharAt == '5') {
            str2 = "$";
        } else if (cCharAt == '9') {
            switch (str.hashCode()) {
                case 54118329:
                    if (!str.equals("90000")) {
                        b2 = -1;
                    }
                    break;
                case 54395376:
                    b2 = !str.equals("99990") ? (byte) -1 : (byte) 2;
                    break;
                case 54395377:
                    b2 = !str.equals("99991") ? (byte) -1 : (byte) 1;
                    break;
                default:
                    b2 = -1;
                    break;
            }
            if (b2 == 0) {
                return null;
            }
            if (b2 == 1) {
                return "0.00";
            }
            if (b2 == 2) {
                return "Used";
            }
        }
        int i = Integer.parseInt(str.substring(1));
        String strValueOf = String.valueOf(i / 100);
        int i2 = i % 100;
        return str2 + strValueOf + FilenameUtils.EXTENSION_SEPARATOR + (i2 < 10 ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.concat(String.valueOf(i2)) : String.valueOf(i2));
    }
}