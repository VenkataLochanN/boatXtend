package com.ido.life.module.device.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.alexa.bean.AlexaV3Alarm;
import com.ido.alexa.bean.AlexaV3AlarmItem;
import com.ido.alexa.util.AlexaNewAlarmUtil;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.bean.AlarmBean;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.HeaderAndFooterWrapper;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.device.presenter.AlarmClockV3Presenter;
import com.ido.life.module.device.view.IAlarmV3View;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockV3Activity extends BaseActivity<AlarmClockV3Presenter> implements IAlarmV3View, MultiItemTypeAdapterForRV.OnItemClickListener, View.OnClickListener {
    public static final int CODE_EDIT_ALARM = 88;
    private int appAlarmSize;
    private String defaultAlarmListString;
    private String defaultAlexaAlarmListString;
    private boolean isSupportV3AlexaAlarm;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private View mFootView;
    private HeaderAndFooterWrapper mFooterWrapper;
    private int mMaxAlarmCount;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_center_add)
    RegularTextView mRtvCenterAdd;
    private List<AlarmV3> mAlarmList = new ArrayList();
    private ArrayList<AlexaV3AlarmItem> mAlexaAlarmList = new ArrayList<>();
    private List<AlarmBean> mTotalList = new ArrayList();
    private int clickedPos = -1;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_alarm_clock;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mCommLoadingView.setVisibility(0);
        this.isSupportV3AlexaAlarm = DeviceConfigHelper.getSupportFunctionInfo().V3_alexa_set_get_alexa_alarm;
        ((AlarmClockV3Presenter) this.mPresenter).getAlexaAlarmList();
        this.mMaxAlarmCount = ((AlarmClockV3Presenter) this.mPresenter).getMaxAlarmCount();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightImg(R.mipmap.icon_setting).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3Activity$yOmWCv5BaEcBnAQiu6BspUTC5wY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmClockV3Activity(view);
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3Activity$d31kH1cF6ds3c3F93uwZefZwL9g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$AlarmClockV3Activity(view);
            }
        }).getTitleLayoutRight(this).setVisibility(4);
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, R.layout.view_alexa_alarm_item, this.mTotalList);
        this.mFootView = LayoutInflater.from(this).inflate(R.layout.item_add_alarm, (ViewGroup) this.mRecyclerView, false);
        this.mFootView.findViewById(R.id.mtv_add_alarm).setOnClickListener(this);
        this.mFooterWrapper = new HeaderAndFooterWrapper(anonymousClass1);
        this.mRecyclerView.setAdapter(this.mFooterWrapper);
        anonymousClass1.setOnItemClickListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmClockV3Activity(View view) {
        startActivity(new SingleTopIntent(this, (Class<?>) AlarmClockSettingActivity.class));
    }

    public /* synthetic */ void lambda$initEvent$1$AlarmClockV3Activity(View view) {
        onBackPressed();
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.AlarmClockV3Activity$1, reason: invalid class name */
    class AnonymousClass1 extends CommonRecyclerViewAdapter<AlarmBean> {
        AnonymousClass1(Context context, int i, List list) {
            super(context, i, list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
        public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, final AlarmBean alarmBean, int i) {
            TextView textView = (TextView) commonRecyclerViewHolder.getView(R.id.rtv_title);
            TextView textView2 = (TextView) commonRecyclerViewHolder.getView(R.id.rtv_subtitle);
            CustomToggleButton customToggleButton = (CustomToggleButton) commonRecyclerViewHolder.getView(R.id.toggle);
            textView.setText(((AlarmClockV3Presenter) AlarmClockV3Activity.this.mPresenter).formatAlarmTime(alarmBean.hour, alarmBean.minute));
            customToggleButton.setSwitchStatus(alarmBean.isOn_off());
            customToggleButton.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3Activity$1$1vLh7_dj0Nc9bAfI--bxl-WoB1I
                @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
                public final void onSwitched(View view, boolean z) {
                    alarmBean.setOn_off(z);
                }
            });
            if (alarmBean.isAlexa()) {
                textView2.setText(((AlarmClockV3Presenter) AlarmClockV3Activity.this.mPresenter).formatWeekRepeat(alarmBean.getWeekRepeat()));
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.mipmap.icon_alexa_alarm, 0);
                return;
            }
            String str = alarmBean.name;
            if (!TextUtils.isEmpty(str)) {
                str = str + "  ";
            }
            textView2.setText(String.format("%s%s", str, ((AlarmClockV3Presenter) AlarmClockV3Activity.this.mPresenter).formatWeekRepeat(alarmBean.getWeekRepeat())));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        List<AlarmBean> list = this.mTotalList;
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.mTotalList);
        this.mTotalList.clear();
        this.mTotalList.addAll(arrayList);
        updateViewDisplay();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_alarm_clock));
        this.mRtvCenterAdd.setText(LanguageUtil.getLanguageText(R.string.device_no_alarm_set));
    }

    private void updateViewDisplay() {
        this.mTitleBar.getTitleLayoutRight(this).setVisibility(0);
        this.mCommLoadingView.setVisibility(8);
        List<AlarmBean> list = this.mTotalList;
        if (list == null || list.size() == 0) {
            this.mRecyclerView.setVisibility(8);
            this.mRtvCenterAdd.setVisibility(0);
        } else {
            this.mFooterWrapper.removeFootView(this.mFootView);
            if (this.appAlarmSize < this.mMaxAlarmCount) {
                this.mFooterWrapper.addFootView(this.mFootView);
            }
            this.mRecyclerView.setVisibility(0);
            this.mRtvCenterAdd.setVisibility(8);
        }
        this.mFooterWrapper.notifyDataSetChanged();
    }

    @OnClick({R.id.rtv_center_add})
    public void onViewClicked(View view) {
        addAlarm();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        addAlarm();
    }

    private void addAlarm() {
        if (clickValid()) {
            this.clickedPos = -1;
            startActivityForResult(new SingleTopIntent(this, (Class<?>) AlarmClockV3EditActivity.class), 88);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 88 || intent == null) {
            return;
        }
        AlarmV3 alarmV3 = (AlarmV3) intent.getSerializableExtra("alarm");
        if (alarmV3 != null) {
            int i3 = this.clickedPos;
            if (i3 == -1) {
                this.mTotalList.add(((AlarmClockV3Presenter) this.mPresenter).AlarmV3ToAlarmBean(alarmV3));
                this.appAlarmSize++;
            } else if (i3 >= 0 && i3 < this.mTotalList.size()) {
                this.mTotalList.remove(this.clickedPos);
                this.mTotalList.add(this.clickedPos, ((AlarmClockV3Presenter) this.mPresenter).AlarmV3ToAlarmBean(alarmV3));
            }
        } else {
            int i4 = this.clickedPos;
            if (i4 >= 0 && i4 < this.mTotalList.size()) {
                this.mTotalList.remove(this.clickedPos);
                this.appAlarmSize--;
            }
        }
        updateViewDisplay();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        if (clickValid()) {
            if (this.mTotalList.get(i).isAlexa()) {
                if (SPHelper.getNoReminderForAlexaAlarmTip()) {
                    return;
                }
                showAlexaAlarmTipDialog();
            } else {
                this.clickedPos = i;
                SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) AlarmClockV3EditActivity.class);
                singleTopIntent.putExtra("alarm", this.mTotalList.get(this.clickedPos));
                startActivityForResult(singleTopIntent, 88);
            }
        }
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        showDeleteAlarmDialog(i);
        return true;
    }

    private void showDeleteAlarmDialog(final int i) {
        CommBottomConfirmDialog.newInstance(getString(R.string.device_confirm_delete_alarm), getString(R.string.public_tip_confirm), getString(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3Activity$-bmljKMUaH9RWEWwoz_XBoVNgRU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showDeleteAlarmDialog$2$AlarmClockV3Activity(i, view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteAlarmDialog$2$AlarmClockV3Activity(int i, View view) {
        this.mTotalList.remove(i);
        updateViewDisplay();
    }

    private void showAlexaAlarmTipDialog() {
        CommBottomConfirmDialog.newInstance(getString(R.string.tips), getString(R.string.alexa_alarm_tip), getString(R.string.sync_ok), getString(R.string.main_setting_bg_protect_no_remind), true).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3Activity$EnCk6iGdxK5KBLI8UUEb7ILVOqw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SPHelper.setNoReminderForAlexaAlarmTip(true);
            }
        }).show(getSupportFragmentManager());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (HomeFragmentPresenter.mIsSyncing) {
            showToast(getLanguageText(R.string.syncing_pls_try_again_later));
        } else {
            ArrayList<AlexaV3AlarmItem> arrayList = new ArrayList<>();
            for (AlarmBean alarmBean : this.mTotalList) {
                if (alarmBean.isAlexa()) {
                    arrayList.add(((AlarmClockV3Presenter) this.mPresenter).toAlexaV3AlarmItem(alarmBean));
                } else {
                    this.mAlarmList.add(alarmBean);
                }
            }
            if (this.isSupportV3AlexaAlarm && ((AlarmClockV3Presenter) this.mPresenter).isDataChanged(this.defaultAlexaAlarmListString, ((AlarmClockV3Presenter) this.mPresenter).alexaFormatList2String(arrayList))) {
                ((AlarmClockV3Presenter) this.mPresenter).sendAlexaAlarm2Device(arrayList);
            } else {
                sendAlarmClock2Device();
            }
        }
        finish();
    }

    private void sendAlarmClock2Device() {
        ArrayList arrayList = new ArrayList();
        for (AlarmBean alarmBean : this.mTotalList) {
            if (!alarmBean.isAlexa()) {
                arrayList.add(alarmBean);
            }
        }
        if (((AlarmClockV3Presenter) this.mPresenter).isDataChanged(this.defaultAlarmListString, ((AlarmClockV3Presenter) this.mPresenter).formatList2String(arrayList))) {
            ((AlarmClockV3Presenter) this.mPresenter).sendAlarmClock2Device(arrayList);
        }
    }

    @Override // com.ido.life.module.device.view.IAlarmV3View
    public void onGetAlarmList(List<AlarmV3> list) {
        this.appAlarmSize = list.size();
        this.defaultAlarmListString = ((AlarmClockV3Presenter) this.mPresenter).formatList2String(list);
        this.mTotalList.addAll(((AlarmClockV3Presenter) this.mPresenter).AlexaAlarmToAlarmBean(this.mAlexaAlarmList));
        this.mTotalList.addAll(((AlarmClockV3Presenter) this.mPresenter).AlarmV3ToAlarmBean(list));
        updateViewDisplay();
    }

    @Override // com.ido.life.module.device.view.IAlarmV3View
    public void onGetAlexaAlarm(List<AlexaV3AlarmItem> list) {
        this.mAlexaAlarmList.clear();
        this.mAlexaAlarmList.addAll(list);
        this.defaultAlexaAlarmListString = ((AlarmClockV3Presenter) this.mPresenter).alexaFormatList2String(list);
    }

    @Override // com.ido.life.module.device.view.IAlarmV3View
    public void onSetAlarmSuccess() {
        CommonLogUtil.d("onSetAlarmSuccess");
    }

    @Override // com.ido.life.module.device.view.IAlarmV3View
    public void onSetAlarmFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
    }

    @Override // com.ido.life.module.device.view.IAlarmV3View
    public void onSetAlexaAlarmSuccess(AlexaV3Alarm alexaV3Alarm) {
        CommonLogUtil.d("onSetAlexaAlarmSuccess");
        AlexaNewAlarmUtil.getInstance().handlerDeviceAlexaAlarm(GsonUtil.toJson(alexaV3Alarm));
        sendAlarmClock2Device();
    }

    @Override // com.ido.life.module.device.view.IAlarmV3View
    public void onSetAlexaAlarmFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
        CommonLogUtil.d("onSetAlexaAlarmFailed");
        sendAlarmClock2Device();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (baseMessage.getType() == 600) {
            for (AlarmBean alarmBean : this.mTotalList) {
                if (alarmBean != null && !alarmBean.isAlexa()) {
                    alarmBean.delay_min = ((AlarmClockV3Presenter) this.mPresenter).getIntervalMinute();
                    alarmBean.repeat_times = ((AlarmClockV3Presenter) this.mPresenter).getRepeatCount();
                }
            }
        }
    }
}