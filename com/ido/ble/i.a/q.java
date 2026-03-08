package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.VoiceControlAlarm;
import com.ido.ble.protocol.model.VoiceControlBrightnessLevel;
import com.ido.ble.protocol.model.VoiceControlFuncWithNoPara;
import com.ido.ble.protocol.model.VoiceControlMusic;
import com.ido.ble.protocol.model.VoiceControlReminder;
import com.ido.ble.protocol.model.VoiceControlSports;
import com.ido.ble.protocol.model.VoiceControlSwitchFunc;
import com.ido.ble.protocol.model.VoiceCountDown;
import com.ido.ble.protocol.model.VoiceLoginState;
import com.ido.ble.protocol.model.VoiceNotifyState;
import com.ido.ble.protocol.model.VoiceRecognizeState;
import com.ido.ble.protocol.model.VoiceStopWatch;

/* JADX INFO: loaded from: classes2.dex */
public class q {
    static void a() {
        u.b(com.veryfit.multi.nativeprotocol.b.k5, com.veryfit.multi.nativeprotocol.b.X4);
    }

    static void a(VoiceControlAlarm voiceControlAlarm) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlAlarm)), com.veryfit.multi.nativeprotocol.b.D3);
    }

    static void a(VoiceControlBrightnessLevel voiceControlBrightnessLevel) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlBrightnessLevel)), com.veryfit.multi.nativeprotocol.b.U4);
    }

    static void a(VoiceControlFuncWithNoPara voiceControlFuncWithNoPara) {
        int i;
        int i2 = voiceControlFuncWithNoPara.funcType;
        if (i2 == 1) {
            i = com.veryfit.multi.nativeprotocol.b.I4;
        } else if (i2 == 2) {
            i = com.veryfit.multi.nativeprotocol.b.J4;
        } else if (i2 == 3) {
            i = com.veryfit.multi.nativeprotocol.b.N4;
        } else if (i2 == 4) {
            i = com.veryfit.multi.nativeprotocol.b.O4;
        } else if (i2 != 5) {
            return;
        } else {
            i = com.veryfit.multi.nativeprotocol.b.W4;
        }
        u.b(com.veryfit.multi.nativeprotocol.b.j5, i);
    }

    static void a(VoiceControlMusic voiceControlMusic) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlMusic)), com.veryfit.multi.nativeprotocol.b.P4);
    }

    static void a(VoiceControlReminder voiceControlReminder) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlReminder)), 5029);
    }

    static void a(VoiceControlSports voiceControlSports) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSports)), 7601);
    }

    static void a(VoiceControlSwitchFunc voiceControlSwitchFunc) {
        int i = voiceControlSwitchFunc.type;
        if (i == 1) {
            u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSwitchFunc)), com.veryfit.multi.nativeprotocol.b.Q4);
        } else if (i == 2) {
            u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSwitchFunc)), com.veryfit.multi.nativeprotocol.b.R4);
        } else if (i == 3) {
            u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceControlSwitchFunc)), com.veryfit.multi.nativeprotocol.b.V4);
        }
    }

    static void a(VoiceCountDown voiceCountDown) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceCountDown)), com.veryfit.multi.nativeprotocol.b.G4);
    }

    static void a(VoiceLoginState voiceLoginState) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceLoginState)), com.veryfit.multi.nativeprotocol.b.Y4);
    }

    static void a(VoiceNotifyState voiceNotifyState) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceNotifyState)), com.veryfit.multi.nativeprotocol.b.a5);
    }

    static void a(VoiceRecognizeState voiceRecognizeState) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceRecognizeState)), com.veryfit.multi.nativeprotocol.b.Z4);
    }

    static void a(VoiceStopWatch voiceStopWatch) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(voiceStopWatch)), 7602);
    }

    static void b() {
        u.d(com.veryfit.multi.nativeprotocol.b.B4);
    }

    static void c() {
        u.d(com.veryfit.multi.nativeprotocol.b.D4);
    }
}