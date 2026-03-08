package com.ido.alexa.callbacks;

import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.protocol.model.VoiceData;
import com.ido.ble.protocol.model.VoiceOpusSectionData;
import com.ido.ble.protocol.model.VoicePcmSectionData;
import com.ido.ble.protocol.model.VoiceTranState;

/* JADX INFO: loaded from: classes2.dex */
public class BaseVoiceCallBack implements VoiceCallBack.ICallBack {
    @Override // com.ido.ble.callback.VoiceCallBack.ICallBack
    public void onControlResult(VoiceCallBack.VoiceControlType voiceControlType, boolean z) {
    }

    @Override // com.ido.ble.callback.VoiceCallBack.ICallBack
    public void onGetDefaultLang(int i) {
    }

    @Override // com.ido.ble.callback.VoiceCallBack.ICallBack
    public void onGetOpusSectionData(VoiceOpusSectionData voiceOpusSectionData) {
    }

    @Override // com.ido.ble.callback.VoiceCallBack.ICallBack
    public void onGetPcmSectionData(VoicePcmSectionData voicePcmSectionData) {
    }

    @Override // com.ido.ble.callback.VoiceCallBack.ICallBack
    public void onGetVoiceData(VoiceData voiceData) {
    }

    @Override // com.ido.ble.callback.VoiceCallBack.ICallBack
    public void onVoiceTranState(VoiceTranState voiceTranState) {
    }
}