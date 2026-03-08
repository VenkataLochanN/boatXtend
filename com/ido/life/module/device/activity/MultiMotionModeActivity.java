package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.SortBean;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.customview.recyclerview.SortDragAdapter;
import com.ido.life.module.device.presenter.MultiMotionModePresenter;
import com.ido.life.module.device.view.IMultiMotionModeView;
import com.ido.life.module.home.HomeFragmentPresenter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MultiMotionModeActivity extends BaseActivity<MultiMotionModePresenter> implements OnStartDragListener, IMultiMotionModeView {
    private List<SortBean> defaultStateList = new ArrayList();
    private SortDragAdapter mAdapter;
    private DefaultItemTouchHelper mItemTouchHelper;
    private int mMaxShowCount;
    private int mMinShowCount;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.rtv_motion_mode_tip)
    RegularTextView mRtvMotionTypeTip;
    private List<SortBean> mTypeStateList;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_motion_type;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        initRecyclerView();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mAdapter.setData(((MultiMotionModePresenter) this.mPresenter).getMotionTypeStateList());
        this.mTypeStateList = this.mAdapter.getDatas();
        this.defaultStateList.addAll(this.mTypeStateList);
        int[] showCount = ((MultiMotionModePresenter) this.mPresenter).getShowCount();
        this.mMaxShowCount = showCount[0];
        this.mMinShowCount = showCount[1];
        this.mAdapter.setLimitedCount(this.mMaxShowCount, this.mMinShowCount);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MultiMotionModeActivity$lVhyvgSgpVdAMtH_ENiBvyBRgWE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$MultiMotionModeActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$MultiMotionModeActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_multi_motion_mode));
        this.mRtvMotionTypeTip.setText(String.format(getLanguageText(R.string.device_multi_motion_mode_tip), Integer.valueOf(this.mMinShowCount)));
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new SortDragAdapter(new ArrayList(), this);
        this.mItemTouchHelper = new DefaultItemTouchHelper(new DefaultItemTouchHelperCallback(this.mAdapter));
        this.mItemTouchHelper.setDragEnable(true);
        this.mItemTouchHelper.setSwipeEnable(false);
        this.mItemTouchHelper.attachToRecyclerView(this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private boolean isDataChanged() {
        return !this.mTypeStateList.toString().equals(this.defaultStateList.toString());
    }

    @Override // com.ido.life.customview.recyclerview.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.onStartDrag(viewHolder);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            } else {
                ((MultiMotionModePresenter) this.mPresenter).sendMotionType2Device(this.mAdapter.getSelectedList());
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    @Override // com.ido.life.module.device.view.IMultiMotionModeView
    public void onSetMotionTypeSuccess() {
        CommonLogUtil.d("onSetMotionTypeSuccess");
    }

    @Override // com.ido.life.module.device.view.IMultiMotionModeView
    public void onSetMotionTypeFailed() {
        showCmdResultToast(false);
    }
}