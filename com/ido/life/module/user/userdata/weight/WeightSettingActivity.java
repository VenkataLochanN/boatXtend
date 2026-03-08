package com.ido.life.module.user.userdata.weight;

import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.RulerView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeightSettingActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/ido/life/module/user/userdata/weight/WeightSettingActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/user/userdata/weight/WeightSettingPresenter;", "Lcom/ido/life/module/user/userdata/weight/IWeightSettingView;", "()V", "dismissLoading", "", "getLayoutResId", "", "initViews", "saveWeightFailed", "errorMsg", "", "saveWeightSuccess", "showLoading", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeightSettingActivity extends BaseActivity<WeightSettingPresenter> implements IWeightSettingView {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_weight_setting_layout;
    }

    @Override // com.ido.life.module.user.userdata.weight.IWeightSettingView
    public void saveWeightFailed(String errorMsg) {
        Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
    }

    public static final /* synthetic */ WeightSettingPresenter access$getMPresenter$p(WeightSettingActivity weightSettingActivity) {
        return (WeightSettingPresenter) weightSettingActivity.mPresenter;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(1);
        this.mTitleBar.setRightImg(R.mipmap.icon_save);
        TextView tv_title = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
        tv_title.setText(getLanguageText(R.string.mine_data_weight));
        if (((WeightSettingPresenter) this.mPresenter).getUnit() == 1) {
            ((RulerView) _$_findCachedViewById(com.ido.life.R.id.ruler_weight)).initData(getString(R.string.weight_kg_unit_short), "", 250, 10, 1, 5);
        } else {
            ((RulerView) _$_findCachedViewById(com.ido.life.R.id.ruler_weight)).initData(getString(R.string.weight_pound_unit_short), "", 551, 22, 10, 5);
        }
        ((RulerView) _$_findCachedViewById(com.ido.life.R.id.ruler_weight)).setData(((WeightSettingPresenter) this.mPresenter).getUserWeight());
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.user.userdata.weight.WeightSettingActivity.initViews.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightSettingPresenter weightSettingPresenterAccess$getMPresenter$p = WeightSettingActivity.access$getMPresenter$p(WeightSettingActivity.this);
                RulerView ruler_weight = (RulerView) WeightSettingActivity.this._$_findCachedViewById(com.ido.life.R.id.ruler_weight);
                Intrinsics.checkExpressionValueIsNotNull(ruler_weight, "ruler_weight");
                weightSettingPresenterAccess$getMPresenter$p.saveWeight(ruler_weight.getCenterData());
            }
        });
    }

    @Override // com.ido.life.module.user.userdata.weight.IWeightSettingView
    public void saveWeightSuccess() {
        NormalToast.showToast(getLanguageText(R.string.setting_success), 2000);
        setResult(-1);
        finish();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }
}