package com.ido.ble.firmware.log.flash;

import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.k;
import com.ido.ble.firmware.log.b;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.FlashLogParam;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4542e = "COLLECT_FLASH_LOG";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static a f4543f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ICollectFlashLogListener f4545b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.ido.ble.firmware.log.b f4546c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f4544a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private DeviceResponseCommonCallBack.ICallBack f4547d = new C0085a();

    /* JADX INFO: renamed from: com.ido.ble.firmware.log.flash.a$a, reason: collision with other inner class name */
    class C0085a implements DeviceResponseCommonCallBack.ICallBack {
        C0085a() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 5512 || i == 5513) {
                a.this.a();
            }
        }
    }

    class b implements b.InterfaceC0084b {
        b() {
        }

        @Override // com.ido.ble.firmware.log.b.InterfaceC0084b
        public void onTimeOut() {
            a.this.d();
        }
    }

    class c implements b.InterfaceC0084b {
        c() {
        }

        @Override // com.ido.ble.firmware.log.b.InterfaceC0084b
        public void onTimeOut() {
            a.this.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(f4542e, "finish");
        c();
        ICollectFlashLogListener iCollectFlashLogListener = this.f4545b;
        if (iCollectFlashLogListener != null) {
            iCollectFlashLogListener.onFinish();
            this.f4545b = null;
        }
    }

    public static a b() {
        if (f4543f == null) {
            f4543f = new a();
        }
        return f4543f;
    }

    private void c() {
        LogTool.d(f4542e, "release");
        this.f4544a = false;
        this.f4546c.a();
        com.ido.ble.callback.b.K().b(this.f4547d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.b(f4542e, "timeout");
        c();
        ICollectFlashLogListener iCollectFlashLogListener = this.f4545b;
        if (iCollectFlashLogListener != null) {
            iCollectFlashLogListener.onFinish();
            this.f4545b = null;
        }
    }

    public void a(int i, String str, int i2, ICollectFlashLogListener iCollectFlashLogListener) {
        if (this.f4544a) {
            LogTool.b(f4542e, "in doing state...");
            return;
        }
        this.f4545b = iCollectFlashLogListener;
        this.f4544a = true;
        LogTool.d(f4542e, "[start] type=" + i + ",filePath=" + str + ",timeoutSecond=" + i2 + ",listener=" + iCollectFlashLogListener);
        this.f4546c = new com.ido.ble.firmware.log.b();
        this.f4546c.a(new b(), i2);
        this.f4545b.onStart();
        com.ido.ble.callback.b.K().a(this.f4547d);
        FlashLogParam flashLogParam = new FlashLogParam();
        flashLogParam.filePath = str;
        flashLogParam.type = i;
        u.b(k.a(flashLogParam).getBytes(), com.veryfit.multi.nativeprotocol.b.W3);
    }

    public void b(int i, String str, int i2, ICollectFlashLogListener iCollectFlashLogListener) {
        if (this.f4544a) {
            LogTool.b(f4542e, "SecondChip in doing state...");
            return;
        }
        this.f4545b = iCollectFlashLogListener;
        this.f4544a = true;
        LogTool.d(f4542e, "SecondChip [start] type=" + i + ",filePath=" + str + ",timeoutSecond=" + i2 + ",listener=" + iCollectFlashLogListener);
        this.f4546c = new com.ido.ble.firmware.log.b();
        this.f4546c.a(new c(), i2);
        this.f4545b.onStart();
        com.ido.ble.callback.b.K().a(this.f4547d);
        FlashLogParam flashLogParam = new FlashLogParam();
        flashLogParam.filePath = str;
        flashLogParam.type = i;
        u.b(k.a(flashLogParam).getBytes(), com.veryfit.multi.nativeprotocol.b.d4);
    }
}