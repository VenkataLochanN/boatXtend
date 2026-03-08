package com.ido.life.module.bind;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.DrawableTextView;

/* JADX INFO: loaded from: classes2.dex */
public class BindHelpActivity_ViewBinding implements Unbinder {
    private BindHelpActivity target;

    public BindHelpActivity_ViewBinding(BindHelpActivity bindHelpActivity) {
        this(bindHelpActivity, bindHelpActivity.getWindow().getDecorView());
    }

    public BindHelpActivity_ViewBinding(BindHelpActivity bindHelpActivity, View view) {
        this.target = bindHelpActivity;
        bindHelpActivity.mDtvHelp1 = (DrawableTextView) Utils.findRequiredViewAsType(view, R.id.dtv_help_1, "field 'mDtvHelp1'", DrawableTextView.class);
        bindHelpActivity.mDtvHelp2 = (DrawableTextView) Utils.findRequiredViewAsType(view, R.id.dtv_help_2, "field 'mDtvHelp2'", DrawableTextView.class);
        bindHelpActivity.mDtvHelp3 = (DrawableTextView) Utils.findRequiredViewAsType(view, R.id.dtv_help_3, "field 'mDtvHelp3'", DrawableTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindHelpActivity bindHelpActivity = this.target;
        if (bindHelpActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindHelpActivity.mDtvHelp1 = null;
        bindHelpActivity.mDtvHelp2 = null;
        bindHelpActivity.mDtvHelp3 = null;
    }
}