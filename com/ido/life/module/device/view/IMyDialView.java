package com.ido.life.module.device.view;

import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.MyDialListEntity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IMyDialView extends BaseDialView {
    void onDeleteDial(boolean z);

    void onDialOrder(boolean z);

    void onGetCollectDialNum(int i);

    void onGetDialInfoFailed();

    void onGetDialInfoFromName(DialMarket.DialType.Dial dial);

    void onGetMyDialFailed();

    void onGetMyDialList(List<MyDialListEntity.DialInfo> list);

    void onGetMyDialNum(int i);

    void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo);

    void startLoading();
}