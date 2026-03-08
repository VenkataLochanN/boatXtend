package com.ido.life.location;

import android.content.Context;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.IBaseLocation;
import com.ido.life.base.ILocationCallback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseLocationPresenter implements IBaseLocation {
    private static final int LOCATION_MAX_TIME = 3;
    private static final String TAG = BaseLocationPresenter.class.getSimpleName();
    protected ILocationCallback mCallback;
    protected Context mContext;
    private int mLocationTime;
    protected int mUpdateInterval = 5000;
    protected boolean mLocationOnce = false;

    @Override // com.ido.life.base.IBaseLocation
    public void startLocation(Context context, boolean z) {
        CommonLogUtil.d(TAG, "startLocation  isOnce:" + z);
        this.mContext = context;
        this.mLocationOnce = z;
    }

    @Override // com.ido.life.base.IBaseLocation
    public void stopLocation() {
        CommonLogUtil.d(TAG, "stopLocation");
    }

    @Override // com.ido.life.base.IBaseLocation
    public void onDestroy() {
        CommonLogUtil.d(TAG, "onDestroy");
        stopLocation();
    }

    @Override // com.ido.life.base.IBaseLocation
    public final void onLocationChanged(LocationMessage locationMessage) {
        if (locationMessage == null) {
            if (this.mLocationOnce) {
                this.mLocationTime++;
                if (this.mLocationTime > 3) {
                    stopLocation();
                    ILocationCallback iLocationCallback = this.mCallback;
                    if (iLocationCallback != null) {
                        iLocationCallback.onLocationFailed("定位失败!");
                        return;
                    }
                    return;
                }
                return;
            }
            ILocationCallback iLocationCallback2 = this.mCallback;
            if (iLocationCallback2 != null) {
                iLocationCallback2.onLocationFailed("定位失败!");
                return;
            }
            return;
        }
        ILocationCallback iLocationCallback3 = this.mCallback;
        if (iLocationCallback3 != null) {
            iLocationCallback3.onLocationUpdate(locationMessage);
        }
        if (this.mLocationOnce) {
            stopLocation();
        }
    }

    public int getUpdateInterval() {
        return this.mUpdateInterval;
    }

    public void setUpdateInterval(int i) {
        if (i == this.mUpdateInterval) {
            return;
        }
        this.mUpdateInterval = i;
        stopLocation();
        startLocation(this.mContext, this.mLocationOnce);
    }

    @Override // com.ido.life.base.IBaseLocation
    public void setLocationListener(ILocationCallback iLocationCallback) {
        this.mCallback = iLocationCallback;
    }

    @Override // com.ido.life.base.IBaseLocation
    public String[] permissions() {
        return new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    }

    protected boolean checkPermission() {
        String[] strArrPermissions = permissions();
        if (strArrPermissions == null || strArrPermissions.length <= 0) {
            return true;
        }
        return PermissionUtil.checkSelfPermission(this.mContext, strArrPermissions);
    }
}