package com.ido.life.module.activity;

import android.content.Intent;
import android.view.View;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.NotificationApp;
import com.ido.life.bean.NotificationAppKt;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.module.device.activity.NotificationSettingActivity;
import com.ido.life.module.presenter.ThirdPartyReminderPresenter;
import com.ido.life.module.view.IThirdPartyReminderView;
import com.ido.life.util.MsgNotificationHelper;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThirdPartyReminderActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\tH\u0016J-\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\t2\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u000bH\u0002J\b\u0010\u001a\u001a\u00020\u000bH\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/ido/life/module/activity/ThirdPartyReminderActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/presenter/ThirdPartyReminderPresenter;", "Lcom/ido/life/module/view/IThirdPartyReminderView;", "Lcom/ido/life/customview/ReminderSelectView$OnReminderChangedListener;", "()V", "mNotificationApp", "Lcom/ido/life/bean/NotificationApp;", "getLayoutResId", "", "initViews", "", "isStatusChanged", "", "onBackPressed", "onReminderChanged", "reminderStatus", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "openMissCall", "saveData", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ThirdPartyReminderActivity extends BaseActivity<ThirdPartyReminderPresenter> implements IThirdPartyReminderView, ReminderSelectView.OnReminderChangedListener {
    private HashMap _$_findViewCache;
    private NotificationApp mNotificationApp;

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
        return R.layout.activity_third_party_reminder;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.activity.ThirdPartyReminderActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartyReminderActivity.this.onBackPressed();
            }
        });
        this.mNotificationApp = (NotificationApp) getIntent().getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        CommonLogUtil.printAndSave("设置消息提醒类型：" + this.mNotificationApp);
        NotificationApp notificationApp = this.mNotificationApp;
        if (notificationApp != null) {
            notificationApp.setOldStatus(notificationApp.getStatus());
            this.mTitleBar.setTitle(notificationApp.getName());
            ((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).select(notificationApp.getStatus());
        }
        ((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).setOnReminderChangedListener(this);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        if (requestCode == 502) {
            String[] onlyPhonePermission = PermissionUtil.getOnlyPhonePermission();
            if (PermissionUtil.checkSelfPermission(this, (String[]) Arrays.copyOf(onlyPhonePermission, onlyPhonePermission.length))) {
                openMissCall();
                return;
            }
            ((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).selectDeny();
            ((ThirdPartyReminderPresenter) this.mPresenter).setMissCallSwitch(false);
            NotificationApp notificationApp = this.mNotificationApp;
            if (notificationApp != null) {
                notificationApp.setStatus(((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).getReminderStatus());
            }
        }
    }

    @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
    public void onReminderChanged(int reminderStatus) {
        NotificationApp notificationApp = this.mNotificationApp;
        if (notificationApp != null) {
            if (NotificationAppKt.isMissCall(notificationApp)) {
                MsgNotificationHelper.saveLog("mItemMissCall isSwitchOn ：" + reminderStatus);
                if (reminderStatus != 3) {
                    String[] phonePermission = PermissionUtil.getPhonePermission();
                    if (!PermissionUtil.checkSelfPermission(this, (String[]) Arrays.copyOf(phonePermission, phonePermission.length))) {
                        String[] phonePermission2 = PermissionUtil.getPhonePermission();
                        requestPermissions(502, (String[]) Arrays.copyOf(phonePermission2, phonePermission2.length));
                        return;
                    } else {
                        openMissCall();
                        return;
                    }
                }
                NotificationApp notificationApp2 = this.mNotificationApp;
                if (notificationApp2 != null) {
                    notificationApp2.setStatus(((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).getReminderStatus());
                }
                ((ThirdPartyReminderPresenter) this.mPresenter).setMissCallSwitch(false);
                return;
            }
            NotificationApp notificationApp3 = this.mNotificationApp;
            if (notificationApp3 != null) {
                notificationApp3.setStatus(reminderStatus);
            }
        }
    }

    private final void openMissCall() {
        NotificationApp notificationApp = this.mNotificationApp;
        if (notificationApp != null) {
            notificationApp.setStatus(((ReminderSelectView) _$_findCachedViewById(com.ido.life.R.id.vReminder)).getReminderStatus());
        }
        ((ThirdPartyReminderPresenter) this.mPresenter).setMissCallSwitch(true);
        startService(new Intent(this, (Class<?>) DeviceAssistService.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ido.life.base.BaseActivity
    public void saveData() {
        boolean zIsStatusChanged = isStatusChanged();
        MsgNotificationHelper.saveLog("ThirdPartyReminderActivity statusChanged = " + zIsStatusChanged);
        setResult(zIsStatusChanged ? -1 : 0, new Intent(this, (Class<?>) NotificationSettingActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, this.mNotificationApp));
        finishAfterTransition();
    }

    private final boolean isStatusChanged() {
        NotificationApp notificationApp = this.mNotificationApp;
        if (notificationApp != null) {
            Integer numValueOf = notificationApp != null ? Integer.valueOf(notificationApp.getStatus()) : null;
            if (!Intrinsics.areEqual(numValueOf, this.mNotificationApp != null ? Integer.valueOf(r3.getOldStatus()) : null)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }
}