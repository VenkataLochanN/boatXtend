package com.ido.ble.dfu.rtk.auth;

import android.text.TextUtils;
import android.util.Log;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.callback.b;
import com.ido.ble.common.c;
import com.ido.ble.common.k;
import com.ido.ble.common.m;
import com.ido.ble.dfu.a;
import com.ido.ble.dfu.rtk.auth.RtkAuth;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.image.BinFactory;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.model.BinInfo;
import java.util.Locale;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class RtkAuthTask {
    private static final int MAX_RETRY_TIMES = 3;
    private static boolean isDoing = false;
    private String filePath;
    private IResult iResult;
    private int retryTimes = 0;
    private int mTimeoutTaskId = -1;
    private DeviceResponseCommonCallBack.ICallBack iCallBack = new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.ble.dfu.rtk.auth.RtkAuthTask.1
        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            RtkAuth.AuthResult authResult;
            if (i != 407) {
                return;
            }
            LogTool.d(a.f4247c, "[RtkAuthTask] response json is " + str);
            if (TextUtils.isEmpty(str) || (authResult = (RtkAuth.AuthResult) k.c(str, RtkAuth.AuthResult.class)) == null || authResult.errCode != 0) {
                RtkAuthTask.this.failed();
            } else {
                RtkAuthTask.this.success();
            }
        }
    };

    public interface IResult {
        void onFailed(String str);

        void onSuccess();
    }

    public RtkAuthTask(String str) {
        this.filePath = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failed() {
        LogTool.b(a.f4247c, "[RtkAuthTask] rtk auth failed!");
        finished();
        this.iResult.onFailed("");
    }

    private void finished() {
        LogTool.d(a.f4247c, "[RtkAuthTask] finished!");
        m.a(this.mTimeoutTaskId);
        release();
    }

    private int getVersionFromBinFile() {
        try {
            BinInfo binInfoLoadImageBinInfo = BinFactory.loadImageBinInfo(new LoadParams.Builder().setPrimaryIcType(5).setFilePath(this.filePath).build());
            if (binInfoLoadImageBinInfo == null) {
                return -1;
            }
            LogTool.d(a.f4247c, "bin file's BinInfo = " + binInfoLoadImageBinInfo.toString());
            int i = binInfoLoadImageBinInfo.version;
            int i2 = i & 15;
            int i3 = (i >> 4) & 255;
            int i4 = (i >> 12) & 32767;
            int i5 = (i >> 27) & 31;
            LogTool.d(a.f4247c, "bin file's version is " + String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
            return i5 > 0 ? i4 | 32768 : i4;
        } catch (DfuException e2) {
            LogTool.b(a.f4247c, e2.getMessage());
            return -1;
        }
    }

    private void release() {
        isDoing = false;
        b.K().b(this.iCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restart() {
        if (this.retryTimes > 3) {
            LogTool.d(a.f4247c, "[RtkAuthTask] out of max retry times.");
            failed();
        } else {
            LogTool.d(a.f4247c, "[RtkAuthTask] restart...");
            this.retryTimes++;
            startTimeOutTask();
            sendAuthCmd();
        }
    }

    private void sendAuthCmd() {
        RtkAuth.AuthPara authPara = new RtkAuth.AuthPara();
        authPara.deviceId = getVersionFromBinFile();
        if (authPara.deviceId < 0) {
            LogTool.b(a.f4247c, "[RtkAuthTask] get binInfo's version failed: " + k.a(authPara));
            failed();
            return;
        }
        LogTool.d(a.f4247c, "[RtkAuthTask] auth para is " + k.a(authPara));
        u.b(c.b(k.a(authPara)), 407);
    }

    private void startTimeOutTask() {
        this.mTimeoutTaskId = m.a(new m.b() { // from class: com.ido.ble.dfu.rtk.auth.RtkAuthTask.2
            @Override // com.ido.ble.common.m.b
            public void onTimeOut() {
                Log.e(a.f4247c, "[RtkAuthTask] onTimeOut, retry...");
                RtkAuthTask.this.restart();
            }
        }, BootloaderScanner.TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void success() {
        LogTool.d(a.f4247c, "[RtkAuthTask] rtk auth success!");
        finished();
        this.iResult.onSuccess();
    }

    public void start(IResult iResult) {
        if (isDoing) {
            LogTool.b(a.f4247c, "[RtkAuthTask] is doing, ignore this action!");
            return;
        }
        LogTool.d(a.f4247c, "[RtkAuthTask] start...");
        this.iResult = iResult;
        b.K().a(this.iCallBack);
        isDoing = true;
        startTimeOutTask();
        sendAuthCmd();
    }

    public void stop() {
        if (isDoing) {
            LogTool.d(a.f4247c, "[RtkAuthTask] stop task!");
            release();
        }
    }
}