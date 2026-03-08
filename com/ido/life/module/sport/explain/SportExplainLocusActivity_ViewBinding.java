package com.ido.life.module.sport.explain;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportExplainLocusActivity_ViewBinding implements Unbinder {
    private SportExplainLocusActivity target;
    private View view7f0a0776;

    public SportExplainLocusActivity_ViewBinding(SportExplainLocusActivity sportExplainLocusActivity) {
        this(sportExplainLocusActivity, sportExplainLocusActivity.getWindow().getDecorView());
    }

    public SportExplainLocusActivity_ViewBinding(final SportExplainLocusActivity sportExplainLocusActivity, View view) {
        this.target = sportExplainLocusActivity;
        sportExplainLocusActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeft' and method 'toBack'");
        sportExplainLocusActivity.mTitleLeft = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeft'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.explain.SportExplainLocusActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportExplainLocusActivity.toBack(view2);
            }
        });
        sportExplainLocusActivity.mTitleRight = (Button) Utils.findRequiredViewAsType(view, R.id.title_rightBtn, "field 'mTitleRight'", Button.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportExplainLocusActivity sportExplainLocusActivity = this.target;
        if (sportExplainLocusActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportExplainLocusActivity.mTitleText = null;
        sportExplainLocusActivity.mTitleLeft = null;
        sportExplainLocusActivity.mTitleRight = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}