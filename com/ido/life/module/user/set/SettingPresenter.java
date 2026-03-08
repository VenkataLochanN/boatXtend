package com.ido.life.module.user.set;

import android.content.Context;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.BLEManager;
import com.ido.ble.protocol.model.Units;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.ISettingRepository;
import com.ido.life.data.me.SettingRepository;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.set.SettingContract;
import com.ido.life.util.UnitUtil;

/* JADX INFO: loaded from: classes3.dex */
public class SettingPresenter implements SettingContract.Presenter {
    private static final String TAG = "SettingPresenter";
    private ISettingRepository mRepository = SettingRepository.getInstance();
    private SettingContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SettingPresenter(SettingContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.set.SettingContract.Presenter
    public void changeUnit(int i) {
        CommonLogUtil.d(TAG, "changeUnit: " + i);
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        int heightUnit = userInfo.getHeightUnit();
        int weightUnit = userInfo.getWeightUnit();
        CommonLogUtil.d(TAG, "changeUnit: " + heightUnit + AppInfo.DELIM + weightUnit);
        int i2 = i + 1;
        if (heightUnit != i2) {
            if (heightUnit == 1) {
                int iCm2inchs = UnitUtil.cm2inchs((int) userInfo.getHeight());
                CommonLogUtil.d(TAG, "setHeightType: " + iCm2inchs);
                AccountRepository.getInstance().updateHeight((float) iCm2inchs, 2);
            } else {
                AccountRepository.getInstance().updateHeight(UnitUtil.inch2cm(userInfo.getHeight()), 1);
            }
        }
        if (weightUnit != i2) {
            if (weightUnit == 1) {
                float fKg2lb = UnitUtil.kg2lb(userInfo.getWeight());
                CommonLogUtil.d(TAG, "setHeightType: " + fKg2lb);
                AccountRepository.getInstance().updateWeight((float) ((int) fKg2lb), 2);
            } else {
                AccountRepository.getInstance().updateWeight((int) UnitUtil.lb2kg(userInfo.getWeight()), 1);
            }
        }
        Units units = BleSdkWrapper.getUnits();
        units.dist = i2;
        units.weight = i2;
        BLEManager.setUnit(units);
    }

    @Override // com.ido.life.module.user.set.SettingContract.Presenter
    public void getCacheSize(Context context) {
        this.mView.showCacheSize(this.mRepository.getCacheFileSize(context));
    }

    @Override // com.ido.life.module.user.set.SettingContract.Presenter
    public void doClearCache(Context context) {
        this.mRepository.clearCache(context);
        this.mView.showCacheSize(this.mRepository.getCacheFileSize(context));
    }

    @Override // com.ido.life.module.user.set.SettingContract.Presenter
    public boolean isUserLogin() {
        return AccountRepository.getInstance().isUserSignIn();
    }
}