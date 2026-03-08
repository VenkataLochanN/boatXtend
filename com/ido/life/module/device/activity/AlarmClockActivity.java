package com.ido.life.module.device.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.Alarm;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.HeaderAndFooterWrapper;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.AlarmClockPresenter;
import com.ido.life.module.device.view.IAlarmView;
import com.ido.life.module.home.HomeFragmentPresenter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockActivity extends BaseActivity<AlarmClockPresenter> implements IAlarmView, MultiItemTypeAdapterForRV.OnItemClickListener, View.OnClickListener {
    public static final int CODE_EDIT_ALARM = 88;
    private int clickedPos = -1;
    private List<Alarm> defaultAlarmList;
    private List<Alarm> mAlarmList;
    private View mFootView;
    private HeaderAndFooterWrapper mFooterWrapper;
    private int mMaxAlarmCount;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_center_add)
    RegularTextView mRtvCenterAdd;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_alarm_clock;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mAlarmList = ((AlarmClockPresenter) this.mPresenter).getAlarmList();
        this.defaultAlarmList = new ArrayList(this.mAlarmList);
        this.mMaxAlarmCount = ((AlarmClockPresenter) this.mPresenter).getMaxAlarmCount();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockActivity$v1klV16cbGtqOTKvY-pvuyTtKeM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmClockActivity(view);
            }
        });
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, R.layout.item_alarm, this.mAlarmList);
        this.mFootView = LayoutInflater.from(this).inflate(R.layout.item_add_alarm, (ViewGroup) this.mRecyclerView, false);
        this.mFootView.findViewById(R.id.mtv_add_alarm).setOnClickListener(this);
        this.mFooterWrapper = new HeaderAndFooterWrapper(anonymousClass1);
        this.mRecyclerView.setAdapter(this.mFooterWrapper);
        anonymousClass1.setOnItemClickListener(this);
        updateViewDisplay();
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmClockActivity(View view) {
        onBackPressed();
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.activity.AlarmClockActivity$1, reason: invalid class name */
    class AnonymousClass1 extends CommonRecyclerViewAdapter<Alarm> {
        AnonymousClass1(Context context, int i, List list) {
            super(context, i, list);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
        public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, final Alarm alarm, int i) {
            CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_alarm);
            customItemLabelView.setTitle(((AlarmClockPresenter) AlarmClockActivity.this.mPresenter).formatAlarmTime(alarm.getAlarmHour(), alarm.getAlarmMinute()));
            String alarmNameByType = ((AlarmClockPresenter) AlarmClockActivity.this.mPresenter).getAlarmNameByType(alarm.getAlarmType());
            if (!TextUtils.isEmpty(alarmNameByType)) {
                alarmNameByType = alarmNameByType + "  ";
            }
            customItemLabelView.setSubtitle(String.format("%s%s", alarmNameByType, ((AlarmClockPresenter) AlarmClockActivity.this.mPresenter).formatWeekRepeat(alarm.getWeekRepeat())));
            customItemLabelView.setSwitchStatus(alarm.getOn_off());
            customItemLabelView.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockActivity$1$ocTz4sYowTFJd3HQ-JbYBiAKLZs
                @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
                public final void onSwitched(View view, boolean z) {
                    alarm.setOn_off(z);
                }
            });
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        List<Alarm> list = this.mAlarmList;
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        this.mAlarmList.clear();
        this.mAlarmList.addAll(arrayList);
        updateViewDisplay();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_alarm_clock));
        this.mRtvCenterAdd.setText(LanguageUtil.getLanguageText(R.string.device_no_alarm_set));
    }

    private void updateViewDisplay() {
        List<Alarm> list = this.mAlarmList;
        if (list == null || list.size() == 0) {
            this.mRecyclerView.setVisibility(8);
            this.mRtvCenterAdd.setVisibility(0);
        } else {
            this.mFooterWrapper.removeFootView(this.mFootView);
            if (this.mAlarmList.size() < this.mMaxAlarmCount) {
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
            startActivityForResult(new SingleTopIntent(this, (Class<?>) AlarmClockEditActivity.class), 88);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 88 || intent == null) {
            return;
        }
        Alarm alarm = (Alarm) intent.getSerializableExtra("alarm");
        if (alarm != null) {
            int i3 = this.clickedPos;
            if (i3 == -1) {
                this.mAlarmList.add(alarm);
            } else if (i3 >= 0 && i3 < this.mAlarmList.size()) {
                this.mAlarmList.remove(this.clickedPos);
                this.mAlarmList.add(this.clickedPos, alarm);
            }
        } else {
            int i4 = this.clickedPos;
            if (i4 >= 0 && i4 < this.mAlarmList.size()) {
                this.mAlarmList.remove(this.clickedPos);
            }
        }
        updateViewDisplay();
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        if (clickValid()) {
            this.clickedPos = i;
            SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) AlarmClockEditActivity.class);
            singleTopIntent.putExtra("alarm", this.mAlarmList.get(i));
            startActivityForResult(singleTopIntent, 88);
        }
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        showDeleteAlarmDialog(i);
        return true;
    }

    private void showDeleteAlarmDialog(final int i) {
        CommBottomConfirmDialog.newInstance(getString(R.string.device_confirm_delete_alarm), getString(R.string.public_tip_confirm), getString(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockActivity$0kB40YGabEH1L66d2_dqvnbtOL0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showDeleteAlarmDialog$1$AlarmClockActivity(i, view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showDeleteAlarmDialog$1$AlarmClockActivity(int i, View view) {
        this.mAlarmList.remove(i);
        updateViewDisplay();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (((AlarmClockPresenter) this.mPresenter).isDataChanged(this.mAlarmList)) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                finish();
                return;
            } else {
                showLoadingDialog();
                ((AlarmClockPresenter) this.mPresenter).sendAlarmClock2Device(this.mAlarmList);
                return;
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    @Override // com.ido.life.module.device.view.IAlarmView
    public void onsetAlarmSuccess() {
        dismissLoadingDialog();
        showCmdResultToast(true);
        finish();
    }

    @Override // com.ido.life.module.device.view.IAlarmView
    public void onSetAlarmFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
        finish();
    }
}