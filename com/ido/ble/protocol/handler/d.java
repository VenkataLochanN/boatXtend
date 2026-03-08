package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.AppExchangeDataCallBack;
import com.ido.ble.callback.DeviceExchangeDataCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataPauseDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataResumeDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStopDeviceReplyData;
import com.ido.ble.protocol.model.DeviceExchangeDataIngPara;
import com.ido.ble.protocol.model.DeviceExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceExchangeDataStartPara;
import com.ido.ble.protocol.model.DeviceExchangeDataStopPara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopPara;
import com.ido.life.constants.Constants;

/* JADX INFO: loaded from: classes2.dex */
final class d {
    d() {
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        switch (i) {
            case 601:
                String strD = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDataStart" + strD);
                AppExchangeDataCallBack.a((AppExchangeDataStartDeviceReplyData) new Gson().fromJson(strD, AppExchangeDataStartDeviceReplyData.class));
                break;
            case 603:
                AppExchangeDataCallBack.a((AppExchangeDataIngDeviceReplyData) new Gson().fromJson(com.ido.ble.common.c.d(bArr), AppExchangeDataIngDeviceReplyData.class));
                break;
            case 605:
                String strD2 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDateStop" + strD2);
                AppExchangeDataCallBack.a((AppExchangeDataStopDeviceReplyData) new Gson().fromJson(strD2, AppExchangeDataStopDeviceReplyData.class));
                break;
            case 607:
                String strD3 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDatePause" + strD3);
                AppExchangeDataCallBack.a((AppExchangeDataPauseDeviceReplyData) new Gson().fromJson(strD3, AppExchangeDataPauseDeviceReplyData.class));
                break;
            case 609:
                String strD4 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onReplyAppExchangeDateResume" + strD4);
                AppExchangeDataCallBack.a((AppExchangeDataResumeDeviceReplyData) new Gson().fromJson(strD4, AppExchangeDataResumeDeviceReplyData.class));
                break;
            case 610:
                String strD5 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceNoticeAppPause" + strD5);
                AppExchangeDataCallBack.a((DeviceNoticeAppExchangeDataPausePara) new Gson().fromJson(strD5, DeviceNoticeAppExchangeDataPausePara.class));
                break;
            case 612:
                String strD6 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceNoticeAppResume" + strD6);
                AppExchangeDataCallBack.a((DeviceNoticeAppExchangeDataResumePara) new Gson().fromJson(strD6, DeviceNoticeAppExchangeDataResumePara.class));
                break;
            case 614:
                String strD7 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceNoticeAppStop" + strD7);
                AppExchangeDataCallBack.a((DeviceNoticeAppExchangeDataStopPara) new Gson().fromJson(strD7, DeviceNoticeAppExchangeDataStopPara.class));
                break;
            case com.veryfit.multi.nativeprotocol.b.J2 /* 620 */:
                String strD8 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataStart" + strD8);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataStartPara) new Gson().fromJson(strD8, DeviceExchangeDataStartPara.class));
                break;
            case com.veryfit.multi.nativeprotocol.b.L2 /* 622 */:
                DeviceExchangeDataCallBack.a((DeviceExchangeDataIngPara) new Gson().fromJson(com.ido.ble.common.c.d(bArr), DeviceExchangeDataIngPara.class));
                break;
            case com.veryfit.multi.nativeprotocol.b.N2 /* 624 */:
                String strD9 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataStop" + strD9);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataStopPara) new Gson().fromJson(strD9, DeviceExchangeDataStopPara.class));
                break;
            case com.veryfit.multi.nativeprotocol.b.P2 /* 626 */:
                String strD10 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataPause" + strD10);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataPausePara) new Gson().fromJson(strD10, DeviceExchangeDataPausePara.class));
                break;
            case com.veryfit.multi.nativeprotocol.b.R2 /* 628 */:
                String strD11 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.o, "onDeviceExchangeDataResume" + strD11);
                DeviceExchangeDataCallBack.a((DeviceExchangeDataResumePara) new Gson().fromJson(strD11, DeviceExchangeDataResumePara.class));
                break;
        }
    }

    static boolean a(int i) {
        switch (i) {
            case 600:
            case 601:
            case 602:
            case 603:
            case 604:
            case 605:
            case 606:
            case 607:
            case 608:
            case 609:
            case 610:
            case 611:
            case 612:
            case 613:
            case 614:
            case 615:
            case com.veryfit.multi.nativeprotocol.b.J2 /* 620 */:
            case com.veryfit.multi.nativeprotocol.b.K2 /* 621 */:
            case com.veryfit.multi.nativeprotocol.b.L2 /* 622 */:
            case com.veryfit.multi.nativeprotocol.b.M2 /* 623 */:
            case com.veryfit.multi.nativeprotocol.b.N2 /* 624 */:
            case com.veryfit.multi.nativeprotocol.b.O2 /* 625 */:
            case com.veryfit.multi.nativeprotocol.b.P2 /* 626 */:
            case com.veryfit.multi.nativeprotocol.b.Q2 /* 627 */:
            case com.veryfit.multi.nativeprotocol.b.R2 /* 628 */:
            case com.veryfit.multi.nativeprotocol.b.S2 /* 629 */:
                return true;
            case Constants.EventConstants.EVENT_FIRST_ONFOOT /* 616 */:
            case Constants.EventConstants.EVENT_DRIVING_OUT /* 617 */:
            case com.veryfit.multi.nativeprotocol.b.I2 /* 618 */:
            case 619:
            default:
                return false;
        }
    }
}