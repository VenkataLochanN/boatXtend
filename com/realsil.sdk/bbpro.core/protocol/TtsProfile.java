package com.realsil.sdk.bbpro.core.protocol;

import androidx.core.internal.view.SupportMenu;
import com.bumptech.glide.load.Key;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.protocol.BasePacket;
import java.io.UnsupportedEncodingException;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class TtsProfile {
    public static final short CMD_TTS = 11;
    public static final int EVENT_CALLER_ID = 5;
    public static final int EVENT_REQ_TTS_ACTION = 14;
    public static final int EVENT_RESUME_TTS = 15;
    public static final byte PARAM_TYPE_SEND_CONTINUE_PACKET = 2;
    public static final byte PARAM_TYPE_SEND_END_PACKET = 3;
    public static final byte PARAM_TYPE_SEND_SINGLE_PACKET = 0;
    public static final byte PARAM_TYPE_SEND_START_PACKET = 1;
    public static final byte PARAM_TYPE_START_1 = 0;
    public static final byte PARAM_TYPE_START_2 = 1;
    public static final int TTS_FLOW_MECHANISM = 0;
    public static final int TTS_FLOW_MECHANISM_2 = 1;

    public static class CallerId extends BasePacket {
        public static final byte TYPE_NAME = 1;
        public static final byte TYPE_PHONE_NUMBER = 0;
        public byte mType;
        public byte pa;
        public String qa;

        public static CallerId builderPacket(byte[] bArr) {
            CallerId callerId = new CallerId();
            if (callerId.parse(bArr)) {
                return callerId;
            }
            ZLogger.d("invalid packet");
            return null;
        }

        public String getCallerId() {
            return this.qa;
        }

        public byte getType() {
            return this.mType;
        }

        @Override // com.realsil.sdk.core.protocol.BasePacket
        public boolean parse(byte[] bArr) {
            if (bArr != null && bArr.length >= 3) {
                this.mType = bArr[1];
                byte b2 = this.mType;
                if (b2 != 0 && b2 != 1) {
                    ZLogger.w("invalid type=" + ((int) this.mType));
                    return false;
                }
                this.pa = bArr[2];
                int length = bArr.length;
                byte b3 = this.pa;
                if (length != b3 + 3) {
                    return false;
                }
                try {
                    this.qa = this.mType == 0 ? new String(bArr, 3, b3, "ascii") : new String(bArr, 3, b3, Key.STRING_CHARSET_NAME);
                    return true;
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            return false;
        }
    }

    public class CmdTtsType {

        public class V1 {
            public static final byte TYPE_SEND_TTS_ENCODE_DATA = 1;
            public static final byte TYPE_START_TTS = 0;
            public static final byte TYPE_TOOL_CONNECTED = 2;

            public V1() {
            }
        }

        public class V2 {
            public static final byte TYPE_TTS_SESSION_ABORT = 3;
            public static final byte TYPE_TTS_SESSION_CLOSE = 4;
            public static final byte TYPE_TTS_SESSION_OPEN = 0;
            public static final byte TYPE_TTS_SESSION_PAUSE = 1;
            public static final byte TYPE_TTS_SESSION_RESUME = 2;
            public static final byte TYPE_TTS_SESSION_SEND_CONTINUE_FRAME = 7;
            public static final byte TYPE_TTS_SESSION_SEND_END_FRAME = 8;
            public static final byte TYPE_TTS_SESSION_SEND_SINGLE_FRAME = 5;
            public static final byte TYPE_TTS_SESSION_SEND_START_FRAME = 6;

            public V2() {
            }
        }

        public CmdTtsType() {
        }
    }

    public static class ReqTtsData {
        public static final byte TYPE_ABORT_SESSION = 3;
        public static final byte TYPE_ABORT_TTS = 1;
        public static final byte TYPE_CLOSE_SESSION = 4;
        public static final byte TYPE_OPEN_SESSION = 0;
        public static final byte TYPE_PAUSE_SESSION = 1;
        public static final byte TYPE_RESUME_SESSION = 2;
        public static final byte TYPE_SEND_ENCODED_DATA = 5;
        public static final byte TYPE_SEND_TTS = 0;
    }

    public static class Resume {
        public static final int EVENT_ID = 15;

        public static boolean parse(byte[] bArr) {
            if (bArr.length < 2) {
                return false;
            }
            return (((bArr[0] & UByte.MAX_VALUE) | (bArr[1] << 8)) & SupportMenu.USER_MASK) == 15;
        }
    }

    public static byte[] build(int i, int i2, int i3, byte[] bArr) {
        byte[] bArr2 = new byte[bArr != null ? bArr.length + 4 : 4];
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) (i2 & 255);
        bArr2[2] = (byte) (i3 & 255);
        bArr2[3] = (byte) ((i3 >> 8) & 255);
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        }
        return bArr2;
    }

    public static byte[] buildPacket1(byte b2, byte[] bArr, int i) {
        byte[] bArr2 = new byte[i + 4];
        bArr2[0] = 11;
        bArr2[1] = 0;
        bArr2[2] = 1;
        bArr2[3] = b2;
        System.arraycopy(bArr, 0, bArr2, 4, i);
        return bArr2;
    }

    public static byte[] buildPacket2(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i + 2];
        bArr2[0] = 11;
        bArr2[1] = 0;
        System.arraycopy(bArr, 0, bArr2, 2, i);
        return bArr2;
    }
}