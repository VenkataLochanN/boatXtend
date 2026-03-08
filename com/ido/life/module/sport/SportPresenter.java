package com.ido.life.module.sport;

import com.ido.common.IdoApp;
import com.ido.life.bean.SortBean;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.boatservice.GDLocationManager;
import com.ido.life.database.model.SportCard;
import com.ido.life.module.sport.SportContract;
import com.ido.life.module.sport.bean.LocationMessage;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportPresenter implements SportContract.Presenter {
    private static final String TAG = "SportPresenter";
    private GDLocationManager gdLocationManager;
    private GDLocationManager.LocationStringListener locationStringListener;
    private SportContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.life.module.sport.SportContract.Presenter
    public void showDeviceStatus() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SportPresenter(SportContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.sport.SportContract.Presenter
    public void getCardData() {
        setSportChoose();
    }

    private void setSportChoose() {
        List<Integer> valueList;
        SportCard sportCardQuerySportCard = GreenDaoUtil.querySportCard(RunTimeUtil.getInstance().getUserId());
        if (sportCardQuerySportCard != null && sportCardQuerySportCard.getValueList() != null && sportCardQuerySportCard.getValueList().size() > 0) {
            valueList = sportCardQuerySportCard.getValueList();
        } else if (RunTimeUtil.getInstance().getUserId() == -1) {
            List<Integer> defaultSportCard = SportCard.getDefaultSportCard();
            if (sportCardQuerySportCard != null) {
                sportCardQuerySportCard.setValueList(defaultSportCard);
                sportCardQuerySportCard.update();
            } else {
                SportCard sportCard = new SportCard();
                sportCard.setUploadSuccess(false);
                sportCard.setValueList(defaultSportCard);
                sportCard.setUserId(RunTimeUtil.getInstance().getUserId());
                GreenDaoUtil.addSportCard(sportCard);
            }
            valueList = defaultSportCard;
        } else {
            valueList = null;
        }
        if (valueList != null) {
            ArrayList arrayList = new ArrayList();
            int size = valueList.size();
            for (int i = 0; i < size; i++) {
                int iIntValue = valueList.get(i).intValue();
                arrayList.add(new SortBean(iIntValue, SportCard.initTypeNameId(iIntValue), true));
            }
            this.mView.setSportChoose(arrayList);
        }
    }

    @Override // com.ido.life.module.sport.SportContract.Presenter
    public void getDeviceStatus() {
        if (BleSdkWrapper.isConnected()) {
            this.mView.showConnectDevice();
        } else {
            this.mView.showDisconnectDevice();
        }
    }

    @Override // com.ido.life.module.sport.SportContract.Presenter
    public void startLocation() {
        stopLocation();
        if (this.locationStringListener == null) {
            this.locationStringListener = new GDLocationManager.LocationStringListener() { // from class: com.ido.life.module.sport.SportPresenter.1
                @Override // com.ido.life.boatservice.GDLocationManager.LocationStringListener
                public void onReceiveLocation(LocationMessage locationMessage) {
                    SportPresenter.this.mView.setGpsStatus(locationMessage.gpsAccuracyStatus);
                }
            };
        }
        this.gdLocationManager = GDLocationManager.getInstance(IdoApp.getAppContext());
        this.gdLocationManager.startLocation(this.locationStringListener);
    }

    @Override // com.ido.life.module.sport.SportContract.Presenter
    public void stopLocation() {
        GDLocationManager gDLocationManager = this.gdLocationManager;
        if (gDLocationManager != null) {
            gDLocationManager.stopLocation(this.locationStringListener);
        }
    }
}