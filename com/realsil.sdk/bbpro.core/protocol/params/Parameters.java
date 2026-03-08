package com.realsil.sdk.bbpro.core.protocol.params;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class Parameters {
    public static final byte DSP_STATUS_ACTION_A2DP_DECODE = 4;
    public static final byte EQ_SAMPLE_RATE_441K = 3;
    public static final byte EQ_SAMPLE_RATE_48K = 4;
    public static final byte MFB_SETTINGS_FAVORITE_CALL = 1;
    public static final byte MFB_SETTINGS_VOICE_DIAL = 0;
    public static final byte RWS_CHANNEL_0 = 18;
    public static final byte RWS_CHANNEL_1 = 33;
    public static final byte RWS_CHANNEL_2 = 51;
    public static final byte RWS_STATE_DISCONNECTED = 0;
    public static final byte RWS_STATE_PRIMARY_SPEAKER = 2;
    public static final byte STATUS_INDEX_AUDIO_PASS_THROUGH_STATUS = 3;
    public static final byte STATUS_INDEX_BATTERY_STATUS = 2;
    public static Map<Byte, String> STATUS_INDEX_MAPS = new HashMap();
    public static final byte STATUS_INDEX_RWS_CHANNEL = 1;
    public static final byte STATUS_INDEX_RWS_STATE = 0;

    public interface EQ_INDEX {
        public static final short BUILD_IN_EQ_1 = 16;
        public static final short BUILD_IN_EQ_2 = 32;
        public static final short BUILD_IN_EQ_3 = 64;
        public static final short BUILD_IN_EQ_4 = 128;
        public static final short BUILD_IN_EQ_5 = 256;
        public static final short CUSTOMER_EQ_1 = 2;
        public static final short CUSTOMER_EQ_2 = 4;
        public static final short CUSTOMER_EQ_3 = 8;
        public static final short EQ_OFF = 1;
        public static final short REALTIME_EQ = 512;
    }

    public interface SAMPLE_RATE {
        public static final int SR_16K = 1;
        public static final int SR_32K = 2;
        public static final int SR_44P1K = 3;
        public static final int SR_48K = 4;
        public static final int SR_88P2K = 5;
        public static final int SR_8K = 0;
        public static final int SR_96K = 6;
    }

    public interface SCENARIO {
        public static final int SCENARIO_A2DP = 0;
        public static final int SCENARIO_LINEIN = 2;
        public static final int SCENARIO_SCO = 1;
    }

    static {
        STATUS_INDEX_MAPS.put((byte) 0, "RWS_STATE");
        STATUS_INDEX_MAPS.put((byte) 1, "RWS_CHANNEL");
        STATUS_INDEX_MAPS.put((byte) 2, "BATTERY_STATUS");
        STATUS_INDEX_MAPS.put((byte) 3, "AUDIO_PASS_THROUGH_STATUS");
    }
}