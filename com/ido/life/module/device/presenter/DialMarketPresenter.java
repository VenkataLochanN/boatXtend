package com.ido.life.module.device.presenter;

import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.module.device.view.IDialMarketView;
import com.ido.life.util.WallpaperDialManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketPresenter extends BaseDialPresenter<IDialMarketView> {
    public static final int TYPE_FINE_RECOMMENDED = -2;
    public static final int TYPE_LATEST_ONLINE = -1;
    private List<String> mColorList;

    public void requestDialList() {
        DeviceManager.getDeviceTypeByIdDialMarket(getDeviceInfo(), new DeviceManager.OnDeviceCallback<List<DialMarket.DialType>>() { // from class: com.ido.life.module.device.presenter.DialMarketPresenter.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<DialMarket.DialType> list) {
                if (DialMarketPresenter.this.isAttachView()) {
                    ((IDialMarketView) DialMarketPresenter.this.getView()).onGetDialList(list);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                if (DialMarketPresenter.this.isAttachView()) {
                    ((IDialMarketView) DialMarketPresenter.this.getView()).onGetDialList(null);
                }
            }
        });
    }

    public String formatTypeNameById(int i) {
        if (i != -2) {
            return i != -1 ? "" : LanguageUtil.getLanguageText(R.string.device_dial_latest_online);
        }
        return LanguageUtil.getLanguageText(R.string.device_dial_fine_recommended);
    }

    public void getWallpaperDialInfo() {
        if (!isConnected()) {
            saveDialLog("getWallpaperDialInfo, device disconnect");
            if (isAttachView()) {
                ((IDialMarketView) getView()).onGetDialInfoFailed();
                return;
            }
            return;
        }
        BLEManager.registerPhotoWallpaperOperateCallBack(new PhotoWallpaperOperateCallBack.ICallBack() { // from class: com.ido.life.module.device.presenter.DialMarketPresenter.2
            @Override // com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.ICallBack
            public void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo) {
                if (responseInfo != null && responseInfo.operate == 0) {
                    DialMarketPresenter.this.saveDialLog("getWallpaperDialInfo, onOperateResult : " + GsonUtil.toJson(responseInfo));
                    BLEManager.unregisterPhotoWallpaperOperateCallBack(this);
                    if (DialMarketPresenter.this.isAttachView()) {
                        ((IDialMarketView) DialMarketPresenter.this.getView()).onGetWallpaperDialInfo(responseInfo);
                    }
                }
            }
        });
        PhotoWallpaperOperation.SetParams setParams = new PhotoWallpaperOperation.SetParams();
        setParams.operate = 0;
        BLEManager.photoWallpaperOperate(setParams);
    }

    public List<String> getWallpaperColorList() {
        if (this.mColorList == null) {
            this.mColorList = new ArrayList();
        }
        if (this.mColorList.isEmpty()) {
            this.mColorList.addAll(WallpaperDialManager.getWallpaperColorList());
        }
        return this.mColorList;
    }

    public boolean isSupportWallpaperFunctionSetting() {
        return getSupportFunctionInfo().V3_support_watch_photo_position_move || getSupportFunctionInfo().v2_support_wallpaper_watch_face_only_time_color;
    }

    public boolean isNotSupportWallpaperFunctionEdit() {
        return getSupportFunctionInfo().v2_support_wallpaper_watch_face_only_time_color;
    }
}