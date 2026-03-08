package com.realsil.sdk.bbpro.core.protocol;

import com.realsil.sdk.bbpro.core.protocol.params.Parameters;

/* JADX INFO: loaded from: classes3.dex */
public class CommandContract {
    public static final short CMD_ACK = 0;
    public static final short CMD_DSP_CLEAR_AUDIO_EQ = 518;
    public static final short CMD_DSP_GET_AUDIO_EQ = 517;
    public static final short CMD_DSP_GET_AUDIO_EQ_SETTING_IDX = 519;
    public static final short CMD_DSP_GET_PARAM = 515;
    public static final short CMD_DSP_SET_AUDIO_EQ = 516;
    public static final short CMD_DSP_SET_AUDIO_EQ_SETTING_IDX = 520;
    public static final short CMD_GET_LANGUAGE = 21;
    public static final short CMD_GET_LE_ADDR = 261;
    public static final short CMD_GET_NAME = 23;
    public static final short CMD_GET_STATUS = 24;
    public static final short CMD_INFO_REQ = 12;
    public static final short CMD_MMI = 4;
    public static final short CMD_SET_CONFIGURATION = 18;
    public static final short CMD_SET_LANGUAGE = 22;
    public static final short CMD_TONE_GEN = 8;
    public static final int HEADER_LENGTH = 4;
    public static final byte PARAM_TYPE_CMD_SET_VERSION = 0;

    public interface Motor {
        public static final short CMD_MOTOR_CHECK_VIBRATION = 2822;
        public static final short CMD_MOTOR_GET_MODE_PARAMETER = 2823;
        public static final short CMD_MOTOR_GET_STATUS = 2819;
        public static final short CMD_MOTOR_SET_DISENABLE = 2817;
        public static final short CMD_MOTOR_SET_ENABLE = 2816;
        public static final short CMD_MOTOR_SET_MODE = 2820;
        public static final short CMD_MOTOR_STOP_VIBRATION = 2821;
        public static final short CMD_MOTOR_TOGGLE = 2818;
    }

    public interface Ota {
        public static final short CMD_OTA_ACTIVE_RESET = 1542;
        public static final short CMD_OTA_BUFFER_CHECK = 1544;
        public static final short CMD_OTA_BUFFER_CHECK_ENABLE = 1543;
        public static final short CMD_OTA_ENTER_OTA = 1550;
        public static final short CMD_OTA_GET_DEVICE_INFO = 1536;
        public static final short CMD_OTA_GET_IMAGE_INFO = 1537;
        public static final short CMD_OTA_GET_MAC = 1548;
        public static final short CMD_OTA_GET_OTHER_INFO = 1547;
        public static final short CMD_OTA_IMAGE_INFO = 1545;
        public static final short CMD_OTA_PACKET = 1539;
        public static final short CMD_OTA_RESET = 1541;
        public static final short CMD_OTA_RWS_INFO = 1546;
        public static final short CMD_OTA_SET_RECEIVE_IMAGE = 1549;
        public static final short CMD_OTA_START = 1538;
        public static final short CMD_OTA_VALID = 1540;
    }

    public interface ToneAndTalk {
        public static final short CMD_TONE_AND_TALK_GET_MFB_SETTING = 3073;
        public static final short CMD_TONE_AND_TALK_SET_MFB_SETTING = 3072;
    }

    public static byte[] buildPacket(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)};
    }

    public static byte[] buildPacket(short s, byte b2) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255), b2};
    }

    public static byte[] buildPacket(short s, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 2];
        bArr2[0] = (byte) (s & 255);
        bArr2[1] = (byte) ((s >> 8) & 255);
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 2, length);
        }
        return bArr2;
    }

    public static byte[] builderCmdMmiPacket(byte b2) {
        return new byte[]{4, 0, 0, b2};
    }

    public static byte[] builderDspSetAudioEQPacket(byte b2, byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 4];
        bArr2[0] = 4;
        bArr2[1] = 2;
        bArr2[2] = (byte) length;
        bArr2[3] = b2;
        System.arraycopy(bArr, 0, bArr2, 4, length);
        return bArr2;
    }

    public static byte[] prepareSetConfigurationPacket(byte b2, String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[length + 4];
        bArr[0] = Parameters.RWS_CHANNEL_0;
        bArr[1] = 0;
        bArr[2] = b2;
        bArr[3] = (byte) length;
        System.arraycopy(bytes, 0, bArr, 4, length);
        return bArr;
    }

    public static byte[] reqCmdInfo(byte b2) {
        return new byte[]{12, 0, b2};
    }
}