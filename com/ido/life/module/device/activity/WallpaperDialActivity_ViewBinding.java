package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.CircleImageView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.LoadingTextView;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperDialActivity_ViewBinding implements Unbinder {
    private WallpaperDialActivity target;
    private View view7f0a0258;
    private View view7f0a0276;
    private View view7f0a0634;
    private View view7f0a0637;
    private View view7f0a0695;
    private View view7f0a079d;

    public WallpaperDialActivity_ViewBinding(WallpaperDialActivity wallpaperDialActivity) {
        this(wallpaperDialActivity, wallpaperDialActivity.getWindow().getDecorView());
    }

    public WallpaperDialActivity_ViewBinding(final WallpaperDialActivity wallpaperDialActivity, View view) {
        this.target = wallpaperDialActivity;
        wallpaperDialActivity.mIvLoading = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading, "field 'mIvLoading'", AppCompatImageView.class);
        wallpaperDialActivity.mIvWallpaperDial = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_wallpaper_dial, "field 'mIvWallpaperDial'", ImageView.class);
        wallpaperDialActivity.mMtvPhoto = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_photo, "field 'mMtvPhoto'", MediumTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall' and method 'onViewClicked'");
        wallpaperDialActivity.mRtvAddAndInstall = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall'", RegularTextView.class);
        this.view7f0a0634 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperDialActivity.onViewClicked(view2);
            }
        });
        wallpaperDialActivity.mMtvDescription = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_description, "field 'mMtvDescription'", MediumTextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.rtv_album, "field 'mRtvAlbum' and method 'onViewClicked'");
        wallpaperDialActivity.mRtvAlbum = (TextView) Utils.castView(viewFindRequiredView2, R.id.rtv_album, "field 'mRtvAlbum'", TextView.class);
        this.view7f0a0637 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperDialActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.rtv_take_photo, "field 'mRtvTakePhoto' and method 'onViewClicked'");
        wallpaperDialActivity.mRtvTakePhoto = (TextView) Utils.castView(viewFindRequiredView3, R.id.rtv_take_photo, "field 'mRtvTakePhoto'", TextView.class);
        this.view7f0a0695 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperDialActivity.onViewClicked(view2);
            }
        });
        wallpaperDialActivity.mIvWallpaperDialBg = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_wallpaper_dial_bg, "field 'mIvWallpaperDialBg'", AppCompatImageView.class);
        wallpaperDialActivity.mIvWallpaperDialPreview = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_wallpaper_dial_preview, "field 'mIvWallpaperDialPreview'", ImageView.class);
        wallpaperDialActivity.mLayoutPicContentPreview = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_pic_content_preview, "field 'mLayoutPicContentPreview'", RelativeLayout.class);
        wallpaperDialActivity.mIvWallpaperDialBgPreview = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_wallpaper_dial_bg_preview, "field 'mIvWallpaperDialBgPreview'", AppCompatImageView.class);
        wallpaperDialActivity.mIvDialTimePreview = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_time_preview, "field 'mIvDialTimePreview'", AppCompatImageView.class);
        wallpaperDialActivity.mIvDialFunctionPreview = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_function_preview, "field 'mIvDialFunctionPreview'", AppCompatImageView.class);
        wallpaperDialActivity.mIvDialCirclePreview = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_circle_preview, "field 'mIvDialCirclePreview'", CircleImageView.class);
        wallpaperDialActivity.llDialWidgetPreview = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_dial_widget_preview, "field 'llDialWidgetPreview'", LinearLayout.class);
        wallpaperDialActivity.mIvDialTime = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_time, "field 'mIvDialTime'", AppCompatImageView.class);
        wallpaperDialActivity.mIvDialFunction = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_function, "field 'mIvDialFunction'", AppCompatImageView.class);
        wallpaperDialActivity.mIvDialCircle = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial_circle, "field 'mIvDialCircle'", CircleImageView.class);
        wallpaperDialActivity.mLayoutPic = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.layout_pic, "field 'mLayoutPic'", FrameLayout.class);
        wallpaperDialActivity.mLayoutPicContent = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_pic_content, "field 'mLayoutPicContent'", RelativeLayout.class);
        wallpaperDialActivity.mLayoutAddAndInstall = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_add_and_install, "field 'mLayoutAddAndInstall'", LinearLayout.class);
        wallpaperDialActivity.mScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.scroll_view, "field 'mScrollView'", NestedScrollView.class);
        wallpaperDialActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        wallpaperDialActivity.mLayoutColorContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_color_container, "field 'mLayoutColorContainer'", LinearLayout.class);
        wallpaperDialActivity.mLayoutColorSelect = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_color_select, "field 'mLayoutColorSelect'", LinearLayout.class);
        wallpaperDialActivity.mLayoutFunctionColorContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_function_color_container, "field 'mLayoutFunctionColorContainer'", LinearLayout.class);
        wallpaperDialActivity.mLayoutFunctionColorSelect = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_function_color_select, "field 'mLayoutFunctionColorSelect'", LinearLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_install_dial, "field 'mItemInstallDial' and method 'onViewClicked'");
        wallpaperDialActivity.mItemInstallDial = (LoadingTextView) Utils.castView(viewFindRequiredView4, R.id.item_install_dial, "field 'mItemInstallDial'", LoadingTextView.class);
        this.view7f0a0276 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperDialActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.item_delete_dial, "field 'mItemDeleteDial' and method 'onViewClicked'");
        wallpaperDialActivity.mItemDeleteDial = (LoadingTextView) Utils.castView(viewFindRequiredView5, R.id.item_delete_dial, "field 'mItemDeleteDial'", LoadingTextView.class);
        this.view7f0a0258 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperDialActivity.onViewClicked(view2);
            }
        });
        wallpaperDialActivity.mDividerInstall = Utils.findRequiredView(view, R.id.divider_install, "field 'mDividerInstall'");
        wallpaperDialActivity.mDividerDelete = Utils.findRequiredView(view, R.id.divider_delete, "field 'mDividerDelete'");
        wallpaperDialActivity.mDividerBottom = Utils.findRequiredView(view, R.id.divider_bottom, "field 'mDividerBottom'");
        wallpaperDialActivity.ll_dial_usage_history = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_dial_usage_history, "field 'll_dial_usage_history'", LinearLayout.class);
        wallpaperDialActivity.rv_dial_usage_history = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_dial_record, "field 'rv_dial_usage_history'", RecyclerView.class);
        wallpaperDialActivity.ll_time_location = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_time_location, "field 'll_time_location'", LinearLayout.class);
        wallpaperDialActivity.rv_time_location = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_time_location, "field 'rv_time_location'", RecyclerView.class);
        wallpaperDialActivity.ll_content = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_content, "field 'll_content'", LinearLayout.class);
        wallpaperDialActivity.ll_function = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_function, "field 'll_function'", LinearLayout.class);
        wallpaperDialActivity.tvDialSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dial_size, "field 'tvDialSize'", TextView.class);
        wallpaperDialActivity.tvFunctionName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_function_name, "field 'tvFunctionName'", TextView.class);
        wallpaperDialActivity.llDialWidget = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_dial_widget, "field 'llDialWidget'", LinearLayout.class);
        wallpaperDialActivity.tv_color_title = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_color_title, "field 'tv_color_title'", TextView.class);
        wallpaperDialActivity.vLineColor = Utils.findRequiredView(view, R.id.vLineColor, "field 'vLineColor'");
        wallpaperDialActivity.tvNoUsageHistory = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_no_usage_history, "field 'tvNoUsageHistory'", TextView.class);
        wallpaperDialActivity.llLoad = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_load_Ll, "field 'llLoad'", LinearLayout.class);
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.tvFunctionEdit, "method 'onViewClicked'");
        this.view7f0a079d = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WallpaperDialActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                wallpaperDialActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WallpaperDialActivity wallpaperDialActivity = this.target;
        if (wallpaperDialActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        wallpaperDialActivity.mIvLoading = null;
        wallpaperDialActivity.mIvWallpaperDial = null;
        wallpaperDialActivity.mMtvPhoto = null;
        wallpaperDialActivity.mRtvAddAndInstall = null;
        wallpaperDialActivity.mMtvDescription = null;
        wallpaperDialActivity.mRtvAlbum = null;
        wallpaperDialActivity.mRtvTakePhoto = null;
        wallpaperDialActivity.mIvWallpaperDialBg = null;
        wallpaperDialActivity.mIvWallpaperDialPreview = null;
        wallpaperDialActivity.mLayoutPicContentPreview = null;
        wallpaperDialActivity.mIvWallpaperDialBgPreview = null;
        wallpaperDialActivity.mIvDialTimePreview = null;
        wallpaperDialActivity.mIvDialFunctionPreview = null;
        wallpaperDialActivity.mIvDialCirclePreview = null;
        wallpaperDialActivity.llDialWidgetPreview = null;
        wallpaperDialActivity.mIvDialTime = null;
        wallpaperDialActivity.mIvDialFunction = null;
        wallpaperDialActivity.mIvDialCircle = null;
        wallpaperDialActivity.mLayoutPic = null;
        wallpaperDialActivity.mLayoutPicContent = null;
        wallpaperDialActivity.mLayoutAddAndInstall = null;
        wallpaperDialActivity.mScrollView = null;
        wallpaperDialActivity.mCommLoadingView = null;
        wallpaperDialActivity.mLayoutColorContainer = null;
        wallpaperDialActivity.mLayoutColorSelect = null;
        wallpaperDialActivity.mLayoutFunctionColorContainer = null;
        wallpaperDialActivity.mLayoutFunctionColorSelect = null;
        wallpaperDialActivity.mItemInstallDial = null;
        wallpaperDialActivity.mItemDeleteDial = null;
        wallpaperDialActivity.mDividerInstall = null;
        wallpaperDialActivity.mDividerDelete = null;
        wallpaperDialActivity.mDividerBottom = null;
        wallpaperDialActivity.ll_dial_usage_history = null;
        wallpaperDialActivity.rv_dial_usage_history = null;
        wallpaperDialActivity.ll_time_location = null;
        wallpaperDialActivity.rv_time_location = null;
        wallpaperDialActivity.ll_content = null;
        wallpaperDialActivity.ll_function = null;
        wallpaperDialActivity.tvDialSize = null;
        wallpaperDialActivity.tvFunctionName = null;
        wallpaperDialActivity.llDialWidget = null;
        wallpaperDialActivity.tv_color_title = null;
        wallpaperDialActivity.vLineColor = null;
        wallpaperDialActivity.tvNoUsageHistory = null;
        wallpaperDialActivity.llLoad = null;
        this.view7f0a0634.setOnClickListener(null);
        this.view7f0a0634 = null;
        this.view7f0a0637.setOnClickListener(null);
        this.view7f0a0637 = null;
        this.view7f0a0695.setOnClickListener(null);
        this.view7f0a0695 = null;
        this.view7f0a0276.setOnClickListener(null);
        this.view7f0a0276 = null;
        this.view7f0a0258.setOnClickListener(null);
        this.view7f0a0258 = null;
        this.view7f0a079d.setOnClickListener(null);
        this.view7f0a079d = null;
    }
}