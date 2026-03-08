package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.lzy.imagepicker.view.CropImageView;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperImageCropActivity_ViewBinding implements Unbinder {
    private WallpaperImageCropActivity target;
    private View view7f0a051f;
    private View view7f0a0529;

    public WallpaperImageCropActivity_ViewBinding(WallpaperImageCropActivity wallpaperImageCropActivity) {
        this(wallpaperImageCropActivity, wallpaperImageCropActivity.getWindow().getDecorView());
    }

    public WallpaperImageCropActivity_ViewBinding(final WallpaperImageCropActivity wallpaperImageCropActivity, View view) {
        this.target = wallpaperImageCropActivity;
        wallpaperImageCropActivity.mCropImageView = (CropImageView) Utils.findRequiredViewAsType(view, R.id.cv_crop_image, "field 'mCropImageView'", CropImageView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.mtv_cancel, "field 'mMtvCancel' and method 'onClick'");
        wallpaperImageCropActivity.mMtvCancel = (MediumTextView) Utils.castView(viewFindRequiredView, R.id.mtv_cancel, "field 'mMtvCancel'", MediumTextView.class);
        this.view7f0a051f = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperImageCropActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperImageCropActivity.onClick(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.mtv_finish, "field 'mMtvFinish' and method 'onClick'");
        wallpaperImageCropActivity.mMtvFinish = (MediumTextView) Utils.castView(viewFindRequiredView2, R.id.mtv_finish, "field 'mMtvFinish'", MediumTextView.class);
        this.view7f0a0529 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperImageCropActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperImageCropActivity.onClick(view2);
            }
        });
        wallpaperImageCropActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WallpaperImageCropActivity wallpaperImageCropActivity = this.target;
        if (wallpaperImageCropActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        wallpaperImageCropActivity.mCropImageView = null;
        wallpaperImageCropActivity.mMtvCancel = null;
        wallpaperImageCropActivity.mMtvFinish = null;
        wallpaperImageCropActivity.mCommLoadingView = null;
        this.view7f0a051f.setOnClickListener(null);
        this.view7f0a051f = null;
        this.view7f0a0529.setOnClickListener(null);
        this.view7f0a0529 = null;
    }
}