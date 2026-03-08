package com.ido.life.dialog;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.DialRoundRelativelayout;

/* JADX INFO: loaded from: classes2.dex */
public class DialFunctionSetDialog_ViewBinding implements Unbinder {
    private DialFunctionSetDialog target;
    private View view7f0a01b7;
    private View view7f0a01b8;
    private View view7f0a01b9;

    public DialFunctionSetDialog_ViewBinding(final DialFunctionSetDialog dialFunctionSetDialog, View view) {
        this.target = dialFunctionSetDialog;
        dialFunctionSetDialog.mRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.function_recycler, "field 'mRecycleView'", RecyclerView.class);
        dialFunctionSetDialog.ivDialRl = (DialRoundRelativelayout) Utils.findRequiredViewAsType(view, R.id.iv_dial_rl, "field 'ivDialRl'", DialRoundRelativelayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.function_close, "field 'mFuncionClose' and method 'onViewClicked'");
        dialFunctionSetDialog.mFuncionClose = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.function_close, "field 'mFuncionClose'", RegularTextView.class);
        this.view7f0a01b8 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.DialFunctionSetDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialFunctionSetDialog.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.function_confirm, "method 'onViewClicked'");
        this.view7f0a01b9 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.DialFunctionSetDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialFunctionSetDialog.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.function_cancel, "method 'onViewClicked'");
        this.view7f0a01b7 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.DialFunctionSetDialog_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialFunctionSetDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialFunctionSetDialog dialFunctionSetDialog = this.target;
        if (dialFunctionSetDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialFunctionSetDialog.mRecycleView = null;
        dialFunctionSetDialog.ivDialRl = null;
        dialFunctionSetDialog.mFuncionClose = null;
        this.view7f0a01b8.setOnClickListener(null);
        this.view7f0a01b8 = null;
        this.view7f0a01b9.setOnClickListener(null);
        this.view7f0a01b9 = null;
        this.view7f0a01b7.setOnClickListener(null);
        this.view7f0a01b7 = null;
    }
}