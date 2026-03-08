package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.VoiceData;
import com.ido.ble.protocol.model.VoiceOpusSectionData;
import com.ido.ble.protocol.model.VoicePcmSectionData;
import com.ido.ble.protocol.model.VoiceTranState;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class x {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        String strD;
        VoiceCallBack.VoiceControlType voiceControlType;
        if (i == 7500) {
            d(bArr, i2);
            return;
        }
        if (i == 7503) {
            e(bArr, i2);
            return;
        }
        if (i == 7504) {
            b(bArr, i2);
            return;
        }
        if (i == 5028) {
            strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle voice control alarm JsonString] " + strD);
            voiceControlType = VoiceCallBack.VoiceControlType.VOICE_CONTROL_ALARM;
        } else if (i == 7602) {
            strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle voice control stopwatch JsonString] " + strD);
            voiceControlType = VoiceCallBack.VoiceControlType.VOICE_CONTROL_STOPWATCH;
        } else if (i == 5029) {
            strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle voice control reminder JsonString] " + strD);
            voiceControlType = VoiceCallBack.VoiceControlType.VOICE_CONTROL_REMINDER;
        } else {
            if (i == 7508) {
                c(bArr, i2);
                return;
            }
            if (i == 5025) {
                strD = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle set voice text reply info JsonString] " + strD);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_TO_TEXT;
            } else if (i == 7506) {
                strD = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle app start voice JsonString] " + strD);
                voiceControlType = VoiceCallBack.VoiceControlType.APP_START_VOICE_RECOGNIZE;
            } else if (i == 7509) {
                strD = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle app stop voice JsonString] " + strD);
                voiceControlType = VoiceCallBack.VoiceControlType.APP_STOP_VOICE_RECOGNIZE;
            } else if (i != 7632) {
                if (i == 7623) {
                    a(bArr, i2);
                    return;
                }
                return;
            } else {
                strD = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handle set notify state JsonString] " + strD);
                voiceControlType = VoiceCallBack.VoiceControlType.VOICE_NOTIFY_STATE;
            }
        }
        a(strD, voiceControlType);
    }

    private static void a(String str, VoiceCallBack.VoiceControlType voiceControlType) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("is_success");
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.k + e2.getMessage());
            iOptInt = 0;
        }
        VoiceCallBack.onControlResult(voiceControlType, iOptInt == 1);
    }

    private static void a(byte[] bArr, int i) {
        int iOptInt = -1;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            String strD = com.ido.ble.common.c.d(bArr);
            if (TextUtils.isEmpty(strD)) {
                LogTool.b(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleDefaultLang] json is null");
                VoiceCallBack.onGetDefaultLang(-1);
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleDefaultLang] json is" + strD);
            try {
                iOptInt = new JSONObject(strD).optInt("alexa_default_language");
            } catch (JSONException e2) {
                LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.k + e2.getMessage());
            }
        }
        VoiceCallBack.onGetDefaultLang(iOptInt);
    }

    static boolean a(int i) {
        if (i == 7503 || i == 7504 || i == 7508 || i == 7509 || i == 5025 || i == 5028 || i == 7500 || i == 7506 || i == 7623) {
            return true;
        }
        switch (i) {
            case 7601:
            case 7602:
            case com.veryfit.multi.nativeprotocol.b.G4 /* 7603 */:
            case com.veryfit.multi.nativeprotocol.b.H4 /* 7604 */:
            case com.veryfit.multi.nativeprotocol.b.I4 /* 7605 */:
            case com.veryfit.multi.nativeprotocol.b.J4 /* 7606 */:
            case com.veryfit.multi.nativeprotocol.b.K4 /* 7607 */:
            case com.veryfit.multi.nativeprotocol.b.L4 /* 7608 */:
            case com.veryfit.multi.nativeprotocol.b.M4 /* 7609 */:
            case com.veryfit.multi.nativeprotocol.b.N4 /* 7610 */:
            case com.veryfit.multi.nativeprotocol.b.O4 /* 7611 */:
            case com.veryfit.multi.nativeprotocol.b.P4 /* 7612 */:
            case com.veryfit.multi.nativeprotocol.b.Q4 /* 7613 */:
            case com.veryfit.multi.nativeprotocol.b.R4 /* 7614 */:
            case com.veryfit.multi.nativeprotocol.b.S4 /* 7615 */:
            case com.veryfit.multi.nativeprotocol.b.T4 /* 7616 */:
            case com.veryfit.multi.nativeprotocol.b.U4 /* 7617 */:
            case com.veryfit.multi.nativeprotocol.b.V4 /* 7618 */:
            case com.veryfit.multi.nativeprotocol.b.W4 /* 7619 */:
                return true;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.Y4 /* 7630 */:
                    case com.veryfit.multi.nativeprotocol.b.Z4 /* 7631 */:
                    case com.veryfit.multi.nativeprotocol.b.a5 /* 7632 */:
                        return true;
                    default:
                        return false;
                }
        }
    }

    private static void b(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onGetVoiceOpusSectionData(null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleOpusSectionVoiceData] json is null");
            VoiceCallBack.onGetVoiceOpusSectionData(null);
            return;
        }
        VoiceOpusSectionData voiceOpusSectionData = (VoiceOpusSectionData) new Gson().fromJson(strD, VoiceOpusSectionData.class);
        if (voiceOpusSectionData != null && voiceOpusSectionData.opus_voice_data != null) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleOpusSectionVoiceData] json is, size= " + voiceOpusSectionData.encode_data_len + ", byte length =" + voiceOpusSectionData.opus_voice_data.length);
        }
        VoiceCallBack.onGetVoiceOpusSectionData(voiceOpusSectionData);
    }

    private static void c(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onGetVoicePcmSectionData(null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handlePcmSectionVoiceData] json is null");
            VoiceCallBack.onGetVoicePcmSectionData(null);
            return;
        }
        VoicePcmSectionData voicePcmSectionData = (VoicePcmSectionData) new Gson().fromJson(strD, VoicePcmSectionData.class);
        if (voicePcmSectionData != null && voicePcmSectionData.pcm_voice_data != null) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handlePcmSectionVoiceData] json is, size= " + voicePcmSectionData.pcm_data_len + ", byte length =" + voicePcmSectionData.pcm_voice_data.length);
        }
        VoiceCallBack.onGetVoicePcmSectionData(voicePcmSectionData);
    }

    private static void d(byte[] bArr, int i) {
        byte[] bArr2;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onGetVoiceData(null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleVoiceData] json is null");
            VoiceCallBack.onGetVoiceData(null);
            return;
        }
        VoiceData voiceData = (VoiceData) new Gson().fromJson(strD, VoiceData.class);
        if (voiceData != null && (bArr2 = voiceData.voiceFile) != null && bArr2.length > 0) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleVoiceData] json is, size= " + voiceData.size + ", byte length =" + voiceData.voiceFile.length);
        }
        VoiceCallBack.onGetVoiceData(voiceData);
    }

    private static void e(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            VoiceCallBack.onVoiceTranState(null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleVoiceTranState] json is null");
            VoiceCallBack.onVoiceTranState(null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "IDO_VOICE[handleVoiceTranState] json is" + strD);
        VoiceCallBack.onVoiceTranState((VoiceTranState) new Gson().fromJson(strD, VoiceTranState.class));
    }
}