package com.ido.life.module.device.activity;

import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationTipsActivity extends BaseActivity {
    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_notification_tips;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_allow_notifications));
    }
}