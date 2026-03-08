package com.ido.life.module.sport.run;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.bean.LatLngBean;
import com.ido.life.database.model.SportHealth;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunContract {

    interface Presenter extends BasePresenter {
        boolean back();

        void clearListener();

        void close();

        void end();

        boolean getInit();

        void getSportNameByType(int i);

        void hideMap(android.view.View view, android.view.View view2);

        void initUserTarget();

        void isSportOutDoor(boolean z);

        void onRestoreInstanceState();

        void pause();

        void reStart();

        void setSportRunListener();

        void showMap(android.view.View view, android.view.View view2);

        void stopLocation();

        void stopRun(boolean z);

        void toSoundOffOrOn();
    }

    interface View extends BaseView<Presenter> {
        void addCurrentMarker(LatLngBean latLngBean);

        void addFirstCurrentMarker(LatLngBean latLngBean);

        void addPolylineAndMove(LatLngBean latLngBean, boolean z);

        void clearPoint();

        void hideLoading();

        void loadMap(LatLngBean latLngBean);

        void setDeviceStatus(String str);

        void setDeviceStatusMap(String str);

        void setGPSSingleStrength(int i);

        void setGPSSingleStrengthMap(int i);

        void setGpsStatusDesc(String str);

        void setSoundAlpha(float f2);

        void setSoundEnable(boolean z);

        void setSoundSrc(int i);

        void setSportCalorie(String str);

        void setSportCalorieDesc(String str);

        void setSportDistance(String str);

        void setSportDistanceMap(String str);

        void setSportDistanceUnit(String str);

        void setSportDistanceUnitMap(String str);

        void setSportSpeed(String str);

        void setSportSpeedMap(String str);

        void setSportSpeedMapTitle(String str);

        void setSportSpeedTitle(String str);

        void setSportStatus(boolean z);

        void setSportTime(String str);

        void setSportTimeMap(String str);

        void setSportTypeName(String str);

        void showBack(boolean z);

        void showBottomDistanceView(boolean z);

        void showEndConfirmDialog();

        void showGpsSingle(boolean z);

        void showGpsStatusDesc(boolean z);

        void showLoading(String str);

        void showMapView(boolean z);

        void showMessage(String str);

        void showMiddleDistanceView(boolean z);

        void showSportMapIcon(boolean z);

        void toBack();

        void toIndoorRun(int i, String str);

        void toSportHistory(int i, SportHealth sportHealth);
    }
}