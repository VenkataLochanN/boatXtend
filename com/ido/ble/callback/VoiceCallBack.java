package com.ido.ble.callback;

import com.ido.ble.protocol.model.VoiceData;
import com.ido.ble.protocol.model.VoiceOpusSectionData;
import com.ido.ble.protocol.model.VoicePcmSectionData;
import com.ido.ble.protocol.model.VoiceTranState;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceCallBack {

    public interface ICallBack {
        void onControlResult(VoiceControlType voiceControlType, boolean z);

        void onGetDefaultLang(int i);

        void onGetOpusSectionData(VoiceOpusSectionData voiceOpusSectionData);

        void onGetPcmSectionData(VoicePcmSectionData voicePcmSectionData);

        void onGetVoiceData(VoiceData voiceData);

        void onVoiceTranState(VoiceTranState voiceTranState);
    }

    public enum VoiceControlType {
        VOICE_TO_TEXT,
        VOICE_CONTROL_ALARM,
        VOICE_CONTROL_STOPWATCH,
        VOICE_CONTROL_REMINDER,
        APP_START_VOICE_RECOGNIZE,
        APP_STOP_VOICE_RECOGNIZE,
        VOICE_NOTIFY_STATE
    }

    public static void onControlResult(final VoiceControlType voiceControlType, final boolean z) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().J().iterator();
                while (it.hasNext()) {
                    it.next().onControlResult(voiceControlType, z);
                }
            }
        });
    }

    public static void onGetDefaultLang(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().J().iterator();
                while (it.hasNext()) {
                    it.next().onGetDefaultLang(i);
                }
            }
        });
    }

    public static void onGetVoiceData(final VoiceData voiceData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().J().iterator();
                while (it.hasNext()) {
                    it.next().onGetVoiceData(voiceData);
                }
            }
        });
    }

    public static void onGetVoiceOpusSectionData(final VoiceOpusSectionData voiceOpusSectionData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().J().iterator();
                while (it.hasNext()) {
                    it.next().onGetOpusSectionData(voiceOpusSectionData);
                }
            }
        });
    }

    public static void onGetVoicePcmSectionData(final VoicePcmSectionData voicePcmSectionData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().J().iterator();
                while (it.hasNext()) {
                    it.next().onGetPcmSectionData(voicePcmSectionData);
                }
            }
        });
    }

    public static void onVoiceTranState(final VoiceTranState voiceTranState) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.VoiceCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().J().iterator();
                while (it.hasNext()) {
                    it.next().onVoiceTranState(voiceTranState);
                }
            }
        });
    }
}