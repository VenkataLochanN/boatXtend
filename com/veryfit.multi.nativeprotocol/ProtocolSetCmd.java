package com.veryfit.multi.nativeprotocol;

/* JADX INFO: loaded from: classes3.dex */
public class ProtocolSetCmd {
    private static ProtocolSetCmd instance;

    private ProtocolSetCmd() {
    }

    public static synchronized ProtocolSetCmd getInstance() {
        if (instance == null) {
            instance = new ProtocolSetCmd();
        }
        return instance;
    }

    public native int ProtocolMissedCallEvt();

    public native int ProtocolSetAlarmEnd();

    public native int ProtocolSetCallEvt(byte[] bArr, byte[] bArr2);

    public native int ProtocolSetNoticeEvt(int i, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public native int ProtocolStopCallEvt();

    public native int ProtooclCleanAlarm();
}