package com.ido.life.appachcodec;

import com.ido.life.appachcodec.BaseNCodec;
import com.realsil.sdk.bbpro.core.protocol.params.Mmi;
import com.realsil.sdk.bbpro.core.protocol.params.Parameters;
import com.realsil.sdk.dfu.DfuConstants;
import java.math.BigInteger;
import java.util.Objects;
import org.apache.commons.fileupload.MultipartStream;

/* JADX INFO: loaded from: classes2.dex */
public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int MASK_2BITS = 3;
    private static final int MASK_4BITS = 15;
    private static final int MASK_6BITS = 63;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 117, 118, 119, 120, 121, 122, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, Mmi.AU_MMI_AUDIO_PASS_THROUGH, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, Mmi.AU_MMI_RWS_SWITCH_CHANNEL, 117, 118, 119, 120, 121, 122, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2, 52, 53, 54, 55, 56, 57, MultipartStream.DASH, 95};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, DfuConstants.BANK_INFO_NOT_SUPPORTED, 16, 17, Parameters.RWS_CHANNEL_0, 19, Mmi.AU_MMI_UPDATE_INDICATOR, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, Parameters.RWS_CHANNEL_1, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, MultipartStream.DASH, 46, 47, Mmi.AU_MMI_VOL_UP, Mmi.AU_MMI_VOL_DOWN, 50, Parameters.RWS_CHANNEL_2};

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr != null) {
            if (containsAlphabetOrPad(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            }
            if (i > 0) {
                this.encodeSize = bArr.length + 4;
                this.lineSeparator = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.lineSeparator, 0, bArr.length);
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
            }
        } else {
            this.encodeSize = 4;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    @Override // com.ido.life.appachcodec.BaseNCodec
    void encode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (i2 >= 0) {
            int i3 = i;
            int i4 = 0;
            while (i4 < i2) {
                byte[] bArrEnsureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + 1) % 3;
                int i5 = i3 + 1;
                int i6 = bArr[i3];
                if (i6 < 0) {
                    i6 += 256;
                }
                context.ibitWorkArea = (context.ibitWorkArea << 8) + i6;
                if (context.modulus == 0) {
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    bArrEnsureBufferSize[i7] = this.encodeTable[(context.ibitWorkArea >> 18) & 63];
                    int i8 = context.pos;
                    context.pos = i8 + 1;
                    bArrEnsureBufferSize[i8] = this.encodeTable[(context.ibitWorkArea >> 12) & 63];
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    bArrEnsureBufferSize[i9] = this.encodeTable[(context.ibitWorkArea >> 6) & 63];
                    int i10 = context.pos;
                    context.pos = i10 + 1;
                    bArrEnsureBufferSize[i10] = this.encodeTable[context.ibitWorkArea & 63];
                    context.currentLinePos += 4;
                    if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                        System.arraycopy(this.lineSeparator, 0, bArrEnsureBufferSize, context.pos, this.lineSeparator.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
                i4++;
                i3 = i5;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] bArrEnsureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i11 = context.pos;
        int i12 = context.modulus;
        if (i12 != 0) {
            if (i12 == 1) {
                int i13 = context.pos;
                context.pos = i13 + 1;
                bArrEnsureBufferSize2[i13] = this.encodeTable[(context.ibitWorkArea >> 2) & 63];
                int i14 = context.pos;
                context.pos = i14 + 1;
                bArrEnsureBufferSize2[i14] = this.encodeTable[(context.ibitWorkArea << 4) & 63];
                if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                    int i15 = context.pos;
                    context.pos = i15 + 1;
                    bArrEnsureBufferSize2[i15] = this.pad;
                    int i16 = context.pos;
                    context.pos = i16 + 1;
                    bArrEnsureBufferSize2[i16] = this.pad;
                }
            } else if (i12 == 2) {
                int i17 = context.pos;
                context.pos = i17 + 1;
                bArrEnsureBufferSize2[i17] = this.encodeTable[(context.ibitWorkArea >> 10) & 63];
                int i18 = context.pos;
                context.pos = i18 + 1;
                bArrEnsureBufferSize2[i18] = this.encodeTable[(context.ibitWorkArea >> 4) & 63];
                int i19 = context.pos;
                context.pos = i19 + 1;
                bArrEnsureBufferSize2[i19] = this.encodeTable[(context.ibitWorkArea << 2) & 63];
                if (this.encodeTable == STANDARD_ENCODE_TABLE) {
                    int i20 = context.pos;
                    context.pos = i20 + 1;
                    bArrEnsureBufferSize2[i20] = this.pad;
                }
            } else {
                throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
        context.currentLinePos += context.pos - i11;
        if (this.lineLength <= 0 || context.currentLinePos <= 0) {
            return;
        }
        System.arraycopy(this.lineSeparator, 0, bArrEnsureBufferSize2, context.pos, this.lineSeparator.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // com.ido.life.appachcodec.BaseNCodec
    void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b2;
        if (context.eof) {
            return;
        }
        if (i2 < 0) {
            context.eof = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            byte[] bArrEnsureBufferSize = ensureBufferSize(this.decodeSize, context);
            int i4 = i + 1;
            byte b3 = bArr[i];
            if (b3 == this.pad) {
                context.eof = true;
                break;
            }
            if (b3 >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b3 < bArr2.length && (b2 = bArr2[b3]) >= 0) {
                    context.modulus = (context.modulus + 1) % 4;
                    context.ibitWorkArea = (context.ibitWorkArea << 6) + b2;
                    if (context.modulus == 0) {
                        int i5 = context.pos;
                        context.pos = i5 + 1;
                        bArrEnsureBufferSize[i5] = (byte) ((context.ibitWorkArea >> 16) & 255);
                        int i6 = context.pos;
                        context.pos = i6 + 1;
                        bArrEnsureBufferSize[i6] = (byte) ((context.ibitWorkArea >> 8) & 255);
                        int i7 = context.pos;
                        context.pos = i7 + 1;
                        bArrEnsureBufferSize[i7] = (byte) (context.ibitWorkArea & 255);
                    }
                }
            }
            i3++;
            i = i4;
        }
        if (!context.eof || context.modulus == 0) {
            return;
        }
        byte[] bArrEnsureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        int i8 = context.modulus;
        if (i8 != 1) {
            if (i8 == 2) {
                validateCharacter(15, context);
                context.ibitWorkArea >>= 4;
                int i9 = context.pos;
                context.pos = i9 + 1;
                bArrEnsureBufferSize2[i9] = (byte) (context.ibitWorkArea & 255);
                return;
            }
            if (i8 == 3) {
                validateCharacter(3, context);
                context.ibitWorkArea >>= 2;
                int i10 = context.pos;
                context.pos = i10 + 1;
                bArrEnsureBufferSize2[i10] = (byte) ((context.ibitWorkArea >> 8) & 255);
                int i11 = context.pos;
                context.pos = i11 + 1;
                bArrEnsureBufferSize2[i11] = (byte) (context.ibitWorkArea & 255);
                return;
            }
            throw new IllegalStateException("Impossible modulus " + context.modulus);
        }
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b2) {
        if (b2 != 61) {
            if (b2 >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b2 >= bArr.length || bArr[b2] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtils.getBytesUtf8(str));
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isBase64(bArr[i]) && !isWhiteSpace(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtils.newStringUsAscii(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtils.newStringUsAscii(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength > i) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
        }
        return base64.encode(bArr);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "bigInteger");
        return encodeBase64(toIntegerBytes(bigInteger), false);
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int iBitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == iBitLength / 8) {
            return byteArray;
        }
        int i = 0;
        int length = byteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            length--;
            i = 1;
        }
        int i2 = iBitLength / 8;
        int i3 = i2 - length;
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, i, bArr, i3, length);
        return bArr;
    }

    @Override // com.ido.life.appachcodec.BaseNCodec
    protected boolean isInAlphabet(byte b2) {
        if (b2 >= 0) {
            byte[] bArr = this.decodeTable;
            if (b2 < bArr.length && bArr[b2] != -1) {
                return true;
            }
        }
        return false;
    }

    private static void validateCharacter(int i, BaseNCodec.Context context) {
        if ((i & context.ibitWorkArea) != 0) {
            throw new IllegalArgumentException("Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible value. Expected the discarded bits to be zero.");
        }
    }
}