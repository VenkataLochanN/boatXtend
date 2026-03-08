package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.AlarmType;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.AlarmTypePresenter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmTypeEditActivity extends BaseActivity<AlarmTypePresenter> implements MultiItemTypeAdapterForRV.OnItemClickListener {
    public static final String ALARM_TYPE = "alarm_type";
    public static final int ALARM_TYPE_DEFAULT = -1;
    public static final int CODE_ALARM_TYPE = 33;
    private CommonRecyclerViewAdapter<AlarmType> mAdapter;
    private List<AlarmType> mAlarmTypeList;
    private int mCheckedType;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_alarm_type_edit;
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mCheckedType = getIntent().getIntExtra(ALARM_TYPE, -1);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setTitle(R.string.device_alarm_name).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmTypeEditActivity$qCeQn8MR-lZAokhWEG1iKU7vaDc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmTypeEditActivity(view);
            }
        });
        initRecyclerView();
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmTypeEditActivity(View view) {
        onBackPressed();
    }

    private void initRecyclerView() {
        this.mAlarmTypeList = ((AlarmTypePresenter) this.mPresenter).getSupportAlarmType();
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new CommonRecyclerViewAdapter<AlarmType>(this, R.layout.item_comm_list, this.mAlarmTypeList) { // from class: com.ido.life.module.device.activity.AlarmTypeEditActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, AlarmType alarmType, int i) {
                CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_label_view);
                customItemLabelView.setHasBottomDivider(i != AlarmTypeEditActivity.this.mAlarmTypeList.size() - 1);
                customItemLabelView.setTitle(alarmType.nameResId);
                customItemLabelView.setDrawableRight(alarmType.type == AlarmTypeEditActivity.this.mCheckedType ? R.mipmap.icon_radio_checked : R.mipmap.icon_radio_normal);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = this.mAlarmTypeList.get(i).type;
        if (this.mCheckedType == i2) {
            return;
        }
        this.mCheckedType = i2;
        this.mAdapter.setData(this.mAlarmTypeList);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(33, new Intent().putExtra(ALARM_TYPE, this.mCheckedType));
        finish();
    }
}