package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.RadiusProgressBar;

/* JADX INFO: loaded from: classes3.dex */
public class OxyUptakeViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private OxyUptakeViewHolder target;

    public OxyUptakeViewHolder_ViewBinding(OxyUptakeViewHolder oxyUptakeViewHolder, View view) {
        super(oxyUptakeViewHolder, view);
        this.target = oxyUptakeViewHolder;
        oxyUptakeViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        oxyUptakeViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
        oxyUptakeViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        oxyUptakeViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        oxyUptakeViewHolder.mLayEmpty = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_empty, "field 'mLayEmpty'", LinearLayout.class);
        oxyUptakeViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        oxyUptakeViewHolder.mOxyUptakeProgress = (RadiusProgressBar) Utils.findRequiredViewAsType(view, R.id.oxy_uptake_progress, "field 'mOxyUptakeProgress'", RadiusProgressBar.class);
        oxyUptakeViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        oxyUptakeViewHolder.mTvOxygenUptake = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oxygen_uptake, "field 'mTvOxygenUptake'", TextView.class);
        oxyUptakeViewHolder.mTvOxygenUptakeUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_oxygen_uptake_unit, "field 'mTvOxygenUptakeUnit'", TextView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        OxyUptakeViewHolder oxyUptakeViewHolder = this.target;
        if (oxyUptakeViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        oxyUptakeViewHolder.mTvTitle = null;
        oxyUptakeViewHolder.mAnimView = null;
        oxyUptakeViewHolder.mTvDate = null;
        oxyUptakeViewHolder.mLayContent = null;
        oxyUptakeViewHolder.mLayEmpty = null;
        oxyUptakeViewHolder.mImgEmpty = null;
        oxyUptakeViewHolder.mOxyUptakeProgress = null;
        oxyUptakeViewHolder.mLayBottom = null;
        oxyUptakeViewHolder.mTvOxygenUptake = null;
        oxyUptakeViewHolder.mTvOxygenUptakeUnit = null;
        super.unbind();
    }
}