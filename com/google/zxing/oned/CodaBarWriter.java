package com.google.zxing.oned;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] START_END_CHARS = {'A', 'B', 'C', 'D'};
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {IOUtils.DIR_SEPARATOR_UNIX, ':', '+', FilenameUtils.EXTENSION_SEPARATOR};
    private static final char DEFAULT_GUARD = START_END_CHARS[0];

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int i;
        if (str.length() < 2) {
            str = DEFAULT_GUARD + str + DEFAULT_GUARD;
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean zArrayContains = CodaBarReader.arrayContains(START_END_CHARS, upperCase);
            boolean zArrayContains2 = CodaBarReader.arrayContains(START_END_CHARS, upperCase2);
            boolean zArrayContains3 = CodaBarReader.arrayContains(ALT_START_END_CHARS, upperCase);
            boolean zArrayContains4 = CodaBarReader.arrayContains(ALT_START_END_CHARS, upperCase2);
            if (zArrayContains) {
                if (!zArrayContains2) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
            } else if (!zArrayContains3) {
                if (zArrayContains2 || zArrayContains4) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
                str = DEFAULT_GUARD + str + DEFAULT_GUARD;
            } else if (!zArrayContains4) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
            }
        }
        int i2 = 20;
        for (int i3 = 1; i3 < str.length() - 1; i3++) {
            if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '-' || str.charAt(i3) == '$') {
                i2 += 9;
            } else {
                if (!CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, str.charAt(i3))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i3) + '\'');
                }
                i2 += 10;
            }
        }
        boolean[] zArr = new boolean[i2 + (str.length() - 1)];
        int i4 = 0;
        for (int i5 = 0; i5 < str.length(); i5++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i5));
            if (i5 == 0 || i5 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i6 = 0;
            while (true) {
                if (i6 >= CodaBarReader.ALPHABET.length) {
                    i = 0;
                    break;
                }
                if (upperCase3 == CodaBarReader.ALPHABET[i6]) {
                    i = CodaBarReader.CHARACTER_ENCODINGS[i6];
                    break;
                }
                i6++;
            }
            int i7 = 0;
            boolean z = true;
            int i8 = i4;
            int i9 = 0;
            while (i9 < 7) {
                zArr[i8] = z;
                i8++;
                if (((i >> (6 - i9)) & 1) == 0 || i7 == 1) {
                    z = !z;
                    i9++;
                    i7 = 0;
                } else {
                    i7++;
                }
            }
            if (i5 < str.length() - 1) {
                zArr[i8] = false;
                i8++;
            }
            i4 = i8;
        }
        return zArr;
    }
}