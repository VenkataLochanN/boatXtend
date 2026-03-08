package com.ido.life.module.user.userdata.weight;

import com.ido.common.net.http.Result;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: WeightSettingPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, d2 = {"Lcom/ido/life/module/user/userdata/weight/WeightSettingPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/userdata/weight/IWeightSettingView;", "()V", "getUnit", "", "getUserWeight", "", "saveWeight", "", "weight", "uploadWeightRecord", "userInfo", "Lcom/ido/life/database/model/UserInfo;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeightSettingPresenter extends BasePresenter<IWeightSettingView> {
    public static final /* synthetic */ IWeightSettingView access$getView(WeightSettingPresenter weightSettingPresenter) {
        return weightSettingPresenter.getView();
    }

    public final float getUserWeight() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId());
        if (userInfoQueryUserInfo == null) {
            return 0;
        }
        float weight = userInfoQueryUserInfo.getWeight();
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        if (runTimeUtil2.getUnitSet() == 1) {
            return userInfoQueryUserInfo.getWeightUnit() == 2 ? UnitUtil.getPound2Kg(weight) : weight;
        }
        if (userInfoQueryUserInfo.getWeightUnit() == 1) {
            weight = UnitUtil.getKg2Pound(weight);
        }
        return MathKt.roundToInt(weight);
    }

    public final int getUnit() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getUnitSet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.ido.life.database.model.UserInfo] */
    public final void saveWeight(float weight) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        objectRef.element = GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId());
        if (((UserInfo) objectRef.element) == null) {
            IWeightSettingView view = getView();
            if (view != null) {
                view.saveWeightSuccess();
                return;
            }
            return;
        }
        ((UserInfo) objectRef.element).setWeight(weight);
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        if (runTimeUtil2.getUnitSet() == 2) {
            if (((UserInfo) objectRef.element).getWeightUnit() == 1) {
                ((UserInfo) objectRef.element).setWeight(UnitUtil.getPound2Kg(weight));
            }
        } else if (((UserInfo) objectRef.element).getWeightUnit() == 2) {
            ((UserInfo) objectRef.element).setWeight(UnitUtil.getKg2Pound(weight));
        }
        ((UserInfo) objectRef.element).setUploadSuccess(false);
        ((UserInfo) objectRef.element).update();
        uploadWeightRecord((UserInfo) objectRef.element);
        if (RunTimeUtil.getInstance().enableUploadPrivateData()) {
            UserInfo userInfoM27clone = ((UserInfo) objectRef.element).m27clone();
            Intrinsics.checkExpressionValueIsNotNull(userInfoM27clone, "userInfo.clone()");
            UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(((UserInfo) objectRef.element).getUserId());
            if (unitSettingQueryUnitSetting != null) {
                userInfoM27clone.setWeightUnit(unitSettingQueryUnitSetting.getUnit());
                userInfoM27clone.setHeightUnit(unitSettingQueryUnitSetting.getUnit());
            } else {
                userInfoM27clone.setWeightUnit(1);
                userInfoM27clone.setHeightUnit(1);
            }
            IWeightSettingView view2 = getView();
            if (view2 != null) {
                view2.showLoading();
            }
            AccountManager.updateUserInfo(userInfoM27clone, new OnResultCallback() { // from class: com.ido.life.module.user.userdata.weight.WeightSettingPresenter.saveWeight.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result<?> result) {
                    ((UserInfo) objectRef.element).setUploadSuccess(true);
                    ((UserInfo) objectRef.element).update();
                    IWeightSettingView iWeightSettingViewAccess$getView = WeightSettingPresenter.access$getView(WeightSettingPresenter.this);
                    if (iWeightSettingViewAccess$getView != null) {
                        iWeightSettingViewAccess$getView.dismissLoading();
                    }
                    IWeightSettingView iWeightSettingViewAccess$getView2 = WeightSettingPresenter.access$getView(WeightSettingPresenter.this);
                    if (iWeightSettingViewAccess$getView2 != null) {
                        iWeightSettingViewAccess$getView2.saveWeightSuccess();
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String message) {
                    IWeightSettingView iWeightSettingViewAccess$getView = WeightSettingPresenter.access$getView(WeightSettingPresenter.this);
                    if (iWeightSettingViewAccess$getView != null) {
                        iWeightSettingViewAccess$getView.dismissLoading();
                    }
                    IWeightSettingView iWeightSettingViewAccess$getView2 = WeightSettingPresenter.access$getView(WeightSettingPresenter.this);
                    if (iWeightSettingViewAccess$getView2 != null) {
                        iWeightSettingViewAccess$getView2.saveWeightSuccess();
                    }
                }
            });
            return;
        }
        IWeightSettingView view3 = getView();
        if (view3 != null) {
            view3.saveWeightSuccess();
        }
    }

    private final void uploadWeightRecord(UserInfo userInfo) {
        WeightItemBean weightItemBean = new WeightItemBean();
        if (userInfo.getWeightUnit() == 1) {
            weightItemBean.setTotalWeight(userInfo.getWeight());
        } else {
            weightItemBean.setTotalWeight(UnitUtil.getPound2Kg(userInfo.getWeight()));
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
        weightItemBean.setUploadSuccess(false);
        weightItemBean.setTimeStamp(calendar.getTimeInMillis());
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        weightItemBean.setUserId(runTimeUtil.getUserId());
        weightItemBean.setBmi(WeightBmiEnum.caluteBmi(weightItemBean.getTotalWeight(), userInfo.getHeightCm()));
        GreenDaoUtil.addWeight(weightItemBean);
    }
}