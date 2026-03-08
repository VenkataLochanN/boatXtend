package com.ido.life.module.user.usertarget;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes3.dex */
public class UserTargetPresenter extends BasePresenter<UserTargetView> {
    private static final String TAG = UserTargetPresenter.class.getSimpleName();
    private int mUnit;

    private void sendGoal2Device(int i) {
        if (NetworkUtil.isConnected(VeryFitApp.getApp())) {
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            Goal goal = new Goal();
            goal.sport_step = i;
            BLEManager.setGoalPending(goal);
            BLEManager.setGoal(goal);
            if (supportFunctionInfo != null && !supportFunctionInfo.ex_main3_distance_goal && !supportFunctionInfo.ex_main3_calorie_goal) {
            }
        }
    }

    public void initUserTarget() {
        this.mUnit = RunTimeUtil.getInstance().getUnitSet();
        getView().refreshUnitSetting(this.mUnit);
        if (!AccountRepository.getInstance().isUserSignIn()) {
            initDefaultTarget();
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            initDefaultTarget();
        } else {
            getView().showLoading();
            HealthRepository.getInstance().getUserTarget(new HealthManager.OnUserTargetCallback() { // from class: com.ido.life.module.user.usertarget.UserTargetPresenter.1
                @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
                public void onSuccess(UserTargetEntity userTargetEntity) {
                    ((UserTargetView) UserTargetPresenter.this.getView()).dismissLoading();
                    if (userTargetEntity.getResult() != null) {
                        UserTargetPresenter.this.initTarget(userTargetEntity);
                    } else {
                        UserTargetPresenter.this.initDefaultTarget();
                    }
                }

                @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
                public void onFailed(String str) {
                    ((UserTargetView) UserTargetPresenter.this.getView()).dismissLoading();
                    ((UserTargetView) UserTargetPresenter.this.getView()).showMessage(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDefaultTarget() {
        getView().setStep(10000);
        if (this.mUnit == 1) {
            getView().setWeight(50, this.mUnit);
        } else {
            getView().setWeight(110, this.mUnit);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTarget(UserTargetEntity userTargetEntity) {
        getView().setStep(userTargetEntity.getResult().getStep());
        if (this.mUnit == 1) {
            getView().setWeight(userTargetEntity.getResult().getWeightInt(), this.mUnit);
        } else {
            getView().setWeight(Math.round(UnitUtil.getKg2Pound(userTargetEntity.getResult().getWeight())), this.mUnit);
        }
    }

    public int getUnit() {
        return this.mUnit;
    }

    public void saveInfo(int i, int i2) {
        long userId;
        long updateTime;
        int iRound = i;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "注册时的设置目标界面，保存的值：" + String.format("weight=%d,step=%d", Integer.valueOf(i), Integer.valueOf(i2)));
        String currentDayDate = TimeUtil.getCurrentDayDate();
        long userId2 = RunTimeUtil.getInstance().getUserId();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "注册时的设置目标界面，获取到的userId：" + userId2);
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(userId2);
        if (userInfoQueryUserInfo != null) {
            userId = userInfoQueryUserInfo.getUserId();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "userInfo!=null，userInfo的userId：" + userId);
        } else {
            userId = -1;
        }
        getView().showLoading();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "查询数据库获取UserTargetNew时的userId：" + userId);
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(userId);
        if (userTargetNewQueryUserLatestTarget != null) {
            userTargetNewQueryUserLatestTarget.setWeight(iRound);
            userTargetNewQueryUserLatestTarget.setStep(i2);
            userTargetNewQueryUserLatestTarget.setWeightUnit(userInfoQueryUserInfo.getWeightUnit());
            userTargetNewQueryUserLatestTarget.setHasUpload(false);
            userTargetNewQueryUserLatestTarget.update();
            updateTime = userTargetNewQueryUserLatestTarget.getUpdateTime();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "数据库targetNew不为空：" + userTargetNewQueryUserLatestTarget.toString());
        } else {
            UserTargetNew userTargetNew = new UserTargetNew();
            userTargetNew.setUserId(userId);
            userTargetNew.setHasUpload(false);
            userTargetNew.setStep(i2);
            userTargetNew.setWeightUnit(userInfoQueryUserInfo.getWeightUnit());
            userTargetNew.setWeight(iRound);
            GreenDaoUtil.addUserTarget(userTargetNew);
            updateTime = userTargetNew.getUpdateTime();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "数据库targetNew为空,新建一个：" + userTargetNew.toString());
        }
        if (userInfoQueryUserInfo.getWeightUnit() == 2) {
            iRound = Math.round(UnitUtil.getPound2Kg(iRound));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "选择的是英制单位，先转成公制再上传服务器：weight = " + iRound);
        }
        int i3 = iRound;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "上传服务器最终的体重目标finalWeight：" + i3);
        if (userId == -1) {
            getView().showFirstSuccess();
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            getView().showFirstSuccess();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "无网络后，先保存目标在本地，直接下一步。");
        } else {
            HealthRepository.getInstance().setUserTarget(currentDayDate, i2, i3, updateTime, new OnResultCallback() { // from class: com.ido.life.module.user.usertarget.UserTargetPresenter.2
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), UserTargetPresenter.TAG, "上传服务器体重onSuccess：" + result.toString());
                    if (UserTargetPresenter.this.getView() != null) {
                        ((UserTargetView) UserTargetPresenter.this.getView()).dismissLoading();
                        ((UserTargetView) UserTargetPresenter.this.getView()).showFirstSuccess();
                    }
                    EventBusHelper.post(106);
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str) {
                    ((UserTargetView) UserTargetPresenter.this.getView()).dismissLoading();
                    ((UserTargetView) UserTargetPresenter.this.getView()).showMessage(str);
                    EventBusHelper.post(106);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), UserTargetPresenter.TAG, "上传注册时设置的目标到服务器失败：" + str);
                }
            });
        }
    }
}