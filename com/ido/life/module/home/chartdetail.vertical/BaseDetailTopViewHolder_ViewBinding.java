package com.ido.life.module.home.chartdetail.vertical;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDetailTopViewHolder_ViewBinding implements Unbinder {
    private BaseDetailTopViewHolder target;

    public BaseDetailTopViewHolder_ViewBinding(BaseDetailTopViewHolder baseDetailTopViewHolder, View view) {
        this.target = baseDetailTopViewHolder;
        baseDetailTopViewHolder.mLayLoading = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_loading, "field 'mLayLoading'", LinearLayout.class);
        baseDetailTopViewHolder.mImgLoading = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_data_loading, "field 'mImgLoading'", ImageView.class);
        baseDetailTopViewHolder.mTvLoadingState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_data_loading_state, "field 'mTvLoadingState'", TextView.class);
        baseDetailTopViewHolder.mImgLoadFailed = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_data_load_failed, "field 'mImgLoadFailed'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BaseDetailTopViewHolder baseDetailTopViewHolder = this.target;
        if (baseDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        baseDetailTopViewHolder.mLayLoading = null;
        baseDetailTopViewHolder.mImgLoading = null;
        baseDetailTopViewHolder.mTvLoadingState = null;
        baseDetailTopViewHolder.mImgLoadFailed = null;
    }
}