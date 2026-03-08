package com.ido.life.module.home.pressure.guide;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BasePresenter;
import com.ido.life.module.home.pressure.ajust.PressureAjustActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PressureAjustGuideActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/pressure/guide/PressureAjustGuideActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/base/BasePresenter;", "()V", "mScore", "", "getLayoutResId", "initData", "", "initLabelLanguage", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureAjustGuideActivity extends BaseActivity<BasePresenter<?>> {
    private HashMap _$_findViewCache;
    private int mScore;

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
        return R.layout.activity_pressure_ajust_guide_layout;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mScore = getIntent().getIntExtra("score", 0);
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_start)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.pressure.guide.PressureAjustGuideActivity.initData.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Intent intent = new Intent(PressureAjustGuideActivity.this, (Class<?>) PressureAjustActivity.class);
                intent.putExtra("score", PressureAjustGuideActivity.this.mScore);
                PressureAjustGuideActivity.this.startActivity(intent);
                PressureAjustGuideActivity.this.supportFinishAfterTransition();
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.pressure_ajust));
        TextView tv_title_wear_check = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_wear_check);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_wear_check, "tv_title_wear_check");
        tv_title_wear_check.setText(LanguageUtil.getLanguageText(R.string.wear_device_testing));
        TextView tv_desc_wear_check = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_desc_wear_check);
        Intrinsics.checkExpressionValueIsNotNull(tv_desc_wear_check, "tv_desc_wear_check");
        tv_desc_wear_check.setText(LanguageUtil.getLanguageText(R.string.wear_device_testing_desc));
        TextView tv_time_wear_check = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_time_wear_check);
        Intrinsics.checkExpressionValueIsNotNull(tv_time_wear_check, "tv_time_wear_check");
        tv_time_wear_check.setText(LanguageUtil.getLanguageText(R.string.wear_device_test_time));
        TextView tv_start = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_start);
        Intrinsics.checkExpressionValueIsNotNull(tv_start, "tv_start");
        tv_start.setText(LanguageUtil.getLanguageText(R.string.sport_begin));
    }
}