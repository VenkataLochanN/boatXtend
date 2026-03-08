package com.realsil.sdk.bbpro.core.transportlayer;

import androidx.core.internal.view.SupportMenu;
import com.realsil.sdk.core.protocol.BasePacket;
import com.realsil.sdk.core.utility.DataConverter;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class EventPacket extends BasePacket {
    public int sa;
    public int ta = 0;
    public byte[] ua;

    public static EventPacket builderPacket(byte[] bArr) {
        EventPacket eventPacket = new EventPacket();
        if (eventPacket.parse(bArr)) {
            return eventPacket;
        }
        return null;
    }

    public int getEventId() {
        return this.sa;
    }

    public byte[] getEventParams() {
        return this.ua;
    }

    @Override // com.realsil.sdk.core.protocol.BasePacket
    public boolean parse(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return false;
        }
        this.sa = ((bArr[1] << 8) | (bArr[0] & UByte.MAX_VALUE)) & SupportMenu.USER_MASK;
        this.ta = bArr.length - 2;
        int i = this.ta;
        if (i > 0) {
            this.ua = new byte[i];
            System.arraycopy(bArr, 2, this.ua, 0, i);
        } else {
            this.ua = null;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mEventId=" + this.sa);
        sb.append("params: " + DataConverter.bytes2Hex(this.ua));
        return sb.toString();
    }
}