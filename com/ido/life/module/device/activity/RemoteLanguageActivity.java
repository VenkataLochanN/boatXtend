package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommRefreshHeader;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.customview.viewgroup.LoadingTextView;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.module.device.presenter.RemoteLanguagePresenter;
import com.ido.life.module.device.view.IRemoteLanguageView;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.smartrefresh.layout.SmartRefreshLayout;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteLanguageActivity extends BaseActivity<RemoteLanguagePresenter> implements OnRefreshListener, IRemoteLanguageView, MultiItemTypeAdapterForRV.OnItemClickListener {
    private CommonRecyclerViewAdapter<RemoteLanguage.LanguageInfo> mAdapter;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private CommBottomConfirmDialog mDialog;
    private boolean mFollowSys;
    private List<RemoteLanguage.LanguageInfo> mLanguageList;

    @BindView(R.id.layout_failed)
    LinearLayout mLayoutFailed;
    private int mLoadingPosition = -1;
    private int mLoadingType;
    private LoadingTextView mLoadingView;

    @BindView(R.id.mtv_no_language)
    MediumTextView mMtvNoLanguage;

    @BindView(R.id.mtv_retry)
    MediumTextView mMtvRetry;
    private int mProgress;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.refresh_header)
    CommRefreshHeader mRefreshHeader;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mSelectedLanguage;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_remote_language;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return false;
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mLanguageList = new ArrayList();
        this.mFollowSys = ((RemoteLanguagePresenter) this.mPresenter).isFollowSys();
        getLanguageInfo();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$RemoteLanguageActivity$0jc5UCCuHjT4dIgHtzcQI8rxe3c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$RemoteLanguageActivity(view);
            }
        });
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new CommonRecyclerViewAdapter<RemoteLanguage.LanguageInfo>(this, R.layout.item_remote_language, this.mLanguageList) { // from class: com.ido.life.module.device.activity.RemoteLanguageActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, RemoteLanguage.LanguageInfo languageInfo, int i) {
                RemoteLanguageActivity.this.bindData2View(commonRecyclerViewHolder, languageInfo, i);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
        this.mRefreshLayout.setOnRefreshListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$RemoteLanguageActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_language_set));
        this.mMtvNoLanguage.setText(getLanguageText(R.string.device_not_obtained_language));
        this.mMtvRetry.setText(getLanguageText(R.string.device_retry));
    }

    private void getLanguageInfo() {
        this.mRefreshLayout.setVisibility(8);
        this.mLayoutFailed.setVisibility(8);
        this.mCommLoadingView.setVisibility(0);
        ((RemoteLanguagePresenter) this.mPresenter).getDeviceLanguageInfo();
        ((RemoteLanguagePresenter) this.mPresenter).requestLanguageList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindData2View(CommonRecyclerViewHolder commonRecyclerViewHolder, RemoteLanguage.LanguageInfo languageInfo, int i) {
        CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_label_view);
        LoadingTextView loadingTextView = (LoadingTextView) commonRecyclerViewHolder.getView(R.id.view_loading);
        customItemLabelView.setDrawableRightVisibility(0);
        customItemLabelView.setHasBottomDivider(i == 0);
        customItemLabelView.setTitle(languageInfo.getName());
        int codeId = languageInfo.getCodeId();
        int i2 = R.mipmap.icon_radio_checked;
        if (codeId == 0) {
            loadingTextView.setVisibility(8);
            loadingTextView.stopAnimation();
            if (!this.mFollowSys) {
                i2 = R.mipmap.icon_radio_normal;
            }
            customItemLabelView.setDrawableRight(i2);
            return;
        }
        if (this.mLoadingPosition == i) {
            customItemLabelView.setDrawableRightVisibility(8);
            if (this.mLoadingView == null) {
                this.mLoadingView = loadingTextView;
            }
            refreshLoadingProgress();
            return;
        }
        loadingTextView.setVisibility(8);
        loadingTextView.stopAnimation();
        if (languageInfo.installed) {
            if (languageInfo.hasNewVersion) {
                customItemLabelView.setDrawableRight(R.mipmap.icon_language_upgrade);
                return;
            }
            if (languageInfo.getCodeId() != this.mSelectedLanguage || this.mFollowSys) {
                i2 = R.mipmap.icon_radio_normal;
            }
            customItemLabelView.setDrawableRight(i2);
            return;
        }
        customItemLabelView.setDrawableRight(R.mipmap.icon_language_download);
    }

    private void refreshLoadingProgress() {
        this.mLoadingView.setText(String.format(getLanguageText(this.mLoadingType == 1 ? R.string.device_downloading_x : R.string.device_installing_x), Integer.valueOf(this.mProgress)));
        int i = this.mProgress;
        if (i == 0) {
            this.mLoadingView.setVisibility(0);
            this.mLoadingView.startAnimation();
        } else if (i == 100) {
            this.mLoadingView.setImageResource(R.mipmap.icon_set_complete);
            this.mLoadingView.setText(getLanguageText(this.mLoadingType == 1 ? R.string.device_download_success : R.string.device_install_success));
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
        ((RemoteLanguagePresenter) this.mPresenter).requestLanguageList();
    }

    @Override // com.ido.life.module.device.view.IRemoteLanguageView
    public void onRequestLanguageSuccess(int i, List<RemoteLanguage.LanguageInfo> list) {
        this.mCommLoadingView.setVisibility(8);
        this.mRefreshLayout.finishRefresh();
        this.mSelectedLanguage = i;
        if (list == null) {
            return;
        }
        this.mAdapter.addAll(list);
    }

    @Override // com.ido.life.module.device.view.IRemoteLanguageView
    public void onGetDeviceLanguageInfoSuccess() {
        this.mCommLoadingView.setVisibility(8);
        this.mRefreshLayout.setVisibility(0);
        this.mLayoutFailed.setVisibility(8);
    }

    @Override // com.ido.life.module.device.view.IRemoteLanguageView
    public void onGetDeviceLanguageInfoFailed() {
        this.mCommLoadingView.setVisibility(8);
        this.mRefreshLayout.setVisibility(8);
        this.mLayoutFailed.setVisibility(0);
    }

    @Override // com.ido.life.module.device.view.IRemoteLanguageView
    public void onLanguageProgress(int i, int i2) {
        this.mLoadingType = i;
        this.mProgress = i2;
        if (this.mLoadingView == null) {
            this.mAdapter.notifyItemChanged(this.mLoadingPosition);
        } else {
            refreshLoadingProgress();
        }
    }

    @Override // com.ido.life.module.device.view.IRemoteLanguageView
    public void onProgressComplete(int i, boolean z) {
        if (i == 2 && z) {
            finish();
            return;
        }
        if (z) {
            return;
        }
        this.mRefreshLayout.setEnabled(true);
        this.mLoadingView = null;
        this.mLoadingPosition = -1;
        CommonRecyclerViewAdapter<RemoteLanguage.LanguageInfo> commonRecyclerViewAdapter = this.mAdapter;
        commonRecyclerViewAdapter.setData(commonRecyclerViewAdapter.getDatas());
        showFailedDialog(i);
    }

    private void showFailedDialog(int i) {
        String languageText;
        if (i == 1) {
            languageText = getLanguageText(R.string.device_download_failed_tip);
        } else {
            languageText = getLanguageText(R.string.device_install_failed_tip);
        }
        CommBottomConfirmDialog.newInstance(getLanguageText(R.string.setting_failed), languageText, getLanguageText(R.string.i_see), "", false).show(getSupportFragmentManager());
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        if (RemoteLanguageHelper.isLoading) {
            return;
        }
        if (!((RemoteLanguagePresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
            return;
        }
        RemoteLanguage.LanguageInfo languageInfo = this.mLanguageList.get(i);
        if (languageInfo.getCodeId() == 0) {
            if (this.mFollowSys) {
                return;
            }
            this.mFollowSys = true;
            this.mSelectedLanguage = 0;
            this.mAdapter.setData(this.mLanguageList);
            ((RemoteLanguagePresenter) this.mPresenter).switchDeviceLanguage(this.mSelectedLanguage);
            return;
        }
        if (languageInfo.hasNewVersion || !languageInfo.installed) {
            if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
                showToast(getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
                return;
            }
            this.mRefreshLayout.setEnabled(false);
            this.mLoadingPosition = i;
            ((RemoteLanguagePresenter) this.mPresenter).downloadLanguageFile(languageInfo);
            return;
        }
        if (languageInfo.getCodeId() == this.mSelectedLanguage) {
            return;
        }
        this.mFollowSys = false;
        this.mSelectedLanguage = languageInfo.getCodeId();
        this.mAdapter.setData(this.mLanguageList);
        ((RemoteLanguagePresenter) this.mPresenter).switchDeviceLanguage(this.mSelectedLanguage);
    }

    private void showInstallingDialog() {
        if (this.mDialog == null) {
            this.mDialog = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.device_language), getLanguageText(R.string.device_language_setting_tip), getLanguageText(R.string.i_see), null, false);
        }
        this.mDialog.show(getSupportFragmentManager());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (RemoteLanguageHelper.isLoading) {
            showInstallingDialog();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.mtv_retry})
    public void onViewClicked() {
        this.mLayoutFailed.setVisibility(8);
        getLanguageInfo();
    }
}