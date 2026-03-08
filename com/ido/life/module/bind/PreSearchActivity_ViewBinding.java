package com.ido.life.module.bind;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class PreSearchActivity_ViewBinding implements Unbinder {
    private PreSearchActivity target;
    private View view7f0a063f;
    private View view7f0a08d4;

    public PreSearchActivity_ViewBinding(PreSearchActivity preSearchActivity) {
        this(preSearchActivity, preSearchActivity.getWindow().getDecorView());
    }

    public PreSearchActivity_ViewBinding(final PreSearchActivity preSearchActivity, View view) {
        this.target = preSearchActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_not_bind, "method 'onViewClicked'");
        this.view7f0a08d4 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.PreSearchActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preSearchActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.rtv_btn_bottom, "method 'onViewClicked'");
        this.view7f0a063f = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.PreSearchActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preSearchActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0a08d4.setOnClickListener(null);
        this.view7f0a08d4 = null;
        this.view7f0a063f.setOnClickListener(null);
        this.view7f0a063f = null;
    }
}