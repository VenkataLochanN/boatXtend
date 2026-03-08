package com.ido.life.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class HomeFootViewHolder_ViewBinding implements Unbinder {
    private HomeFootViewHolder target;

    public HomeFootViewHolder_ViewBinding(HomeFootViewHolder homeFootViewHolder, View view) {
        this.target = homeFootViewHolder;
        homeFootViewHolder.mLayCard = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_edit_card, "field 'mLayCard'", LinearLayout.class);
        homeFootViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_edit_card, "field 'mTvTitle'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HomeFootViewHolder homeFootViewHolder = this.target;
        if (homeFootViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeFootViewHolder.mLayCard = null;
        homeFootViewHolder.mTvTitle = null;
    }
}