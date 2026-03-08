package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.text.Selection;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.base.TextLengthWatcher;
import com.ido.life.bean.DialPlateProxy;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.ble.BleHelper;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.customview.viewgroup.CustomValueUnitView;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.StepDayData;
import com.ido.life.dialog.CommLoadingDialog;
import com.ido.life.module.alexa.AlexaLoginActivity;
import com.ido.life.module.device.presenter.DeviceInfoPresenter;
import com.ido.life.module.device.reminders.RemindersActivity;
import com.ido.life.module.device.view.IDeviceInfoView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.module.user.feedback.FeedbackActivity;
import com.ido.life.util.NoticeAppUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfoActivity extends BaseActivity<DeviceInfoPresenter> implements IDeviceInfoView, TextLengthWatcher.OnTextChangedListener, MultiItemTypeAdapterForRV.OnItemClickListener {
    public static final int CODE_DEVICE_SETTING_REQUEST = 10;
    public static final String DEVICE = "device";
    public static final int EVENT_REMINDER_STATUS = 11;
    private static final int FUNCTION_ALARM_CLOCK = 3;
    private static final int FUNCTION_HEALTH_MONITORING = 1;
    private static final int FUNCTION_MESSAGE_NOTICE = 4;
    private static final int FUNCTION_TUTORIAL = 2;
    private int clickedCount;
    private DeviceInfoActivity mActivity;
    private CommonRecyclerViewAdapter<Integer> mAdapter;

    @BindView(R.id.item_call_reminder)
    CustomItemLabelView mCallReminder;
    private CommBottomConfirmDialog mDeleteDialog;
    private DeviceListEntity.DeviceInfo mDeviceInfo;
    private List<TopDialPlateEntity.DialPlate> mDialPlateList;

    @BindView(R.id.et_device_custom_name)
    EditText mEtDeviceCustomName;
    private ArrayList<Integer> mFunctionList;
    private boolean mIsBracelet;
    private boolean mIsCircle;

    @BindView(R.id.item_amazon_alexa)
    CustomItemLabelView mItemAlexa;

    @BindView(R.id.item_calorie)
    CustomValueUnitView mItemCalorie;

    @BindView(R.id.item_device_upgrade)
    CustomItemLabelView mItemDeviceUpgrade;

    @BindView(R.id.item_heart_rate)
    CustomValueUnitView mItemHeartRate;

    @BindView(R.id.item_intelligent_motion)
    CustomItemLabelView mItemIntelligentMotion;

    @BindView(R.id.item_problems_and_suggestions)
    CustomItemLabelView mItemProblemsAndSuggestions;

    @BindView(R.id.item_remind)
    CustomItemLabelView mItemRemind;

    @BindView(R.id.item_step)
    CustomValueUnitView mItemStep;

    @BindView(R.id.iv_device_img)
    ImageView mIvDeviceImg;

    @BindView(R.id.iv_dial_1)
    AppCompatImageView mIvDial1;

    @BindView(R.id.iv_dial_2)
    AppCompatImageView mIvDial2;

    @BindView(R.id.iv_dial_3)
    AppCompatImageView mIvDial3;

    @BindView(R.id.iv_dial_4)
    AppCompatImageView mIvDial4;

    @BindView(R.id.iv_editor)
    ImageView mIvEditor;
    private long mLastTimeMillis;

    @BindView(R.id.layout_connect)
    LinearLayout mLayoutConnect;

    @BindView(R.id.layout_dial)
    LinearLayout mLayoutDial;

    @BindView(R.id.layout_dial_market)
    LinearLayout mLayoutDialMarket;

    @BindView(R.id.layout_disconnect)
    RelativeLayout mLayoutDisconnect;

    @BindView(R.id.layout_edit)
    TableLayout mLayoutEdit;

    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;
    private CommLoadingDialog mLoadingDialog;

    @BindView(R.id.mtv_device_custom_name)
    MediumTextView mMtvDeviceCustomName;

    @BindView(R.id.mtv_device_name)
    MediumTextView mMtvDeviceName;

    @BindView(R.id.mtv_status)
    MediumTextView mMtvStatus;
    private int mRadius;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_battery)
    RegularTextView mRtvBattery;
    private final View.OnScrollChangeListener mScrollChangeListener = new View.OnScrollChangeListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity.3
        @Override // android.view.View.OnScrollChangeListener
        public void onScrollChange(View view, int i, int i2, int i3, int i4) {
            if (DeviceInfoActivity.this.mEtDeviceCustomName.isShown()) {
                DeviceInfoActivity.this.resetEditMode();
            }
        }
    };

    @BindView(R.id.scroll_view)
    NestedScrollView mScrollView;
    private SwitchStatus mSwitchStatus;

    private void jump2TestActivity() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_device_info;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mActivity = this;
        this.mDeviceInfo = (DeviceListEntity.DeviceInfo) getIntent().getSerializableExtra("device");
        if (this.mDeviceInfo == null) {
            this.mDeviceInfo = new DeviceListEntity.DeviceInfo(((DeviceInfoPresenter) this.mPresenter).getDeviceInfo());
        }
        ((DeviceInfoPresenter) this.mPresenter).saveBindLog("mDeviceInfo = " + this.mDeviceInfo.toString());
        this.mSwitchStatus = ((DeviceInfoPresenter) this.mPresenter).getSwitchStatus();
        this.mIsBracelet = this.mDeviceInfo.type == 3;
        this.mRadius = (int) getResources().getDimension(this.mIsBracelet ? R.dimen.sw_dp_4 : R.dimen.sw_dp_7);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.getTitleLayoutMid(this).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceInfoActivity$5Bq5tCajBV_KIT9B9EDog0kfLH8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$DeviceInfoActivity(view);
            }
        });
        ImageLoaderUtil.loadImgFillet(this.mIvDeviceImg, this.mDeviceInfo.getImageUrl(), 0, this.mIsBracelet ? R.mipmap.icon_type_bracelet : R.mipmap.icon_type_watch);
        this.mEtDeviceCustomName.addTextChangedListener(new TextLengthWatcher(60, this));
        this.mScrollView.setOnScrollChangeListener(this.mScrollChangeListener);
        showDisplayByConnectStatus();
        initDeviceInfo();
        initDeviceData();
        initFunctionRecycleView();
        initDialDefaultShape();
        initFunctionItem();
    }

    public /* synthetic */ void lambda$initEvent$0$DeviceInfoActivity(View view) {
        jump2TestActivity();
    }

    private void initFunctionRecycleView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this.mActivity));
        if (this.mFunctionList == null) {
            this.mFunctionList = new ArrayList<>();
        }
        this.mRecyclerView.setEnabled(false);
        this.mAdapter = new CommonRecyclerViewAdapter<Integer>(this.mActivity, R.layout.item_function_grid, this.mFunctionList) { // from class: com.ido.life.module.device.activity.DeviceInfoActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, Integer num, int i) {
                CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.tv_item_function);
                customItemLabelView.setDrawableRight(R.mipmap.device_right_arrow);
                customItemLabelView.setHasBottomDivider(true);
                int iIntValue = num.intValue();
                if (iIntValue == 1) {
                    customItemLabelView.setTitle(DeviceInfoActivity.this.getLanguageText(R.string.device_health_monitoring));
                    customItemLabelView.setDrawableLeft(R.mipmap.icon_health_monitoring);
                    return;
                }
                if (iIntValue == 2) {
                    customItemLabelView.setTitle(DeviceInfoActivity.this.getLanguageText(R.string.device_tutorial));
                    customItemLabelView.setDrawableLeft(R.mipmap.icon_tutorial);
                } else if (iIntValue == 3) {
                    customItemLabelView.setTitle(DeviceInfoActivity.this.getLanguageText(R.string.device_alarm_clock));
                    customItemLabelView.setDrawableLeft(R.mipmap.icon_alarm_clock);
                } else {
                    if (iIntValue != 4) {
                        return;
                    }
                    customItemLabelView.setTitle(DeviceInfoActivity.this.getLanguageText(R.string.device_msg_notification));
                    customItemLabelView.setDrawableLeft(R.mipmap.icon_message_notice);
                    customItemLabelView.setHasBottomDivider(false);
                }
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        boolean zIsCurrentBindDevice = ((DeviceInfoPresenter) this.mPresenter).isCurrentBindDevice(this.mDeviceInfo.getMac());
        if ((this.mDialPlateList == null && zIsCurrentBindDevice && ((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().multiDial) || ((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().V3_get_watch_list_new) {
            ((DeviceInfoPresenter) this.mPresenter).requestTopDialPlate(this.mDeviceInfo);
        }
        if (zIsCurrentBindDevice) {
            ((DeviceInfoPresenter) this.mPresenter).requestFirmwareInfo(this.mDeviceInfo);
            ((DeviceInfoPresenter) this.mPresenter).getFlashInfoFromDevice(this.mDeviceInfo);
            ((DeviceInfoPresenter) this.mPresenter).startGetBatteryTask();
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(R.string.device_info);
    }

    private void initFunctionItem() {
        SupportFunctionInfo supportFunctionInfo = ((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo();
        this.mLayoutDialMarket.setVisibility((supportFunctionInfo.multiDial || supportFunctionInfo.V3_get_watch_list_new) ? 0 : 8);
        if (supportFunctionInfo.multiDial || supportFunctionInfo.V3_get_watch_list_new) {
            initDialImageSize();
        }
        this.mFunctionList.clear();
        if (((DeviceInfoPresenter) this.mPresenter).isSupportHealthMonitoring()) {
            this.mFunctionList.add(1);
        }
        this.mFunctionList.add(2);
        if (((DeviceInfoPresenter) this.mPresenter).isSupportAlarm()) {
            this.mFunctionList.add(3);
        }
        if (((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().calling) {
            this.mCallReminder.setVisibility(0);
            this.mCallReminder.setTitle(getLanguageText(((DeviceInfoPresenter) this.mPresenter).isSupportCallAndRemind() ? R.string.device_contract_conversation : R.string.device_call_reminder));
        }
        if (((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().V3_schedule_reminder) {
            this.mItemRemind.setVisibility(0);
            this.mItemRemind.setBackground(R.drawable.selector_item_top_corner_bg);
            this.mCallReminder.setBackground(R.drawable.user_center_item_selector);
        } else {
            this.mItemRemind.setVisibility(8);
            this.mCallReminder.setBackground(R.drawable.selector_item_top_corner_bg);
        }
        this.mFunctionList.add(4);
        if (this.mFunctionList.isEmpty()) {
            this.mRecyclerView.setVisibility(8);
        }
        this.mAdapter.notifyDataSetChanged();
        this.mItemAlexa.setVisibility(supportFunctionInfo.ex_table_main7_voice_transmission ? 0 : 8);
        this.mItemIntelligentMotion.setVisibility(supportFunctionInfo.activity_switch ? 0 : 8);
        this.mItemDeviceUpgrade.setVisibility(supportFunctionInfo.deviceUpdate ? 0 : 8);
        if (supportFunctionInfo.deviceUpdate) {
            return;
        }
        this.mItemProblemsAndSuggestions.setBackground(R.drawable.selector_item_top_corner_bg);
    }

    private void initDialDefaultShape() {
        this.mIsCircle = 1 == ((DeviceInfoPresenter) this.mPresenter).getDeviceShape();
        for (int i = 0; i < this.mLayoutDial.getChildCount(); i++) {
            if (this.mIsCircle) {
                ((ImageView) this.mLayoutDial.getChildAt(i)).setImageResource(R.mipmap.ic_default_dial_oval);
            } else if (this.mIsBracelet) {
                ((ImageView) this.mLayoutDial.getChildAt(i)).setImageResource(R.mipmap.ic_default_dial_bracelet);
            } else {
                ((ImageView) this.mLayoutDial.getChildAt(i)).setImageResource(((DeviceInfoPresenter) this.mPresenter).getDialImageAspectRatio() > 1.0f ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square);
            }
        }
    }

    private void initDialImageSize() {
        this.mIvDial4.setVisibility(this.mIsBracelet ? 0 : 8);
        this.mLayoutDial.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                DeviceInfoActivity.this.mLayoutDial.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                DeviceInfoActivity.this.setDialLayoutParams();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialLayoutParams() {
        float dialImageAspectRatio = ((DeviceInfoPresenter) this.mPresenter).getDialImageAspectRatio();
        for (int i = 0; i < this.mLayoutDial.getChildCount(); i++) {
            View childAt = this.mLayoutDial.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            layoutParams.height = (int) (childAt.getWidth() * dialImageAspectRatio);
            childAt.setLayoutParams(layoutParams);
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        int type = baseMessage.getType();
        if (type == 101) {
            showDisplayByConnectStatus();
            this.mMtvStatus.setText(R.string.device_connected);
            if (((DeviceInfoPresenter) this.mPresenter).isCurrentBindDevice(this.mDeviceInfo.getMac())) {
                initDialDefaultShape();
                List<TopDialPlateEntity.DialPlate> list = this.mDialPlateList;
                if (list != null) {
                    onRequestDialSuccess(list);
                } else if (((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().multiDial || ((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().V3_get_watch_list_new) {
                    ((DeviceInfoPresenter) this.mPresenter).requestTopDialPlate(this.mDeviceInfo);
                }
                ((DeviceInfoPresenter) this.mPresenter).requestFirmwareInfo(this.mDeviceInfo);
                ((DeviceInfoPresenter) this.mPresenter).getFlashInfoFromDevice(this.mDeviceInfo);
                ((DeviceInfoPresenter) this.mPresenter).startGetBatteryTask();
            }
            initFunctionItem();
            return;
        }
        if (type == 102) {
            this.mMtvStatus.setText(R.string.device_disconnected);
            return;
        }
        if (type == 104) {
            initDeviceInfo();
            initDeviceData();
            return;
        }
        if (type == 824) {
            this.mItemDeviceUpgrade.cancelPromptDot();
            return;
        }
        if (type == 846) {
            if (((Boolean) baseMessage.getData()).booleanValue()) {
                String deviceImageUrl = ((DeviceInfoPresenter) this.mPresenter).getDeviceImageUrl(this.mDeviceInfo.getDeviceId());
                if (TextUtils.isEmpty(deviceImageUrl)) {
                    return;
                }
                ImageLoaderUtil.loadImgFillet(this.mIvDeviceImg, deviceImageUrl, 0, this.mIsBracelet ? R.mipmap.icon_type_bracelet : R.mipmap.icon_type_watch);
                this.mDeviceInfo.setImageUrl(deviceImageUrl);
                return;
            }
            ((DeviceInfoPresenter) this.mPresenter).queryDeviceInfo();
            return;
        }
        if (type != 849) {
            return;
        }
        setDialLayoutParams();
        if (((DeviceInfoPresenter) this.mPresenter).isCurrentBindDevice(this.mDeviceInfo.getMac())) {
            List<TopDialPlateEntity.DialPlate> list2 = this.mDialPlateList;
            if (list2 != null) {
                onRequestDialSuccess(list2);
            } else if (((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().multiDial || ((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().V3_get_watch_list_new) {
                ((DeviceInfoPresenter) this.mPresenter).requestTopDialPlate(this.mDeviceInfo);
            }
        }
    }

    private void showDisplayByConnectStatus() {
        boolean zIsCurrentConnectedDevice = ((DeviceInfoPresenter) this.mPresenter).isCurrentConnectedDevice(this.mDeviceInfo.getMac());
        this.mLayoutConnect.setVisibility(zIsCurrentConnectedDevice ? 0 : 8);
        this.mLayoutDisconnect.setVisibility(zIsCurrentConnectedDevice ? 8 : 0);
    }

    private void initDeviceInfo() {
        this.mMtvDeviceName.setText(this.mDeviceInfo.getDeviceName());
        this.mRtvBattery.setText(String.format(getString(R.string.battery_percent), Integer.valueOf(((DeviceInfoPresenter) this.mPresenter).getDeviceBattery())));
        this.mRtvBattery.setCompoundDrawablesWithIntrinsicBounds(ResourceUtil.getDrawable(((DeviceInfoPresenter) this.mPresenter).getBatteryIconResByPower(((DeviceInfoPresenter) this.mPresenter).getDeviceBattery())), (Drawable) null, (Drawable) null, (Drawable) null);
        this.mMtvDeviceCustomName.setText(this.mDeviceInfo.getCustomName());
        this.mCallReminder.setValue(getString((this.mSwitchStatus.callReminderSwitched && checkSelfPermission(PermissionUtil.getOnlyPhonePermission())) ? R.string.public_opened : R.string.public_unopened));
        this.mItemDeviceUpgrade.setValue("V" + ((DeviceInfoPresenter) this.mPresenter).getAppBleDevice().getDeviceThirdVersion());
    }

    private void initDeviceData() {
        StepDayData todayStep = ((DeviceInfoPresenter) this.mPresenter).getTodayStep();
        this.mItemStep.setValue(todayStep == null ? "--" : String.valueOf(todayStep.getNumSteps()));
        this.mItemStep.setUnit(todayStep == null ? "" : getString(R.string.public_sport_step));
        CalorieDayData todayCalories = ((DeviceInfoPresenter) this.mPresenter).getTodayCalories();
        this.mItemCalorie.setValue(todayCalories == null ? "--" : String.valueOf(todayCalories.getActivityCalorie()));
        this.mItemCalorie.setUnit(todayCalories == null ? "" : getString(R.string.public_heat_calorie_short));
        int todayLastHeartRate = ((DeviceInfoPresenter) this.mPresenter).getTodayLastHeartRate();
        this.mItemHeartRate.setValue(todayLastHeartRate != 0 ? String.valueOf(todayLastHeartRate) : "--");
        this.mItemHeartRate.setUnit(todayLastHeartRate != 0 ? getString(R.string.device_heart_rate_unit) : "");
    }

    private void showUnbindDialog() {
        if (this.mDeleteDialog == null) {
            this.mDeleteDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_delete_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceInfoActivity$GBBb4x7_Dy1Vsxn7Nxn3k9HzLCM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showUnbindDialog$1$DeviceInfoActivity(view);
                }
            });
        }
        this.mDeleteDialog.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showUnbindDialog$1$DeviceInfoActivity(View view) {
        ((DeviceInfoPresenter) this.mPresenter).deleteDevice(this.mDeviceInfo);
    }

    @OnClick({R.id.rtv_delete_device, R.id.rtv_relink})
    public void onViewClick(View view) {
        int id = view.getId();
        if (id == R.id.rtv_delete_device) {
            showUnbindDialog();
        } else {
            if (id != R.id.rtv_relink) {
                return;
            }
            ((DeviceInfoPresenter) this.mPresenter).connectDevice(this.mDeviceInfo);
        }
    }

    @OnClick({R.id.iv_editor, R.id.item_dial_market, R.id.iv_dial_1, R.id.iv_dial_2, R.id.iv_dial_3, R.id.iv_dial_4, R.id.item_call_reminder, R.id.item_intelligent_motion, R.id.item_more, R.id.item_device_setting, R.id.item_device_upgrade, R.id.item_problems_and_suggestions, R.id.item_about, R.id.item_amazon_alexa, R.id.item_remind})
    public void onViewClicked(View view) {
        if (!((DeviceInfoPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
        switch (view.getId()) {
            case R.id.item_about /* 2131362375 */:
                SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DeviceAboutActivity.class);
                singleTopIntent.putExtra("device", this.mDeviceInfo);
                startActivity(singleTopIntent);
                break;
            case R.id.item_alarm_clock /* 2131362379 */:
                if (((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().ex_table_main8_v3_sync_alarm) {
                    startActivity(new SingleTopIntent(this.mActivity, (Class<?>) AlarmClockV3Activity.class));
                } else {
                    startActivity(new SingleTopIntent(this.mActivity, (Class<?>) AlarmClockActivity.class));
                }
                break;
            case R.id.item_amazon_alexa /* 2131362382 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) AlexaLoginActivity.class));
                break;
            case R.id.item_call_reminder /* 2131362387 */:
                startActivityForResult(new SingleTopIntent(this.mActivity, (Class<?>) CallReminderActivity.class), 11);
                break;
            case R.id.item_device_setting /* 2131362395 */:
                startActivityForResult(new SingleTopIntent(this.mActivity, (Class<?>) DeviceSettingActivity.class), 10);
                break;
            case R.id.item_device_upgrade /* 2131362396 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) DeviceUpgradeNewActivity.class));
                break;
            case R.id.item_dial_market /* 2131362397 */:
                if (HomeFragmentPresenter.mIsSyncing) {
                    showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                } else {
                    startActivity(new SingleTopIntent(this.mActivity, (Class<?>) DialMarketActivity.class));
                }
                break;
            case R.id.item_health_monitoring /* 2131362416 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) HealthMonitoringActivity.class));
                break;
            case R.id.item_intelligent_motion /* 2131362423 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) MotionRecognitionActivity.class));
                break;
            case R.id.item_problems_and_suggestions /* 2131362462 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) FeedbackActivity.class));
                break;
            case R.id.item_remind /* 2131362465 */:
                startActivity(new Intent(this.mActivity, (Class<?>) RemindersActivity.class));
                break;
            case R.id.item_tutorial /* 2131362495 */:
                Intent singleTopIntent2 = new SingleTopIntent(this.mActivity, (Class<?>) CommonWebViewActivity.class);
                singleTopIntent2.putExtra("type", 1);
                startActivity(singleTopIntent2);
                break;
            case R.id.iv_dial_1 /* 2131362557 */:
                jump2DialDetail(0);
                break;
            case R.id.iv_dial_2 /* 2131362558 */:
                jump2DialDetail(1);
                break;
            case R.id.iv_dial_3 /* 2131362559 */:
                jump2DialDetail(2);
                break;
            case R.id.iv_dial_4 /* 2131362560 */:
                jump2DialDetail(3);
                break;
            case R.id.iv_editor /* 2131362572 */:
                editDeviceCustomName();
                break;
        }
    }

    private void jump2DialDetail(int i) {
        List<TopDialPlateEntity.DialPlate> list = this.mDialPlateList;
        if (list == null || list.size() <= i) {
            return;
        }
        if (TextUtils.equals(this.mDialPlateList.get(i).getFaceType(), "SELF_CUSTOMIZE")) {
            SingleTopIntent singleTopIntent = null;
            TopDialPlateEntity.DialPlate dialPlate = this.mDialPlateList.get(i);
            if (TextUtils.isEmpty(dialPlate.getCustomFaceType())) {
                return;
            }
            if (dialPlate.getCustomFaceType().equals("CUSTOM_SIMPLE")) {
                singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDefinedActivity.class);
            } else if (dialPlate.getCustomFaceType().equals("CUSTOM_FUNCTION")) {
                singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DialDefinedFunctionActivity.class);
            } else if (dialPlate.getCustomFaceType().equals("CUSTOM_PHOTO")) {
                singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) WallpaperDialActivity.class);
            }
            if (singleTopIntent != null) {
                singleTopIntent.putExtra("dial", new MyDialListEntity.DialInfo(dialPlate));
                singleTopIntent.putExtra("come_from_device_info_activity", true);
                startActivity(singleTopIntent);
                return;
            }
            return;
        }
        SingleTopIntent singleTopIntent2 = new SingleTopIntent(this.mActivity, (Class<?>) DialDetailActivity.class);
        singleTopIntent2.putExtra("dial", new MyDialListEntity.DialInfo(this.mDialPlateList.get(i)));
        singleTopIntent2.putExtra("come_from_device_info_activity", true);
        startActivity(singleTopIntent2);
    }

    @Override // com.ido.life.base.TextLengthWatcher.OnTextChangedListener
    public void onAfterTextChanged(String str) {
        this.mEtDeviceCustomName.setText(str);
        Selection.setSelection(this.mEtDeviceCustomName.getText(), this.mEtDeviceCustomName.getText().toString().length());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10 && intent != null) {
            this.mActivity.finish();
        }
        if (i == 11) {
            this.mSwitchStatus = ((DeviceInfoPresenter) this.mPresenter).getSwitchStatus();
            this.mCallReminder.setValue(getString((this.mSwitchStatus.callReminderSwitched && checkSelfPermission(PermissionUtil.getOnlyPhonePermission())) ? R.string.public_opened : R.string.public_unopened));
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideKeyboard(currentFocus, motionEvent)) {
                if (currentFocus != null) {
                    hideKeyboardAndSaveData(currentFocus.getWindowToken());
                } else {
                    resetEditMode();
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean isShouldHideKeyboard(View view, MotionEvent motionEvent) {
        if (view == null && this.mEtDeviceCustomName.isShown()) {
            view = this.mEtDeviceCustomName;
        }
        if (!(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public void hideKeyboardAndSaveData(IBinder iBinder) {
        InputMethodManager inputMethodManager;
        if (iBinder == null || (inputMethodManager = (InputMethodManager) getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(iBinder, 2);
        resetEditMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetEditMode() {
        String string = this.mEtDeviceCustomName.getText().toString();
        if (!TextUtils.isEmpty(string)) {
            this.mDeviceInfo.setCustomName(string);
            ((DeviceInfoPresenter) this.mPresenter).saveDeviceInfo(this.mDeviceInfo);
            this.mMtvDeviceCustomName.setText(this.mDeviceInfo.getCustomName());
        }
        this.mEtDeviceCustomName.setVisibility(8);
        this.mLayoutEdit.setVisibility(0);
    }

    private void editDeviceCustomName() {
        this.mEtDeviceCustomName.setText(this.mDeviceInfo.getCustomName());
        this.mLayoutEdit.setVisibility(8);
        this.mEtDeviceCustomName.setVisibility(0);
        Selection.setSelection(this.mEtDeviceCustomName.getText(), this.mEtDeviceCustomName.getText().toString().length());
    }

    @Override // com.ido.life.module.device.view.IDeviceInfoView
    public void onGetBattery(int i) {
        this.mRtvBattery.setText(String.format(getString(R.string.battery_percent), Integer.valueOf(i)));
    }

    @Override // com.ido.life.module.device.view.IDeviceInfoView
    public void onRequestedNewOtaInfo() {
        this.mItemDeviceUpgrade.setPromptDotAndTip(getLanguageText(R.string.find_new_version));
    }

    @Override // com.ido.life.module.device.view.IDeviceInfoView
    public void onDeleteDeviceSuccess() {
        showToast(getLanguageText(R.string.device_delete_success));
        finish();
    }

    @Override // com.ido.life.module.device.view.IDeviceInfoView
    public void onDeleteDeviceFailed() {
        showToast(getLanguageText(R.string.device_delete_failed));
    }

    @Override // com.ido.life.module.device.view.IDeviceInfoView
    public void onRequestDialSuccess(List<TopDialPlateEntity.DialPlate> list) {
        if (list == null) {
            return;
        }
        this.mDialPlateList = list;
        int i = 0;
        boolean z = ((DeviceInfoPresenter) this.mPresenter).getDialImageAspectRatio() > 1.0f;
        while (true) {
            if (i >= Math.min(list.size(), this.mIsBracelet ? 4 : 3)) {
                return;
            }
            TopDialPlateEntity.DialPlate dialPlate = list.get(i);
            if (dialPlate != null) {
                DialPlateProxy dialPlateProxy = new DialPlateProxy(list.get(i));
                Drawable drawable = getResources().getDrawable(this.mIsCircle ? R.mipmap.ic_default_dial_oval : this.mIsBracelet ? R.mipmap.ic_default_dial_bracelet : z ? R.mipmap.ic_default_dial_rectangle : R.mipmap.ic_default_dial_square);
                String imageUrl = dialPlateProxy.getImageUrl();
                boolean zHasRefresh = dialPlateProxy.hasRefresh();
                CommonLogUtil.d(dialPlate.getOtaFaceName() + ", hasRefresh = " + zHasRefresh);
                if (i == 0) {
                    AppCompatImageView appCompatImageView = this.mIvDial1;
                    int i2 = this.mRadius;
                    if (zHasRefresh) {
                        drawable = appCompatImageView.getDrawable();
                    }
                    ImageLoaderUtil.loadImgFillet(appCompatImageView, imageUrl, zHasRefresh, i2, drawable);
                } else if (i == 1) {
                    AppCompatImageView appCompatImageView2 = this.mIvDial2;
                    int i3 = this.mRadius;
                    if (zHasRefresh) {
                        drawable = appCompatImageView2.getDrawable();
                    }
                    ImageLoaderUtil.loadImgFillet(appCompatImageView2, imageUrl, zHasRefresh, i3, drawable);
                } else if (i == 2) {
                    AppCompatImageView appCompatImageView3 = this.mIvDial3;
                    int i4 = this.mRadius;
                    if (zHasRefresh) {
                        drawable = appCompatImageView3.getDrawable();
                    }
                    ImageLoaderUtil.loadImgFillet(appCompatImageView3, imageUrl, zHasRefresh, i4, drawable);
                } else {
                    AppCompatImageView appCompatImageView4 = this.mIvDial4;
                    int i5 = this.mRadius;
                    if (zHasRefresh) {
                        drawable = appCompatImageView4.getDrawable();
                    }
                    ImageLoaderUtil.loadImgFillet(appCompatImageView4, imageUrl, zHasRefresh, i5, drawable);
                }
            }
            i++;
        }
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedOpenBle() {
        BleHelper.openBLE(this.mActivity);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectStart(BLEDevice bLEDevice) {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.dismissAllowingStateLoss();
            this.mLoadingDialog = null;
        }
        this.mLoadingDialog = CommLoadingDialog.getInstance(getLanguageText(R.string.device_connecting), getLanguageText(R.string.device_connect_success), getLanguageText(R.string.device_connect_failed), true);
        this.mLoadingDialog.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectSuccess(boolean z) {
        if (z) {
            ((DeviceInfoPresenter) this.mPresenter).getBasicInfoFromDevice();
            return;
        }
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(true);
        }
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectFailed(int i) {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(false);
        }
        if (i == 408) {
            finish();
        }
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onInDfuMode(final BLEDevice bLEDevice) {
        dismissLoadingDialog();
        ((DeviceInfoPresenter) this.mPresenter).disconnect();
        CommBottomConfirmDialog.newInstance(getString(R.string.dfu_mode_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceInfoActivity$gHse8tpY3ezJR9k-y814mQeuIL4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onInDfuMode$2$DeviceInfoActivity(bLEDevice, view);
            }
        }).show(this.mActivity.getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$onInDfuMode$2$DeviceInfoActivity(BLEDevice bLEDevice, View view) {
        SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) DeviceUpgradeNewActivity.class);
        singleTopIntent.putExtra("device_info", bLEDevice);
        startActivity(singleTopIntent);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindWrongDevice(BLEDevice bLEDevice) {
        CommonLogUtil.d("onBindWrongDevice = " + bLEDevice);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onGetDeviceInfoSuccess() {
        ((DeviceInfoPresenter) this.mPresenter).bindDevice();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedAuthCode(int i) {
        CommonLogUtil.d("onNeedAuthCode len = " + i);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedConfirm(int i, String str) {
        CommonLogUtil.d("onNeedConfirm mac = " + str + ";    shape = " + i);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindSuccess() {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(true);
        }
        initDeviceData();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindTimeOut(int i) {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog != null) {
            commLoadingDialog.setComplete(false);
        }
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindFailed(int i, boolean z) {
        CommLoadingDialog commLoadingDialog = this.mLoadingDialog;
        if (commLoadingDialog == null) {
            return;
        }
        if (z) {
            commLoadingDialog.setComplete(false, getLanguageText(R.string.device_bind_failed_rejected));
        } else {
            commLoadingDialog.setComplete(false);
        }
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        int iIntValue = this.mFunctionList.get(i).intValue();
        if (iIntValue == 1) {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) HealthMonitoringActivity.class));
            return;
        }
        if (iIntValue == 2) {
            SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) CommonWebViewActivity.class);
            singleTopIntent.putExtra("type", 6);
            startActivity(singleTopIntent);
        } else if (iIntValue != 3) {
            if (iIntValue != 4) {
                return;
            }
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) (NoticeAppUtil.isSupportV3Notify() ? NotificationSettingActivity.class : NotificationActivity.class)));
        } else if (((DeviceInfoPresenter) this.mPresenter).getSupportFunctionInfo().ex_table_main8_v3_sync_alarm) {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) AlarmClockV3Activity.class));
        } else {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) AlarmClockActivity.class));
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ((DeviceInfoPresenter) this.mPresenter).pauseGetBatteryTask();
    }
}