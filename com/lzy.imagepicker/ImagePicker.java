package com.lzy.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.core.content.FileProvider;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.lzy.imagepicker.bean.ImageFolder;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.util.ProviderUtil;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.CropImageView;
import com.realsil.sdk.dfu.DfuException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class ImagePicker {
    public static final String EXTRA_FROM_ITEMS = "extra_from_items";
    public static final String EXTRA_IMAGE_ITEMS = "extra_image_items";
    public static final String EXTRA_RESULT_ITEMS = "extra_result_items";
    public static final String EXTRA_SELECTED_IMAGE_POSITION = "selected_image_position";
    public static final int REQUEST_CODE_CROP = 1002;
    public static final int REQUEST_CODE_PREVIEW = 1003;
    public static final int REQUEST_CODE_TAKE = 1001;
    public static final int RESULT_CODE_BACK = 1005;
    public static final int RESULT_CODE_ITEMS = 1004;
    public static final String TAG = ImagePicker.class.getSimpleName();
    private static ImagePicker mInstance;
    public Bitmap cropBitmap;
    private File cropCacheFolder;
    private ImageLoader imageLoader;
    private List<ImageFolder> mImageFolders;
    private List<OnImageSelectedListener> mImageSelectedListeners;
    private File takeImageFile;
    private boolean multiMode = true;
    private int selectLimit = 9;
    private boolean crop = true;
    private boolean showCamera = true;
    private boolean isSaveRectangle = false;
    private int outPutX = GLMapStaticValue.ANIMATION_MOVE_TIME;
    private int outPutY = GLMapStaticValue.ANIMATION_MOVE_TIME;
    private int focusWidth = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
    private int focusHeight = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
    private CropImageView.Style style = CropImageView.Style.RECTANGLE;
    private ArrayList<ImageItem> mSelectedImages = new ArrayList<>();
    private int mCurrentImageFolderPosition = 0;

    public interface OnImageSelectedListener {
        void onImageSelected(int i, ImageItem imageItem, boolean z);
    }

    private ImagePicker() {
    }

    public static ImagePicker getInstance() {
        if (mInstance == null) {
            synchronized (ImagePicker.class) {
                if (mInstance == null) {
                    mInstance = new ImagePicker();
                }
            }
        }
        return mInstance;
    }

    public boolean isMultiMode() {
        return this.multiMode;
    }

    public void setMultiMode(boolean z) {
        this.multiMode = z;
    }

    public int getSelectLimit() {
        return this.selectLimit;
    }

    public void setSelectLimit(int i) {
        this.selectLimit = i;
    }

    public boolean isCrop() {
        return this.crop;
    }

    public void setCrop(boolean z) {
        this.crop = z;
    }

    public boolean isShowCamera() {
        return this.showCamera;
    }

    public void setShowCamera(boolean z) {
        this.showCamera = z;
    }

    public boolean isSaveRectangle() {
        return this.isSaveRectangle;
    }

    public void setSaveRectangle(boolean z) {
        this.isSaveRectangle = z;
    }

    public int getOutPutX() {
        return this.outPutX;
    }

    public void setOutPutX(int i) {
        this.outPutX = i;
    }

    public int getOutPutY() {
        return this.outPutY;
    }

    public void setOutPutY(int i) {
        this.outPutY = i;
    }

    public int getFocusWidth() {
        return this.focusWidth;
    }

    public void setFocusWidth(int i) {
        this.focusWidth = i;
    }

    public int getFocusHeight() {
        return this.focusHeight;
    }

    public void setFocusHeight(int i) {
        this.focusHeight = i;
    }

    public File getTakeImageFile() {
        return this.takeImageFile;
    }

    public File getCropCacheFolder(Context context) {
        if (this.cropCacheFolder == null) {
            this.cropCacheFolder = new File(context.getCacheDir() + "/ImagePicker/cropTemp/");
        }
        return this.cropCacheFolder;
    }

    public void setCropCacheFolder(File file) {
        this.cropCacheFolder = file;
    }

    public ImageLoader getImageLoader() {
        if (this.imageLoader == null) {
            this.imageLoader = new GlideImageLoader();
        }
        return this.imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public CropImageView.Style getStyle() {
        return this.style;
    }

    public void setStyle(CropImageView.Style style) {
        this.style = style;
    }

    public List<ImageFolder> getImageFolders() {
        return this.mImageFolders;
    }

    public void setImageFolders(List<ImageFolder> list) {
        this.mImageFolders = list;
    }

    public int getCurrentImageFolderPosition() {
        return this.mCurrentImageFolderPosition;
    }

    public void setCurrentImageFolderPosition(int i) {
        this.mCurrentImageFolderPosition = i;
    }

    public ArrayList<ImageItem> getCurrentImageFolderItems() {
        List<ImageFolder> list = this.mImageFolders;
        if (list == null || list.size() <= 0) {
            return null;
        }
        if (this.mCurrentImageFolderPosition >= this.mImageFolders.size()) {
            return this.mImageFolders.get(r0.size() - 1).images;
        }
        return this.mImageFolders.get(this.mCurrentImageFolderPosition).images;
    }

    public boolean isSelect(ImageItem imageItem) {
        return this.mSelectedImages.contains(imageItem);
    }

    public int getSelectImageCount() {
        ArrayList<ImageItem> arrayList = this.mSelectedImages;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public ArrayList<ImageItem> getSelectedImages() {
        return this.mSelectedImages;
    }

    public void clearSelectedImages() {
        ArrayList<ImageItem> arrayList = this.mSelectedImages;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void clear() {
        List<OnImageSelectedListener> list = this.mImageSelectedListeners;
        if (list != null) {
            list.clear();
            this.mImageSelectedListeners = null;
        }
        List<ImageFolder> list2 = this.mImageFolders;
        if (list2 != null) {
            list2.clear();
            this.mImageFolders = null;
        }
        ArrayList<ImageItem> arrayList = this.mSelectedImages;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.mCurrentImageFolderPosition = 0;
    }

    public void takePicture(Activity activity, int i) {
        Uri uriForFile;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(67108864);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            if (Utils.existSDCard()) {
                this.takeImageFile = new File(Environment.getExternalStorageDirectory(), "/DCIM/camera/");
            } else {
                this.takeImageFile = Environment.getDataDirectory();
            }
            this.takeImageFile = createFile(this.takeImageFile, "IMG_", ".jpg");
            if (this.takeImageFile != null) {
                if (Build.VERSION.SDK_INT <= 23) {
                    uriForFile = Uri.fromFile(this.takeImageFile);
                } else {
                    uriForFile = FileProvider.getUriForFile(activity, ProviderUtil.getFileProviderName(activity), this.takeImageFile);
                    Iterator<ResolveInfo> it = activity.getPackageManager().queryIntentActivities(intent, 65536).iterator();
                    while (it.hasNext()) {
                        activity.grantUriPermission(it.next().activityInfo.packageName, uriForFile, 3);
                    }
                }
                intent.putExtra("output", uriForFile);
            }
        }
        activity.startActivityForResult(intent, i);
    }

    public static File createFile(File file, String str, String str2) {
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return new File(file, str + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date(System.currentTimeMillis())) + str2);
    }

    public static void galleryAddPic(Context context, File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }

    public void addOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
        if (this.mImageSelectedListeners == null) {
            this.mImageSelectedListeners = new ArrayList();
        }
        this.mImageSelectedListeners.add(onImageSelectedListener);
    }

    public void removeOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
        List<OnImageSelectedListener> list = this.mImageSelectedListeners;
        if (list == null) {
            return;
        }
        list.remove(onImageSelectedListener);
    }

    public void addSelectedImageItem(int i, ImageItem imageItem, boolean z) {
        if (z) {
            this.mSelectedImages.add(imageItem);
        } else {
            this.mSelectedImages.remove(imageItem);
        }
        notifyImageSelectedChanged(i, imageItem, z);
    }

    public void setSelectedImages(ArrayList<ImageItem> arrayList) {
        if (arrayList == null) {
            return;
        }
        this.mSelectedImages = arrayList;
    }

    private void notifyImageSelectedChanged(int i, ImageItem imageItem, boolean z) {
        List<OnImageSelectedListener> list = this.mImageSelectedListeners;
        if (list == null) {
            return;
        }
        Iterator<OnImageSelectedListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onImageSelected(i, imageItem, z);
        }
    }

    public void restoreInstanceState(Bundle bundle) {
        this.cropCacheFolder = (File) bundle.getSerializable("cropCacheFolder");
        this.takeImageFile = (File) bundle.getSerializable("takeImageFile");
        this.imageLoader = (ImageLoader) bundle.getSerializable("imageLoader");
        this.style = (CropImageView.Style) bundle.getSerializable("style");
        this.multiMode = bundle.getBoolean("multiMode");
        this.crop = bundle.getBoolean("crop");
        this.showCamera = bundle.getBoolean("showCamera");
        this.isSaveRectangle = bundle.getBoolean("isSaveRectangle");
        this.selectLimit = bundle.getInt("selectLimit");
        this.outPutX = bundle.getInt("outPutX");
        this.outPutY = bundle.getInt("outPutY");
        this.focusWidth = bundle.getInt("focusWidth");
        this.focusHeight = bundle.getInt("focusHeight");
    }

    public void saveInstanceState(Bundle bundle) {
        bundle.putSerializable("cropCacheFolder", this.cropCacheFolder);
        bundle.putSerializable("takeImageFile", this.takeImageFile);
        bundle.putSerializable("imageLoader", this.imageLoader);
        bundle.putSerializable("style", this.style);
        bundle.putBoolean("multiMode", this.multiMode);
        bundle.putBoolean("crop", this.crop);
        bundle.putBoolean("showCamera", this.showCamera);
        bundle.putBoolean("isSaveRectangle", this.isSaveRectangle);
        bundle.putInt("selectLimit", this.selectLimit);
        bundle.putInt("outPutX", this.outPutX);
        bundle.putInt("outPutY", this.outPutY);
        bundle.putInt("focusWidth", this.focusWidth);
        bundle.putInt("focusHeight", this.focusHeight);
    }
}