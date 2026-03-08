package com.ido.life.module.home.menstrualcycle.guide;

import com.google.android.gms.fitness.FitnessActivities;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.PrivateSettingSaveBean;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Typography;

/* JADX INFO: compiled from: MenstrualCycleGuidePresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/guide/MenstrualCycleGuidePresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/menstrualcycle/guide/IMenstrualCycleGuideView;", "()V", "TAG", "", "mCycleTime", "", "getMCycleTime", "()J", "setMCycleTime", "(J)V", "mMensCycle", "", "getMMensCycle", "()I", "setMMensCycle", "(I)V", "mMensLength", "getMMensLength", "setMMensLength", "generateHistoryData", "", "config", "Lcom/ido/life/database/model/MenstruationConfig;", "saveSetting", "uploadMensConfig", "requestBody", "Lcom/ido/life/bean/PrivateSettingSaveBean;", "uploadToServer", "MensRequestInfo", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrualCycleGuidePresenter extends BasePresenter<IMenstrualCycleGuideView> {
    private final String TAG = "MenstrualCycleGuidePresenter";
    private int mMensLength = -1;
    private int mMensCycle = -1;
    private long mCycleTime = -1;

    public static final /* synthetic */ IMenstrualCycleGuideView access$getView(MenstrualCycleGuidePresenter menstrualCycleGuidePresenter) {
        return menstrualCycleGuidePresenter.getView();
    }

    public final int getMMensLength() {
        return this.mMensLength;
    }

    public final void setMMensLength(int i) {
        this.mMensLength = i;
    }

    public final int getMMensCycle() {
        return this.mMensCycle;
    }

    public final void setMMensCycle(int i) {
        this.mMensCycle = i;
    }

    public final long getMCycleTime() {
        return this.mCycleTime;
    }

    public final void setMCycleTime(long j) {
        this.mCycleTime = j;
    }

    public final void saveSetting() {
        CommonLogUtil.printAndSave("用户设置的经期长度是" + this.mMensLength + ",周期长度是" + this.mMensCycle + ",最近一次经期开始时间" + this.mCycleTime + Typography.quote);
        MenstruationConfig menstruationConfig = new MenstruationConfig();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        menstruationConfig.setUserId(runTimeUtil.getUserId());
        menstruationConfig.setMensLength(this.mMensLength);
        menstruationConfig.setMensCycle(this.mMensCycle);
        menstruationConfig.setUpdateTimeStamp(this.mCycleTime);
        menstruationConfig.setUploadSuccess(false);
        GreenDaoUtil.addMenstruationConfig(menstruationConfig);
        generateHistoryData(menstruationConfig);
        if (RunTimeUtil.getInstance().hasLogin() && NetworkUtil.isConnected(IdoApp.getAppContext())) {
            uploadToServer(menstruationConfig);
            return;
        }
        IMenstrualCycleGuideView view = getView();
        if (view != null) {
            view.hideLoading();
        }
        IMenstrualCycleGuideView view2 = getView();
        if (view2 != null) {
            view2.saveSuccess();
        }
    }

    private final void generateHistoryData(MenstruationConfig config) {
        List<List<Integer>> itemList;
        List list;
        HomeHelperKt.printAndSave$default("generateHistoryData config = " + GsonUtil.toJson(config), null, 2, null);
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTimeInMillis(config.getUpdateTimeStamp());
        LifeCycleItemBean lifeCycleItemBean = (LifeCycleItemBean) null;
        calendar.add(5, config.getMensCycle() - 14);
        int i = calendar.get(5);
        calendar.add(5, -(config.getMensCycle() - 14));
        int mensLength = config.getMensLength();
        if (1 <= mensLength) {
            int i2 = -1;
            LifeCycleItemBean lifeCycleItemBean2 = lifeCycleItemBean;
            int i3 = 1;
            int i4 = -1;
            while (true) {
                if (i2 != calendar.get(2) || i4 != calendar.get(1)) {
                    int i5 = calendar.get(2);
                    int i6 = calendar.get(1);
                    if (lifeCycleItemBean2 != null) {
                        GreenDaoUtil.insertLifeCycle(lifeCycleItemBean2);
                    }
                    lifeCycleItemBean2 = new LifeCycleItemBean();
                    lifeCycleItemBean2.setUserId(config.getUserId());
                    lifeCycleItemBean2.setMonth(DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_3));
                    lifeCycleItemBean2.setMensesCycle(config.getMensCycle());
                    lifeCycleItemBean2.setMensesDays(config.getMensLength());
                    lifeCycleItemBean2.setItemList(new ArrayList());
                    lifeCycleItemBean2.setTimeStamp(calendar.getTimeInMillis());
                    lifeCycleItemBean2.setMensesStartDay(calendar.get(5));
                    lifeCycleItemBean2.getItemList().add(new ArrayList());
                    List<List<Integer>> itemList2 = lifeCycleItemBean2.getItemList();
                    Intrinsics.checkExpressionValueIsNotNull(itemList2, "lifeCycleItemBean.itemList");
                    ((List) CollectionsKt.last((List) itemList2)).add(Integer.valueOf(calendar.get(5)));
                    lifeCycleItemBean2.setUpload(false);
                    lifeCycleItemBean2.setNeedSyncToDevice(true);
                    lifeCycleItemBean2.setOvulationDay(i);
                    BLEDevice lastConnectedDeviceInfo = LocalDataManager.getLastConnectedDeviceInfo();
                    if (lastConnectedDeviceInfo != null) {
                        lifeCycleItemBean2.setSourceMac(lastConnectedDeviceInfo.mDeviceAddress);
                        lifeCycleItemBean2.setDeviceName(lastConnectedDeviceInfo.mDeviceName);
                    }
                    MenstrualRemind menstrualRemind = LocalDataManager.getMenstrualRemind();
                    if (menstrualRemind != null) {
                        lifeCycleItemBean2.setPregnancyDayBeforeRemind(menstrualRemind.pregnancy_day_before_remind);
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Integer.valueOf(menstrualRemind.hour), Integer.valueOf(menstrualRemind.minute)};
                        String str = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                        lifeCycleItemBean2.setReminderTime(str);
                    } else {
                        lifeCycleItemBean2.setPregnancyDayBeforeRemind(3);
                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                        Object[] objArr2 = {20, 0};
                        String str2 = String.format("%02d:%02d", Arrays.copyOf(objArr2, objArr2.length));
                        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                        lifeCycleItemBean2.setReminderTime(str2);
                    }
                    i2 = i5;
                    i4 = i6;
                } else if (lifeCycleItemBean2 != null && (itemList = lifeCycleItemBean2.getItemList()) != null && (list = (List) CollectionsKt.last((List) itemList)) != null) {
                    list.add(Integer.valueOf(calendar.get(5)));
                }
                calendar.add(5, 1);
                if (calendar.getTimeInMillis() > System.currentTimeMillis() || i3 == mensLength) {
                    break;
                } else {
                    i3++;
                }
            }
            lifeCycleItemBean = lifeCycleItemBean2;
        }
        HomeHelperKt.printAndSave$default("generateHistoryData lifeCycleItemBean = " + GsonUtil.toJson(lifeCycleItemBean), null, 2, null);
        if (lifeCycleItemBean != null) {
            GreenDaoUtil.insertLifeCycle(lifeCycleItemBean);
        }
    }

    private final void uploadToServer(MenstruationConfig config) {
        PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
        privateSettingSaveBean.timestamp = config.getUpdateTimeStamp();
        privateSettingSaveBean.version = 1;
        privateSettingSaveBean.value = GsonUtil.toJson(new MensRequestInfo(config.getMensLength(), config.getMensCycle()));
        uploadMensConfig(privateSettingSaveBean);
    }

    private final void uploadMensConfig(PrivateSettingSaveBean requestBody) {
        HomeHelperKt.printAndSave("开始保存主账号生理周期配置信息", this.TAG);
        IMenstrualCycleGuideView view = getView();
        if (view != null) {
            view.showLoading();
        }
        AccountManager.saveMensConfig(requestBody, new AccountManager.CommonCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.module.home.menstrualcycle.guide.MenstrualCycleGuidePresenter.uploadMensConfig.1
            @Override // com.ido.life.data.me.remote.AccountManager.CommonCallback
            public void onSuccess(BaseEntityNew<Boolean> t) {
                HomeHelperKt.printAndSave("主账号生理周期配置信息保存成功", MenstrualCycleGuidePresenter.this.TAG);
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(runTimeUtil.getUserId());
                if (menstruationConfigQueryMenstruationConfig != null) {
                    menstruationConfigQueryMenstruationConfig.setUploadSuccess(true);
                    menstruationConfigQueryMenstruationConfig.update();
                }
                IMenstrualCycleGuideView iMenstrualCycleGuideViewAccess$getView = MenstrualCycleGuidePresenter.access$getView(MenstrualCycleGuidePresenter.this);
                if (iMenstrualCycleGuideViewAccess$getView != null) {
                    iMenstrualCycleGuideViewAccess$getView.hideLoading();
                }
                IMenstrualCycleGuideView iMenstrualCycleGuideViewAccess$getView2 = MenstrualCycleGuidePresenter.access$getView(MenstrualCycleGuidePresenter.this);
                if (iMenstrualCycleGuideViewAccess$getView2 != null) {
                    iMenstrualCycleGuideViewAccess$getView2.saveSuccess();
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.CommonCallback
            public void onFailed(int code, String errorMsg) {
                HomeHelperKt.printAndSave("主账号生理周期配置信息保存失败,code=" + code + ",message=" + errorMsg, MenstrualCycleGuidePresenter.this.TAG);
                IMenstrualCycleGuideView iMenstrualCycleGuideViewAccess$getView = MenstrualCycleGuidePresenter.access$getView(MenstrualCycleGuidePresenter.this);
                if (iMenstrualCycleGuideViewAccess$getView != null) {
                    iMenstrualCycleGuideViewAccess$getView.hideLoading();
                }
                IMenstrualCycleGuideView iMenstrualCycleGuideViewAccess$getView2 = MenstrualCycleGuidePresenter.access$getView(MenstrualCycleGuidePresenter.this);
                if (iMenstrualCycleGuideViewAccess$getView2 != null) {
                    iMenstrualCycleGuideViewAccess$getView2.saveSuccess();
                }
            }
        });
    }

    /* JADX INFO: compiled from: MenstrualCycleGuidePresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/guide/MenstrualCycleGuidePresenter$MensRequestInfo;", "", "mensLength", "", "mensCycle", "(II)V", "getMensCycle", "()I", "getMensLength", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class MensRequestInfo {
        private final int mensCycle;
        private final int mensLength;

        public static /* synthetic */ MensRequestInfo copy$default(MensRequestInfo mensRequestInfo, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = mensRequestInfo.mensLength;
            }
            if ((i3 & 2) != 0) {
                i2 = mensRequestInfo.mensCycle;
            }
            return mensRequestInfo.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMensLength() {
            return this.mensLength;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMensCycle() {
            return this.mensCycle;
        }

        public final MensRequestInfo copy(int mensLength, int mensCycle) {
            return new MensRequestInfo(mensLength, mensCycle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MensRequestInfo)) {
                return false;
            }
            MensRequestInfo mensRequestInfo = (MensRequestInfo) other;
            return this.mensLength == mensRequestInfo.mensLength && this.mensCycle == mensRequestInfo.mensCycle;
        }

        public int hashCode() {
            return (Integer.valueOf(this.mensLength).hashCode() * 31) + Integer.valueOf(this.mensCycle).hashCode();
        }

        public String toString() {
            return "MensRequestInfo(mensLength=" + this.mensLength + ", mensCycle=" + this.mensCycle + ")";
        }

        public MensRequestInfo(int i, int i2) {
            this.mensLength = i;
            this.mensCycle = i2;
        }

        public final int getMensCycle() {
            return this.mensCycle;
        }

        public final int getMensLength() {
            return this.mensLength;
        }
    }
}