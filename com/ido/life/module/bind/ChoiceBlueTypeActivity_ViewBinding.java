package com.ido.life.module.bind;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class ChoiceBlueTypeActivity_ViewBinding implements Unbinder {
    private ChoiceBlueTypeActivity target;
    private View view7f0a024f;
    private View view7f0a02ca;

    public ChoiceBlueTypeActivity_ViewBinding(ChoiceBlueTypeActivity choiceBlueTypeActivity) {
        this(choiceBlueTypeActivity, choiceBlueTypeActivity.getWindow().getDecorView());
    }

    public ChoiceBlueTypeActivity_ViewBinding(final ChoiceBlueTypeActivity choiceBlueTypeActivity, View view) {
        this.target = choiceBlueTypeActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_watch, "method 'onViewClicked'");
        this.view7f0a02ca = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.ChoiceBlueTypeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                choiceBlueTypeActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_bracelet, "method 'onViewClicked'");
        this.view7f0a024f = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.ChoiceBlueTypeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                choiceBlueTypeActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0a02ca.setOnClickListener(null);
        this.view7f0a02ca = null;
        this.view7f0a024f.setOnClickListener(null);
        this.view7f0a024f = null;
    }
}