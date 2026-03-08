package com.ido.life.dialog;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportShareDialogFragment_ViewBinding implements Unbinder {
    private SportShareDialogFragment target;
    private View view7f0a07f9;

    public SportShareDialogFragment_ViewBinding(final SportShareDialogFragment sportShareDialogFragment, View view) {
        this.target = sportShareDialogFragment;
        sportShareDialogFragment.mRecyclerShare = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_share, "field 'mRecyclerShare'", RecyclerView.class);
        sportShareDialogFragment.mLlShareLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_share_layout, "field 'mLlShareLayout'", LinearLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "method 'toCancel'");
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.SportShareDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportShareDialogFragment.toCancel(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportShareDialogFragment sportShareDialogFragment = this.target;
        if (sportShareDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportShareDialogFragment.mRecyclerShare = null;
        sportShareDialogFragment.mLlShareLayout = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
    }
}