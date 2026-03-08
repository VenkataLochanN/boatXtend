package com.ido.life.module.device.fragment;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketFragment_ViewBinding implements Unbinder {
    private DialMarketFragment target;

    public DialMarketFragment_ViewBinding(DialMarketFragment dialMarketFragment, View view) {
        this.target = dialMarketFragment;
        dialMarketFragment.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        dialMarketFragment.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialMarketFragment dialMarketFragment = this.target;
        if (dialMarketFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialMarketFragment.mRecyclerView = null;
        dialMarketFragment.mCommLoadingView = null;
    }
}