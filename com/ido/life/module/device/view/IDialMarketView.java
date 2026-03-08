package com.ido.life.module.device.view;

import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.life.data.api.entity.DialMarket;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IDialMarketView extends BaseDialView {
    void onGetDialInfoFailed();

    void onGetDialList(List<DialMarket.DialType> list);

    void onGetWallpaperDialInfo(PhotoWallpaperOperation.ResponseInfo responseInfo);
}