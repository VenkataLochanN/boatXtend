package com.ido.life.module.user.set.target;

import com.ido.common.IdoApp;
import com.ido.common.net.http.Result;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.constants.Constants;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: SettingTargetPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lcom/ido/life/module/user/set/target/SettingTargetPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/set/target/ISettingTargetView;", "()V", "getTargetInfo", "", "saveTargetInfo", "step", "", "weight", "weightUnit", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingTargetPresenter extends BasePresenter<ISettingTargetView> {
    public static final /* synthetic */ ISettingTargetView access$getView(SettingTargetPresenter settingTargetPresenter) {
        return settingTargetPresenter.getView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String] */
    public final void saveTargetInfo(int step, int weight, int weightUnit) {
        long updateTime;
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = DateUtil.format(Calendar.getInstance(Locale.CHINA), DateUtil.DATE_FORMAT_YMD);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserTargetNew userTargetNewQueryUserTarget = GreenDaoUtil.queryUserTarget(runTimeUtil.getUserId(), (String) objectRef.element);
        if (userTargetNewQueryUserTarget != null) {
            userTargetNewQueryUserTarget.setWeight(weight);
            userTargetNewQueryUserTarget.setWeightUnit(weightUnit);
            userTargetNewQueryUserTarget.setStep(step);
            userTargetNewQueryUserTarget.setHasUpload(false);
            userTargetNewQueryUserTarget.setHasSyncToDevice(false);
            userTargetNewQueryUserTarget.update();
            updateTime = userTargetNewQueryUserTarget.getUpdateTime();
        } else {
            UserTargetNew userTargetNew = new UserTargetNew();
            userTargetNew.setStep(step);
            userTargetNew.setWeight(weight);
            userTargetNew.setWeightUnit(weightUnit);
            userTargetNew.setDate((String) objectRef.element);
            RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
            userTargetNew.setUserId(runTimeUtil2.getUserId());
            GreenDaoUtil.addUserTarget(userTargetNew);
            updateTime = userTargetNew.getUpdateTime();
        }
        long j = updateTime;
        EventBusHelper.post(Constants.EventConstants.EVENT_SYNC_STEP_TO_DEVICE);
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            ISettingTargetView view = getView();
            if (view != null) {
                view.showLoading();
            }
            if (weightUnit == 2) {
                weight = MathKt.roundToInt(UnitUtil.getPound2Kg(weight));
            }
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            HealthManager.setUserTarget(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD), step, weight, j, new OnResultCallback() { // from class: com.ido.life.module.user.set.target.SettingTargetPresenter.saveTargetInfo.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result<?> result) {
                    RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
                    UserTargetNew userTarget = GreenDaoUtil.queryUserTarget(runTimeUtil3.getUserId(), (String) objectRef.element);
                    Intrinsics.checkExpressionValueIsNotNull(userTarget, "userTarget");
                    userTarget.setHasUpload(true);
                    userTarget.update();
                    ISettingTargetView iSettingTargetViewAccess$getView = SettingTargetPresenter.access$getView(SettingTargetPresenter.this);
                    if (iSettingTargetViewAccess$getView != null) {
                        iSettingTargetViewAccess$getView.setTargetInfoSuccess();
                    }
                    ISettingTargetView iSettingTargetViewAccess$getView2 = SettingTargetPresenter.access$getView(SettingTargetPresenter.this);
                    if (iSettingTargetViewAccess$getView2 != null) {
                        iSettingTargetViewAccess$getView2.dismissLoading();
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String message) {
                    ISettingTargetView iSettingTargetViewAccess$getView = SettingTargetPresenter.access$getView(SettingTargetPresenter.this);
                    if (iSettingTargetViewAccess$getView != null) {
                        iSettingTargetViewAccess$getView.setTargetInfoSuccess();
                    }
                    ISettingTargetView iSettingTargetViewAccess$getView2 = SettingTargetPresenter.access$getView(SettingTargetPresenter.this);
                    if (iSettingTargetViewAccess$getView2 != null) {
                        iSettingTargetViewAccess$getView2.dismissLoading();
                    }
                }
            });
            return;
        }
        ISettingTargetView view2 = getView();
        if (view2 != null) {
            view2.setTargetInfoSuccess();
        }
    }

    public final void getTargetInfo() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil.getUserId());
        if (userTargetNewQueryUserLatestTarget != null) {
            ISettingTargetView view = getView();
            if (view != null) {
                view.getTargetInfoSuccess(userTargetNewQueryUserLatestTarget);
                return;
            }
            return;
        }
        ISettingTargetView view2 = getView();
        if (view2 != null) {
            view2.getTargetInfoFailed();
        }
    }
}