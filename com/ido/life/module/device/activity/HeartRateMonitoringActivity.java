package com.ido.life.module.device.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity;
import com.ido.life.bean.HeartRateMode;
import com.ido.life.bean.HeartRateModeBean;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.dialog.DialogUtils;
import com.ido.life.module.device.presenter.HeartRateMonitoringPresenter;
import com.ido.life.module.device.view.IHeartRateView;
import com.ido.life.module.home.HomeFragmentPresenter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateMonitoringActivity extends BaseHealthMonitoringActivity<HeartRateMonitoringPresenter> implements CustomToggleButton.OnSwitchListener, IHeartRateView, MultiItemTypeAdapterForRV.OnItemClickListener, TimeDialogFragment.OnItemSelectedListener, ReminderSelectView.OnReminderChangedListener {
    private static final int ITEM_END_TIME = 1;
    private static final int ITEM_START_TIME = 0;
    public static final String MODE = "mode";
    private HeartRateModeBean defaultBean;
    private boolean isOpenDetection;

    @BindView(R.id.llHeartModel)
    LinearLayout llHeartModel;
    private CommonRecyclerViewAdapter<HeartRateMode> mAdapter;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private List<HeartRateMode> mHeartRateModeList;
    private HeartRateSmartMode mHeartRateSmartMode;
    private String mHighHeartRateValue;
    private int mHighHeartRateValuePos;
    private List<String> mHighHeartRateValues;

    @BindView(R.id.item_end_time)
    CustomItemLabelView mItemEndTime;

    @BindView(R.id.item_heart_rate_switch)
    CustomItemLabelView mItemHeartRateSwitch;

    @BindView(R.id.measuring_detection_high_remind)
    CustomItemLabelView mItemHighRemind;

    @BindView(R.id.measuring_detection_low_remind)
    CustomItemLabelView mItemLowRemind;

    @BindView(R.id.item_start_time)
    CustomItemLabelView mItemStartTime;

    @BindView(R.id.layout_content)
    NestedScrollView mLayoutContent;

    @BindView(R.id.layout_load_failed)
    LinearLayout mLayoutLoadFailed;

    @BindView(R.id.device_measuring_time_layout)
    LinearLayout mLayoutMeasuringTime;

    @BindView(R.id.device_measuring_remind_layout)
    LinearLayout mLayoutRemindTime;
    private String mLowHeartRateValue;
    private int mLowHeartRateValuePos;
    private List<String> mLowHeartRateValues;
    private HeartRateModeBean mModeBean;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_remind_tip)
    RegularTextView mRtvRemindTip;
    private boolean mSupportHeartRateModeCustomTime;

    @BindView(R.id.tipsTv)
    TextView mTipsTv;

    @BindView(R.id.tv_measuring_frequency)
    MediumTextView mTvMeasuringFrequency;

    @BindView(R.id.tvTip1)
    TextView tvTip1;

    @BindView(R.id.vReminder)
    ReminderSelectView vReminder;
    private int selected = 0;
    private int mMeasurementInterval = -1;
    private int mDefaultMeasurementInterval = -1;
    private int mMeasurementMode = -1;
    private int mDefaultMeasurementMode = -1;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_heart_rate_detection;
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mCommLoadingView.setVisibility(0);
        this.mLayoutContent.setVisibility(8);
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            ((HeartRateMonitoringPresenter) this.mPresenter).getHeartRateRemindValues();
            ((HeartRateMonitoringPresenter) this.mPresenter).getHeartRateSmartMode();
            return;
        }
        onGetHeartRateModeSuccess(getIntent().getIntExtra(MODE, -1));
        this.mLayoutMeasuringTime.setVisibility(((HeartRateMonitoringPresenter) this.mPresenter).isSupportHeartRateDetectionTime() ? 0 : 8);
        this.mSupportHeartRateModeCustomTime = ((HeartRateMonitoringPresenter) this.mPresenter).isSupportHeartRateModeCustomTime();
        if (this.mSupportHeartRateModeCustomTime) {
            ((HeartRateMonitoringPresenter) this.mPresenter).getHeartRateRemindValues();
            this.mLayoutRemindTime.setVisibility(0);
        } else {
            this.mLayoutRemindTime.setVisibility(8);
        }
        ((HeartRateMonitoringPresenter) this.mPresenter).getHeartRateMode(false);
    }

    private void setRateRemindData() {
        String languageText;
        String languageText2;
        CommonLogUtil.printAndSave("mModeBean = " + this.mModeBean);
        if (this.mModeBean == null) {
            this.mModeBean = ((HeartRateMonitoringPresenter) this.mPresenter).getSupportHeartRateModeList(null);
            this.defaultBean = ((HeartRateMonitoringPresenter) this.mPresenter).getSupportHeartRateModeList(null);
        }
        int highHeartValue = this.mModeBean.getHighHeartValue();
        int highHeartMode = this.mModeBean.getHighHeartMode();
        int lowHeartValue = this.mModeBean.getLowHeartValue();
        int lowHeartMode = this.mModeBean.getLowHeartMode();
        if (highHeartMode == 170 && this.mItemHeartRateSwitch.getSwitchStatus()) {
            languageText = String.format(getLanguageText(R.string.health_heart_rate_unit_with_value), Integer.valueOf(highHeartValue));
        } else {
            languageText = getLanguageText(R.string.public_close);
        }
        if (lowHeartMode == 170 && this.mItemHeartRateSwitch.getSwitchStatus()) {
            languageText2 = String.format(getLanguageText(R.string.health_heart_rate_unit_with_value), Integer.valueOf(lowHeartValue));
        } else {
            languageText2 = getLanguageText(R.string.public_close);
        }
        this.mItemHighRemind.setValue(languageText);
        this.mItemLowRemind.setValue(languageText2);
        this.mLowHeartRateValuePos = Math.max(this.mLowHeartRateValues.indexOf(lowHeartValue + ""), 0);
        this.mHighHeartRateValuePos = Math.max(this.mHighHeartRateValues.indexOf(highHeartValue + ""), 0);
    }

    @Override // com.ido.life.base.BaseHealthMonitoringActivity, com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        boolean zIsSupportSmartHeartRate = ((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate();
        this.mLayoutRemindTime.setVisibility(zIsSupportSmartHeartRate ? 0 : 8);
        this.vReminder.setVisibility(zIsSupportSmartHeartRate ? 0 : 8);
        this.tvTip1.setVisibility(zIsSupportSmartHeartRate ? 0 : 8);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$HeartRateMonitoringActivity$ntq61LQ-34c06kvSKwfLBaE1wgA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$HeartRateMonitoringActivity(view);
            }
        });
        this.mItemHeartRateSwitch.setOnSwitchListener(this);
        this.vReminder.setOnReminderChangedListener(this);
        initRecycleView();
    }

    public /* synthetic */ void lambda$initEvent$0$HeartRateMonitoringActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.home_card_heartbeat_title));
        this.mItemHeartRateSwitch.setTitle(getLanguageText(R.string.device_continuous_heart_rate_measurement));
        this.mRtvRemindTip.setText(getLanguageText(R.string.device_heart_rate_monitoring_tip));
    }

    private void initRecycleView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mHeartRateModeList = new ArrayList();
        this.mAdapter = new CommonRecyclerViewAdapter<HeartRateMode>(this, R.layout.item_heartrate_mode, this.mHeartRateModeList) { // from class: com.ido.life.module.device.activity.HeartRateMonitoringActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, HeartRateMode heartRateMode, int i) {
                HeartRateMonitoringActivity.this.bindData2View(commonRecyclerViewHolder, heartRateMode);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindData2View(CommonRecyclerViewHolder commonRecyclerViewHolder, HeartRateMode heartRateMode) {
        CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_label_view);
        if (heartRateMode.unitType == 1 && heartRateMode.interval == 5) {
            customItemLabelView.setTitle(R.string.device_real_time_monitoring);
        } else if (heartRateMode.unitType == 2 && heartRateMode.interval == 5) {
            customItemLabelView.setTitle(R.string.device_auto_monitoring);
        } else {
            customItemLabelView.setTitle(String.format(getString(heartRateMode.unitType == 1 ? R.string.device_x_second : R.string.device_x_minuter), Integer.valueOf(heartRateMode.interval)));
        }
        int i = heartRateMode.unitType;
        int i2 = heartRateMode.interval;
        if (i == 2) {
            i2 *= 60;
        }
        customItemLabelView.setDrawableRight(this.mMeasurementInterval == i2 ? R.mipmap.icon_radio_checked : R.mipmap.icon_radio_normal);
    }

    private void setCheckedMode() {
        int i = this.mMeasurementMode;
        if (i == 136 || i == 153) {
            List<HeartRateMode> list = this.mHeartRateModeList;
            if (list != null && !list.isEmpty()) {
                this.mMeasurementMode = 204;
            }
        } else if (i != 204) {
            this.mItemHeartRateSwitch.setSwitchStatus(false);
            setRecycleViewAlphaBySwitch(false);
            return;
        }
        this.mItemHeartRateSwitch.setSwitchStatus(true);
        setRecycleViewAlphaBySwitch(true);
        this.isOpenDetection = true;
    }

    private boolean isDataChanged() {
        HeartRateModeBean heartRateModeBean = this.mModeBean;
        return heartRateModeBean != null ? (this.mMeasurementInterval == this.mDefaultMeasurementInterval && this.mMeasurementMode == this.mDefaultMeasurementMode && heartRateModeBean.toString().equals(this.defaultBean.toString())) ? false : true : (this.mMeasurementInterval == this.mDefaultMeasurementInterval && this.mMeasurementMode == this.mDefaultMeasurementMode) ? false : true;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            String value = this.mItemStartTime.getValue();
            String value2 = this.mItemEndTime.getValue();
            try {
                if (!TextUtils.isEmpty(value)) {
                    String[] strArrSplit = value.split(":");
                    this.mHeartRateSmartMode.start_hour = Integer.parseInt(strArrSplit[0]);
                    this.mHeartRateSmartMode.start_minute = Integer.parseInt(strArrSplit[1]);
                }
                if (!TextUtils.isEmpty(value2)) {
                    String[] strArrSplit2 = value2.split(":");
                    this.mHeartRateSmartMode.end_hour = Integer.parseInt(strArrSplit2[0]);
                    this.mHeartRateSmartMode.end_minute = Integer.parseInt(strArrSplit2[1]);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (((HeartRateMonitoringPresenter) this.mPresenter).isDataChanged(this.mHeartRateSmartMode)) {
                showSettingLoading(false);
                ((HeartRateMonitoringPresenter) this.mPresenter).setHeartRateSmart(this.mHeartRateSmartMode);
                return;
            } else {
                finish();
                return;
            }
        }
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            } else {
                ((HeartRateMonitoringPresenter) this.mPresenter).sendMonitoringMode2Device(this.mMeasurementMode, this.mMeasurementInterval, this.mModeBean);
            }
            finish();
        }
    }

    @OnClick({R.id.item_start_time, R.id.item_end_time, R.id.measuring_detection_high_remind, R.id.measuring_detection_low_remind})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.item_end_time /* 2131362405 */:
                this.selected = 1;
                showTimePickerDialog(this.mModeBean.getEndHour(), this.mModeBean.getEndMinute());
                break;
            case R.id.item_start_time /* 2131362486 */:
                this.selected = 0;
                showTimePickerDialog(this.mModeBean.getStartHour(), this.mModeBean.getStartMinute());
                break;
            case R.id.measuring_detection_high_remind /* 2131363042 */:
                if (this.isOpenDetection || isSmartRemindOpen()) {
                    showHighHeartRateSetDialog();
                }
                break;
            case R.id.measuring_detection_low_remind /* 2131363043 */:
                if (this.isOpenDetection || isSmartRemindOpen()) {
                    showLowHeartRateSetDialog();
                }
                break;
        }
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            HeartRateSmartMode heartRateSmartMode = this.mHeartRateSmartMode;
            if (heartRateSmartMode != null) {
                heartRateSmartMode.mode = z ? 170 : 85;
            }
            updateRemindLayout();
            return;
        }
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportHeartRateV3()) {
            if (z) {
                List<HeartRateMode> list = this.mHeartRateModeList;
                i = (list == null || list.isEmpty()) ? 153 : 204;
            }
            this.mMeasurementMode = i;
        } else {
            this.mMeasurementMode = z ? 153 : 170;
        }
        setRecycleViewAlphaBySwitch(z);
        if (this.mSupportHeartRateModeCustomTime) {
            this.isOpenDetection = z;
            setRateRemindSwitch(z);
        }
    }

    private void setRateRemindSwitch(boolean z) {
        if (z) {
            setRateRemindData();
        } else {
            this.mItemHighRemind.setValue(getLanguageText(R.string.public_close));
            this.mItemLowRemind.setValue(getLanguageText(R.string.public_close));
        }
    }

    private void setRecycleViewAlphaBySwitch(boolean z) {
        this.mTvMeasuringFrequency.setAlpha(z ? 1.0f : 0.3f);
        this.mRecyclerView.setAlpha(z ? 1.0f : 0.3f);
    }

    private void showLowHeartRateSetDialog() {
        DialogUtils.INSTANCE.showHeartRateThresholdSelectDialog(this, LanguageUtil.getLanguageText(R.string.health_heart_rate_low_remind), this.mLowHeartRateValues, this.mLowHeartRateValuePos, new Function1() { // from class: com.ido.life.module.device.activity.-$$Lambda$HeartRateMonitoringActivity$Lh5Oi_aVLhw5VdEB9iMOkFNBvLM
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$showLowHeartRateSetDialog$1$HeartRateMonitoringActivity((Integer) obj);
            }
        });
    }

    public /* synthetic */ Unit lambda$showLowHeartRateSetDialog$1$HeartRateMonitoringActivity(Integer num) {
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            ((HeartRateMonitoringPresenter) this.mPresenter).setHeartRateLowReminder(this.mLowHeartRateValues.get(num.intValue()));
            return null;
        }
        String str = this.mLowHeartRateValues.get(num.intValue());
        this.mLowHeartRateValuePos = num.intValue();
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            int i = Integer.parseInt(str);
            this.mModeBean.setLowHeartValue(i);
            this.mModeBean.setLowHeartMode(170);
            this.mItemLowRemind.setValue(String.format(getLanguageText(R.string.heart_rate_unit), Integer.valueOf(i)));
            return null;
        }
        this.mModeBean.setLowHeartValue(0);
        this.mModeBean.setLowHeartMode(85);
        this.mItemLowRemind.setValue(getLanguageText(R.string.public_close));
        return null;
    }

    private void showHighHeartRateSetDialog() {
        DialogUtils.INSTANCE.showHeartRateThresholdSelectDialog(this, LanguageUtil.getLanguageText(R.string.health_heart_rate_high_remind), this.mHighHeartRateValues, this.mHighHeartRateValuePos, new Function1() { // from class: com.ido.life.module.device.activity.-$$Lambda$HeartRateMonitoringActivity$qz7kDHcpJHNKJvpklZe8SClgu7k
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$showHighHeartRateSetDialog$2$HeartRateMonitoringActivity((Integer) obj);
            }
        });
    }

    public /* synthetic */ Unit lambda$showHighHeartRateSetDialog$2$HeartRateMonitoringActivity(Integer num) {
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            ((HeartRateMonitoringPresenter) this.mPresenter).setHeartRateHighReminder(this.mHighHeartRateValues.get(num.intValue()));
            return null;
        }
        this.mHighHeartRateValuePos = num.intValue();
        String str = this.mHighHeartRateValues.get(num.intValue());
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            int i = Integer.parseInt(str);
            this.mModeBean.setHighHeartValue(i);
            this.mModeBean.setHighHeartMode(170);
            this.mItemHighRemind.setValue(String.format(getLanguageText(R.string.heart_rate_unit), Integer.valueOf(i)));
            return null;
        }
        this.mModeBean.setHighHeartValue(0);
        this.mModeBean.setHighHeartMode(85);
        this.mItemHighRemind.setValue(getLanguageText(R.string.public_close));
        return null;
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateModeSuccess(int i) {
        this.mLayoutLoadFailed.setVisibility(8);
        this.mCommLoadingView.setVisibility(8);
        this.mLayoutContent.setVisibility(0);
        this.mMeasurementMode = i;
        this.mDefaultMeasurementMode = i;
        setCheckedMode();
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateModeV3Success(int i, int i2) {
        if (i == 187 && i2 > 0) {
            i = 204;
        }
        onGetHeartRateModeSuccess(i);
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateMeasureModeV3(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        this.mLayoutLoadFailed.setVisibility(8);
        this.mCommLoadingView.setVisibility(8);
        this.mLayoutContent.setVisibility(0);
        this.mMeasurementInterval = heartRateMeasureModeV3.measurementInterval;
        this.mDefaultMeasurementInterval = heartRateMeasureModeV3.measurementInterval;
        this.mModeBean = ((HeartRateMonitoringPresenter) this.mPresenter).getSupportHeartRateModeList(heartRateMeasureModeV3);
        this.defaultBean = ((HeartRateMonitoringPresenter) this.mPresenter).getSupportHeartRateModeList(heartRateMeasureModeV3);
        List<HeartRateMode> rateModes = this.mModeBean.getRateModes();
        this.mTvMeasuringFrequency.setVisibility(rateModes.size() > 1 ? 0 : 8);
        this.llHeartModel.setVisibility(this.mTvMeasuringFrequency.getVisibility());
        this.mRecyclerView.setVisibility(rateModes.size() > 1 ? 0 : 8);
        if (rateModes.size() == 1) {
            HeartRateMode heartRateMode = rateModes.get(0);
            int i = heartRateMode.unitType;
            int i2 = heartRateMode.interval;
            if (i == 2) {
                i2 *= 60;
            }
            this.mMeasurementInterval = i2;
        }
        this.mItemStartTime.setValue(((HeartRateMonitoringPresenter) this.mPresenter).formatTimeByTimeMode(this.mModeBean.getStartHour(), this.mModeBean.getStartMinute()));
        this.mItemEndTime.setValue(((HeartRateMonitoringPresenter) this.mPresenter).formatTimeByTimeMode(this.mModeBean.getEndHour(), this.mModeBean.getEndMinute()));
        this.mAdapter.addAll(rateModes);
        setHeartRateMeasurementTip(rateModes);
        if (this.mSupportHeartRateModeCustomTime) {
            setRateRemindData();
        }
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateModeFailed() {
        this.mCommLoadingView.setVisibility(8);
        this.mLayoutContent.setVisibility(8);
        this.mLayoutLoadFailed.setVisibility(0);
    }

    private void setHeartRateMeasurementTip(List<HeartRateMode> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string.device_continuous_heart_rate_measurement).concat(IOUtils.LINE_SEPARATOR_UNIX));
        boolean z = false;
        boolean z2 = false;
        for (HeartRateMode heartRateMode : list) {
            if (heartRateMode.unitType == 1 && heartRateMode.interval <= 5) {
                z = true;
            } else if (heartRateMode.unitType == 2 && heartRateMode.interval <= 5) {
                z2 = true;
            }
        }
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate() || (z && z2 && list.size() == 2)) {
            arrayList.add(getString(R.string.device_heart_rate_monitoring_tip_0_1));
        } else if (list.size() > 2) {
            arrayList.add(getString(R.string.device_heart_rate_monitoring_tip_0_2));
        }
        arrayList.add(getString(R.string.device_heart_rate_monitoring_tip_2));
        arrayList.add(getString(R.string.device_heart_rate_monitoring_tip_3));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                sb.append((String) arrayList.get(i));
            } else {
                sb.append(i);
                sb.append((String) arrayList.get(i));
            }
        }
        this.mTipsTv.setVisibility(0);
        this.mTipsTv.setText(sb.toString());
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onSetHeartRateModeSuccess() {
        CommonLogUtil.d("onSetHeartRateModeSuccess");
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onSetHeartRateModeFailed() {
        showCmdResultToast(false);
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateRemindValues(List<String> list, List<String> list2) {
        this.mLowHeartRateValues = list;
        this.mHighHeartRateValues = list2;
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateHighReminder(int i) {
        String languageText;
        if (i > 0) {
            languageText = i + "";
        } else {
            languageText = getLanguageText(R.string.public_close);
        }
        this.mHighHeartRateValue = languageText;
        this.mHighHeartRateValuePos = this.mHighHeartRateValues.indexOf(this.mHighHeartRateValue + "");
        this.mItemHighRemind.setValue(TextUtils.isDigitsOnly(this.mHighHeartRateValue) ? String.format(getLanguageText(R.string.health_heart_rate_unit_with_value), this.mHighHeartRateValue) : this.mHighHeartRateValue);
        HeartRateSmartMode heartRateSmartMode = this.mHeartRateSmartMode;
        if (heartRateSmartMode != null) {
            heartRateSmartMode.high_heart_value = i;
            heartRateSmartMode.high_heart_mode = i > 0 ? 170 : 85;
        }
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetHeartRateLowReminder(int i) {
        String languageText;
        if (i > 0) {
            languageText = i + "";
        } else {
            languageText = getLanguageText(R.string.public_close);
        }
        this.mLowHeartRateValue = languageText;
        this.mLowHeartRateValuePos = this.mLowHeartRateValues.indexOf(this.mLowHeartRateValue + "");
        this.mItemLowRemind.setValue(TextUtils.isDigitsOnly(this.mLowHeartRateValue) ? String.format(getLanguageText(R.string.health_heart_rate_unit_with_value), this.mLowHeartRateValue) : this.mLowHeartRateValue);
        HeartRateSmartMode heartRateSmartMode = this.mHeartRateSmartMode;
        if (heartRateSmartMode != null) {
            heartRateSmartMode.low_heart_value = i;
            heartRateSmartMode.low_heart_mode = i > 0 ? 170 : 85;
        }
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onGetSmartHeartRateMode(HeartRateSmartMode heartRateSmartMode) {
        this.mCommLoadingView.setVisibility(8);
        this.mLayoutContent.setVisibility(0);
        this.mHeartRateSmartMode = heartRateSmartMode;
        HeartRateSmartMode heartRateSmartMode2 = this.mHeartRateSmartMode;
        if (heartRateSmartMode2 != null) {
            this.vReminder.select(heartRateSmartMode2.notify_flag);
            this.mItemHeartRateSwitch.setSwitchStatus(this.mHeartRateSmartMode.mode == 170);
        }
        setHeartRateMeasurementTip(new ArrayList());
        updateRemindLayout();
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onSetSmartHeartRateModeFailed() {
        CommonLogUtil.printAndSave("onSetSmartHeartRateModeFailed");
        showToast(getLanguageText(R.string.public_set_failed));
        finish();
    }

    @Override // com.ido.life.module.device.view.IHeartRateView
    public void onSetSmartHeartRateModeSuccess() {
        CommonLogUtil.printAndSave("onSetSmartHeartRateModeSuccess");
        setResult(-1);
        finish();
    }

    private void updateRemindLayout() {
        boolean zIsSmartRemindOpen = isSmartRemindOpen();
        this.mItemLowRemind.setSwitchEnable(zIsSmartRemindOpen);
        this.mItemHighRemind.setSwitchEnable(zIsSmartRemindOpen);
    }

    private boolean isSmartRemindOpen() {
        HeartRateSmartMode heartRateSmartMode = this.mHeartRateSmartMode;
        return (heartRateSmartMode == null || heartRateSmartMode.mode != 170 || this.mHeartRateSmartMode.notify_flag == 3) ? false : true;
    }

    @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
    public void onReminderChanged(int i) {
        HeartRateSmartMode heartRateSmartMode = this.mHeartRateSmartMode;
        if (heartRateSmartMode != null) {
            heartRateSmartMode.notify_flag = i;
        }
        updateRemindLayout();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        if (this.mMeasurementMode != 204) {
            return;
        }
        HeartRateMode heartRateMode = this.mHeartRateModeList.get(i);
        int i2 = heartRateMode.unitType;
        int i3 = heartRateMode.interval;
        if (i2 == 2) {
            i3 *= 60;
        }
        if (this.mMeasurementInterval == i3) {
            return;
        }
        this.mMeasurementInterval = i3;
        this.mAdapter.addAll(new ArrayList(this.mHeartRateModeList));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((HeartRateMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            saveData();
        } else if (this.mMeasurementInterval != -1 && isDataChanged()) {
            saveData();
        } else {
            super.onBackPressed();
        }
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((HeartRateMonitoringPresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((HeartRateMonitoringPresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)});
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        int i4 = this.selected;
        if (i4 == 0) {
            this.mModeBean.setStartHour(i2);
            this.mModeBean.setStartMinute(i3);
            this.mItemStartTime.setValue(((HeartRateMonitoringPresenter) this.mPresenter).formatTimeByTimeMode(i2, i3));
        } else {
            if (i4 != 1) {
                return;
            }
            this.mModeBean.setEndHour(i2);
            this.mModeBean.setEndMinute(i3);
            this.mItemEndTime.setValue(((HeartRateMonitoringPresenter) this.mPresenter).formatTimeByTimeMode(i2, i3));
        }
    }
}