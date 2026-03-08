package com.ido.life.module.sport.explain;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class SportExplainActivity_ViewBinding implements Unbinder {
    private SportExplainActivity target;
    private View view7f0a03cd;
    private View view7f0a03ce;
    private View view7f0a03cf;

    public SportExplainActivity_ViewBinding(SportExplainActivity sportExplainActivity) {
        this(sportExplainActivity, sportExplainActivity.getWindow().getDecorView());
    }

    public SportExplainActivity_ViewBinding(final SportExplainActivity sportExplainActivity, View view) {
        this.target = sportExplainActivity;
        sportExplainActivity.mTvExplainContent = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_explain_content, "field 'mTvExplainContent'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.lay_sport_type_explain, "method 'onClick'");
        this.view7f0a03cf = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.explain.SportExplainActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportExplainActivity.onClick(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.lay_sport_name_explain, "method 'onClick'");
        this.view7f0a03cd = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.explain.SportExplainActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportExplainActivity.onClick(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.lay_sport_train_explain, "method 'onClick'");
        this.view7f0a03ce = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.explain.SportExplainActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportExplainActivity.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportExplainActivity sportExplainActivity = this.target;
        if (sportExplainActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportExplainActivity.mTvExplainContent = null;
        this.view7f0a03cf.setOnClickListener(null);
        this.view7f0a03cf = null;
        this.view7f0a03cd.setOnClickListener(null);
        this.view7f0a03cd = null;
        this.view7f0a03ce.setOnClickListener(null);
        this.view7f0a03ce = null;
    }
}