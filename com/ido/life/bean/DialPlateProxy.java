package com.ido.life.bean;

import android.text.TextUtils;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.util.WallpaperDialManager;

/* JADX INFO: loaded from: classes2.dex */
public class DialPlateProxy extends TopDialPlateEntity.DialPlate {
    private boolean hasRefresh;
    private final TopDialPlateEntity.DialPlate mDialPlate;

    public DialPlateProxy(TopDialPlateEntity.DialPlate dialPlate) {
        this.mDialPlate = dialPlate;
    }

    public boolean hasRefresh() {
        return this.hasRefresh;
    }

    @Override // com.ido.life.data.api.entity.TopDialPlateEntity.DialPlate
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