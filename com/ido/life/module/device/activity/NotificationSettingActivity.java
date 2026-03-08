package com.ido.life.module.device.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.R;
import com.ido.life.adapter.HealthNotificationSettingAdapter;
import com.ido.life.adapter.NotificationSettingAdapter;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.HealthMonitoringBean;
import com.ido.life.bean.HealthNotificationStateChangeEvent;
import com.ido.life.bean.NotificationApp;
import com.ido.life.bean.NotificationAppKt;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.boatservice.NBoatService;
import com.ido.life.constants.Constants;
import com.ido.life.customview.CustomItemDecoration;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.LoadingLayout;
import com.ido.life.customview.OnItemClickListener;
import com.ido.life.data.Constant;
import com.ido.life.data.cache.HealthMonitoringManager;
import com.ido.life.dialog.NotificationPermissionSettingDialog;
import com.ido.life.module.activity.ThirdPartyReminderActivity;
import com.ido.life.module.device.presenter.NotificationSettingPresenter;
import com.ido.life.module.device.view.INotificationSettingView;
import com.ido.life.util.ListUtils;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: NotificationSettingActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 O2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001OB\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0018H\u0014J\b\u0010\u001d\u001a\u00020\u0018H\u0014J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\b\u0010\u001f\u001a\u00020\u0018H\u0014J\b\u0010 \u001a\u00020\u0018H\u0002J\b\u0010!\u001a\u00020\u0018H\u0002J\b\u0010\"\u001a\u00020\u0018H\u0002J\b\u0010#\u001a\u00020\u0018H\u0016J\b\u0010$\u001a\u00020\u0018H\u0002J\"\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u001b2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020\u0018H\u0016J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020\u0018H\u0014J\b\u0010/\u001a\u00020\u0018H\u0016J\u0010\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u0007H\u0016J\b\u00102\u001a\u00020\u0018H\u0016J\u0016\u00103\u001a\u00020\u00182\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\b\u00105\u001a\u00020\u0018H\u0016J\u0016\u00106\u001a\u00020\u00182\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\u0016J\u0010\u00108\u001a\u00020\u00182\u0006\u00109\u001a\u00020:H\u0007J\u0014\u0010;\u001a\u00020\u00182\n\u00109\u001a\u0006\u0012\u0002\b\u00030<H\u0007J\b\u0010=\u001a\u00020\u0018H\u0014J\b\u0010>\u001a\u00020\u0018H\u0016J\b\u0010?\u001a\u00020\u0018H\u0016J\u0010\u0010@\u001a\u00020\u00182\u0006\u0010A\u001a\u00020\u0012H\u0016J\u0010\u0010B\u001a\u00020\u00182\u0006\u0010A\u001a\u00020\u0012H\u0016J\b\u0010C\u001a\u00020\u0018H\u0016J\b\u0010D\u001a\u00020\u0018H\u0016J\u0014\u0010E\u001a\u00020\u00182\n\u00109\u001a\u0006\u0012\u0002\b\u00030<H\u0007J\b\u0010F\u001a\u00020\u0018H\u0014J\u0010\u0010G\u001a\u00020\u00182\u0006\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020\u0018H\u0002J\b\u0010K\u001a\u00020\u0018H\u0002J\u0012\u0010L\u001a\u00020\u00182\b\u0010A\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010M\u001a\u00020\u00182\u0006\u0010A\u001a\u00020\u0012H\u0002J\b\u0010N\u001a\u00020\u0018H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lcom/ido/life/module/device/activity/NotificationSettingActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/presenter/NotificationSettingPresenter;", "Lcom/ido/life/module/device/view/INotificationSettingView;", "Landroid/view/View$OnClickListener;", "()V", "mDeviceUnreadReminder", "Lcom/ido/ble/protocol/model/DeviceUnreadReminder;", "mHandler", "Landroid/os/Handler;", "mHealthAdapter", "Lcom/ido/life/adapter/HealthNotificationSettingAdapter;", "mHealthAppList", "", "Lcom/ido/life/bean/HealthMonitoringBean;", "mNotificationAdapter", "Lcom/ido/life/adapter/NotificationSettingAdapter;", "mNotificationAppList", "Lcom/ido/life/bean/NotificationApp;", "mNotificationPermissionSettingDialog", "Lcom/ido/life/dialog/NotificationPermissionSettingDialog;", "mNotificationStatus", "Lcom/ido/life/bean/SwitchStatus$NotificationSwitch;", "checkEmpty", "", "getHealthAppList", "getLayoutResId", "", "initData", "initEvent", "initHealthRecyclerView", "initLabelLanguage", "initNotificationRecyclerView", "initSwitchStatus", "initTips", "initViews", "jump2SettingActivity", "onActivityResult", "requestCode", "resultCode", AeUtil.ROOT_DATA_PATH_OLD_NAME, "Landroid/content/Intent;", "onBackPressed", "onClick", "v", "Landroid/view/View;", "onDestroy", "onGetDeviceUnreadReminderFailed", "onGetDeviceUnreadReminderSuccess", "param", "onGetHealthAppListFailed", "onGetHealthAppListSuccess", "healthApps", "onGetNotificationAppListFailed", "onGetNotificationAppListSuccess", "notificationApps", "onHealNotificationStateChanged", "event", "Lcom/ido/life/bean/HealthNotificationStateChangeEvent;", "onHealthAppNotificationStateChanged", "Lcom/ido/life/base/BaseMessage;", "onResume", "onSendHealthNotificationStatus2DeviceFailed", "onSendHealthNotificationStatus2DeviceSuccess", "onSendNotificationStatus2DeviceFailed", "mNotificationApp", "onSendNotificationStatus2DeviceSuccess", "onSetDeviceUnreadReminderFailed", "onSetDeviceUnreadReminderSuccess", "onThirdPartyAppNotificationStateChanged", "saveData", "setThirdPartyRemindEnable", "notificationEnabled", "", "showErrorPage", "showPermissionSettingDialog", "updateAppStatus", "updateDefaultAppStatus", "updateNotificationStatus", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NotificationSettingActivity extends BaseActivity<NotificationSettingPresenter> implements INotificationSettingView, View.OnClickListener {
    public static final int HEALTH_REMIND_REQUEST_CODE = 2000;
    public static final String KEY_SCENE_NOTIFICATION_SETTING = "KEY_SCENE_NOTIFICATION_SETTING";
    public static final int SCENE_NOTIFICATION_SETTING = 1;
    public static final int THIRD_REMIND_REQUEST_CODE = 1000;
    public static final int TOGGLE_STATUS_CHANGED = 1;
    private HashMap _$_findViewCache;
    private DeviceUnreadReminder mDeviceUnreadReminder;
    private HealthNotificationSettingAdapter mHealthAdapter;
    private NotificationSettingAdapter mNotificationAdapter;
    private NotificationPermissionSettingDialog mNotificationPermissionSettingDialog;
    private SwitchStatus.NotificationSwitch mNotificationStatus;
    private List<HealthMonitoringBean> mHealthAppList = new ArrayList();
    private List<NotificationApp> mNotificationAppList = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity$mHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            if (it.what == 1) {
                boolean z = false;
                boolean z2 = it.arg1 == 1;
                Log.e("NotificationSetting", "start");
                boolean zNotificationEnabled = AppUtil.notificationEnabled(this.this$0);
                MsgNotificationHelper.saveLog(StringsKt.trimIndent("\n            mItemMasterSwitch isSwitchOn ：" + z2 + "\n            isNotificationEnabled : " + zNotificationEnabled + "\n            "));
                if (!z2 || zNotificationEnabled) {
                    SwitchStatus.NotificationSwitch notificationSwitch = this.this$0.mNotificationStatus;
                    if (notificationSwitch != null) {
                        notificationSwitch.masterSwitched = z2;
                    }
                    this.this$0.updateNotificationStatus();
                    NotificationSettingActivity notificationSettingActivity = this.this$0;
                    if (zNotificationEnabled && z2) {
                        z = true;
                    }
                    notificationSettingActivity.setThirdPartyRemindEnable(z);
                    Log.e("NotificationSetting", "end");
                } else {
                    this.this$0.showPermissionSettingDialog();
                    CustomToggleButton tgSync = (CustomToggleButton) this.this$0._$_findCachedViewById(R.id.tgSync);
                    Intrinsics.checkExpressionValueIsNotNull(tgSync, "tgSync");
                    tgSync.setSwitchStatus(false);
                    return true;
                }
            }
            return true;
        }
    });

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
        return com.boat.Xtend.two.R.layout.activity_notification_setting;
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onGetDeviceUnreadReminderFailed() {
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onSendHealthNotificationStatus2DeviceSuccess() {
    }

    public static final /* synthetic */ NotificationSettingPresenter access$getMPresenter$p(NotificationSettingActivity notificationSettingActivity) {
        return (NotificationSettingPresenter) notificationSettingActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(com.boat.Xtend.two.R.string.device_notification_reminder));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        initHealthRecyclerView();
        initNotificationRecyclerView();
        initTips();
        EventBusHelper.register(this);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBusHelper.unregister(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        initSwitchStatus();
    }

    private final void initSwitchStatus() {
        boolean zNotificationEnabled = AppUtil.notificationEnabled(this);
        NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
        this.mNotificationStatus = notificationSettingPresenter != null ? notificationSettingPresenter.getNotificationStatus() : null;
        SwitchStatus.NotificationSwitch notificationSwitch = this.mNotificationStatus;
        boolean z = false;
        if ((notificationSwitch != null ? notificationSwitch.masterSwitched : false) && zNotificationEnabled) {
            z = true;
        }
        CustomToggleButton tgSync = (CustomToggleButton) _$_findCachedViewById(R.id.tgSync);
        Intrinsics.checkExpressionValueIsNotNull(tgSync, "tgSync");
        tgSync.setSwitchStatus(z);
        setThirdPartyRemindEnable(z);
    }

    private final void initTips() {
        String string = getString(AppUtil.notificationEnabled(this) ? com.boat.Xtend.two.R.string.notification_sync_tip_open : com.boat.Xtend.two.R.string.notification_sync_tip_close);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(if (AppUtil.no…ification_sync_tip_close)");
        TextView tvNotificationTips = (TextView) _$_findCachedViewById(R.id.tvNotificationTips);
        Intrinsics.checkExpressionValueIsNotNull(tvNotificationTips, "tvNotificationTips");
        tvNotificationTips.setText(string);
    }

    private final void initHealthRecyclerView() {
        NotificationSettingActivity notificationSettingActivity = this;
        this.mHealthAdapter = new HealthNotificationSettingAdapter(notificationSettingActivity, this.mHealthAppList);
        RecyclerView health_recyclerview = (RecyclerView) _$_findCachedViewById(R.id.health_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(health_recyclerview, "health_recyclerview");
        health_recyclerview.setAdapter(this.mHealthAdapter);
        RecyclerView health_recyclerview2 = (RecyclerView) _$_findCachedViewById(R.id.health_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(health_recyclerview2, "health_recyclerview");
        ((RecyclerView) _$_findCachedViewById(R.id.health_recyclerview)).addItemDecoration(new CustomItemDecoration(health_recyclerview2).color(ContextCompat.getColor(notificationSettingActivity, com.boat.Xtend.two.R.color.color_F2F2F6)).marginLeft(DipPixelUtil.dip2pxF(16.0f)).height(DipPixelUtil.dip2pxF(0.5f)));
        HealthNotificationSettingAdapter healthNotificationSettingAdapter = this.mHealthAdapter;
        if (healthNotificationSettingAdapter != null) {
            healthNotificationSettingAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity.initHealthRecyclerView.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    HealthMonitoringBean healthMonitoringBean = (HealthMonitoringBean) NotificationSettingActivity.this.mHealthAppList.get(position);
                    Class<?> healthModuleClazz = HealthMonitoringManager.INSTANCE.getHealthModuleClazz(healthMonitoringBean.getType());
                    if (healthModuleClazz != null) {
                        NotificationSettingActivity notificationSettingActivity2 = NotificationSettingActivity.this;
                        notificationSettingActivity2.startActivityForResult(new SingleTopIntent(notificationSettingActivity2, healthModuleClazz).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, healthMonitoringBean.getType()).putExtra(Constant.KEY_IS_NOTICE_SCENE, true), 2000);
                    }
                }
            });
        }
    }

    private final void initNotificationRecyclerView() {
        this.mNotificationAdapter = new NotificationSettingAdapter(this, this.mNotificationAppList);
        RecyclerView app_recyclerview = (RecyclerView) _$_findCachedViewById(R.id.app_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(app_recyclerview, "app_recyclerview");
        app_recyclerview.setAdapter(this.mNotificationAdapter);
        NotificationSettingAdapter notificationSettingAdapter = this.mNotificationAdapter;
        if (notificationSettingAdapter != null) {
            notificationSettingAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity.initNotificationRecyclerView.1
                @Override // com.ido.life.customview.OnItemClickListener
                public void onItemClick(int position) {
                    SwitchStatus.NotificationSwitch notificationSwitch = NotificationSettingActivity.this.mNotificationStatus;
                    if (notificationSwitch == null || !notificationSwitch.masterSwitched) {
                        return;
                    }
                    NotificationSettingActivity notificationSettingActivity = NotificationSettingActivity.this;
                    notificationSettingActivity.startActivityForResult(new SingleTopIntent(notificationSettingActivity, (Class<?>) ThirdPartyReminderActivity.class).putExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME, (Serializable) NotificationSettingActivity.this.mNotificationAppList.get(position)), 1000);
                }
            });
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        NotificationSettingActivity notificationSettingActivity = this;
        ((TextView) _$_findCachedViewById(R.id.tvNoticeSet)).setOnClickListener(notificationSettingActivity);
        ((RegularTextView) _$_findCachedViewById(R.id.allNotificationID)).setOnClickListener(notificationSettingActivity);
        ((CustomToggleButton) _$_findCachedViewById(R.id.tgSync)).setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity.initEvent.1
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.arg1 = z ? 1 : 0;
                Handler handler = NotificationSettingActivity.this.mHandler;
                if (handler != null) {
                    handler.sendMessageDelayed(messageObtain, 5L);
                }
            }
        });
        ((CustomToggleButton) _$_findCachedViewById(R.id.allToggle)).setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity.initEvent.2
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                DeviceUnreadReminder deviceUnreadReminder = NotificationSettingActivity.this.mDeviceUnreadReminder;
                if (deviceUnreadReminder != null) {
                    deviceUnreadReminder.on_off = z ? 170 : 85;
                    NotificationSettingActivity.this.showSettingLoading(false);
                    NotificationSettingActivity.access$getMPresenter$p(NotificationSettingActivity.this).setDeviceUnreadReminder(deviceUnreadReminder);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setThirdPartyRemindEnable(boolean notificationEnabled) {
        LinearLayout lay_third = (LinearLayout) _$_findCachedViewById(R.id.lay_third);
        Intrinsics.checkExpressionValueIsNotNull(lay_third, "lay_third");
        lay_third.setAlpha(notificationEnabled ? 1.0f : 0.3f);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int iIndexOf;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1000 || resultCode != -1 || data == null) {
            if (requestCode == 2000) {
                MsgNotificationHelper.saveLog("the notification state set succeed，refresh health app list now!!!");
                getHealthAppList();
                return;
            }
            if (requestCode == 500) {
                boolean zNotificationEnabled = AppUtil.notificationEnabled(this);
                MsgNotificationHelper.saveLog("the notification permission granted " + zNotificationEnabled + " !!!");
                if (zNotificationEnabled) {
                    EventBusHelper.postSticky(Constants.EventConstants.EVENT_NOTIFICATION_PERMISSION_CHANGED);
                    return;
                }
                return;
            }
            return;
        }
        NotificationApp notificationApp = (NotificationApp) data.getSerializableExtra(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        if (notificationApp == null || (iIndexOf = this.mNotificationAppList.indexOf(notificationApp)) < 0) {
            return;
        }
        MsgNotificationHelper.saveLog("返回的mNotificationApp = " + notificationApp);
        MsgNotificationHelper.saveLog("返回的mNotificationAppList[index] = " + this.mNotificationAppList.get(iIndexOf));
        if (this.mNotificationAppList.get(iIndexOf).getStatus() != notificationApp.getStatus()) {
            this.mNotificationAppList.get(iIndexOf).setStatus(notificationApp.getStatus());
            NotificationSettingAdapter notificationSettingAdapter = this.mNotificationAdapter;
            if (notificationSettingAdapter != null) {
                notificationSettingAdapter.notifyItemChanged(iIndexOf, "");
            }
            NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
            if (notificationSettingPresenter != null) {
                notificationSettingPresenter.sendNotificationStatus2Device(notificationApp);
            }
            updateDefaultAppStatus(notificationApp);
        }
    }

    private final void updateDefaultAppStatus(NotificationApp mNotificationApp) {
        SwitchStatus.NotificationSwitch notificationSwitch;
        boolean z = mNotificationApp.getStatus() != 3;
        if (NotificationAppKt.isSms(mNotificationApp)) {
            SwitchStatus.NotificationSwitch notificationSwitch2 = this.mNotificationStatus;
            if (notificationSwitch2 != null) {
                notificationSwitch2.smsSwitched = z;
            }
        } else if (NotificationAppKt.isMissCall(mNotificationApp) && (notificationSwitch = this.mNotificationStatus) != null) {
            notificationSwitch.missedCall = z;
        }
        updateNotificationStatus();
    }

    private final void getHealthAppList() {
        NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
        if (notificationSettingPresenter != null) {
            notificationSettingPresenter.getHealthAppList();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onHealNotificationStateChanged(HealthNotificationStateChangeEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        CommonLogUtil.d("onHealNotificationStateChanged refresh list");
        getHealthAppList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onHealthAppNotificationStateChanged(BaseMessage<?> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() == 902) {
            MsgNotificationHelper.saveLog("onHealthAppNotificationStateChanged refresh list");
            NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
            if (notificationSettingPresenter != null) {
                notificationSettingPresenter.getHealthAppList();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onThirdPartyAppNotificationStateChanged(BaseMessage<?> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() == 891) {
            MsgNotificationHelper.saveLog("onThirdPartyAppNotificationStateChanged refresh list");
            NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
            if (notificationSettingPresenter != null) {
                notificationSettingPresenter.getNotificationApps();
            }
        }
    }

    private final void updateAppStatus(NotificationApp mNotificationApp) {
        int iIndexOf;
        if (mNotificationApp == null || (iIndexOf = this.mNotificationAppList.indexOf(mNotificationApp)) < 0) {
            return;
        }
        this.mNotificationAppList.get(iIndexOf).setStatus(mNotificationApp.getStatus());
        NotificationSettingAdapter notificationSettingAdapter = this.mNotificationAdapter;
        if (notificationSettingAdapter != null) {
            notificationSettingAdapter.notifyItemChanged(iIndexOf, "");
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ((LoadingLayout) _$_findCachedViewById(R.id.llContainer)).showLoading();
        getHealthAppList();
        NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
        if (notificationSettingPresenter != null) {
            notificationSettingPresenter.getNotificationApps();
        }
        NotificationSettingPresenter notificationSettingPresenter2 = (NotificationSettingPresenter) this.mPresenter;
        if (notificationSettingPresenter2 != null) {
            notificationSettingPresenter2.getDeviceUnreadReminder();
        }
    }

    public final void checkEmpty() {
        if (ListUtils.INSTANCE.isNotEmpty(this.mHealthAppList) || ListUtils.INSTANCE.isNotEmpty(this.mNotificationAppList)) {
            ((LoadingLayout) _$_findCachedViewById(R.id.llContainer)).showContent();
        } else {
            LoadingLayout.showEmpty$default((LoadingLayout) _$_findCachedViewById(R.id.llContainer), null, 0, 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPermissionSettingDialog() {
        MsgNotificationHelper.saveLog("NotificationActivity，showPermissionSettingDialog");
        this.mNotificationPermissionSettingDialog = NotificationPermissionSettingDialog.getInstance();
        NotificationPermissionSettingDialog notificationPermissionSettingDialog = this.mNotificationPermissionSettingDialog;
        if (notificationPermissionSettingDialog != null) {
            notificationPermissionSettingDialog.setOnClickListener(new NotificationPermissionSettingDialog.OnClickListener() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity.showPermissionSettingDialog.1
                @Override // com.ido.life.dialog.NotificationPermissionSettingDialog.OnClickListener
                public void onConfirmClicked() {
                    MsgNotificationHelper.saveLog("PermissionSettingDialog，onConfirmClicked");
                    NotificationSettingActivity.this.jump2SettingActivity();
                    NotificationPermissionSettingDialog notificationPermissionSettingDialog2 = NotificationSettingActivity.this.mNotificationPermissionSettingDialog;
                    if (notificationPermissionSettingDialog2 != null) {
                        notificationPermissionSettingDialog2.dismissAllowingStateLoss();
                    }
                }

                @Override // com.ido.life.dialog.NotificationPermissionSettingDialog.OnClickListener
                public void onCancelClicked() {
                    MsgNotificationHelper.saveLog("PermissionSettingDialog，onCancelClicked");
                }
            });
        }
        NotificationPermissionSettingDialog notificationPermissionSettingDialog2 = this.mNotificationPermissionSettingDialog;
        if (notificationPermissionSettingDialog2 != null) {
            notificationPermissionSettingDialog2.show(getSupportFragmentManager());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump2SettingActivity() {
        MsgNotificationHelper.saveLog("NotificationActivity，jump2SettingActivity");
        try {
            startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 500);
        } catch (ActivityNotFoundException e2) {
            MsgNotificationHelper.saveLog("ActivityNotFoundException ：" + e2);
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity"));
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                startActivityForResult(intent, 500);
            } catch (Exception e3) {
                MsgNotificationHelper.saveLog("Exception ：" + e3);
                e3.printStackTrace();
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getNotificationLogPath(), "--------------对不起，您的手机暂不支持------------->>");
            }
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onGetNotificationAppListSuccess(List<NotificationApp> notificationApps) {
        Intrinsics.checkParameterIsNotNull(notificationApps, "notificationApps");
        this.mNotificationAppList.clear();
        this.mNotificationAppList.addAll(notificationApps);
        CommonLogUtil.d("onGetNotificationAppListSuccess mNotificationAppList = " + this.mNotificationAppList);
        NotificationSettingAdapter notificationSettingAdapter = this.mNotificationAdapter;
        if (notificationSettingAdapter != null) {
            notificationSettingAdapter.notifyDataSetChanged();
        }
        checkEmpty();
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onGetNotificationAppListFailed() {
        showErrorPage();
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onGetHealthAppListSuccess(List<HealthMonitoringBean> healthApps) {
        Intrinsics.checkParameterIsNotNull(healthApps, "healthApps");
        this.mHealthAppList.clear();
        this.mHealthAppList.addAll(healthApps);
        HealthNotificationSettingAdapter healthNotificationSettingAdapter = this.mHealthAdapter;
        if (healthNotificationSettingAdapter != null) {
            healthNotificationSettingAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onGetHealthAppListFailed() {
        showErrorPage();
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onSendNotificationStatus2DeviceSuccess(NotificationApp mNotificationApp) {
        Intrinsics.checkParameterIsNotNull(mNotificationApp, "mNotificationApp");
        MsgNotificationHelper.saveLog("onSendNotificationStatus2DeviceSuccess mNotificationApp = " + mNotificationApp);
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onSendNotificationStatus2DeviceFailed(NotificationApp mNotificationApp) {
        Intrinsics.checkParameterIsNotNull(mNotificationApp, "mNotificationApp");
        MsgNotificationHelper.saveLog("onSendNotificationStatus2DeviceFailed mNotificationApp = " + mNotificationApp);
        int iIndexOf = this.mNotificationAppList.indexOf(mNotificationApp);
        int oldStatus = mNotificationApp.getOldStatus();
        if (iIndexOf >= 0) {
            this.mNotificationAppList.get(iIndexOf).setStatus(oldStatus);
            NotificationSettingAdapter notificationSettingAdapter = this.mNotificationAdapter;
            if (notificationSettingAdapter != null) {
                notificationSettingAdapter.notifyItemChanged(iIndexOf, "");
            }
        }
        showToast(getLanguageText(com.boat.Xtend.two.R.string.public_set_failed));
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onGetDeviceUnreadReminderSuccess(DeviceUnreadReminder param) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        this.mDeviceUnreadReminder = param;
        DeviceUnreadReminder deviceUnreadReminder = this.mDeviceUnreadReminder;
        if (deviceUnreadReminder != null) {
            CustomToggleButton allToggle = (CustomToggleButton) _$_findCachedViewById(R.id.allToggle);
            Intrinsics.checkExpressionValueIsNotNull(allToggle, "allToggle");
            allToggle.setSwitchStatus(deviceUnreadReminder.on_off == 170);
        }
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onSetDeviceUnreadReminderFailed() {
        dismissLoadingDialog();
        showToast(getLanguageText(com.boat.Xtend.two.R.string.public_set_failed));
        CustomToggleButton allToggle = (CustomToggleButton) _$_findCachedViewById(R.id.allToggle);
        Intrinsics.checkExpressionValueIsNotNull(allToggle, "allToggle");
        CustomToggleButton allToggle2 = (CustomToggleButton) _$_findCachedViewById(R.id.allToggle);
        Intrinsics.checkExpressionValueIsNotNull(allToggle2, "allToggle");
        allToggle.setSwitchStatus(!allToggle2.getSwitchStatus());
        DeviceUnreadReminder deviceUnreadReminder = this.mDeviceUnreadReminder;
        if (deviceUnreadReminder != null) {
            CustomToggleButton allToggle3 = (CustomToggleButton) _$_findCachedViewById(R.id.allToggle);
            Intrinsics.checkExpressionValueIsNotNull(allToggle3, "allToggle");
            allToggle3.getSwitchStatus();
            deviceUnreadReminder.on_off = 170;
        }
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onSetDeviceUnreadReminderSuccess() {
        dismissLoadingDialog();
    }

    private final void showErrorPage() {
        if (ListUtils.INSTANCE.isNullOrEmpty(this.mHealthAppList) && ListUtils.INSTANCE.isNullOrEmpty(this.mNotificationAppList)) {
            LoadingLayout.showError$default((LoadingLayout) _$_findCachedViewById(R.id.llContainer), null, false, 0, 7, null);
            ((LoadingLayout) _$_findCachedViewById(R.id.llContainer)).setRetryListener(new Function0<Unit>() { // from class: com.ido.life.module.device.activity.NotificationSettingActivity.showErrorPage.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    NotificationSettingActivity.this.initData();
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        int id = v.getId();
        if (id == com.boat.Xtend.two.R.id.allNotificationID) {
            startActivity(new SingleTopIntent(this, (Class<?>) NotificationTipsActivity.class));
        } else {
            if (id != com.boat.Xtend.two.R.id.tvNoticeSet) {
                return;
            }
            MsgNotificationHelper.saveLog("click top tips");
            jump2SettingActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ido.life.base.BaseActivity
    public void saveData() {
        updateNotificationStatus();
        setResult(2);
        finishAfterTransition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNotificationStatus() {
        if (this.mNotificationStatus != null) {
            NotificationSettingPresenter notificationSettingPresenter = (NotificationSettingPresenter) this.mPresenter;
            SwitchStatus.NotificationSwitch notificationSwitch = this.mNotificationStatus;
            if (notificationSwitch == null) {
                Intrinsics.throwNpe();
            }
            if (notificationSettingPresenter.isStatusChanged(notificationSwitch)) {
                MsgNotificationHelper.saveLog("statusChanged，mNotificationStatus ：" + this.mNotificationStatus);
                ((NotificationSettingPresenter) this.mPresenter).saveNotificationStatus(this.mNotificationStatus);
            }
            NotificationSettingActivity notificationSettingActivity = this;
            startService(new Intent(notificationSettingActivity, (Class<?>) NBoatService.class));
            SwitchStatus.NotificationSwitch notificationSwitch2 = this.mNotificationStatus;
            if (notificationSwitch2 == null) {
                Intrinsics.throwNpe();
            }
            if (notificationSwitch2.smsSwitched) {
                startService(new Intent(notificationSettingActivity, (Class<?>) DeviceAssistService.class));
            }
        }
    }

    @Override // com.ido.life.module.device.view.INotificationSettingView
    public void onSendHealthNotificationStatus2DeviceFailed() {
        CustomToggleButton allToggle = (CustomToggleButton) _$_findCachedViewById(R.id.allToggle);
        Intrinsics.checkExpressionValueIsNotNull(allToggle, "allToggle");
        CustomToggleButton allToggle2 = (CustomToggleButton) _$_findCachedViewById(R.id.allToggle);
        Intrinsics.checkExpressionValueIsNotNull(allToggle2, "allToggle");
        allToggle.setSwitchStatus(!allToggle2.getSwitchStatus());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }
}