package com.lzy.imagepicker.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.util.BitmapUtil;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.CropImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ImageCropActivity extends ImageBaseActivity implements View.OnClickListener, CropImageView.OnBitmapSaveCompleteListener {
    private ImagePicker imagePicker;
    private Bitmap mBitmap;
    private CropImageView mCropImageView;
    private ArrayList<ImageItem> mImageItems;
    private boolean mIsSaveRectangle;
    private int mOutputX;
    private int mOutputY;

    @Override // com.lzy.imagepicker.view.CropImageView.OnBitmapSaveCompleteListener
    public void onBitmapSaveError(File file) {
    }

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        ImageItem imageItem;
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_crop);
        this.imagePicker = ImagePicker.getInstance();
        findViewById(R.id.btn_back).setOnClickListener(this);
        Button button = (Button) findViewById(R.id.btn_ok);
        button.setText(getString(R.string.ip_complete));
        button.setOnClickListener(this);
        ((TextView) findViewById(R.id.tv_des)).setText(getString(R.string.ip_photo_crop));
        this.mCropImageView = (CropImageView) findViewById(R.id.cv_crop_image);
        this.mCropImageView.setOnBitmapSaveCompleteListener(this);
        this.mOutputX = this.imagePicker.getOutPutX();
        this.mOutputY = this.imagePicker.getOutPutY();
        this.mIsSaveRectangle = this.imagePicker.isSaveRectangle();
        this.mImageItems = this.imagePicker.getSelectedImages();
        ArrayList<ImageItem> arrayList = this.mImageItems;
        if (arrayList == null || arrayList.size() == 0) {
            finish();
            return;
        }
        int size = this.mImageItems.size();
        ImageItem imageItem2 = this.mImageItems.get(0);
        if (imageItem2 == null || TextUtils.isEmpty(imageItem2.path)) {
            imageItem = imageItem2;
            for (int i = 1; i < size; i++) {
                imageItem = this.mImageItems.get(i);
                if (imageItem != null && !TextUtils.isEmpty(imageItem.path)) {
                    break;
                }
            }
        } else {
            imageItem = imageItem2;
        }
        this.mCropImageView.setFocusStyle(this.imagePicker.getStyle());
        this.mCropImageView.setFocusWidth(this.imagePicker.getFocusWidth());
        this.mCropImageView.setFocusHeight(this.imagePicker.getFocusHeight());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (Build.VERSION.SDK_INT > 24) {
            Uri imageContentUri = Utils.getImageContentUri(this, imageItem.path);
            try {
                BitmapFactory.decodeStream(getContentResolver().openInputStream(imageContentUri), null, options);
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                options.inSampleSize = calculateInSampleSize(options, displayMetrics.widthPixels, displayMetrics.heightPixels);
                options.inJustDecodeBounds = false;
                this.mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageContentUri), null, options);
                this.mCropImageView.setImageBitmap(this.mCropImageView.rotate(this.mBitmap, BitmapUtil.getBitmapDegree(getContentResolver().openInputStream(imageContentUri))));
                return;
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                return;
            }
        }
        BitmapFactory.decodeFile(imageItem.path, options);
        DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
        options.inSampleSize = calculateInSampleSize(options, displayMetrics2.widthPixels, displayMetrics2.heightPixels);
        options.inJustDecodeBounds = false;
        this.mBitmap = BitmapFactory.decodeFile(imageItem.path, options);
        CropImageView cropImageView = this.mCropImageView;
        cropImageView.setImageBitmap(cropImageView.rotate(this.mBitmap, BitmapUtil.getBitmapDegree(imageItem.path)));
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i4 <= i2 && i3 <= i) {
            return 1;
        }
        if (i3 > i4) {
            return i3 / i;
        }
        return i4 / i2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            setResult(0);
            finish();
        } else if (id == R.id.btn_ok) {
            this.mCropImageView.saveBitmapToFile(this.imagePicker.getCropCacheFolder(this), this.mOutputX, this.mOutputY, this.mIsSaveRectangle);
        }
    }

    @Override // com.lzy.imagepicker.view.CropImageView.OnBitmapSaveCompleteListener
    public void onBitmapSaveSuccess(File file) {
        this.mImageItems.remove(0);
        ImageItem imageItem = new ImageItem();
        imageItem.path = file.getAbsolutePath();
        this.mImageItems.add(imageItem);
        Intent intent = new Intent();
        intent.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.mImageItems);
        setResult(1004, intent);
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mCropImageView.setOnBitmapSaveCompleteListener(null);
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.mBitmap.recycle();
        this.mBitmap = null;
    }
}