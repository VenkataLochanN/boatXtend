package com.ido.life.module.user.set.settingitem;

import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.net.BaseEntity;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.PrivateSettingSaveBean;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.MapEngine;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SettingItemPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\u0006\u0010$\u001a\u00020\u001dJ\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0005R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001a\u0010\u0019\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\t¨\u0006'"}, d2 = {"Lcom/ido/life/module/user/set/settingitem/SettingItemPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/set/settingitem/ISettingItemView;", "()V", "mSetType", "", "getMSetType", "()I", "setMSetType", "(I)V", "mSettingNewValue", "getMSettingNewValue", "setMSettingNewValue", "mSettingValueBefore", "getMSettingValueBefore", "setMSettingValueBefore", "mSupportTempUnitSetting", "", "getMSupportTempUnitSetting", "()Z", "setMSupportTempUnitSetting", "(Z)V", "mTempSettingNewValue", "getMTempSettingNewValue", "setMTempSettingNewValue", "mTempSettingValueBefore", "getMTempSettingValueBefore", "setMTempSettingValueBefore", "initConfig", "", "initTempUnitSetting", "saveMapSetToServer", "saveTempUnitToServer", "saveTimeSetToServer", "saveUnitSetToServer", "saveWeekStartToServer", "syncConfig", "updateConfig", "position", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingItemPresenter extends BasePresenter<ISettingItemView> {
    private boolean mSupportTempUnitSetting;
    private int mSettingValueBefore = -1;
    private int mSettingNewValue = -1;
    private int mSetType = -1;
    private int mTempSettingValueBefore = -1;
    private int mTempSettingNewValue = -1;

    public static final /* synthetic */ ISettingItemView access$getView(SettingItemPresenter settingItemPresenter) {
        return settingItemPresenter.getView();
    }

    public final int getMSettingValueBefore() {
        return this.mSettingValueBefore;
    }

    public final void setMSettingValueBefore(int i) {
        this.mSettingValueBefore = i;
    }

    public final int getMSettingNewValue() {
        return this.mSettingNewValue;
    }

    public final void setMSettingNewValue(int i) {
        this.mSettingNewValue = i;
    }

    public final int getMSetType() {
        return this.mSetType;
    }

    public final void setMSetType(int i) {
        this.mSetType = i;
    }

    public final int getMTempSettingValueBefore() {
        return this.mTempSettingValueBefore;
    }

    public final void setMTempSettingValueBefore(int i) {
        this.mTempSettingValueBefore = i;
    }

    public final int getMTempSettingNewValue() {
        return this.mTempSettingNewValue;
    }

    public final void setMTempSettingNewValue(int i) {
        this.mTempSettingNewValue = i;
    }

    public final boolean getMSupportTempUnitSetting() {
        return this.mSupportTempUnitSetting;
    }

    public final void setMSupportTempUnitSetting(boolean z) {
        this.mSupportTempUnitSetting = z;
    }

    public final void initConfig() {
        if (this.mSetType == SettingItemActivity.INSTANCE.getTYPE_SETTING_UNIT()) {
            initTempUnitSetting();
        }
    }

    private final void initTempUnitSetting() {
        this.mSupportTempUnitSetting = false;
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            this.mSupportTempUnitSetting = supportFunctionInfo.support_fahrenheit;
        }
    }

    public final void updateConfig(int position) {
        int i = this.mSetType;
        if (i == SettingItemActivity.INSTANCE.getTYPE_SETTING_MAP_ENGINE()) {
            if (position == 0) {
                this.mSettingNewValue = 1;
                return;
            } else {
                this.mSettingNewValue = 2;
                return;
            }
        }
        if (i == SettingItemActivity.INSTANCE.getTYPE_SETTING_UNIT()) {
            if (position == 0) {
                this.mSettingNewValue = 1;
                return;
            }
            if (position == 1) {
                this.mSettingNewValue = 2;
                return;
            } else if (position == 3) {
                this.mTempSettingNewValue = 1;
                return;
            } else {
                if (position != 4) {
                    return;
                }
                this.mTempSettingNewValue = 2;
                return;
            }
        }
        if (i == SettingItemActivity.INSTANCE.getTYPE_SETTING_WEEK_START()) {
            if (position == 0) {
                this.mSettingNewValue = 1;
                return;
            } else if (position == 1) {
                this.mSettingNewValue = 7;
                return;
            } else {
                this.mSettingNewValue = 2;
                return;
            }
        }
        if (i == SettingItemActivity.INSTANCE.getTYPE_SETTING_TIME()) {
            if (position == 0) {
                this.mSettingNewValue = 0;
            } else if (position == 1) {
                this.mSettingNewValue = 2;
            } else {
                this.mSettingNewValue = 1;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
    
        if (r1 != (-1)) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void syncConfig() {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.set.settingitem.SettingItemPresenter.syncConfig():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.life.database.model.MapEngine] */
    private final void saveMapSetToServer() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            objectRef.element = GreenDaoUtil.queryMapEngine(runTimeUtil.getUserId());
            if (((MapEngine) objectRef.element) != null) {
                PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
                privateSettingSaveBean.timestamp = ((MapEngine) objectRef.element).getUpdateTime();
                privateSettingSaveBean.value = ((MapEngine) objectRef.element).getMapEngine() == 1 ? MapEngine.TYPE_GOOGLE_MAP : MapEngine.TYPE_AMAP;
                ISettingItemView view = getView();
                if (view != null) {
                    view.showLoading();
                }
                AccountManager.saveMapEngineSetting(privateSettingSaveBean, new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.settingitem.SettingItemPresenter.saveMapSetToServer.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onSuccess(BaseEntity t) {
                        if (((MapEngine) objectRef.element) != null) {
                            try {
                                ((MapEngine) objectRef.element).setHasUpload(true);
                                ((MapEngine) objectRef.element).update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                GreenDaoUtil.addMapEngine((MapEngine) objectRef.element);
                            }
                        }
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }

                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onFailed(String message) {
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }
                });
                return;
            }
            ISettingItemView view2 = getView();
            if (view2 != null) {
                view2.dismissLoading();
            }
            ISettingItemView view3 = getView();
            if (view3 != null) {
                view3.onConfigSuccess();
                return;
            }
            return;
        }
        ISettingItemView view4 = getView();
        if (view4 != null) {
            view4.dismissLoading();
        }
        ISettingItemView view5 = getView();
        if (view5 != null) {
            view5.onConfigSuccess();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.life.database.model.TimeSet] */
    private final void saveTimeSetToServer() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            objectRef.element = GreenDaoUtil.queryTimeSet(runTimeUtil.getUserId());
            if (((TimeSet) objectRef.element) != null) {
                PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
                int timeFormat = ((TimeSet) objectRef.element).getTimeFormat();
                if (timeFormat == 1) {
                    privateSettingSaveBean.value = TimeSet.KEY_HOUR_24;
                } else if (timeFormat == 2) {
                    privateSettingSaveBean.value = TimeSet.KEY_HOUR_12;
                } else {
                    privateSettingSaveBean.value = TimeSet.KEY_FLOW_SYSTEM;
                }
                privateSettingSaveBean.timestamp = ((TimeSet) objectRef.element).getUpdateTime();
                ISettingItemView view = getView();
                if (view != null) {
                    view.showLoading();
                }
                AccountManager.saveTimeSetting(privateSettingSaveBean, new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.settingitem.SettingItemPresenter.saveTimeSetToServer.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onSuccess(BaseEntity t) {
                        if (((TimeSet) objectRef.element) != null) {
                            try {
                                ((TimeSet) objectRef.element).setHasUpload(true);
                                ((TimeSet) objectRef.element).update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                GreenDaoUtil.addTimeSet((TimeSet) objectRef.element);
                            }
                        }
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }

                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onFailed(String message) {
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }
                });
                return;
            }
            ISettingItemView view2 = getView();
            if (view2 != null) {
                view2.dismissLoading();
            }
            ISettingItemView view3 = getView();
            if (view3 != null) {
                view3.onConfigSuccess();
                return;
            }
            return;
        }
        ISettingItemView view4 = getView();
        if (view4 != null) {
            view4.dismissLoading();
        }
        ISettingItemView view5 = getView();
        if (view5 != null) {
            view5.onConfigSuccess();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.life.database.model.WeekStartSetting] */
    private final void saveWeekStartToServer() {
        String str;
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            objectRef.element = GreenDaoUtil.queryWeekStart(runTimeUtil.getUserId());
            if (((WeekStartSetting) objectRef.element) != null) {
                PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
                privateSettingSaveBean.timestamp = ((WeekStartSetting) objectRef.element).getUpdateTime();
                if (((WeekStartSetting) objectRef.element).getWeekStart() == 2) {
                    str = WeekStartSetting.MONDAY;
                } else {
                    str = ((WeekStartSetting) objectRef.element).getWeekStart() == 7 ? WeekStartSetting.SATURDAY : WeekStartSetting.SUNDAY;
                }
                privateSettingSaveBean.value = str;
                ISettingItemView view = getView();
                if (view != null) {
                    view.showLoading();
                }
                AccountManager.saveWeekStartSetting(privateSettingSaveBean, new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.settingitem.SettingItemPresenter.saveWeekStartToServer.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onSuccess(BaseEntity t) {
                        if (((WeekStartSetting) objectRef.element) != null) {
                            try {
                                ((WeekStartSetting) objectRef.element).setHasUpload(true);
                                ((WeekStartSetting) objectRef.element).update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                GreenDaoUtil.addWeekStart((WeekStartSetting) objectRef.element);
                            }
                        }
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }

                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onFailed(String message) {
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }
                });
                return;
            }
            ISettingItemView view2 = getView();
            if (view2 != null) {
                view2.dismissLoading();
            }
            ISettingItemView view3 = getView();
            if (view3 != null) {
                view3.onConfigSuccess();
                return;
            }
            return;
        }
        ISettingItemView view4 = getView();
        if (view4 != null) {
            view4.dismissLoading();
        }
        ISettingItemView view5 = getView();
        if (view5 != null) {
            view5.onConfigSuccess();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.life.database.model.UnitSetting] */
    private final void saveUnitSetToServer() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            objectRef.element = GreenDaoUtil.queryUnitSetting(runTimeUtil.getUserId());
            if (((UnitSetting) objectRef.element) != null) {
                PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
                privateSettingSaveBean.timestamp = ((UnitSetting) objectRef.element).getUpdateTime();
                privateSettingSaveBean.value = ((UnitSetting) objectRef.element).getUnit() == 1 ? UnitSetting.METRIC_SYSTEM_UNIT : UnitSetting.BRITISH_SYSTEM_UNIT;
                ISettingItemView view = getView();
                if (view != null) {
                    view.showLoading();
                }
                AccountManager.saveSystemUnitSetting(privateSettingSaveBean, new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.settingitem.SettingItemPresenter.saveUnitSetToServer.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onSuccess(BaseEntity t) {
                        if (((UnitSetting) objectRef.element) != null) {
                            ((UnitSetting) objectRef.element).setHasUpload(true);
                            try {
                                ((UnitSetting) objectRef.element).update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                GreenDaoUtil.addUnitSetting((UnitSetting) objectRef.element);
                            }
                        }
                        if (SettingItemPresenter.this.getMTempSettingNewValue() == 1 || SettingItemPresenter.this.getMTempSettingNewValue() == 2) {
                            SettingItemPresenter.this.saveTempUnitToServer();
                            return;
                        }
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }

                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onFailed(String message) {
                        if (SettingItemPresenter.this.getMTempSettingNewValue() == 1 || SettingItemPresenter.this.getMTempSettingNewValue() == 2) {
                            SettingItemPresenter.this.saveTempUnitToServer();
                            return;
                        }
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigFailed();
                        }
                    }
                });
                return;
            }
            ISettingItemView view2 = getView();
            if (view2 != null) {
                view2.dismissLoading();
            }
            ISettingItemView view3 = getView();
            if (view3 != null) {
                view3.onConfigSuccess();
                return;
            }
            return;
        }
        ISettingItemView view4 = getView();
        if (view4 != null) {
            view4.dismissLoading();
        }
        ISettingItemView view5 = getView();
        if (view5 != null) {
            view5.onConfigSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.ido.life.database.model.TempUnitSetting] */
    public final void saveTempUnitToServer() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            objectRef.element = GreenDaoUtil.queryTempUnitSetting(runTimeUtil.getUserId());
            if (((TempUnitSetting) objectRef.element) != null) {
                PrivateSettingSaveBean privateSettingSaveBean = new PrivateSettingSaveBean();
                privateSettingSaveBean.timestamp = ((TempUnitSetting) objectRef.element).getUpdateTime();
                privateSettingSaveBean.value = ((TempUnitSetting) objectRef.element).getTemp() == 1 ? TempUnitSetting.KEY_C : TempUnitSetting.KEY_F;
                ISettingItemView view = getView();
                if (view != null) {
                    view.showLoading();
                }
                AccountManager.saveTempSetting(privateSettingSaveBean, new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.module.user.set.settingitem.SettingItemPresenter.saveTempUnitToServer.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onSuccess(BaseEntity t) {
                        if (((TempUnitSetting) objectRef.element) != null) {
                            try {
                                ((TempUnitSetting) objectRef.element).setHasUpload(true);
                                ((TempUnitSetting) objectRef.element).update();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                GreenDaoUtil.saveTempUnitSetting((TempUnitSetting) objectRef.element);
                            }
                        }
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigSuccess();
                        }
                    }

                    @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                    public void onFailed(String message) {
                        ISettingItemView iSettingItemViewAccess$getView = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView != null) {
                            iSettingItemViewAccess$getView.dismissLoading();
                        }
                        ISettingItemView iSettingItemViewAccess$getView2 = SettingItemPresenter.access$getView(SettingItemPresenter.this);
                        if (iSettingItemViewAccess$getView2 != null) {
                            iSettingItemViewAccess$getView2.onConfigFailed();
                        }
                    }
                });
                return;
            }
            ISettingItemView view2 = getView();
            if (view2 != null) {
                view2.dismissLoading();
            }
            ISettingItemView view3 = getView();
            if (view3 != null) {
                view3.onConfigSuccess();
                return;
            }
            return;
        }
        ISettingItemView view4 = getView();
        if (view4 != null) {
            view4.dismissLoading();
        }
        ISettingItemView view5 = getView();
        if (view5 != null) {
            view5.onConfigSuccess();
        }
    }
}