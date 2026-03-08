package com.ido.life.module.guide;

import android.os.Bundle;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseFragment;

/* JADX INFO: loaded from: classes2.dex */
public class GuideThirdFragment extends BaseFragment {

    @BindView(R.id.rtv_context_second)
    RegularTextView mRegularTextViewContext;

    @BindView(R.id.rtv_title_first)
    RegularTextView mRegularTextViewTitle;

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_guide_third;
    }

    public static GuideThirdFragment newInstance() {
        Bundle bundle = new Bundle();
        GuideThirdFragment guideThirdFragment = new GuideThirdFragment();
        guideThirdFragment.setArguments(bundle);
        return guideThirdFragment;
    }

    @Override // com.ido.life.base.BaseFragment
    protected void refreshLanguage() {
        super.refreshLanguage();
        this.mRegularTextViewTitle.setText(LanguageUtil.getLanguageText(R.string.logn_lauch_guide_three));
        this.mRegularTextViewContext.setText(LanguageUtil.getLanguageText(R.string.logn_lauch_guide_third));
    }
}