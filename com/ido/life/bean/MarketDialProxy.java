package com.ido.life.bean;

import android.text.TextUtils;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.util.WallpaperDialManager;

/* JADX INFO: loaded from: classes2.dex */
public class MarketDialProxy extends DialMarket.DialType.Dial {
    private boolean hasRefresh;
    private final DialMarket.DialType.Dial mDialPlate;

    public MarketDialProxy(DialMarket.DialType.Dial dial) {
        this.mDialPlate = dial;
    }

    public boolean hasRefresh() {
        return this.hasRefresh;
    }

    @Override // com.ido.life.data.api.entity.DialMarket.DialType.Dial
    public String getImageUrl() {
        this.hasRefresh = false;
        if ("CUSTOM_PHOTO".equals(this.mDialPlate.getCustomFaceType()) && WallpaperDialManager.isDeviceCwdDirExist(this.mDialPlate.getOtaFaceName())) {
            String devicePreviewImagePath = WallpaperDialManager.getDevicePreviewImagePath(this.mDialPlate.getOtaFaceName());
            if (!TextUtils.isEmpty(devicePreviewImagePath)) {
                this.hasRefresh = true;
                return devicePreviewImagePath;
            }
        }
        return this.mDialPlate.getImageUrl();
    }
}