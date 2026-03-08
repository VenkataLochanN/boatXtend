package com.ido.life.module.guide;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class GuideSecondFragment_ViewBinding implements Unbinder {
    private GuideSecondFragment target;

    public GuideSecondFragment_ViewBinding(GuideSecondFragment guideSecondFragment, View view) {
        this.target = guideSecondFragment;
        guideSecondFragment.mRegularTextViewTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_title_first, "field 'mRegularTextViewTitle'", RegularTextView.class);
        guideSecondFragment.mRegularTextViewContext = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_context_second, "field 'mRegularTextViewContext'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        GuideSecondFragment guideSecondFragment = this.target;
        if (guideSecondFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        guideSecondFragment.mRegularTextViewTitle = null;
        guideSecondFragment.mRegularTextViewContext = null;
    }
}