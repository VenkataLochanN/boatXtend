package com.alibaba.fastjson.asm;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public class ClassReader {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f28b;
    public final int header;
    private final int[] items;
    private final int maxStringLength;
    private final String[] strings;

    public ClassReader(InputStream inputStream) throws IOException {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = inputStream.read(bArr);
            i = 0;
            if (i2 == -1) {
                break;
            } else if (i2 > 0) {
                byteArrayOutputStream.write(bArr, 0, i2);
            }
        }
        inputStream.close();
        this.f28b = byteArrayOutputStream.toByteArray();
        this.items = new int[readUnsignedShort(8)];
        int length = this.items.length;
        this.strings = new String[length];
        int i3 = 10;
        int i4 = 1;
        while (i4 < length) {
            int i5 = i3 + 1;
            this.items[i4] = i5;
            byte b2 = this.f28b[i3];
            int unsignedShort = 5;
            if (b2 == 1) {
                unsignedShort = readUnsignedShort(i5) + 3;
                if (unsignedShort > i) {
                    i = unsignedShort;
                }
            } else if (b2 == 15) {
                unsignedShort = 4;
            } else if (b2 != 18 && b2 != 3 && b2 != 4) {
                if (b2 != 5 && b2 != 6) {
                    switch (b2) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            break;
                        default:
                            unsignedShort = 3;
                            break;
                    }
                } else {
                    unsignedShort = 9;
                    i4++;
                }
            }
            i3 += unsignedShort;
            i4++;
        }
        this.maxStringLength = i;
        this.header = i3;
    }

    public void accept(TypeCollector typeCollector) {
        char[] cArr = new char[this.maxStringLength];
        int i = this.header;
        int i2 = this.items[readUnsignedShort(i + 4)];
        int unsignedShort = readUnsignedShort(i + 6);
        int i3 = i + 8;
        for (int i4 = 0; i4 < unsignedShort; i4++) {
            i3 += 2;
        }
        int i5 = i3 + 2;
        int i6 = i5;
        for (int unsignedShort2 = readUnsignedShort(i3); unsignedShort2 > 0; unsignedShort2--) {
            i6 += 8;
            for (int unsignedShort3 = readUnsignedShort(i6 + 6); unsignedShort3 > 0; unsignedShort3--) {
                i6 += readInt(i6 + 2) + 6;
            }
        }
        int i7 = i6 + 2;
        for (int unsignedShort4 = readUnsignedShort(i6); unsignedShort4 > 0; unsignedShort4--) {
            i7 += 8;
            for (int unsignedShort5 = readUnsignedShort(i7 + 6); unsignedShort5 > 0; unsignedShort5--) {
                i7 += readInt(i7 + 2) + 6;
            }
        }
        int i8 = i7 + 2;
        for (int unsignedShort6 = readUnsignedShort(i7); unsignedShort6 > 0; unsignedShort6--) {
            i8 += readInt(i8 + 2) + 6;
        }
        for (int unsignedShort7 = readUnsignedShort(i3); unsignedShort7 > 0; unsignedShort7--) {
            i5 += 8;
            for (int unsignedShort8 = readUnsignedShort(i5 + 6); unsignedShort8 > 0; unsignedShort8--) {
                i5 += readInt(i5 + 2) + 6;
            }
        }
        int method = i5 + 2;
        for (int unsignedShort9 = readUnsignedShort(i5); unsignedShort9 > 0; unsignedShort9--) {
            method = readMethod(typeCollector, cArr, method);
        }
    }

    private int readMethod(TypeCollector typeCollector, char[] cArr, int i) {
        int unsignedShort = readUnsignedShort(i);
        String utf8 = readUTF8(i + 2, cArr);
        String utf82 = readUTF8(i + 4, cArr);
        int i2 = i + 8;
        int i3 = 0;
        int i4 = 0;
        for (int unsignedShort2 = readUnsignedShort(i + 6); unsignedShort2 > 0; unsignedShort2--) {
            String utf83 = readUTF8(i2, cArr);
            int i5 = readInt(i2 + 2);
            int i6 = i2 + 6;
            if (utf83.equals(DatabaseHelper.authorizationCode)) {
                i4 = i6;
            }
            i2 = i6 + i5;
        }
        MethodCollector methodCollectorVisitMethod = typeCollector.visitMethod(unsignedShort, utf8, utf82);
        if (methodCollectorVisitMethod != null && i4 != 0) {
            int i7 = i4 + 8 + readInt(i4 + 4);
            int i8 = i7 + 2;
            for (int unsignedShort3 = readUnsignedShort(i7); unsignedShort3 > 0; unsignedShort3--) {
                i8 += 8;
            }
            int i9 = i8 + 2;
            int i10 = 0;
            for (int unsignedShort4 = readUnsignedShort(i8); unsignedShort4 > 0; unsignedShort4--) {
                String utf84 = readUTF8(i9, cArr);
                if (utf84.equals("LocalVariableTable")) {
                    i3 = i9 + 6;
                } else if (utf84.equals("LocalVariableTypeTable")) {
                    i10 = i9 + 6;
                }
                i9 += readInt(i9 + 2) + 6;
            }
            if (i3 != 0) {
                if (i10 != 0) {
                    int unsignedShort5 = readUnsignedShort(i10) * 3;
                    int i11 = i10 + 2;
                    int[] iArr = new int[unsignedShort5];
                    while (unsignedShort5 > 0) {
                        int i12 = unsignedShort5 - 1;
                        iArr[i12] = i11 + 6;
                        int i13 = i12 - 1;
                        iArr[i13] = readUnsignedShort(i11 + 8);
                        unsignedShort5 = i13 - 1;
                        iArr[unsignedShort5] = readUnsignedShort(i11);
                        i11 += 10;
                    }
                }
                int i14 = i3 + 2;
                for (int unsignedShort6 = readUnsignedShort(i3); unsignedShort6 > 0; unsignedShort6--) {
                    methodCollectorVisitMethod.visitLocalVariable(readUTF8(i14 + 4, cArr), readUnsignedShort(i14 + 8));
                    i14 += 10;
                }
            }
        }
        return i2;
    }

    private int readUnsignedShort(int i) {
        byte[] bArr = this.f28b;
        return (bArr[i + 1] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 8);
    }

    private int readInt(int i) {
        byte[] bArr = this.f28b;
        return (bArr[i + 3] & UByte.MAX_VALUE) | ((bArr[i] & UByte.MAX_VALUE) << 24) | ((bArr[i + 1] & UByte.MAX_VALUE) << 16) | ((bArr[i + 2] & UByte.MAX_VALUE) << 8);
    }

    private String readUTF8(int i, char[] cArr) {
        int unsignedShort = readUnsignedShort(i);
        String[] strArr = this.strings;
        String str = strArr[unsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.items[unsignedShort];
        String utf = readUTF(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[unsignedShort] = utf;
        return utf;
    }

    private String readUTF(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.f28b;
        int i5 = 0;
        char c2 = 0;
        char c3 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            if (c2 == 0) {
                int i7 = b2 & UByte.MAX_VALUE;
                if (i7 < 128) {
                    cArr[i5] = (char) i7;
                    i5++;
                } else if (i7 >= 224 || i7 <= 191) {
                    c3 = (char) (i7 & 15);
                    c2 = 2;
                } else {
                    i3 = i7 & 31;
                    c3 = (char) i3;
                    c2 = 1;
                }
            } else if (c2 == 1) {
                cArr[i5] = (char) ((b2 & 63) | (c3 << 6));
                i5++;
                c2 = 0;
            } else if (c2 == 2) {
                i3 = (b2 & 63) | (c3 << 6);
                c3 = (char) i3;
                c2 = 1;
            }
            i = i6;
        }
        return new String(cArr, 0, i5);
    }
}