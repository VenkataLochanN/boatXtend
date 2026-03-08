package com.ido.life.module.bind;

import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.DrawableTextView;

/* JADX INFO: loaded from: classes2.dex */
public class BindHelpActivity extends BaseActivity {

    @BindView(R.id.dtv_help_1)
    DrawableTextView mDtvHelp1;

    @BindView(R.id.dtv_help_2)
    DrawableTextView mDtvHelp2;

    @BindView(R.id.dtv_help_3)
    DrawableTextView mDtvHelp3;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_bind_help;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.help));
        this.mDtvHelp1.setText(getLanguageText(R.string.device_bind_help_1));
        this.mDtvHelp2.setText(getLanguageText(R.string.device_bind_help_2));
        this.mDtvHelp3.setText(getLanguageText(R.string.device_bind_help_3));
    }
}