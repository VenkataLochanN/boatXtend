package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.SortBean;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelper;
import com.ido.life.customview.recyclerview.DefaultItemTouchHelperCallback;
import com.ido.life.customview.recyclerview.OnStartDragListener;
import com.ido.life.customview.recyclerview.SortDragAdapter;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.device.presenter.QuickAppPresenter;
import com.ido.life.module.device.view.IQuickAppView;
import com.ido.life.module.home.HomeFragmentPresenter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class QuickAppActivity extends BaseActivity<QuickAppPresenter> implements IQuickAppView, OnStartDragListener {
    private SortDragAdapter mAdapter;

    @BindView(R.id.rtv_quick_app_tip)
    RegularTextView mAppTip;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private DefaultItemTouchHelper mItemTouchHelper;

    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;

    @BindView(R.id.layout_load_failed)
    LinearLayout mLayoutFailed;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_quick_app;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$QuickAppActivity$-29kOeMaja3CGvCodWSJN7Wn3gg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$QuickAppActivity(view);
            }
        });
        initRecyclerView();
        ((QuickAppPresenter) this.mPresenter).getQuickAppItemList();
    }

    public /* synthetic */ void lambda$initEvent$0$QuickAppActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_quick_app));
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

    @Override // com.ido.life.customview.recyclerview.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.onStartDrag(viewHolder);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        ArrayList<SortBean> selectedList = this.mAdapter.getSelectedList();
        if (((QuickAppPresenter) this.mPresenter).isDataChanged(selectedList)) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            } else {
                ((QuickAppPresenter) this.mPresenter).sendQuickApp2Device(selectedList);
            }
        }
        finish();
    }

    @Override // com.ido.life.module.device.view.IQuickAppView
    public void onGetQuickAppStart() {
        this.mLayoutContent.setVisibility(8);
        this.mLayoutFailed.setVisibility(8);
        this.mCommLoadingView.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IQuickAppView
    public void onGetQuickAppSuccess(List<SortBean> list, int i, int i2) {
        this.mLayoutContent.setVisibility(0);
        this.mLayoutFailed.setVisibility(8);
        this.mCommLoadingView.setVisibility(8);
        this.mAdapter.setLimitedCount(i, i2);
        this.mAppTip.setText(String.format(LanguageUtil.getLanguageText(R.string.device_quick_app_tip), Integer.valueOf(i2)));
        this.mAdapter.setData(list);
    }

    @Override // com.ido.life.module.device.view.IQuickAppView
    public void onGetQuickAppFailed() {
        this.mCommLoadingView.setVisibility(8);
        this.mLayoutContent.setVisibility(8);
        this.mLayoutFailed.setVisibility(0);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }
}