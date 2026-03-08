package com.ido.life.module.user.userdata.weight;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseCoreFragment;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.RulerView;
import com.ido.life.module.user.userdata.OnChangeListener;
import com.ido.life.module.user.userdata.weight.WeightContract;

/* JADX INFO: loaded from: classes3.dex */
public class WeightFragment extends BaseCoreFragment implements WeightContract.View {
    private static final String TAG = "WeightFragment";

    @BindView(R.id.iv_forward)
    ImageButton mIvForward;
    private OnChangeListener mOnChangeListener;
    private WeightContract.Presenter mPresenter;

    @BindView(R.id.ruler_weight)
    RulerView rulerWeight;

    private void initListener() {
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_weight;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initView() {
    }

    public void setmOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public static WeightFragment newInstance() {
        Bundle bundle = new Bundle();
        WeightFragment weightFragment = new WeightFragment();
        weightFragment.setArguments(bundle);
        return weightFragment;
    }

    @Override // com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initListener();
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        WeightContract.Presenter presenter = this.mPresenter;
        if (presenter != null) {
            presenter.initWeight();
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    public void onInVisible() {
        super.onInVisible();
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void setRulerView(int i) {
        if (i == 1) {
            this.rulerWeight.initData(getString(R.string.public_weight_unit_cn), "", 250, 10, 1, 5);
            this.rulerWeight.setData(this.mPresenter.getWeightKg());
        } else {
            this.rulerWeight.initData(getString(R.string.public_unit_pound), "", 551, 22, 10, 5);
            this.rulerWeight.setData(this.mPresenter.getWeightBan());
        }
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected void initData() {
        this.mPresenter = new WeightPresenter(this);
        this.mPresenter.initWeight();
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void changeForwardEnable(boolean z) {
        CommonLogUtil.d(TAG, "changeForwardEnable: " + z);
        this.mIvForward.setEnabled(z);
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void setForward() {
        if (this.mOnChangeListener != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "设置体重界面开始跳转到下一页--设置目标 ");
            this.mOnChangeListener.onPageNext();
        }
    }

    @OnClick({R.id.iv_forward})
    public void toUpdateWeight(View view) {
        this.mPresenter.toCloudSyncDialog(getFragmentManager());
    }

    @OnClick({R.id.iv_back_forward})
    public void backForward(View view) {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onPageBack();
        }
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void showLoading() {
        WaitingDialog.showDialog(getActivity());
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.userdata.weight.WeightContract.View
    public void toSetGoal() {
        this.mPresenter.checkWeight(IdoApp.getAppContext(), this.rulerWeight.getCenterData());
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(WeightContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}