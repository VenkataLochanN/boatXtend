package com.ido.life.module.home.pressure.prestart;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.module.home.pressure.question.PressureQuestionActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PressureAjustPreStartActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/ido/life/module/home/pressure/prestart/PressureAjustPreStartActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/home/pressure/prestart/PressureAjustPreStartPresenter;", "()V", "getLayoutResId", "", "initLabelLanguage", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureAjustPreStartActivity extends BaseActivity<PressureAjustPreStartPresenter> {
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
        return R.layout.activity_pressure_ajust_prestart_layout;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.pressure_ajust));
        TextView tv_title_question = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_question);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_question, "tv_title_question");
        tv_title_question.setText(getLanguageText(R.string.pressure_anwser_question));
        TextView tv_question_desc = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_question_desc);
        Intrinsics.checkExpressionValueIsNotNull(tv_question_desc, "tv_question_desc");
        tv_question_desc.setText(getLanguageText(R.string.pressure_anwser_question_desc));
        TextView tv_title_wear = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_wear);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_wear, "tv_title_wear");
        tv_title_wear.setText(getLanguageText(R.string.pressure_wear_device));
        TextView tv_start = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_start);
        Intrinsics.checkExpressionValueIsNotNull(tv_start, "tv_start");
        tv_start.setText(getLanguageText(R.string.sport_begin));
        ((TextView) _$_findCachedViewById(com.ido.life.R.id.tv_start)).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.pressure.prestart.PressureAjustPreStartActivity.initLabelLanguage.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureAjustPreStartActivity pressureAjustPreStartActivity = PressureAjustPreStartActivity.this;
                pressureAjustPreStartActivity.startActivity(new Intent(pressureAjustPreStartActivity, (Class<?>) PressureQuestionActivity.class));
                PressureAjustPreStartActivity.this.supportFinishAfterTransition();
            }
        });
    }
}