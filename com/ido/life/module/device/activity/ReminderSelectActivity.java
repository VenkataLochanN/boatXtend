package com.ido.life.module.device.activity;

import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.data.cache.HealthMonitoringManager;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.module.device.presenter.ReminderSelectPresenter;
import com.ido.life.module.device.view.IReminderSelectView;
import com.ido.life.util.DialogUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderSelectActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u0007H\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0014J\b\u0010\u001a\u001a\u00020\fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/ido/life/module/device/activity/ReminderSelectActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/ReminderSelectPresenter;", "Lcom/ido/life/module/device/view/IReminderSelectView;", "Lcom/ido/life/customview/ReminderSelectView$OnReminderChangedListener;", "()V", "func", "", "mScheduleReminderSwitch", "Lcom/ido/ble/protocol/model/ScheduleReminderSwitch;", "getLayoutResId", "initData", "", "initEvent", "initLabelLanguage", "initViews", "isDataChanged", "", "onBackPressed", "onGetReminderStatusFailed", "onGetReminderStatusSuccess", "onReminderChanged", "reminderStatus", "onSetReminderStatusFailed", "onSetReminderStatusSuccess", "sendCmd", "showConfirmDialog", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ReminderSelectActivity extends BaseActivity<ReminderSelectPresenter> implements IReminderSelectView, ReminderSelectView.OnReminderChangedListener {
    private HashMap _$_findViewCache;
    private int func = -1;
    private ScheduleReminderSwitch mScheduleReminderSwitch;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_reminder_select;
    }

    @Override // com.ido.life.module.device.view.IReminderSelectView
    public void onGetReminderStatusFailed() {
    }

    @Override // com.ido.life.module.device.view.IReminderSelectView
    public void onGetReminderStatusSuccess() {
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        Pair<Integer, Integer> healthModuleInfo = HealthMonitoringManager.INSTANCE.getHealthModuleInfo(this.func);
        this.mTitleBar.setTitle(healthModuleInfo != null ? getLanguageText(healthModuleInfo.getFirst().intValue()) : "");
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.ReminderSelectActivity.initEvent.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReminderSelectActivity.this.onBackPressed();
            }
        });
        ((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).setOnReminderChangedListener(this);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.func = getIntent().getIntExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, -1);
        ReminderSelectPresenter reminderSelectPresenter = (ReminderSelectPresenter) this.mPresenter;
        this.mScheduleReminderSwitch = reminderSelectPresenter != null ? reminderSelectPresenter.getReminderStatus() : null;
        ReminderSelectView reminderSelectView = (ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder);
        ScheduleReminderSwitch scheduleReminderSwitch = this.mScheduleReminderSwitch;
        reminderSelectView.select(scheduleReminderSwitch != null ? scheduleReminderSwitch.notify_flag : 3);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        showSettingLoading(true);
        ReminderSelectPresenter reminderSelectPresenter = (ReminderSelectPresenter) this.mPresenter;
        if (reminderSelectPresenter != null) {
            reminderSelectPresenter.setReminderStatus(this.mScheduleReminderSwitch);
        }
    }

    private final boolean isDataChanged() {
        String string = ((ReminderSelectPresenter) this.mPresenter).getReminderStatus().toString();
        return !Intrinsics.areEqual(string, this.mScheduleReminderSwitch != null ? r1.toString() : null);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            saveData();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
    public void onReminderChanged(int reminderStatus) {
        if (reminderStatus == 3) {
            showConfirmDialog();
            return;
        }
        ScheduleReminderSwitch scheduleReminderSwitch = this.mScheduleReminderSwitch;
        if (scheduleReminderSwitch != null) {
            scheduleReminderSwitch.notify_flag = reminderStatus;
        }
    }

    private final void showConfirmDialog() {
        DialogUtils dialogUtils = DialogUtils.INSTANCE;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        String languageText = getLanguageText(R.string.tips);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.tips)");
        String languageText2 = getLanguageText(R.string.scheduler_reminder_tips);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "getLanguageText(R.string.scheduler_reminder_tips)");
        DialogUtils.showTipsDialog$default(dialogUtils, supportFragmentManager, languageText, languageText2, null, null, 0, false, false, false, false, 0, new CommonDialog.SampleDialogEventListener() { // from class: com.ido.life.module.device.activity.ReminderSelectActivity.showConfirmDialog.1
            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onConfirmClick(CommonDialog dialog) {
                super.onConfirmClick(dialog);
                ScheduleReminderSwitch scheduleReminderSwitch = ReminderSelectActivity.this.mScheduleReminderSwitch;
                if (scheduleReminderSwitch != null) {
                    scheduleReminderSwitch.notify_flag = ((ReminderSelectView) ReminderSelectActivity.this._$_findCachedViewById(com.ido.life.R.id.vReminder)).getReminderStatus();
                }
                ReminderSelectActivity.this.saveData();
            }

            @Override // com.ido.life.dialog.CommonDialog.SampleDialogEventListener, com.ido.life.dialog.CommonDialog.OnDialogEventListener
            public void onCancelClick(CommonDialog dialog) {
                super.onCancelClick(dialog);
                ReminderSelectView reminderSelectView = (ReminderSelectView) ReminderSelectActivity.this._$_findCachedViewById(com.ido.life.R.id.vReminder);
                ScheduleReminderSwitch scheduleReminderSwitch = ReminderSelectActivity.this.mScheduleReminderSwitch;
                reminderSelectView.select(scheduleReminderSwitch != null ? scheduleReminderSwitch.notify_flag : 1);
            }
        }, 2040, null);
    }

    @Override // com.ido.life.module.device.view.IReminderSelectView
    public void onSetReminderStatusSuccess() {
        dismissLoadingDialog();
        setResult(-1);
        finish();
    }

    @Override // com.ido.life.module.device.view.IReminderSelectView
    public void onSetReminderStatusFailed() {
        dismissLoadingDialog();
        showToast(getLanguageText(R.string.public_set_failed));
        finish();
    }
}