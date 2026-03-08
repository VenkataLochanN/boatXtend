package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.ScreenLuminance;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.ScreenLuminancePresenter;
import com.ido.life.module.device.view.IScreenLuminanceView;
import com.ido.life.module.home.HomeFragmentPresenter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenLuminanceActivity extends BaseActivity<ScreenLuminancePresenter> implements IScreenLuminanceView, MultiItemTypeAdapterForRV.OnItemClickListener {
    private CommonRecyclerViewAdapter<ScreenLuminance> mAdapter;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private int mCurrentLevel;
    private int mCurrentValue;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<ScreenLuminance> mScreenLevelList;

    @BindView(R.id.tv_tip)
    MediumTextView mTvTip;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_screen_luminance;
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setTitle(R.string.device_screen_luminance).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$ScreenLuminanceActivity$TVsRrUFDiK7Q4uj-nN3elPCk6qA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$ScreenLuminanceActivity(view);
            }
        });
        initRecyclerView();
        this.mTvTip.setVisibility(8);
        this.mRecyclerView.setVisibility(8);
        this.mCommLoadingView.setVisibility(0);
        this.mScreenLevelList = ((ScreenLuminancePresenter) this.mPresenter).getScreenLuminanceData();
    }

    public /* synthetic */ void lambda$initEvent$0$ScreenLuminanceActivity(View view) {
        onBackPressed();
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new CommonRecyclerViewAdapter<ScreenLuminance>(this, R.layout.item_comm_list, this.mScreenLevelList) { // from class: com.ido.life.module.device.activity.ScreenLuminanceActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, ScreenLuminance screenLuminance, int i) {
                CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_label_view);
                customItemLabelView.setHasBottomDivider(i != ScreenLuminanceActivity.this.mScreenLevelList.size() - 1);
                customItemLabelView.setTitle(String.format(ScreenLuminanceActivity.this.getLanguageText(R.string.device_x_level), Integer.valueOf(screenLuminance.level)));
                customItemLabelView.setDrawableRight(screenLuminance.level == ScreenLuminanceActivity.this.mCurrentLevel ? R.mipmap.icon_radio_checked : R.mipmap.icon_radio_normal);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
    }

    @Override // com.ido.life.module.device.view.IScreenLuminanceView
    public void onGetScreenLuminanceLevel(int i, int i2) {
        this.mTvTip.setVisibility(0);
        this.mRecyclerView.setVisibility(0);
        this.mCommLoadingView.setVisibility(8);
        this.mCurrentLevel = i;
        this.mCurrentValue = i2;
        this.mAdapter.setData(this.mScreenLevelList);
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        ScreenLuminance screenLuminance = this.mScreenLevelList.get(i);
        int i2 = screenLuminance.level;
        if (i2 == this.mCurrentLevel) {
            return;
        }
        this.mCurrentLevel = i2;
        this.mCurrentValue = screenLuminance.value;
        this.mAdapter.setData(this.mScreenLevelList);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        if (((ScreenLuminancePresenter) this.mPresenter).isDataChanged(this.mCurrentValue)) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            } else {
                ((ScreenLuminancePresenter) this.mPresenter).sendScreenLuminance2Device(this.mCurrentValue);
            }
        }
        finishAfterTransition();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }
}