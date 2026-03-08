package com.ido.life.ble;

import com.ido.ble.business.sync.ISyncProgressListener;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.boatservice.DataUploadService;
import com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.life.util.SPHelper;
import com.ido.life.util.WeatherHelper;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BaseSyncProgressCallback implements ISyncProgressListener {
    @Override // com.ido.ble.business.sync.ISyncProgressListener
    public void onProgress(int i) {
    }

    @Override // com.ido.ble.business.sync.ISyncProgressListener
    public void onStart() {
    }

    @Override // com.ido.ble.business.sync.ISyncProgressListener
    public void onSuccess() {
        EventBusHelper.post(104);
        SPHelper.saveDeviceSyncTime(System.currentTimeMillis());
        WeatherHelper.sendWeather2Device();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "BaseSyncProgressCallback入口：uploadData()");
        GoogleFitPresenter.getInstance().uploadData();
        DataUploadService.start(false, false);
        RemoteLanguageHelper.getDeviceLanguage();
    }

    @Override // com.ido.ble.business.sync.ISyncProgressListener
    public void onFailed() {
        WeatherHelper.sendWeather2Device();
        DataUploadService.start(false, false);
        RemoteLanguageHelper.getDeviceLanguage();
    }
}