package com.ido.life.base;

import android.widget.LinearLayout;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.Constant;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseHealthMonitoringActivity<P extends BasePresenter> extends BaseActivity<P> {
    private boolean isNoticeScene = false;

    @BindView(R.id.lay_content)
    protected LinearLayout lay_content;

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.isNoticeScene = getIntent().getBooleanExtra(Constant.KEY_IS_NOTICE_SCENE, false);
        LinearLayout linearLayout = this.lay_content;
        if (linearLayout != null) {
            linearLayout.setVisibility(this.isNoticeScene ? 8 : 0);
        }
    }
}