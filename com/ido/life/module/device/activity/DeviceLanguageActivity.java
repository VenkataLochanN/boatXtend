package com.ido.life.module.device.activity;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.Language;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.UnitSetPresenter;
import com.ido.life.module.device.view.IUnitSetView;
import com.ido.life.module.home.HomeFragmentPresenter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceLanguageActivity extends BaseActivity<UnitSetPresenter> implements MultiItemTypeAdapterForRV.OnItemClickListener, IUnitSetView {
    private int defaultLanguage;
    private CommonRecyclerViewAdapter<Language> mAdapter;
    private List<Language> mLanguageList;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private int mSelectedLanguage;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_device_language_set;
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mLanguageList = ((UnitSetPresenter) this.mPresenter).getSupportLanguageList();
        this.mSelectedLanguage = ((UnitSetPresenter) this.mPresenter).getSelectedLanguage();
        this.defaultLanguage = ((UnitSetPresenter) this.mPresenter).getSelectedLanguage();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DeviceLanguageActivity$NGkvjg2kZdWMcctpIiNzV1G1UEA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$DeviceLanguageActivity(view);
            }
        });
        this.mRecyclerView.setLayoutManager(new BaseLinearLayoutManager(this));
        this.mAdapter = new CommonRecyclerViewAdapter<Language>(this, R.layout.item_comm_list, this.mLanguageList) { // from class: com.ido.life.module.device.activity.DeviceLanguageActivity.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
            public void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, Language language, int i) {
                CustomItemLabelView customItemLabelView = (CustomItemLabelView) commonRecyclerViewHolder.getView(R.id.item_label_view);
                customItemLabelView.setHasBottomDivider(i == 0);
                customItemLabelView.setTitle(language.getName());
                customItemLabelView.setDrawableRight(language.getCode() == DeviceLanguageActivity.this.mSelectedLanguage ? R.mipmap.icon_radio_checked : R.mipmap.icon_radio_normal);
            }
        };
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$DeviceLanguageActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_language));
    }

    @Override // com.ido.life.customview.recyclerview.MultiItemTypeAdapterForRV.OnItemClickListener
    public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
        int code = this.mLanguageList.get(i).getCode();
        if (this.mSelectedLanguage == code) {
            return;
        }
        this.mSelectedLanguage = code;
        this.mAdapter.setData(this.mLanguageList);
    }

    private boolean isDataChanged() {
        return this.defaultLanguage != this.mSelectedLanguage;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
            } else {
                ((UnitSetPresenter) this.mPresenter).sendLanguage2Device(this.mSelectedLanguage);
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            saveData();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.ido.life.module.device.view.IUnitSetView
    public void onSetUnitSuccess() {
        CommonLogUtil.d("onSetUnitSuccess");
    }

    @Override // com.ido.life.module.device.view.IUnitSetView
    public void onSetUnitFailed() {
        CommonLogUtil.d("onSetUnitFailed");
    }
}