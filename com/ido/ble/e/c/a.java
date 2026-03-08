package com.ido.ble.e.c;

import android.os.Handler;
import android.os.Looper;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.google.gson.Gson;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.c;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.Mp3ToPcmPara;
import com.ido.ble.protocol.model.VoiceFileTranConfigPara;
import com.ido.ble.protocol.model.VoiceFileTranStartPara;
import com.veryfit.multi.nativeprotocol.Protocol;
import com.veryfit.multi.nativeprotocol.b;
import java.io.File;
import sox.ProtocolSox;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4385c = "AlexaVoicePlayManager";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static a f4386d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f4388b = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: com.ido.ble.e.c.a$a, reason: collision with other inner class name */
    class C0078a implements DeviceResponseCommonCallBack.ICallBack {
        C0078a() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 7633) {
                a.this.c(str);
                return;
            }
            if (i == 7634) {
                return;
            }
            if (i == 7635) {
                a.this.i();
            } else if (i == 7636) {
                a.this.b();
            }
        }
    }

    private void a(int i) {
        VoiceFileTranConfigPara voiceFileTranConfigPara = new VoiceFileTranConfigPara();
        voiceFileTranConfigPara.prn = 10;
        voiceFileTranConfigPara.voice_type = 1;
        voiceFileTranConfigPara.sbc_init_enum = 1;
        voiceFileTranConfigPara.opus_init_enum = 1;
        voiceFileTranConfigPara.file_len = (i / GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) * 57;
        LogTool.d(f4385c, "[setTranPara] " + voiceFileTranConfigPara.toString());
        u.b(c.b(k.a(voiceFileTranConfigPara)), b.b5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(f4385c, "abnormal end");
    }

    private boolean b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            LogTool.d(f4385c, "[mp3ToPcm] file not exists:" + str);
            d();
            return false;
        }
        this.f4387a = str.replace(file.getName(), "") + "tempVoice.pcm";
        Mp3ToPcmPara mp3ToPcmPara = new Mp3ToPcmPara();
        mp3ToPcmPara.mp3Path = str;
        mp3ToPcmPara.pcmPath = this.f4387a;
        LogTool.d(f4385c, "[mp3ToPcm] " + mp3ToPcmPara.toString());
        u.b(c.b(k.a(mp3ToPcmPara)), b.a4);
        return true;
    }

    private void c() {
        Protocol.getInstance().testSetTimeInterval(1, 10);
        VoiceFileTranStartPara voiceFileTranStartPara = new VoiceFileTranStartPara();
        voiceFileTranStartPara.fileName = this.f4387a;
        LogTool.d(f4385c, "[beginTran] " + k.a(voiceFileTranStartPara));
        u.b(c.b(k.a(voiceFileTranStartPara)), b.c5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogTool.d(f4385c, "[returnSetTranPara] " + str);
        c();
    }

    private void d() {
        LogTool.b(f4385c, "failed.");
    }

    private Handler e() {
        if (this.f4388b == null) {
            this.f4388b = new Handler(Looper.getMainLooper());
        }
        return this.f4388b;
    }

    public static a f() {
        if (f4386d == null) {
            f4386d = new a();
            f4386d.h();
        }
        return f4386d;
    }

    private long g() {
        String str;
        this.f4387a = ProtocolSox.a().a(this.f4387a);
        LogTool.d(f4385c, "[pcmFileTo16HZ] " + this.f4387a);
        File file = new File(this.f4387a);
        if (!file.exists()) {
            str = "targetPcmFile is not exists";
        } else {
            if (file.length() > 0) {
                return file.length();
            }
            str = "targetPcmFile length is 0";
        }
        LogTool.b(f4385c, str);
        d();
        return -1L;
    }

    private void h() {
        com.ido.ble.callback.b.K().a(new C0078a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d(f4385c, "success.");
    }

    public void a() {
        LogTool.d(f4385c, "[stop] ");
        u.b(c.b(new Gson().toJson("")), b.d5);
    }

    public void a(String str) {
        LogTool.d(f4385c, "[play] mp3FilePath is " + str);
        if (b(str)) {
            long jG = g();
            if (jG <= 0) {
                return;
            }
            a((int) jG);
        }
    }
}