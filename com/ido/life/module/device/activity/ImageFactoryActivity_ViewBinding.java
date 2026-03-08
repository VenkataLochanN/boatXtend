package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.clip.ClipImageLayout;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class ImageFactoryActivity_ViewBinding implements Unbinder {
    private ImageFactoryActivity target;
    private View view7f0a051f;
    private View view7f0a0529;

    public ImageFactoryActivity_ViewBinding(ImageFactoryActivity imageFactoryActivity) {
        this(imageFactoryActivity, imageFactoryActivity.getWindow().getDecorView());
    }

    public ImageFactoryActivity_ViewBinding(final ImageFactoryActivity imageFactoryActivity, View view) {
        this.target = imageFactoryActivity;
        imageFactoryActivity.mCropImageView = (ClipImageLayout) Utils.findRequiredViewAsType(view, R.id.cropImageView, "field 'mCropImageView'", ClipImageLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.mtv_cancel, "field 'mMtvCancel' and method 'onViewClicked'");
        imageFactoryActivity.mMtvCancel = (MediumTextView) Utils.castView(viewFindRequiredView, R.id.mtv_cancel, "field 'mMtvCancel'", MediumTextView.class);
        this.view7f0a051f = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.ImageFactoryActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                imageFactoryActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.mtv_finish, "field 'mMtvFinish' and method 'onViewClicked'");
        imageFactoryActivity.mMtvFinish = (MediumTextView) Utils.castView(viewFindRequiredView2, R.id.mtv_finish, "field 'mMtvFinish'", MediumTextView.class);
        this.view7f0a0529 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.ImageFactoryActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                imageFactoryActivity.onViewClicked(view2);
            }
        });
        imageFactoryActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ImageFactoryActivity imageFactoryActivity = this.target;
        if (imageFactoryActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        imageFactoryActivity.mCropImageView = null;
        imageFactoryActivity.mMtvCancel = null;
        imageFactoryActivity.mMtvFinish = null;
        imageFactoryActivity.mCommLoadingView = null;
        this.view7f0a051f.setOnClickListener(null);
        this.view7f0a051f = null;
        this.view7f0a0529.setOnClickListener(null);
        this.view7f0a0529 = null;
    }
}