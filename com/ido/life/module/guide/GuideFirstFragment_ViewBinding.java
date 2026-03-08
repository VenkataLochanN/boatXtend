package com.ido.life.module.guide;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class GuideFirstFragment_ViewBinding implements Unbinder {
    private GuideFirstFragment target;

    public GuideFirstFragment_ViewBinding(GuideFirstFragment guideFirstFragment, View view) {
        this.target = guideFirstFragment;
        guideFirstFragment.mRegularTextViewTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_title_first, "field 'mRegularTextViewTitle'", RegularTextView.class);
        guideFirstFragment.mRegularTextViewContext = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_context_second, "field 'mRegularTextViewContext'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        GuideFirstFragment guideFirstFragment = this.target;
        if (guideFirstFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        guideFirstFragment.mRegularTextViewTitle = null;
        guideFirstFragment.mRegularTextViewContext = null;
    }
}