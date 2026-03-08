package com.ido.life.module.user.userdata.height;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.userdata.height.HeightContract;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HeightPresenter implements HeightContract.Presenter {
    private static final String TAG = "HeightPresenter";
    private int heightCm = 180;
    private int heightInch = 71;
    boolean isManualSetData = true;
    private int mHeightType = 1;
    private HeightContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public HeightPresenter(HeightContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.Presenter
    public void initHeight() {
        UserInfo userInfo = AccountRepository.getInstance().getUserInfo();
        if (userInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initHeight() ：userInfo = " + userInfo.toString());
            if (((int) userInfo.getHeight()) <= 0 || !this.isManualSetData) {
                if (userInfo.getGender() == 1) {
                    this.heightCm = 180;
                } else {
                    this.heightCm = 160;
                }
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initHeight() ：heightCm = " + this.heightCm);
                this.mView.setRulerView(1);
                return;
            }
            if (userInfo.getHeightUnit() == 2) {
                this.heightInch = Math.round(userInfo.getHeight() * 12.0f);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initVisiableHeight() ：heightInch = " + this.heightInch);
            } else {
                this.heightCm = (int) userInfo.getHeight();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initVisiableHeight() ：heightCm = " + this.heightCm);
            }
            this.mView.setRulerView(this.mHeightType);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initHeight() ：mHeightType = " + this.mHeightType);
        }
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.Presenter
    public int getHeightCm() {
        return this.heightCm;
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.Presenter
    public int getHeightInch() {
        return this.heightInch;
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.Presenter
    public void getHeight(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mView.changeForwardEnable(true);
        } else {
            this.mView.changeForwardEnable(false);
        }
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.Presenter
    public void setHeightType(int i, int[] iArr) {
        if (i == this.mHeightType) {
            return;
        }
        this.mHeightType = i;
        changeTempHeight(iArr);
        this.mView.setRulerView(i);
    }

    private void changeTempHeight(int[] iArr) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "changeTempHeight data[0]: " + iArr[0] + ",data[1]: " + iArr[1]);
        if (this.mHeightType == 1) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "英制切换到公制");
            this.heightInch = UnitUtil.inch2inch(iArr);
            this.heightCm = UnitUtil.inchs2cm(this.heightInch);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "英制=" + this.heightInch + ",公制=" + this.heightCm);
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "公制切换到英制");
        this.heightCm = iArr[0] + iArr[1];
        this.heightInch = UnitUtil.cm2inchs(this.heightCm);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "英制=" + this.heightInch + ",公制=" + this.heightCm);
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.Presenter
    public void checkHeight(int[] iArr) {
        float fInch2foot;
        if (this.mHeightType == 1) {
            fInch2foot = iArr[0] + iArr[1];
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "checkHeight公制的身高：" + fInch2foot);
            int userGender = AccountRepository.getInstance().getUserGender();
            if (userGender == 1 && iArr[0] + iArr[1] == 180) {
                this.isManualSetData = false;
            } else if (userGender == 2 && iArr[0] + iArr[1] == 160) {
                this.isManualSetData = false;
            } else {
                this.isManualSetData = true;
            }
        } else {
            fInch2foot = UnitUtil.inch2foot(iArr);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "checkHeight英制的身高：" + fInch2foot);
            int userGender2 = AccountRepository.getInstance().getUserGender();
            if (userGender2 == 1 && iArr[0] + iArr[1] == 16) {
                this.isManualSetData = false;
            } else if (userGender2 == 2 && iArr[0] + iArr[1] == 8) {
                this.isManualSetData = false;
            } else {
                this.isManualSetData = true;
            }
        }
        updateHeight(fInch2foot, this.mHeightType);
    }

    private void updateHeight(float f2, int i) {
        AccountRepository.getInstance().updateHeight(f2, i);
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(RunTimeUtil.getInstance().getUserId());
        if (unitSettingQueryUnitSetting != null) {
            unitSettingQueryUnitSetting.setUnit(i);
            unitSettingQueryUnitSetting.update();
        } else {
            UnitSetting unitSetting = new UnitSetting();
            unitSetting.setUnit(i);
            unitSetting.setUserId(RunTimeUtil.getInstance().getUserId());
            GreenDaoUtil.addUnitSetting(unitSetting);
        }
        this.mView.setForward();
    }
}