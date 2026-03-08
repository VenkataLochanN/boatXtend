package com.ido.life.module.alexa;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CBLLoginGuideActivity_ViewBinding implements Unbinder {
    private CBLLoginGuideActivity target;

    public CBLLoginGuideActivity_ViewBinding(CBLLoginGuideActivity cBLLoginGuideActivity) {
        this(cBLLoginGuideActivity, cBLLoginGuideActivity.getWindow().getDecorView());
    }

    public CBLLoginGuideActivity_ViewBinding(CBLLoginGuideActivity cBLLoginGuideActivity, View view) {
        this.target = cBLLoginGuideActivity;
        cBLLoginGuideActivity.mTvCode = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_code, "field 'mTvCode'", TextView.class);
        cBLLoginGuideActivity.mTvGo = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_go, "field 'mTvGo'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CBLLoginGuideActivity cBLLoginGuideActivity = this.target;
        if (cBLLoginGuideActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cBLLoginGuideActivity.mTvCode = null;
        cBLLoginGuideActivity.mTvGo = null;
    }
}