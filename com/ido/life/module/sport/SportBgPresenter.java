package com.ido.life.module.sport;

import android.location.LocationManager;
import android.os.Build;
import com.boat.Xtend.two.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.module.sport.SportBgContract;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes2.dex */
public class SportBgPresenter implements SportBgContract.Presenter {
    private static final String TAG = "SportBgPresenter";
    private boolean mIsOutDoor = true;
    private int mSportType = 48;
    private SportBgContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportBgPresenter(SportBgContract.View view) {
        this.mView = view;
    }

    private void getSportDistance(int i) {
        String str;
        String languageText;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float f2 = i / 1000.0f;
        float km2mile = UnitUtil.getKm2mile(f2);
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            str = decimalFormat.format(f2);
            languageText = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
        } else {
            str = decimalFormat.format(km2mile);
            languageText = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi);
        }
        if (i == 0) {
            this.mView.setTotalSportDistance(LanguageUtil.getLanguageText(R.string.sport_record_no_record));
        } else {
            this.mView.setTotalSportDistance(String.format(LanguageUtil.getLanguageText(R.string.sport_record_total_distance), str + languageText));
        }
        CommonLogUtil.d(TAG, "getSportDistance: " + str);
    }

    @Override // com.ido.life.module.sport.SportBgContract.Presenter
    public void startRun() {
        if (HomeFragmentPresenter.mIsSyncing) {
            this.mView.showMessage(ResourceUtil.getString(R.string.syncing_pls_try_again_later));
            return;
        }
        if (this.mIsOutDoor) {
            if (PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getLocationBackGroundPermission())) {
                if (isOpenGPS()) {
                    this.mView.goSportReady(this.mSportType, true);
                    return;
                } else {
                    this.mView.showLocationServiceDialog();
                    return;
                }
            }
            if (Build.VERSION.SDK_INT >= 29) {
                if (!PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), "android.permission.ACCESS_BACKGROUND_LOCATION")) {
                    this.mView.showLocationPermissionDialogForAndroidQ();
                    return;
                } else if (isOpenGPS()) {
                    this.mView.goSportReady(this.mSportType, true);
                    return;
                } else {
                    this.mView.showLocationServiceDialog();
                    return;
                }
            }
            this.mView.showLocationPermissionDialog();
            return;
        }
        if (BleSdkWrapper.isConnected()) {
            this.mView.goSportReady(this.mSportType, this.mIsOutDoor);
        } else if (!BleSdkWrapper.isBind()) {
            this.mView.showDeviceAddDialog();
        } else {
            this.mView.showDeviceConnectDialog();
        }
    }

    @Override // com.ido.life.module.sport.SportBgContract.Presenter
    public void toStartOutSport() {
        if (isOpenGPS()) {
            this.mView.goSportReadyPermission(this.mSportType, true);
        } else {
            this.mView.showLocationServiceDialog();
        }
    }

    private boolean isOpenGPS() {
        LocationManager locationManager = (LocationManager) IdoApp.getAppContext().getSystemService(FirebaseAnalytics.Param.LOCATION);
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }

    @Override // com.ido.life.module.sport.SportBgContract.Presenter
    public void getDeviceStatus() {
        if (BleSdkWrapper.isConnected()) {
            this.mView.showConnectDevice();
        } else {
            this.mView.showDisconnectDevice();
        }
    }

    @Override // com.ido.life.module.sport.SportBgContract.Presenter
    public void showDeviceStatus() {
        if (BleSdkWrapper.isConnected()) {
            this.mView.showMessage(ResourceUtil.getString(R.string.device_connected_already));
        } else if (!BleSdkWrapper.isBind()) {
            this.mView.showMessage(ResourceUtil.getString(R.string.sport_device_unbind));
        } else {
            this.mView.showMessage(ResourceUtil.getString(R.string.device_pls_connect_device));
        }
    }

    @Override // com.ido.life.module.sport.SportBgContract.Presenter
    public void getTotalDistance(int i) {
        getSportDistanceByType(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    @Override // com.ido.life.module.sport.SportBgContract.Presenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showBgByType(int r2) {
        /*
            r1 = this;
            r1.mSportType = r2
            r0 = 4
            if (r2 == r0) goto L1a
            r0 = 52
            if (r2 == r0) goto L1a
            r0 = 53
            if (r2 == r0) goto L11
            switch(r2) {
                case 48: goto L1a;
                case 49: goto L11;
                case 50: goto L1a;
                default: goto L10;
            }
        L10:
            goto L22
        L11:
            r2 = 0
            r1.mIsOutDoor = r2
            com.ido.life.module.sport.SportBgContract$View r2 = r1.mView
            r2.showInDoorBg()
            goto L22
        L1a:
            r2 = 1
            r1.mIsOutDoor = r2
            com.ido.life.module.sport.SportBgContract$View r2 = r1.mView
            r2.showOutDoorBg()
        L22:
            com.ido.life.module.sport.SportBgContract$View r2 = r1.mView
            boolean r0 = r1.mIsOutDoor
            r2.setTotalSportDistanceVisible(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.sport.SportBgPresenter.showBgByType(int):void");
    }

    private void getSportDistanceByType(int i) {
        getSportDistance(HealthManager.getSportHealthDistanceList(i));
    }
}