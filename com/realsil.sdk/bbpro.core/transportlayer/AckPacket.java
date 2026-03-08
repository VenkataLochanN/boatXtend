package com.realsil.sdk.bbpro.core.transportlayer;

import androidx.core.internal.view.SupportMenu;
import com.realsil.sdk.core.protocol.BasePacket;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class AckPacket extends BasePacket {
    public static final byte ACK_STATUS_BUSY = 4;
    public static final byte ACK_STATUS_COMPLETE = 0;
    public static final byte ACK_STATUS_DISALLOW = 1;
    public static final byte ACK_STATUS_PARAMETERS_ERROR = 3;
    public static final byte ACK_STATUS_PROCESS_FAIL = 5;
    public static final byte ACK_STATUS_UNKNOWN_COMMAND = 2;
    public byte mStatus;
    public int ra;

    public AckPacket() {
        this.ra = 0;
        this.mStatus = (byte) 0;
        this.ra = 0;
        this.mStatus = (byte) 0;
    }

    public static AckPacket builder(byte[] bArr) {
        AckPacket ackPacket = new AckPacket();
        if (ackPacket.parse(bArr)) {
            return ackPacket;
        }
        return null;
    }

    public static byte[] encode(int i, byte b2) {
        return new byte[]{0, 0, (byte) (i & 255), (byte) ((i >> 8) & 255), b2};
    }

    public byte getStatus() {
        return this.mStatus;
    }

    public int getToAckId() {
        return this.ra;
    }

    @Override // com.realsil.sdk.core.protocol.BasePacket
    public boolean parse(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        if (bArr.length >= 2) {
            this.ra = ((bArr[0] & UByte.MAX_VALUE) | (bArr[1] << 8)) & SupportMenu.USER_MASK;
            if (bArr.length >= 3) {
                this.mStatus = (byte) (bArr[2] & UByte.MAX_VALUE);
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\tmToAckId=0x%04x", Integer.valueOf(this.ra)));
        sb.append("\tmStatus=" + ((int) this.mStatus));
        return sb.toString();
    }
}