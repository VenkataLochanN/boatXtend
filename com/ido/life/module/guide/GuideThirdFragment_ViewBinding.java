package com.ido.life.module.guide;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class GuideThirdFragment_ViewBinding implements Unbinder {
    private GuideThirdFragment target;

    public GuideThirdFragment_ViewBinding(GuideThirdFragment guideThirdFragment, View view) {
        this.target = guideThirdFragment;
        guideThirdFragment.mRegularTextViewTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_title_first, "field 'mRegularTextViewTitle'", RegularTextView.class);
        guideThirdFragment.mRegularTextViewContext = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_context_second, "field 'mRegularTextViewContext'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        GuideThirdFragment guideThirdFragment = this.target;
        if (guideThirdFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        guideThirdFragment.mRegularTextViewTitle = null;
        guideThirdFragment.mRegularTextViewContext = null;
    }
}