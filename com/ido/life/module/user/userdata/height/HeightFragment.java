package com.ido.life.module.user.userdata.height;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.google.android.material.tabs.TabLayout;
import com.ido.common.base.BaseCoreFragment;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.customview.BaseRulerNewView;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.module.user.userdata.OnChangeListener;
import com.ido.life.module.user.userdata.height.HeightContract;
import com.ido.life.module.user.view.MyTabLayout;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HeightFragment extends BaseCoreFragment implements HeightContract.View {
    private static final String TAG = "HeightFragment";

    @BindView(R.id.iv_forward)
    ImageButton mIvForward;
    private OnChangeListener mOnChangeListener;
    private HeightContract.Presenter mPresenter;

    @BindView(R.id.tab_user_height)
    MyTabLayout mTabUserHeight;
    private List<String> mTitles;

    @BindView(R.id.person_info_height)
    BaseRulerNewView personInfoHeight;

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_height;
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void setHeight(int i, int i2) {
    }

    public void setmOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public static HeightFragment newInstance() {
        Bundle bundle = new Bundle();
        HeightFragment heightFragment = new HeightFragment();
        heightFragment.setArguments(bundle);
        return heightFragment;
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initListener();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onViewCreated ");
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(RunTimeUtil.getInstance().getUserId());
        if (unitSettingQueryUnitSetting != null) {
            if (unitSettingQueryUnitSetting.getUnit() == 1) {
                this.mTabUserHeight.getTabAt(0).select();
            } else {
                this.mTabUserHeight.getTabAt(1).select();
            }
        }
    }

    private void initListener() {
        this.mTabUserHeight.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.ido.life.module.user.userdata.height.HeightFragment.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                HeightFragment.this.mPresenter.setHeightType(tab.getPosition() + 1, HeightFragment.this.personInfoHeight.getData());
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initView() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initView ");
        this.mTitles = new ArrayList();
        this.mTitles.add(ResourceUtil.getString(R.string.mine_unit_metric_system));
        this.mTitles.add(ResourceUtil.getString(R.string.mine_unit_british_system));
        this.mTabUserHeight.setTitle(this.mTitles);
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initData() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initData ");
        this.mPresenter = new HeightPresenter(this);
        this.mPresenter.initHeight();
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onVisible ");
        HeightContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.initHeight();
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    public void onInVisible() {
        super.onInVisible();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onInVisible ");
    }

    @OnClick({R.id.iv_forward})
    public void checkHeight(View view) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "checkHeight ");
        this.mPresenter.checkHeight(this.personInfoHeight.getData());
    }

    @OnClick({R.id.iv_back_forward})
    public void backForward(View view) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "backForward ");
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onPageBack();
        }
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void setCurrentIndex(int i) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "setCurrentIndex ");
        CommonLogUtil.d("index=" + i + " tabCount=" + this.mTabUserHeight.getTabCount());
        if (i < 0) {
            i = 0;
        }
        if (i > this.mTabUserHeight.getTabCount() - 1) {
            i = this.mTabUserHeight.getChildCount() - 1;
        }
        CommonLogUtil.d("index=" + i);
        this.mTabUserHeight.getTabAt(i).select();
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void setRulerView(int i) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "setRulerView ");
        if (i == 1) {
            this.personInfoHeight.initData(new String[]{getString(R.string.public_unit_cm)}, 250.0f, 50.0f, 5, 10, 1);
            this.personInfoHeight.setData(this.mPresenter.getHeightCm() - 50);
        } else {
            this.personInfoHeight.initData(new String[]{ResourceUtil.getString(R.string.public_unit_feet), ResourceUtil.getString(R.string.public_unit_inch)}, 8.2f, 1.8f, 6, 1, 1);
            this.personInfoHeight.setData(this.mPresenter.getHeightInch() - 20);
        }
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void changeForwardEnable(boolean z) {
        this.mIvForward.setEnabled(z);
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void setForward() {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onPageNext();
        }
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void showLoading() {
        WaitingDialog.showDialog(getActivity());
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.userdata.height.HeightContract.View
    public int getHeight() {
        return this.personInfoHeight.getHeight();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(HeightContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}