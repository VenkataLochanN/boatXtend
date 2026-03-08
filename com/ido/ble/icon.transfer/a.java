package com.ido.ble.icon.transfer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.k;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.IconCompressConfig;
import com.ido.ble.protocol.model.IconPara;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f4614g = "IconTransferManager";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f4615h = "msg";
    private static final String i = "sport";
    private static final String j = "sports";
    private static a k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IconTranConfig f4617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IIconTransferListener f4618c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4619d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler f4620e = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private DeviceResponseCommonCallBack.ICallBack f4621f = new b();

    /* JADX INFO: renamed from: com.ido.ble.icon.transfer.a$a, reason: collision with other inner class name */
    class RunnableC0087a implements Runnable {
        RunnableC0087a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.b(a.f4614g, "time out.");
            a.this.c();
        }
    }

    class b implements DeviceResponseCommonCallBack.ICallBack {
        b() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 334) {
                a.this.a(str);
            }
        }
    }

    class c implements IFileTransferListener {
        c() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            LogTool.d(a.f4614g, "[beginTransfer] " + str);
            a.this.c();
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            if (a.this.f4618c != null) {
                a.this.f4618c.onProgress(a.this.f4617b, i);
            }
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            a.this.j();
        }
    }

    private void a(int i2) {
        String str;
        StringBuilder sb;
        String str2;
        IconCompressConfig iconCompressConfig = new IconCompressConfig();
        String str3 = this.f4616a;
        iconCompressConfig.fileName = str3;
        iconCompressConfig.format = i2;
        if (this.f4617b.type == 4) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str3);
            if (bitmapDecodeFile == null || bitmapDecodeFile.getWidth() == 0 || bitmapDecodeFile.getHeight() == 0) {
                iconCompressConfig.pic_num = 20;
                sb = new StringBuilder();
                str2 = "[compressIconFile] wallPaper==null  pic_num:";
            } else {
                iconCompressConfig.pic_num = bitmapDecodeFile.getHeight() / bitmapDecodeFile.getWidth();
                sb = new StringBuilder();
                str2 = "[compressIconFile]   pic_num:";
            }
            sb.append(str2);
            sb.append(iconCompressConfig.pic_num);
            LogTool.d(f4614g, sb.toString());
        } else {
            iconCompressConfig.pic_num = 1;
        }
        int i3 = this.f4617b.type;
        if (i3 == 1) {
            str = "msg";
        } else {
            if (i3 != 3 && i3 != 5 && i3 != 2) {
                if (i3 == 4) {
                    str = j;
                }
                LogTool.d(f4614g, "[compressIconFile json] " + k.a(iconCompressConfig));
                u.a(k.a(iconCompressConfig));
                b();
            }
            str = i;
        }
        iconCompressConfig.endName = str;
        LogTool.d(f4614g, "[compressIconFile json] " + k.a(iconCompressConfig));
        u.a(k.a(iconCompressConfig));
        b();
    }

    private void a(int i2, int i3, int i4) {
        IIconTransferListener iIconTransferListener = this.f4618c;
        if (iIconTransferListener == null) {
            LogTool.b(f4614g, "[getIconFile] listener is null");
            c();
            return;
        }
        this.f4616a = iIconTransferListener.onHandlePicFile(this.f4617b, i2, i3);
        LogTool.d(f4614g, "[getIconFile] iconPath is  " + this.f4616a);
        a(i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.ido.ble.callback.b.K().b(this.f4621f);
        i();
        if (TextUtils.isEmpty(str)) {
            LogTool.b(f4614g, "[returnIconPara] return icon para json is null");
            c();
            return;
        }
        LogTool.d(f4614g, "[returnIconPara] return icon para " + str);
        IconPara.Response response = (IconPara.Response) k.c(str, IconPara.Response.class);
        if (response != null) {
            a(response.icon_width, response.icon_height, response.format);
        } else {
            LogTool.b(f4614g, "[returnIconPara] return icon para json is null");
            c();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b() {
        /*
            r9 = this;
            java.lang.String r0 = "IconTransferManager"
            java.lang.String r1 = "[beginTransfer]"
            com.ido.ble.logs.LogTool.d(r0, r1)
            com.ido.ble.file.transfer.FileTransferConfig r1 = new com.ido.ble.file.transfer.FileTransferConfig
            r1.<init>()
            com.ido.ble.icon.transfer.IconTranConfig r2 = r9.f4617b
            int r2 = r2.type
            r3 = 1
            r4 = 2
            java.lang.String r5 = ".temp."
            if (r2 != r3) goto L33
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r9.f4616a
            r2.append(r6)
            r2.append(r5)
            java.lang.String r6 = "msg"
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.filePath = r2
            java.lang.String r2 = "1.msg"
        L30:
            r1.firmwareSpecName = r2
            goto L84
        L33:
            r6 = 3
            java.lang.String r7 = "1.sport"
            java.lang.String r8 = "sport"
            if (r2 != r6) goto L54
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L40:
            java.lang.String r6 = r9.f4616a
            r2.append(r6)
            r2.append(r5)
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            r1.filePath = r2
            r1.firmwareSpecName = r7
            goto L84
        L54:
            r6 = 5
            if (r2 != r6) goto L5d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L40
        L5d:
            if (r2 != r4) goto L65
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L40
        L65:
            r6 = 4
            if (r2 != r6) goto L84
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = r9.f4616a
            r2.append(r6)
            r2.append(r5)
            java.lang.String r6 = "sports"
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.filePath = r2
            java.lang.String r2 = "1.sports"
            goto L30
        L84:
            java.io.File r2 = new java.io.File
            java.lang.String r6 = r1.filePath
            r2.<init>(r6)
            boolean r2 = r2.exists()
            if (r2 != 0) goto La0
            java.lang.String r2 = "[beginTransfer], remove ‘temp’"
            com.ido.ble.logs.LogTool.d(r0, r2)
            java.lang.String r0 = r1.filePath
            java.lang.String r2 = "."
            java.lang.String r0 = r0.replace(r5, r2)
            r1.filePath = r0
        La0:
            r0 = 10
            r1.PRN = r0
            r1.zipType = r4
            r1.dataType = r4
            com.ido.ble.icon.transfer.IconTranConfig r0 = r9.f4617b
            int r0 = r0.maxRetryTimes
            r1.maxRetryTimes = r0
            r1.isNeedChangeSpeedMode = r3
            com.ido.ble.icon.transfer.a$c r0 = new com.ido.ble.icon.transfer.a$c
            r0.<init>()
            r1.iFileTransferListener = r0
            com.ido.ble.file.transfer.b r0 = com.ido.ble.file.transfer.b.g()
            r0.b(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.icon.transfer.a.b():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.b(f4614g, "failed. ");
        IIconTransferListener iIconTransferListener = this.f4618c;
        if (iIconTransferListener != null) {
            iIconTransferListener.onFailed(this.f4617b);
        }
        g();
    }

    private Handler d() {
        if (this.f4620e == null) {
            this.f4620e = new Handler(Looper.getMainLooper());
        }
        return this.f4620e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r2 == 5) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e() {
        /*
            r6 = this;
            com.ido.ble.callback.b r0 = com.ido.ble.callback.b.K()
            com.ido.ble.callback.DeviceResponseCommonCallBack$ICallBack r1 = r6.f4621f
            r0.a(r1)
            com.ido.ble.protocol.model.IconPara$Get r0 = new com.ido.ble.protocol.model.IconPara$Get
            r0.<init>()
            com.ido.ble.icon.transfer.IconTranConfig r1 = r6.f4617b
            int r2 = r1.type
            r3 = 1
            if (r2 != r3) goto L1a
            int r1 = r1.index
            r0.evt_type = r1
            goto L34
        L1a:
            r4 = 2
            r5 = 3
            if (r2 != r5) goto L25
            r0.type = r4
        L20:
            int r1 = r1.index
            r0.sport_type = r1
            goto L34
        L25:
            if (r2 != r4) goto L2a
        L27:
            r0.type = r3
            goto L20
        L2a:
            r3 = 4
            if (r2 != r3) goto L30
            r0.type = r5
            goto L20
        L30:
            r4 = 5
            if (r2 != r4) goto L34
            goto L27
        L34:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[getIconPara] start get icon para "
            r1.append(r2)
            java.lang.String r2 = r0.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "IconTransferManager"
            com.ido.ble.logs.LogTool.d(r2, r1)
            java.lang.String r0 = com.ido.ble.common.k.a(r0)
            r1 = 334(0x14e, float:4.68E-43)
            com.ido.ble.i.a.a.a(r1, r0)
            r6.h()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.icon.transfer.a.e():void");
    }

    public static a f() {
        if (k == null) {
            k = new a();
        }
        return k;
    }

    private void g() {
        com.ido.ble.callback.b.K().b(this.f4621f);
        this.f4617b = null;
        this.f4618c = null;
        this.f4619d = false;
        this.f4616a = "";
        Handler handler = this.f4620e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f4620e = null;
        }
    }

    private void h() {
        d().postDelayed(new RunnableC0087a(), 20000L);
    }

    private void i() {
        Handler handler = this.f4620e;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogTool.d(f4614g, "success.");
        IIconTransferListener iIconTransferListener = this.f4618c;
        if (iIconTransferListener != null) {
            iIconTransferListener.onSuccess(this.f4617b);
        }
        g();
    }

    public void a() {
        LogTool.b(f4614g, "stopByUser. ");
        com.ido.ble.file.transfer.b.g().a();
        g();
    }

    public void a(IconTranConfig iconTranConfig, IIconTransferListener iIconTransferListener) {
        if (this.f4619d) {
            LogTool.b(f4614g, "[start] is doing, it`s config is " + this.f4617b);
            IIconTransferListener iIconTransferListener2 = this.f4618c;
            if (iIconTransferListener2 != null) {
                iIconTransferListener2.onBusy(this.f4617b);
                return;
            }
            return;
        }
        this.f4617b = iconTranConfig;
        this.f4618c = iIconTransferListener;
        LogTool.d(f4614g, "[start] " + iconTranConfig.toString());
        this.f4619d = true;
        IIconTransferListener iIconTransferListener3 = this.f4618c;
        if (iIconTransferListener3 != null) {
            iIconTransferListener3.onStart(iconTranConfig);
        }
        e();
    }
}