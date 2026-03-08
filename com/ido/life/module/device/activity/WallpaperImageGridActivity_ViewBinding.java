package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperImageGridActivity_ViewBinding implements Unbinder {
    private WallpaperImageGridActivity target;
    private View view7f0a0087;
    private View view7f0a047a;

    public WallpaperImageGridActivity_ViewBinding(WallpaperImageGridActivity wallpaperImageGridActivity) {
        this(wallpaperImageGridActivity, wallpaperImageGridActivity.getWindow().getDecorView());
    }

    public WallpaperImageGridActivity_ViewBinding(final WallpaperImageGridActivity wallpaperImageGridActivity, View view) {
        this.target = wallpaperImageGridActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.btn_preview, "field 'mBtnPre' and method 'onClick'");
        wallpaperImageGridActivity.mBtnPre = (TextView) Utils.castView(viewFindRequiredView, R.id.btn_preview, "field 'mBtnPre'", TextView.class);
        this.view7f0a0087 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperImageGridActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperImageGridActivity.onClick(view2);
            }
        });
        wallpaperImageGridActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler, "field 'mRecyclerView'", RecyclerView.class);
        wallpaperImageGridActivity.mFooterBar = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.footer_bar, "field 'mFooterBar'", RelativeLayout.class);
        wallpaperImageGridActivity.mTvDir = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dir, "field 'mTvDir'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.ll_dir, "method 'onClick'");
        this.view7f0a047a = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperImageGridActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperImageGridActivity.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WallpaperImageGridActivity wallpaperImageGridActivity = this.target;
        if (wallpaperImageGridActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        wallpaperImageGridActivity.mBtnPre = null;
        wallpaperImageGridActivity.mRecyclerView = null;
        wallpaperImageGridActivity.mFooterBar = null;
        wallpaperImageGridActivity.mTvDir = null;
        this.view7f0a0087.setOnClickListener(null);
        this.view7f0a0087 = null;
        this.view7f0a047a.setOnClickListener(null);
        this.view7f0a047a = null;
    }
}