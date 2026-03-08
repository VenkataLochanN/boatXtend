package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.utils.GsonUtil;
import com.ido.life.bean.PrivateSettingSaveBean;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.module.device.view.IMenstrualCycleSettingView;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualCycleSettingPresenter extends BaseMonitoringPresenter<IMenstrualCycleSettingView> {
    private boolean isFirstSet;

    public boolean isFirstSet() {
        return this.isFirstSet;
    }

    public boolean isSupportPregnancyRemind() {
        return getSupportFunctionInfo().V3_v2_menstrual_remind_02_add_pregnancy;
    }

    public boolean isSupportSetMenstrualNotifyFlag() {
        return getSupportFunctionInfo().V3_menstrual_add_notify_flag;
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        removeCallBack();
    }

    public Menstrual getMenstrual() {
        Menstrual menstrual = LocalDataManager.getMenstrual();
        MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId());
        if (menstruationConfigQueryMenstruationConfig != null) {
            if (menstrual == null || menstrual.menstrual_length == 0) {
                this.isFirstSet = true;
                menstrual = new Menstrual();
                menstrual.on_off = 85;
                menstrual.ovulation_interval_day = 14;
                menstrual.ovulation_before_day = 5;
                menstrual.ovulation_after_day = 4;
            }
            menstrual.menstrual_length = menstruationConfigQueryMenstruationConfig.getMensLength();
            menstrual.menstrual_cycle = menstruationConfigQueryMenstruationConfig.getMensCycle();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(menstruationConfigQueryMenstruationConfig.getUpdateTimeStamp());
            String[] strArrSplit = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD).split("-");
            menstrual.last_menstrual_year = Integer.parseInt(strArrSplit[0]);
            menstrual.last_menstrual_month = Integer.parseInt(strArrSplit[1]);
            menstrual.last_menstrual_day = Integer.parseInt(strArrSplit[2]);
        } else if (menstrual == null || menstrual.menstrual_length == 0) {
            this.isFirstSet = true;
            menstrual = new Menstrual();
            menstrual.on_off = 85;
            menstrual.menstrual_length = 7;
            menstrual.menstrual_cycle = 28;
            int[] currentDate = DateUtil.getCurrentDate();
            menstrual.last_menstrual_year = currentDate[0];
            menstrual.last_menstrual_month = currentDate[1];
            menstrual.last_menstrual_day = currentDate[2];
        }
        List<LifeCycleItemBean> listQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(RunTimeUtil.getInstance().getUserId(), 2);
        if (listQueryLatestLifeCycle.size() > 0) {
            LifeCycleItemBean lifeCycleItemBean = listQueryLatestLifeCycle.get(0);
            LifeCycleItemBean lifeCycleItemBean2 = listQueryLatestLifeCycle.size() > 1 ? listQueryLatestLifeCycle.get(1) : null;
            if (lifeCycleItemBean == null || lifeCycleItemBean.getItemList() == null || lifeCycleItemBean.getItemList().size() == 0) {
                lifeCycleItemBean = null;
            }
            if (lifeCycleItemBean == null) {
                lifeCycleItemBean = lifeCycleItemBean2;
            }
            if (lifeCycleItemBean == null || lifeCycleItemBean.getItemList() == null || lifeCycleItemBean.getItemList().size() == 0) {
                lifeCycleItemBean = null;
            }
            Calendar calendar2 = Calendar.getInstance();
            if (lifeCycleItemBean != null) {
                Collections.sort(lifeCycleItemBean.getItemList(), new Comparator<List<Integer>>() { // from class: com.ido.life.module.device.presenter.MenstrualCycleSettingPresenter.1
                    @Override // java.util.Comparator
                    public int compare(List<Integer> list, List<Integer> list2) {
                        return list2.get(0).intValue() - list.get(0).intValue();
                    }
                });
                calendar2.setTime(DateUtil.string2Date(lifeCycleItemBean.getMonth(), DateUtil.DATE_FORMAT_YM_3));
                calendar2.set(5, lifeCycleItemBean.getItemList().get(0).get(0).intValue());
                if (lifeCycleItemBean2 != null && lifeCycleItemBean.getItemList().size() == 1) {
                    Collections.sort(lifeCycleItemBean2.getItemList(), new Comparator<List<Integer>>() { // from class: com.ido.life.module.device.presenter.MenstrualCycleSettingPresenter.2
                        @Override // java.util.Comparator
                        public int compare(List<Integer> list, List<Integer> list2) {
                            return list2.get(0).intValue() - list.get(0).intValue();
                        }
                    });
                    calendar2.add(5, -1);
                    if (TextUtils.equals(DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YM_3), lifeCycleItemBean2.getMonth())) {
                        List<Integer> list = lifeCycleItemBean2.getItemList().get(0);
                        if (calendar2.get(5) == list.get(list.size() - 1).intValue()) {
                            calendar2.set(5, list.get(0).intValue());
                        }
                    }
                }
                String[] strArrSplit2 = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD).split("-");
                menstrual.last_menstrual_year = Integer.parseInt(strArrSplit2[0]);
                menstrual.last_menstrual_month = Integer.parseInt(strArrSplit2[1]);
                menstrual.last_menstrual_day = Integer.parseInt(strArrSplit2[2]);
            }
        }
        if (isSupportSetMenstrualNotifyFlag() && menstrual.notify_flag == 0) {
            menstrual.notify_flag = 1;
        }
        menstrual.ovulation_interval_day = 14;
        menstrual.ovulation_before_day = 5;
        menstrual.ovulation_after_day = 4;
        return menstrual;
    }

    public MenstrualRemind getMenstrualRemind() {
        MenstrualRemind menstrualRemind = LocalDataManager.getMenstrualRemind();
        if (menstrualRemind == null || menstrualRemind.start_day == 0) {
            menstrualRemind = new MenstrualRemind();
            menstrualRemind.start_day = 3;
            menstrualRemind.ovulation_day = 3;
            menstrualRemind.hour = 20;
            menstrualRemind.minute = 0;
            menstrualRemind.pregnancy_day_before_remind = 3;
        }
        if (isSupportPregnancyRemind() && menstrualRemind.pregnancy_day_before_remind <= 0) {
            menstrualRemind.pregnancy_day_before_remind = 1;
        }
        return menstrualRemind;
    }

    public void sendMenstrualRemind2Device(MenstrualRemind menstrualRemind) {
        saveLog("sendMenstrualRemind2Device menstrualRemind = " + menstrualRemind);
        addCallBack();
        BLEManager.setMenstrualRemind(menstrualRemind);
    }

    public void sendMenstrual2Device(Menstrual menstrual) {
        saveLog("sendMenstrual2Device menstrual = " + menstrual);
        BLEManager.setMenstrual(menstrual);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdFailed(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdFailed(settingType);
        if (isAttachView()) {
            saveLog("onSetOtherCmdFailed settingType = " + settingType);
            int i = AnonymousClass4.$SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[settingType.ordinal()];
            if (i == 1 || i == 2) {
                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MENSTRUAL_CYCLE_REMINDER_FAILURE, "", null);
                ((IMenstrualCycleSettingView) getView()).onsetMenstrualFailed();
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.MenstrualCycleSettingPresenter$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType = new int[OtherProtocolCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.MENSTRUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[OtherProtocolCallBack.SettingType.MENSTRUAL_REMIND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetOtherCmdSuccess(OtherProtocolCallBack.SettingType settingType) {
        super.onSetOtherCmdSuccess(settingType);
        saveLog("onSetOtherCmdSuccess settingType = " + settingType);
        int i = AnonymousClass4.$SwitchMap$com$ido$ble$callback$OtherProtocolCallBack$SettingType[settingType.ordinal()];
        if (i == 1) {
            saveMenstrualData();
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MENSTRUAL_CYCLE_REMINDER_SUCCESS, "", null);
            if (isAttachView()) {
                ((IMenstrualCycleSettingView) getView()).onSetMenstrualSuccess();
                return;
            }
            return;
        }
        if (i == 2 && isAttachView()) {
            ((IMenstrualCycleSettingView) getView()).onsetMenstrualReminderSuccess();
        }
    }

    private void saveMenstrualData() {
        MenstruationConfig menstruationConfigQueryMenstruationConfig;
        MenstrualRemind menstrualRemind = LocalDataManager.getMenstrualRemind();
        if (menstrualRemind == null || (menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId())) == null) {
            return;
        }
        Menstrual menstrual = LocalDataManager.getMenstrual();
        if (menstrual != null) {
            menstruationConfigQueryMenstruationConfig.setMensCycle(menstrual.menstrual_cycle);
            menstruationConfigQueryMenstruationConfig.setMensLength(menstrual.menstrual_length);
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, menstrual.last_menstrual_year);
            calendar.set(2, menstrual.last_menstrual_month);
            calendar.set(5, menstrual.last_menstrual_day);
            menstruationConfigQueryMenstruationConfig.setUpdateTimeStamp(calendar.getTimeInMillis());
            menstruationConfigQueryMenstruationConfig.update();
        }
        LifeCycleItemBean lifeCycleItemBeanQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(RunTimeUtil.getInstance().getUserId());
        if (lifeCycleItemBeanQueryLatestLifeCycle != null) {
            lifeCycleItemBeanQueryLatestLifeCycle.setMensesStartDay(menstrualRemind.start_day);
            lifeCycleItemBeanQueryLatestLifeCycle.setOvulationDay(menstrualRemind.ovulation_day);
            lifeCycleItemBeanQueryLatestLifeCycle.setPregnancyDayBeforeRemind(menstrualRemind.pregnancy_day_before_remind);
            lifeCycleItemBeanQueryLatestLifeCycle.setReminderTime(String.format("%02d:%02d", Integer.valueOf(menstrualRemind.hour), Integer.valueOf(menstrualRemind.minute)));
            lifeCycleItemBeanQueryLatestLifeCycle.setMensesCycle(menstruationConfigQueryMenstruationConfig.getMensCycle());
            lifeCycleItemBeanQueryLatestLifeCycle.setMensesDays(menstruationConfigQueryMenstruationConfig.getMensLength());
            lifeCycleItemBeanQueryLatestLifeCycle.setTimeStamp(System.currentTimeMillis());
            lifeCycleItemBeanQueryLatestLifeCycle.setUpload(false);
            GreenDaoUtil.insertLifeCycle(lifeCycleItemBeanQueryLatestLifeCycle);
        }
    }

    public void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "MenstrualCycleSettingPresenter", str);
    }

    private class MensRequestInfo {
        private int mensCycle;
        private int mensLength;

        public MensRequestInfo(int i, int i2) {
            this.mensLength = i;
            this.mensCycle = i2;
        }
    }

    public void uploadToServer(MenstruationConfig menstruationConfig) {
        PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
        privateSettingSaveBean.timestamp = menstruationConfig.getUpdateTimeStamp();
        privateSettingSaveBean.version = 1;
        privateSettingSaveBean.value = GsonUtil.toJson(new MensRequestInfo(menstruationConfig.getMensLength(), menstruationConfig.getMensCycle()));
        uploadMensConfig(privateSettingSaveBean);
    }

    private void uploadMensConfig(PrivateSettingSaveBean privateSettingSaveBean) {
        final String simpleName = getClass().getSimpleName();
        CommonLogUtil.printAndSave("开始保存主账号生理周期配置信息", simpleName);
        AccountManager.saveMensConfig(privateSettingSaveBean, new AccountManager.CommonCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.module.device.presenter.MenstrualCycleSettingPresenter.3
            @Override // com.ido.life.data.me.remote.AccountManager.CommonCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                CommonLogUtil.printAndSave("主账号生理周期配置信息保存成功", simpleName);
                MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId());
                if (menstruationConfigQueryMenstruationConfig != null) {
                    menstruationConfigQueryMenstruationConfig.setUploadSuccess(true);
                    menstruationConfigQueryMenstruationConfig.update();
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.CommonCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.printAndSave("主账号生理周期配置信息保存失败,code=" + i + ",message=" + str, simpleName);
            }
        });
    }
}