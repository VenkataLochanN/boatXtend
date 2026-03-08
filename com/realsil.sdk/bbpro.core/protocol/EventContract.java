package com.realsil.sdk.bbpro.core.protocol;

import androidx.core.view.MotionEventCompat;
import com.realsil.sdk.bbpro.core.transportlayer.EventPacket;
import com.realsil.sdk.core.utility.DataConverter;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class EventContract {
    public static final short EVENT_ACK = 0;
    public static final int EVENT_DSP_IDLE = 513;
    public static final int EVENT_DSP_REPORT_AUDIO_EQ = 516;
    public static final int EVENT_DSP_REPORT_AUDIO_SETTING_EQ_IDX = 517;
    public static final int EVENT_DSP_REPORT_PARAM = 515;
    public static final short EVENT_INFO_REQ = 17;
    public static final int EVENT_LE_ADDR = 260;
    public static final int EVENT_REPORT_NAME = 24;
    public static final int EVENT_REPORT_PROMPT_LANGUAGE = 23;
    public static final int EVENT_REPORT_STATUS = 25;

    public static class AudioEqSettingIndex extends EventPacket {
        public short currentIndex;
        public short supportedIndex;

        public static AudioEqSettingIndex builder(byte[] bArr) {
            AudioEqSettingIndex audioEqSettingIndex = new AudioEqSettingIndex();
            if (audioEqSettingIndex.parse(bArr)) {
                return audioEqSettingIndex;
            }
            return null;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket, com.realsil.sdk.core.protocol.BasePacket
        public boolean parse(byte[] bArr) {
            if (!super.parse(bArr)) {
                return false;
            }
            if (this.ta > 1) {
                byte[] bArr2 = this.ua;
                this.currentIndex = (short) (((bArr2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr2[0] & UByte.MAX_VALUE));
            }
            if (this.ta > 1) {
                byte[] bArr3 = this.ua;
                this.supportedIndex = (short) (((bArr3[3] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr3[2] & UByte.MAX_VALUE));
            }
            return true;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public String toString() {
            return String.format("currentIndex=0x%04X", Short.valueOf(this.currentIndex)) + String.format("supportedIndex=0x%04X", Short.valueOf(this.supportedIndex));
        }
    }

    public interface Motor {
        public static final short EVENT_REPORT_MOTOR_MODE_PARAMETERS = 2818;
        public static final short EVENT_REPORT_MOTOR_STATUS = 2816;
        public static final short EVENT_REPORT_MOTOR_VIBRATION_STATUS = 2817;
    }

    public interface Ota {
        public static final short EVENT_OTA_BUFFER_CHECK = 1542;
        public static final short EVENT_OTA_BUFFER_CHECK_ENABLE = 1541;
        public static final short EVENT_OTA_GET_DEVICE_INFO = 1536;
        public static final short EVENT_OTA_GET_IMAGE_INFO = 1537;
        public static final short EVENT_OTA_GET_OTHER_INFO = 1545;
        public static final short EVENT_OTA_IMAGE_INFO = 1543;
        public static final short EVENT_OTA_PACKET = 1539;
        public static final short EVENT_OTA_RWS_INFO = 1544;
        public static final short EVENT_OTA_START = 1538;
        public static final short EVENT_OTA_VALID = 1540;
    }

    public static class ReportName extends EventPacket {
        public byte[] name;
        public byte type;
        public int va;

        public static ReportName builder(byte[] bArr) {
            ReportName reportName = new ReportName();
            if (reportName.parse(bArr)) {
                return reportName;
            }
            return null;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket, com.realsil.sdk.core.protocol.BasePacket
        public boolean parse(byte[] bArr) {
            if (!super.parse(bArr)) {
                return false;
            }
            if (this.ta > 0) {
                this.type = this.ua[0];
            }
            if (this.ta > 1) {
                this.va = this.ua[1] & UByte.MAX_VALUE;
            }
            int i = this.ta;
            int i2 = this.va;
            if (i >= i2 + 2) {
                this.name = new byte[i2];
                System.arraycopy(this.ua, 2, this.name, 0, i2);
            }
            return true;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("type=" + ((int) this.type));
            sb.append(",name=" + DataConverter.bytes2HexWithSeparate(this.name));
            return sb.toString();
        }
    }

    public static class ReportPromptLan extends EventPacket {
        public byte wa;
        public byte xa;

        public static ReportPromptLan builder(byte[] bArr) {
            ReportPromptLan reportPromptLan = new ReportPromptLan();
            if (reportPromptLan.parse(bArr)) {
                return reportPromptLan;
            }
            return null;
        }

        public byte getCurrentLanuage() {
            return this.wa;
        }

        public byte getSupportedLanguage() {
            return this.xa;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket, com.realsil.sdk.core.protocol.BasePacket
        public boolean parse(byte[] bArr) {
            if (!super.parse(bArr)) {
                return false;
            }
            if (this.ta > 0) {
                this.wa = this.ua[0];
            }
            if (this.ta > 1) {
                this.xa = this.ua[1];
            }
            return true;
        }

        @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("currentLanuage=" + ((int) this.wa));
            sb.append(",supportedLanguage=" + ((int) this.xa));
            return sb.toString();
        }
    }

    public interface ToneAndTalk {
        public static final short EVENT_TONE_AND_TALK_FAVORITE_MFB = 3072;
        public static final short EVENT_TONE_AND_TALK_READING_MSG = 3076;
        public static final short EVENT_TONE_AND_TALK_REPORT_MFB_SETTING = 3077;
        public static final short EVENT_TONE_AND_TALK_VOICE_MEMO = 3075;
        public static final short EVENT_TONE_AND_TALK_VOL_DOWN = 3074;
        public static final short EVENT_TONE_AND_TALK_VOL_UP = 3073;
    }
}