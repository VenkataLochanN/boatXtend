package com.ido.life.module.user.country;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public class CountryChooseTipActivity extends BaseActivity {

    @BindView(R.id.tv_choose_country_description)
    TextView mTvDescription;

    @BindView(R.id.tv_choose_country_one)
    TextView mTvOne;

    @BindView(R.id.tv_choose_country_two)
    TextView mTvTwo;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_choose_country_tip;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initViews();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.register_choose_country_title));
        this.mTvDescription.setText(getLanguageText(R.string.register_choose_country_tip1));
        this.mTvOne.setText(getLanguageText(R.string.register_choose_country_tip2));
        this.mTvTwo.setText(getLanguageText(R.string.register_choose_country_tip3));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }
}