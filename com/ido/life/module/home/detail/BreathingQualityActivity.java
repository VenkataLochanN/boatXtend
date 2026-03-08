package com.ido.life.module.home.detail;

import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;

/* JADX INFO: loaded from: classes2.dex */
public class BreathingQualityActivity extends BaseActivity {

    @BindView(R.id.rtv_description_1)
    RegularTextView mRtvDescription1;

    @BindView(R.id.rtv_description_2)
    RegularTextView mRtvDescription2;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_breathing_quality;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.detail_breathing_quality));
        this.mRtvDescription1.setText(getLanguageText(R.string.breathing_quality_describe_1));
        this.mRtvDescription2.setText(getLanguageText(R.string.breathing_quality_describe_2));
    }
}