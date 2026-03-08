package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.ScaleAnimation;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.util.BitmapUtil;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.CropImageView;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperImageCropActivity extends BaseActivity implements View.OnClickListener, CropImageView.OnBitmapSaveCompleteListener {
    private ImagePicker imagePicker;
    private Bitmap mBitmap;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.cv_crop_image)
    CropImageView mCropImageView;
    private ArrayList<ImageItem> mImageItems;
    private boolean mIsSaveRectangle;

    @BindView(R.id.mtv_cancel)
    MediumTextView mMtvCancel;

    @BindView(R.id.mtv_finish)
    MediumTextView mMtvFinish;
    private int mOutputX;
    private int mOutputY;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_wallpaper_image_crop;
    }

    @Override // com.lzy.imagepicker.view.CropImageView.OnBitmapSaveCompleteListener
    public void onBitmapSaveError(File file) {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ImageItem imageItem;
        super.initData();
        this.imagePicker = ImagePicker.getInstance();
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
        if (imageItem == null) {
            return;
        }
        this.mCropImageView.setFocusStyle(this.imagePicker.getStyle());
        this.mCropImageView.setFocusWidth(this.imagePicker.getFocusWidth());
        this.mCropImageView.setFocusHeight(this.imagePicker.getFocusHeight());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (Build.VERSION.SDK_INT > 24) {
            try {
                if (!TextUtils.isEmpty(imageItem.path)) {
                    saveDialLog("选择壁纸图片路径：" + imageItem.path);
                }
                Uri imageContentUri = Utils.getImageContentUri(this, imageItem.path);
                BitmapFactory.decodeStream(getContentResolver().openInputStream(imageContentUri), null, options);
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                options.inSampleSize = calculateInSampleSize(options, displayMetrics.widthPixels, displayMetrics.heightPixels);
                options.inJustDecodeBounds = false;
                this.mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageContentUri), null, options);
                this.mCropImageView.setImageBitmap(this.mCropImageView.rotate(this.mBitmap, BitmapUtil.getBitmapDegree(getContentResolver().openInputStream(imageContentUri))));
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                saveDialLog("选择壁纸异常：" + e2.getMessage());
                finish();
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

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        setStatusBarColor(getColor(R.color.black));
        this.mMtvFinish.setText(getString(R.string.mine_complete));
        this.mCropImageView.setOnBitmapSaveCompleteListener(this);
        if (this.mOutputX < this.imagePicker.getFocusWidth()) {
            float focusWidth = (this.imagePicker.getFocusWidth() * 1.0f) / this.mOutputX;
            this.mCropImageView.startAnimation(new ScaleAnimation(1.0f, focusWidth, 1.0f, focusWidth, 0.5f, 0.5f));
        }
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
    @OnClick({R.id.mtv_cancel, R.id.mtv_finish})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.mtv_cancel) {
            setResult(0);
            finish();
        } else {
            if (id != R.id.mtv_finish) {
                return;
            }
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

    public void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), str);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CropImageView cropImageView = this.mCropImageView;
        if (cropImageView != null) {
            cropImageView.setOnBitmapSaveCompleteListener(null);
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.mBitmap.recycle();
        this.mBitmap = null;
    }
}