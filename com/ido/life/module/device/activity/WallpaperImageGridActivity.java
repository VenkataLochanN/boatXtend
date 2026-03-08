package com.ido.life.module.device.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseActivity;
import com.lzy.imagepicker.DataHolder;
import com.lzy.imagepicker.ImageDataSource;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.adapter.ImageFolderAdapter;
import com.lzy.imagepicker.adapter.ImageRecyclerAdapter;
import com.lzy.imagepicker.bean.ImageFolder;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.FolderPopUpWindow;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WallpaperImageGridActivity extends BaseActivity implements ImageDataSource.OnImagesLoadedListener, ImageRecyclerAdapter.OnImageItemClickListener, ImagePicker.OnImageSelectedListener, View.OnClickListener {
    public static final String EXTRAS_IMAGES = "IMAGES";
    public static final String EXTRAS_TAKE_PICKERS = "TAKE";
    public static final int REQUEST_PERMISSION_CAMERA = 2;
    public static final int REQUEST_PERMISSION_STORAGE = 1;
    private ImagePicker imagePicker;

    @BindView(R.id.btn_preview)
    TextView mBtnPre;
    private FolderPopUpWindow mFolderPopupWindow;

    @BindView(R.id.footer_bar)
    RelativeLayout mFooterBar;
    private ImageDataSource mImageDataSource;
    private ImageFolderAdapter mImageFolderAdapter;
    private ImageRecyclerAdapter mRecyclerAdapter;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_dir)
    TextView mTvDir;
    private boolean isOrigin = false;
    private List<ImageFolder> mImageFolders = new ArrayList();
    private boolean directPhoto = false;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_wallpaper_image_grid;
    }

    private void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), str);
    }

    @Override // com.ido.life.base.BaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.directPhoto = bundle.getBoolean("TAKE", false);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("TAKE", this.directPhoto);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        saveDialLog("enter...");
        this.imagePicker = ImagePicker.getInstance();
        this.imagePicker.clear();
        this.imagePicker.addOnImageSelectedListener(this);
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        this.directPhoto = intent.getBooleanExtra("TAKE", false);
        if (this.directPhoto) {
            if (!checkSelfPermission(PermissionUtil.getOnlyCameraPermission())) {
                requestPermissions(2, PermissionUtil.getOnlyCameraPermission());
            } else {
                this.imagePicker.takePicture(this, 1001);
            }
        }
        this.imagePicker.setSelectedImages((ArrayList) intent.getSerializableExtra("IMAGES"));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        setStatusBarColor(getColor(R.color.black));
        this.mTitleBar.setBarBackground(R.color.black);
        if (this.imagePicker.isMultiMode()) {
            this.mBtnPre.setVisibility(0);
        } else {
            this.mBtnPre.setVisibility(8);
        }
        this.mImageFolderAdapter = new ImageFolderAdapter(this, null);
        this.mRecyclerAdapter = new ImageRecyclerAdapter(this, null);
        this.mRecyclerAdapter.setOnImageItemClickListener(this);
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            this.mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, Utils.dp2px(this, 2.0f), false));
            this.mRecyclerView.setAdapter(this.mRecyclerAdapter);
        }
        onImageSelected(0, null, false);
        loadImages();
    }

    private void loadImages() {
        if (checkSelfPermission(PermissionUtil.getStoragePermission())) {
            saveDialLog("loadImages start");
            this.mImageDataSource = new ImageDataSource(this, null, this, true);
        } else {
            saveDialLog("loadImages requestPermissions");
            requestPermissions(1, PermissionUtil.getStoragePermission());
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            saveDialLog("onRequestPermissionsResult REQUEST_PERMISSION_STORAGE");
            if (iArr.length <= 0 || iArr[0] != 0) {
                return;
            }
            saveDialLog("onRequestPermissionsResult loadImages start");
            this.mImageDataSource = new ImageDataSource(this, null, this, true);
            return;
        }
        if (i == 2) {
            saveDialLog("onRequestPermissionsResult REQUEST_PERMISSION_CAMERA");
            if (iArr.length <= 0 || iArr[0] != 0) {
                return;
            }
            saveDialLog("onRequestPermissionsResult loadImages start takePicture");
            this.imagePicker.takePicture(this, 1001);
        }
    }

    @Override // android.view.View.OnClickListener
    @OnClick({R.id.ll_dir, R.id.btn_preview})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_preview) {
            Intent intent = new Intent(this, (Class<?>) ImagePreviewActivity.class);
            intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
            intent.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, this.imagePicker.getSelectedImages());
            intent.putExtra(ImagePreviewActivity.ISORIGIN, this.isOrigin);
            intent.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
            startActivityForResult(intent, 1003);
            return;
        }
        if (id != R.id.ll_dir) {
            return;
        }
        if (this.mImageFolders == null) {
            saveDialLog(" 您的手机没有图片");
            return;
        }
        createPopupFolderList();
        this.mImageFolderAdapter.refreshData(this.mImageFolders);
        if (this.mFolderPopupWindow.isShowing()) {
            this.mFolderPopupWindow.dismiss();
            return;
        }
        this.mFolderPopupWindow.showAtLocation(this.mFooterBar, 0, 0, 0);
        int selectIndex = this.mImageFolderAdapter.getSelectIndex();
        if (selectIndex != 0) {
            selectIndex--;
        }
        this.mFolderPopupWindow.setSelection(selectIndex);
    }

    private void createPopupFolderList() {
        this.mFolderPopupWindow = new FolderPopUpWindow(this, this.mImageFolderAdapter);
        this.mFolderPopupWindow.setOnItemClickListener(new FolderPopUpWindow.OnItemClickListener() { // from class: com.ido.life.module.device.activity.WallpaperImageGridActivity.1
            @Override // com.lzy.imagepicker.view.FolderPopUpWindow.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                WallpaperImageGridActivity.this.mImageFolderAdapter.setSelectIndex(i);
                WallpaperImageGridActivity.this.imagePicker.setCurrentImageFolderPosition(i);
                WallpaperImageGridActivity.this.mFolderPopupWindow.dismiss();
                ImageFolder imageFolder = (ImageFolder) adapterView.getAdapter().getItem(i);
                if (imageFolder != null) {
                    WallpaperImageGridActivity.this.mRecyclerAdapter.refreshData(imageFolder.images);
                    WallpaperImageGridActivity.this.mTvDir.setText(imageFolder.name);
                }
            }
        });
        this.mFolderPopupWindow.setMargin(this.mFooterBar.getHeight());
    }

    @Override // com.lzy.imagepicker.ImageDataSource.OnImagesLoadedListener
    public void onImagesLoaded(List<ImageFolder> list) {
        saveDialLog("onImagesLoaded：" + list);
        this.mImageFolders.clear();
        this.mImageFolders.addAll(list);
        this.imagePicker.setImageFolders(list);
        if (this.mImageFolders.size() == 0) {
            this.mRecyclerAdapter.refreshData(null);
        } else {
            CommonLogUtil.d("calculateDiff start");
            this.mRecyclerAdapter.refreshDiffData(list.get(this.imagePicker.getCurrentImageFolderPosition()).images);
            CommonLogUtil.d("calculateDiff end");
        }
        this.mImageFolderAdapter.refreshData(list);
    }

    @Override // com.lzy.imagepicker.adapter.ImageRecyclerAdapter.OnImageItemClickListener
    public void onImageItemClick(View view, ImageItem imageItem, int i) {
        if (this.imagePicker.isShowCamera()) {
            i--;
        }
        if (this.imagePicker.isMultiMode()) {
            Intent intent = new Intent(this, (Class<?>) ImagePreviewActivity.class);
            intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, i);
            DataHolder.getInstance().save(DataHolder.DH_CURRENT_IMAGE_FOLDER_ITEMS, this.imagePicker.getCurrentImageFolderItems());
            intent.putExtra(ImagePreviewActivity.ISORIGIN, this.isOrigin);
            startActivityForResult(intent, 1003);
            return;
        }
        this.imagePicker.clearSelectedImages();
        if (this.imagePicker.getCurrentImageFolderItems() == null || this.imagePicker.getCurrentImageFolderItems().size() <= i) {
            loadImages();
            return;
        }
        ImagePicker imagePicker = this.imagePicker;
        imagePicker.addSelectedImageItem(i, imagePicker.getCurrentImageFolderItems().get(i), true);
        if (this.imagePicker.isCrop()) {
            startActivityForResult(new Intent(this, (Class<?>) WallpaperImageCropActivity.class), 1002);
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.imagePicker.getSelectedImages());
        setResult(1004, intent2);
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v8, types: [int] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.lzy.imagepicker.adapter.ImageRecyclerAdapter] */
    /* JADX WARN: Type inference failed for: r7v11, types: [com.lzy.imagepicker.adapter.ImageRecyclerAdapter] */
    /* JADX WARN: Type inference failed for: r7v8, types: [com.lzy.imagepicker.adapter.ImageRecyclerAdapter] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.lzy.imagepicker.ImagePicker.OnImageSelectedListener
    public void onImageSelected(int i, ImageItem imageItem, boolean z) {
        if (this.imagePicker.getSelectImageCount() > 0) {
            this.mBtnPre.setEnabled(true);
            this.mBtnPre.setText(getResources().getString(R.string.ip_preview_count, Integer.valueOf(this.imagePicker.getSelectImageCount())));
            this.mBtnPre.setTextColor(ContextCompat.getColor(this, R.color.ip_text_primary_inverted));
        } else {
            this.mBtnPre.setEnabled(false);
            this.mBtnPre.setText(getResources().getString(R.string.ip_preview));
            this.mBtnPre.setTextColor(ContextCompat.getColor(this, R.color.ip_text_secondary_inverted));
        }
        for (?? IsShowCamera = this.imagePicker.isShowCamera(); IsShowCamera < this.mRecyclerAdapter.getItemCount(); IsShowCamera++) {
            if (this.mRecyclerAdapter.getItem(IsShowCamera).path != null && this.mRecyclerAdapter.getItem(IsShowCamera).path.equals(imageItem.path)) {
                this.mRecyclerAdapter.notifyItemChanged(IsShowCamera);
                return;
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null || intent.getExtras() == null) {
            if (i2 == -1 && i == 1001) {
                startCropImage();
                return;
            } else {
                if (this.directPhoto) {
                    finish();
                    return;
                }
                return;
            }
        }
        if (i2 == 1005) {
            this.isOrigin = intent.getBooleanExtra(ImagePreviewActivity.ISORIGIN, false);
            return;
        }
        if (intent.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) == null) {
            File takeImageFile = this.imagePicker.getTakeImageFile();
            if (takeImageFile != null && takeImageFile.exists() && takeImageFile.isFile() && takeImageFile.length() > 0) {
                startCropImage();
                return;
            } else {
                finish();
                return;
            }
        }
        setResult(1004, intent);
        finish();
    }

    private void startCropImage() {
        saveDialLog("startCropImage");
        ImagePicker.galleryAddPic(this, this.imagePicker.getTakeImageFile());
        String absolutePath = this.imagePicker.getTakeImageFile().getAbsolutePath();
        ImageItem imageItem = new ImageItem();
        imageItem.path = absolutePath;
        this.imagePicker.clearSelectedImages();
        this.imagePicker.addSelectedImageItem(0, imageItem, true);
        if (this.imagePicker.isCrop()) {
            startActivityForResult(new Intent(this, (Class<?>) WallpaperImageCropActivity.class), 1002);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.imagePicker.getSelectedImages());
        setResult(1004, intent);
        finish();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        saveDialLog("onDestroy, mImageDataSource = " + this.mImageDataSource);
        ImageDataSource imageDataSource = this.mImageDataSource;
        if (imageDataSource != null) {
            imageDataSource.destroy();
        }
        this.imagePicker.removeOnImageSelectedListener(this);
        super.onDestroy();
    }
}