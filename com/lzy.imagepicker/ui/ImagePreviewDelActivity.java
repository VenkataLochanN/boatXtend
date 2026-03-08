package com.lzy.imagepicker.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.util.NavigationBarChangeListener;

/* JADX INFO: loaded from: classes3.dex */
public class ImagePreviewDelActivity extends ImagePreviewBaseActivity implements View.OnClickListener {
    @Override // com.lzy.imagepicker.ui.ImagePreviewBaseActivity, com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ImageView imageView = (ImageView) findViewById(R.id.btn_del);
        imageView.setOnClickListener(this);
        imageView.setVisibility(0);
        this.topBar.findViewById(R.id.btn_back).setOnClickListener(this);
        this.mTitleCount.setText(getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(this.mCurrentPosition + 1), Integer.valueOf(this.mImageItems.size())}));
        this.mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewDelActivity.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ImagePreviewDelActivity imagePreviewDelActivity = ImagePreviewDelActivity.this;
                imagePreviewDelActivity.mCurrentPosition = i;
                imagePreviewDelActivity.mTitleCount.setText(ImagePreviewDelActivity.this.getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(ImagePreviewDelActivity.this.mCurrentPosition + 1), Integer.valueOf(ImagePreviewDelActivity.this.mImageItems.size())}));
            }
        });
        NavigationBarChangeListener.with(this, 2).setListener(new NavigationBarChangeListener.OnSoftInputStateChangeListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewDelActivity.2
            @Override // com.lzy.imagepicker.util.NavigationBarChangeListener.OnSoftInputStateChangeListener
            public void onNavigationBarShow(int i, int i2) {
                ImagePreviewDelActivity.this.topBar.setPadding(0, 0, i2, 0);
            }

            @Override // com.lzy.imagepicker.util.NavigationBarChangeListener.OnSoftInputStateChangeListener
            public void onNavigationBarHide(int i) {
                ImagePreviewDelActivity.this.topBar.setPadding(0, 0, 0, 0);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_del) {
            showDeleteDialog();
        } else if (id == R.id.btn_back) {
            onBackPressed();
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("要删除这张照片吗？");
        builder.setNegativeButton("取消", (DialogInterface.OnClickListener) null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewDelActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ImagePreviewDelActivity.this.mImageItems.remove(ImagePreviewDelActivity.this.mCurrentPosition);
                if (ImagePreviewDelActivity.this.mImageItems.size() > 0) {
                    ImagePreviewDelActivity.this.mAdapter.setData(ImagePreviewDelActivity.this.mImageItems);
                    ImagePreviewDelActivity.this.mAdapter.notifyDataSetChanged();
                    ImagePreviewDelActivity.this.mTitleCount.setText(ImagePreviewDelActivity.this.getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(ImagePreviewDelActivity.this.mCurrentPosition + 1), Integer.valueOf(ImagePreviewDelActivity.this.mImageItems.size())}));
                    return;
                }
                ImagePreviewDelActivity.this.onBackPressed();
            }
        });
        builder.show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, this.mImageItems);
        setResult(1005, intent);
        finish();
        super.onBackPressed();
    }

    @Override // com.lzy.imagepicker.ui.ImagePreviewBaseActivity
    public void onImageSingleTap() {
        if (this.topBar.getVisibility() == 0) {
            this.topBar.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_out));
            this.topBar.setVisibility(8);
            this.tintManager.setStatusBarTintResource(0);
        } else {
            this.topBar.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_in));
            this.topBar.setVisibility(0);
            this.tintManager.setStatusBarTintResource(R.color.ip_color_primary_dark);
        }
    }
}