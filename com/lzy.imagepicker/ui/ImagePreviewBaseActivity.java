package com.lzy.imagepicker.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lzy.imagepicker.DataHolder;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.adapter.ImagePageAdapter;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.ViewPagerFixed;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ImagePreviewBaseActivity extends ImageBaseActivity {
    protected View content;
    protected ImagePicker imagePicker;
    protected ImagePageAdapter mAdapter;
    protected ArrayList<ImageItem> mImageItems;
    protected TextView mTitleCount;
    protected ViewPagerFixed mViewPager;
    protected ArrayList<ImageItem> selectedImages;
    protected View topBar;
    protected int mCurrentPosition = 0;
    protected boolean isFromItems = false;

    public abstract void onImageSingleTap();

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_preview);
        this.mCurrentPosition = getIntent().getIntExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
        this.isFromItems = getIntent().getBooleanExtra(ImagePicker.EXTRA_FROM_ITEMS, false);
        if (this.isFromItems) {
            this.mImageItems = (ArrayList) getIntent().getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
        } else {
            this.mImageItems = (ArrayList) DataHolder.getInstance().retrieve(DataHolder.DH_CURRENT_IMAGE_FOLDER_ITEMS);
        }
        this.imagePicker = ImagePicker.getInstance();
        this.selectedImages = this.imagePicker.getSelectedImages();
        this.content = findViewById(R.id.content);
        this.topBar = findViewById(R.id.top_bar);
        if (Build.VERSION.SDK_INT >= 19) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.topBar.getLayoutParams();
            layoutParams.topMargin = Utils.getStatusHeight(this);
            this.topBar.setLayoutParams(layoutParams);
        }
        this.topBar.findViewById(R.id.btn_ok).setVisibility(8);
        this.topBar.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewBaseActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImagePreviewBaseActivity.this.finish();
            }
        });
        this.mTitleCount = (TextView) findViewById(R.id.tv_des);
        this.mViewPager = (ViewPagerFixed) findViewById(R.id.viewpager);
        this.mAdapter = new ImagePageAdapter(this, this.mImageItems);
        this.mAdapter.setPhotoViewClickListener(new ImagePageAdapter.PhotoViewClickListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewBaseActivity.2
            @Override // com.lzy.imagepicker.adapter.ImagePageAdapter.PhotoViewClickListener
            public void OnPhotoTapListener(View view, float f2, float f3) {
                ImagePreviewBaseActivity.this.onImageSingleTap();
            }
        });
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setCurrentItem(this.mCurrentPosition, false);
        this.mTitleCount.setText(getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(this.mCurrentPosition + 1), Integer.valueOf(this.mImageItems.size())}));
    }

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        ImagePicker.getInstance().restoreInstanceState(bundle);
    }

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ImagePicker.getInstance().saveInstanceState(bundle);
    }
}