package com.ido.life.module.device.view;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.CwdAppBean;
import com.ido.life.bean.UsageDialBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IWallPaperDialView extends IBaseView, BaseDialView {
    View getPreviewView();

    void onAddAccountsuccess();

    void onCwdInfoGetFailed(boolean z);

    void onCwdInstallFailed(boolean z);

    void onCwdInstallStatusUpdate();

    void onCwdInstallSuccess();

    void onDeviceNotEnoughStorage();

    void onDialDeleteComplete(boolean z);

    void onDialDeleteStart();

    void onDialInstallFailed(boolean z);

    void onDialInstallProgress(int i);

    void onDialInstallStart();

    void onDialInstallSuccess();

    void onDownloadFailed();

    void onDownloadsuccess();

    void onGetCwdConfigSuccess(CwdAppBean cwdAppBean);

    void onGetDeviceScreenSize(Point point);

    void onGetDialInfoFailed(boolean z);

    void onGetFunctionListFailed();

    void onGetFunctionListSuccess(List<Integer> list);

    void onGetInstalledDialInfo(boolean z);

    void onGetUsageDialListFailed();

    void onGetUsageDialListSuccess(List<UsageDialBean> list);

    void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo);

    void onLoadWallpaperImageFailed();

    void onLoadWallpaperImageSuccess(Bitmap bitmap);

    void onNoDataChanged();

    void onProgress(int i, int i2);

    void onSetDialColorComplete(boolean z);

    void onSetDialColorStart();

    void onSwitchDialComplete(boolean z);

    void onSwitchDialStart();
}