package com.lzy.imagepicker.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.lzy.imagepicker.DataHolder;
import com.lzy.imagepicker.ImageDataSource;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.adapter.ImageFolderAdapter;
import com.lzy.imagepicker.adapter.ImageRecyclerAdapter;
import com.lzy.imagepicker.bean.ImageFolder;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.FolderPopUpWindow;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ImageGridActivity extends ImageBaseActivity implements ImageDataSource.OnImagesLoadedListener, ImageRecyclerAdapter.OnImageItemClickListener, ImagePicker.OnImageSelectedListener, View.OnClickListener {
    public static final String EXTRAS_IMAGES = "IMAGES";
    public static final String EXTRAS_TAKE_PICKERS = "TAKE";
    public static final int REQUEST_PERMISSION_CAMERA = 2;
    public static final int REQUEST_PERMISSION_STORAGE = 1;
    private ImagePicker imagePicker;
    private Button mBtnOk;
    private TextView mBtnPre;
    private FolderPopUpWindow mFolderPopupWindow;
    private View mFooterBar;
    private ImageDataSource mImageDataSource;
    private ImageFolderAdapter mImageFolderAdapter;
    private ImageRecyclerAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private View mllDir;
    private TextView mtvDir;
    private boolean isOrigin = false;
    private List<ImageFolder> mImageFolders = new ArrayList();
    private boolean directPhoto = false;

    private void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), str);
    }

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.directPhoto = bundle.getBoolean("TAKE", false);
    }

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("TAKE", this.directPhoto);
    }

    @Override // com.lzy.imagepicker.ui.ImageBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_grid);
        saveDialLog("enter...");
        this.imagePicker = ImagePicker.getInstance();
        this.imagePicker.clear();
        this.imagePicker.addOnImageSelectedListener(this);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            this.directPhoto = intent.getBooleanExtra("TAKE", false);
            if (this.directPhoto) {
                if (!checkPermission("android.permission.CAMERA")) {
                    ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 2);
                } else {
                    this.imagePicker.takePicture(this, 1001);
                }
            }
            this.imagePicker.setSelectedImages((ArrayList) intent.getSerializableExtra("IMAGES"));
        }
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        findViewById(R.id.btn_back).setOnClickListener(this);
        this.mBtnOk = (Button) findViewById(R.id.btn_ok);
        this.mBtnOk.setOnClickListener(this);
        this.mBtnPre = (TextView) findViewById(R.id.btn_preview);
        this.mBtnPre.setOnClickListener(this);
        this.mFooterBar = findViewById(R.id.footer_bar);
        this.mllDir = findViewById(R.id.ll_dir);
        this.mllDir.setOnClickListener(this);
        this.mtvDir = (TextView) findViewById(R.id.tv_dir);
        if (this.imagePicker.isMultiMode()) {
            this.mBtnOk.setVisibility(0);
            this.mBtnPre.setVisibility(0);
        } else {
            this.mBtnOk.setVisibility(8);
            this.mBtnPre.setVisibility(8);
        }
        this.mImageFolderAdapter = new ImageFolderAdapter(this, null);
        this.mRecyclerAdapter = new ImageRecyclerAdapter(this, null);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        this.mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, Utils.dp2px(this, 2.0f), false));
        this.mRecyclerAdapter.setOnImageItemClickListener(this);
        this.mRecyclerView.setAdapter(this.mRecyclerAdapter);
        onImageSelected(0, null, false);
        if (Build.VERSION.SDK_INT > 16) {
            if (checkPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
                new ImageDataSource(this, null, this);
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            }
        }
        new ImageDataSource(this, null, this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                new ImageDataSource(this, null, this);
                return;
            } else {
                saveDialLog("权限被禁止，无法选择本地图片...");
                showToast("权限被禁止，无法选择本地图片");
                return;
            }
        }
        if (i == 2) {
            if (iArr.length > 0 && iArr[0] == 0) {
                this.imagePicker.takePicture(this, 1001);
            } else {
                saveDialLog("权限被禁止，无法打开相机...");
                showToast("权限被禁止，无法打开相机");
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        saveDialLog("onDestroy, mImageDataSource = " + this.mImageDataSource);
        ImageDataSource imageDataSource = this.mImageDataSource;
        if (imageDataSource != null) {
            imageDataSource.destroy();
        }
        this.imagePicker.removeOnImageSelectedListener(this);
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            Intent intent = new Intent();
            intent.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.imagePicker.getSelectedImages());
            setResult(1004, intent);
            finish();
            return;
        }
        if (id == R.id.ll_dir) {
            if (this.mImageFolders == null) {
                saveDialLog("您的手机没有图片...");
                Log.i("ImageGridActivity", "您的手机没有图片");
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
            return;
        }
        if (id == R.id.btn_preview) {
            Intent intent2 = new Intent(this, (Class<?>) ImagePreviewActivity.class);
            intent2.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
            intent2.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, this.imagePicker.getSelectedImages());
            intent2.putExtra(ImagePreviewActivity.ISORIGIN, this.isOrigin);
            intent2.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
            startActivityForResult(intent2, 1003);
            return;
        }
        if (id == R.id.btn_back) {
            finish();
        }
    }

    private void createPopupFolderList() {
        this.mFolderPopupWindow = new FolderPopUpWindow(this, this.mImageFolderAdapter);
        this.mFolderPopupWindow.setOnItemClickListener(new FolderPopUpWindow.OnItemClickListener() { // from class: com.lzy.imagepicker.ui.ImageGridActivity.1
            @Override // com.lzy.imagepicker.view.FolderPopUpWindow.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ImageGridActivity.this.mImageFolderAdapter.setSelectIndex(i);
                ImageGridActivity.this.imagePicker.setCurrentImageFolderPosition(i);
                ImageGridActivity.this.mFolderPopupWindow.dismiss();
                ImageFolder imageFolder = (ImageFolder) adapterView.getAdapter().getItem(i);
                if (imageFolder != null) {
                    ImageGridActivity.this.mRecyclerAdapter.refreshData(imageFolder.images);
                    ImageGridActivity.this.mtvDir.setText(imageFolder.name);
                }
            }
        });
        this.mFolderPopupWindow.setMargin(this.mFooterBar.getHeight());
    }

    @Override // com.lzy.imagepicker.ImageDataSource.OnImagesLoadedListener
    public void onImagesLoaded(List<ImageFolder> list) {
        this.mImageFolders.clear();
        this.mImageFolders.addAll(list);
        this.imagePicker.setImageFolders(list);
        if (list.size() == 0) {
            this.mRecyclerAdapter.refreshData(null);
        } else {
            saveDialLog("calculateDiff start");
            this.mRecyclerAdapter.refreshDiffData(list.get(this.imagePicker.getCurrentImageFolderPosition()).images);
            saveDialLog("calculateDiff end");
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
        if (this.imagePicker.getCurrentImageFolderItems().size() > 0) {
            int iMin = Math.min(i, this.imagePicker.getCurrentImageFolderItems().size() - 1);
            ImagePicker imagePicker = this.imagePicker;
            imagePicker.addSelectedImageItem(iMin, imagePicker.getCurrentImageFolderItems().get(iMin), true);
        }
        if (this.imagePicker.isCrop()) {
            startActivityForResult(new Intent(this, (Class<?>) ImageCropActivity.class), 1002);
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.imagePicker.getSelectedImages());
        setResult(1004, intent2);
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11, types: [int] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.lzy.imagepicker.adapter.ImageRecyclerAdapter] */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.lzy.imagepicker.adapter.ImageRecyclerAdapter] */
    /* JADX WARN: Type inference failed for: r7v13, types: [com.lzy.imagepicker.adapter.ImageRecyclerAdapter] */
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
            this.mBtnOk.setText(getString(R.string.ip_select_complete, new Object[]{Integer.valueOf(this.imagePicker.getSelectImageCount()), Integer.valueOf(this.imagePicker.getSelectLimit())}));
            this.mBtnOk.setEnabled(true);
            this.mBtnPre.setEnabled(true);
            this.mBtnPre.setText(getResources().getString(R.string.ip_preview_count, Integer.valueOf(this.imagePicker.getSelectImageCount())));
            this.mBtnPre.setTextColor(ContextCompat.getColor(this, R.color.ip_text_primary_inverted));
            this.mBtnOk.setTextColor(ContextCompat.getColor(this, R.color.ip_text_primary_inverted));
        } else {
            this.mBtnOk.setText(getString(R.string.ip_complete));
            this.mBtnOk.setEnabled(false);
            this.mBtnPre.setEnabled(false);
            this.mBtnPre.setText(getResources().getString(R.string.ip_preview));
            this.mBtnPre.setTextColor(ContextCompat.getColor(this, R.color.ip_text_secondary_inverted));
            this.mBtnOk.setTextColor(ContextCompat.getColor(this, R.color.ip_text_secondary_inverted));
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
        ImagePicker.galleryAddPic(this, this.imagePicker.getTakeImageFile());
        String absolutePath = this.imagePicker.getTakeImageFile().getAbsolutePath();
        ImageItem imageItem = new ImageItem();
        imageItem.path = absolutePath;
        this.imagePicker.clearSelectedImages();
        this.imagePicker.addSelectedImageItem(0, imageItem, true);
        if (this.imagePicker.isCrop()) {
            startActivityForResult(new Intent(this, (Class<?>) ImageCropActivity.class), 1002);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ImagePicker.EXTRA_RESULT_ITEMS, this.imagePicker.getSelectedImages());
        setResult(1004, intent);
        finish();
    }
}