package com.lzy.imagepicker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.util.NavigationBarChangeListener;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.SuperCheckBox;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class ImagePreviewActivity extends ImagePreviewBaseActivity implements ImagePicker.OnImageSelectedListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static final String ISORIGIN = "isOrigin";
    private View bottomBar;
    private boolean isOrigin;
    private Button mBtnOk;
    private SuperCheckBox mCbCheck;
    private SuperCheckBox mCbOrigin;
    private View marginView;

    @Override // com.lzy.imagepicker.ui.ImagePreviewBaseActivity, com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.isOrigin = getIntent().getBooleanExtra(ISORIGIN, false);
        this.imagePicker.addOnImageSelectedListener(this);
        this.mBtnOk = (Button) findViewById(R.id.btn_ok);
        this.mBtnOk.setVisibility(0);
        this.mBtnOk.setOnClickListener(this);
        this.bottomBar = findViewById(R.id.bottom_bar);
        this.bottomBar.setVisibility(0);
        this.mCbCheck = (SuperCheckBox) findViewById(R.id.cb_check);
        this.mCbOrigin = (SuperCheckBox) findViewById(R.id.cb_origin);
        this.marginView = findViewById(R.id.margin_bottom);
        this.mCbOrigin.setText(getString(R.string.ip_origin));
        this.mCbOrigin.setOnCheckedChangeListener(this);
        this.mCbOrigin.setChecked(this.isOrigin);
        onImageSelected(0, null, false);
        boolean zIsSelect = this.imagePicker.isSelect(this.mImageItems.get(this.mCurrentPosition));
        this.mTitleCount.setText(getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(this.mCurrentPosition + 1), Integer.valueOf(this.mImageItems.size())}));
        this.mCbCheck.setChecked(zIsSelect);
        this.mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewActivity.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ImagePreviewActivity imagePreviewActivity = ImagePreviewActivity.this;
                imagePreviewActivity.mCurrentPosition = i;
                ImagePreviewActivity.this.mCbCheck.setChecked(ImagePreviewActivity.this.imagePicker.isSelect(imagePreviewActivity.mImageItems.get(ImagePreviewActivity.this.mCurrentPosition)));
                ImagePreviewActivity.this.mTitleCount.setText(ImagePreviewActivity.this.getString(R.string.ip_preview_image_count, new Object[]{Integer.valueOf(ImagePreviewActivity.this.mCurrentPosition + 1), Integer.valueOf(ImagePreviewActivity.this.mImageItems.size())}));
            }
        });
        this.mCbCheck.setOnClickListener(new View.OnClickListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImageItem imageItem = ImagePreviewActivity.this.mImageItems.get(ImagePreviewActivity.this.mCurrentPosition);
                int selectLimit = ImagePreviewActivity.this.imagePicker.getSelectLimit();
                if (!ImagePreviewActivity.this.mCbCheck.isChecked() || ImagePreviewActivity.this.selectedImages.size() < selectLimit) {
                    ImagePreviewActivity.this.imagePicker.addSelectedImageItem(ImagePreviewActivity.this.mCurrentPosition, imageItem, ImagePreviewActivity.this.mCbCheck.isChecked());
                    return;
                }
                ImagePreviewActivity imagePreviewActivity = ImagePreviewActivity.this;
                Toast.makeText(imagePreviewActivity, imagePreviewActivity.getString(R.string.ip_select_limit, new Object[]{Integer.valueOf(selectLimit)}), 0).show();
                ImagePreviewActivity.this.mCbCheck.setChecked(false);
            }
        });
        NavigationBarChangeListener.with(this).setListener(new NavigationBarChangeListener.OnSoftInputStateChangeListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewActivity.3
            @Override // com.lzy.imagepicker.util.NavigationBarChangeListener.OnSoftInputStateChangeListener
            public void onNavigationBarShow(int i, int i2) {
                ImagePreviewActivity.this.marginView.setVisibility(0);
                ViewGroup.LayoutParams layoutParams = ImagePreviewActivity.this.marginView.getLayoutParams();
                if (layoutParams.height == 0) {
                    layoutParams.height = Utils.getNavigationBarHeight(ImagePreviewActivity.this);
                    ImagePreviewActivity.this.marginView.requestLayout();
                }
            }

            @Override // com.lzy.imagepicker.util.NavigationBarChangeListener.OnSoftInputStateChangeListener
            public void onNavigationBarHide(int i) {
                ImagePreviewActivity.this.marginView.setVisibility(8);
            }
        });
        NavigationBarChangeListener.with(this, 2).setListener(new NavigationBarChangeListener.OnSoftInputStateChangeListener() { // from class: com.lzy.imagepicker.ui.ImagePreviewActivity.4
            @Override // com.lzy.imagepicker.util.NavigationBarChangeListener.OnSoftInputStateChangeListener
            public void onNavigationBarShow(int i, int i2) {
                ImagePreviewActivity.this.topBar.setPadding(0, 0, i2, 0);
                ImagePreviewActivity.this.bottomBar.setPadding(0, 0, i2, 0);
            }

            @Override // com.lzy.imagepicker.util.NavigationBarChangeListener.OnSoftInputStateChangeListener
            public void onNavigationBarHide(int i) {
                ImagePreviewActivity.this.topBar.setPadding(0, 0, 0, 0);
                ImagePreviewActivity.this.bottomBar.setPadding(0, 0, 0, 0);
            }
        });
    }

    @Override // com.lzy.imagepicker.ImagePicker.OnImageSelectedListener
    public void onImageSelected(int i, ImageItem imageItem, boolean z) {
        if (this.imagePicker.getSelectImageCount() > 0) {
            this.mBtnOk.setText(getString(R.string.ip_select_complete, new Object[]{Integer.valueOf(this.imagePicker.getSelectImageCount()), Integer.valueOf(this.imagePicker.getSelectLimit())}));
        } else {
            this.mBtnOk.setText(getString(R.string.ip_complete));
        }
        if (this.mCbOrigin.isChecked()) {
            long j = 0;
            Iterator<ImageItem> it = this.selectedImages.iterator();
            while (it.hasNext()) {
                j += it.next().size;
            }
            this.mCbOrigin.setText(getString(R.string.ip_origin_size, new Object[]{Formatter.formatFileSize(this, j)}));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            if (this.imagePicker.getSelectedImages().size() == 0) {
                this.mCbCheck.setChecked(true);
                this.imagePicker.addSelectedImageItem(this.mCurrentPosition, this.mImageItems.get(this.mCurrentPosition), this.mCbCheck.isChecked());
            }
            Intent intent = new Intent();
            intent.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.imagePicker.getSelectedImages());
            setResult(1004, intent);
            finish();
            return;
        }
        if (id == R.id.btn_back) {
            Intent intent2 = new Intent();
            intent2.putExtra(ISORIGIN, this.isOrigin);
            setResult(1005, intent2);
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(ISORIGIN, this.isOrigin);
        setResult(1005, intent);
        finish();
        super.onBackPressed();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.cb_origin) {
            if (z) {
                long j = 0;
                Iterator<ImageItem> it = this.selectedImages.iterator();
                while (it.hasNext()) {
                    j += it.next().size;
                }
                String fileSize = Formatter.formatFileSize(this, j);
                this.isOrigin = true;
                this.mCbOrigin.setText(getString(R.string.ip_origin_size, new Object[]{fileSize}));
                return;
            }
            this.isOrigin = false;
            this.mCbOrigin.setText(getString(R.string.ip_origin));
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.imagePicker.removeOnImageSelectedListener(this);
        super.onDestroy();
    }

    @Override // com.lzy.imagepicker.ui.ImagePreviewBaseActivity
    public void onImageSingleTap() {
        if (this.topBar.getVisibility() == 0) {
            this.topBar.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_out));
            this.bottomBar.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
            this.topBar.setVisibility(8);
            this.bottomBar.setVisibility(8);
            this.tintManager.setStatusBarTintResource(0);
            return;
        }
        this.topBar.setAnimation(AnimationUtils.loadAnimation(this, R.anim.top_in));
        this.bottomBar.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        this.topBar.setVisibility(0);
        this.bottomBar.setVisibility(0);
        this.tintManager.setStatusBarTintResource(R.color.ip_color_primary_dark);
    }
}