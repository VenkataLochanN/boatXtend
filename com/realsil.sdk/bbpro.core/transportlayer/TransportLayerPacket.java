package com.realsil.sdk.bbpro.core.transportlayer;

import androidx.core.internal.view.SupportMenu;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.protocol.BasePacket;
import com.realsil.sdk.core.utility.DataConverter;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class TransportLayerPacket extends BasePacket {
    public static final int HEADER_LENGTH = 4;
    public static final byte SYNC_WORD = -86;
    public byte[] mData;
    public byte ya = 0;
    public byte za = 0;
    public int mLength = 0;
    public byte[] Aa = null;
    public int Ba = -1;
    public byte[] Ca = null;

    public static TransportLayerPacket builderPacket(byte[] bArr) {
        TransportLayerPacket transportLayerPacket = new TransportLayerPacket();
        if (transportLayerPacket.parse(bArr)) {
            return transportLayerPacket;
        }
        return null;
    }

    public static byte[] encode(int i, short s, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 2];
        bArr2[0] = (byte) (s & 255);
        bArr2[1] = (byte) ((s >> 8) & 255);
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 2, length);
        }
        return encode(i, bArr2);
    }

    public static byte[] encode(int i, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 4];
        bArr2[0] = SYNC_WORD;
        bArr2[1] = (byte) i;
        bArr2[2] = (byte) (length & 255);
        bArr2[3] = (byte) ((length >> 8) & 255);
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 4, length);
        }
        return bArr2;
    }

    public int getOpcode() {
        return this.Ba;
    }

    public int getPacketLength() {
        return this.mLength + 4;
    }

    public byte[] getParameters() {
        return this.Ca;
    }

    public byte[] getPayload() {
        return this.Aa;
    }

    public byte getSeqNum() {
        return this.za;
    }

    @Override // com.realsil.sdk.core.protocol.BasePacket
    public boolean parse(byte[] bArr) {
        String str;
        if (bArr == null || bArr.length < 4) {
            str = "LT_LENGTH_ERROR";
        } else {
            this.mData = bArr;
            this.ya = bArr[0];
            this.za = bArr[1];
            this.mLength = ((bArr[3] << 8) | (bArr[2] & UByte.MAX_VALUE)) & SupportMenu.USER_MASK;
            if (this.ya != -86) {
                str = String.format("LT_SYNCWORD_ERROR: %s", DataConverter.bytes2Hex(bArr));
            } else {
                int i = this.mLength;
                if (i < 2) {
                    str = String.format("LT_PAYLOAD_OPCODE_LENGTH_ERROR: %s", DataConverter.bytes2Hex(bArr));
                } else {
                    this.Aa = new byte[i];
                    System.arraycopy(bArr, 4, this.Aa, 0, i);
                    byte[] bArr2 = this.Aa;
                    this.Ba = ((bArr2[0] & UByte.MAX_VALUE) | (bArr2[1] << 8)) & SupportMenu.USER_MASK;
                    int length = bArr.length;
                    int i2 = this.mLength;
                    if (length >= i2 + 4) {
                        int i3 = i2 - 2;
                        this.Ca = new byte[i3];
                        System.arraycopy(bArr, 6, this.Ca, 0, i3);
                        return true;
                    }
                    str = String.format("LT_PAYLOAD_LENGTH_ERROR: %s", DataConverter.bytes2Hex(bArr));
                }
            }
        }
        ZLogger.w(true, str);
        return false;
    }
}