package com.lzy.imagepicker;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.lzy.imagepicker.bean.ImageFolder;
import com.lzy.imagepicker.bean.ImageItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ImageDataSource implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER_ALL = 0;
    public static final int LOADER_CATEGORY = 1;
    private static final int MAX_EACH_COUNT = 200;
    private static final String TAG = ImageDataSource.class.getSimpleName();
    private final String[] IMAGE_PROJECTION;
    private FragmentActivity activity;
    private boolean destroyed;
    private boolean filterGif;
    private OnImagesLoadedListener loadedListener;
    private LoaderManager loaderManager;
    private Handler mHandler;
    private ArrayList<ImageFolder> mImgFolderList;
    private int mLoaderId;
    private boolean mLoaingIconInfo;

    public interface OnImagesLoadedListener {
        void onImagesLoaded(List<ImageFolder> list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDialLog(String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), str);
    }

    public ImageDataSource(FragmentActivity fragmentActivity, String str, OnImagesLoadedListener onImagesLoadedListener) {
        this.IMAGE_PROJECTION = new String[]{"_display_name", "_data", "_size", "width", "height", "mime_type", "date_added"};
        this.mImgFolderList = new ArrayList<>();
        this.mLoaingIconInfo = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.filterGif = false;
        this.destroyed = false;
        this.activity = fragmentActivity;
        this.loadedListener = onImagesLoadedListener;
        this.destroyed = false;
        this.loaderManager = LoaderManager.getInstance(fragmentActivity);
        saveDialLog("准备加载相册图片");
        if (str == null) {
            this.mLoaderId = 0;
            this.loaderManager.initLoader(this.mLoaderId, null, this);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("path", str);
            this.mLoaderId = 1;
            this.loaderManager.initLoader(this.mLoaderId, bundle, this);
        }
    }

    public ImageDataSource(FragmentActivity fragmentActivity, String str, OnImagesLoadedListener onImagesLoadedListener, boolean z) {
        this.IMAGE_PROJECTION = new String[]{"_display_name", "_data", "_size", "width", "height", "mime_type", "date_added"};
        this.mImgFolderList = new ArrayList<>();
        this.mLoaingIconInfo = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.filterGif = false;
        this.destroyed = false;
        this.activity = fragmentActivity;
        this.loadedListener = onImagesLoadedListener;
        this.filterGif = z;
        this.destroyed = false;
        this.loaderManager = LoaderManager.getInstance(fragmentActivity);
        saveDialLog("准备加载相册图片");
        if (str == null) {
            this.mLoaderId = 0;
            this.loaderManager.initLoader(this.mLoaderId, null, this);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("path", str);
            this.mLoaderId = 1;
            this.loaderManager.initLoader(this.mLoaderId, bundle, this);
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader;
        saveDialLog("创建图片加载器");
        if (i == 0) {
            cursorLoader = new CursorLoader(this.activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, null, null, this.IMAGE_PROJECTION[6] + " DESC");
        } else {
            cursorLoader = null;
        }
        if (i != 1) {
            return cursorLoader;
        }
        return new CursorLoader(this.activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, this.IMAGE_PROJECTION[1] + " like '%" + bundle.getString("path") + "%'", null, this.IMAGE_PROJECTION[6] + " DESC");
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<Cursor> loader, final Cursor cursor) {
        saveDialLog("图片加载完成，mLoaingIconInfo = " + this.mLoaingIconInfo);
        this.mImgFolderList.clear();
        if (cursor == null || cursor.isClosed() || cursor.getCount() == 0) {
            saveDialLog("图片加载完成，数据为空，mLoaingIconInfo = " + this.mLoaingIconInfo);
            if (this.mLoaingIconInfo) {
                this.mLoaingIconInfo = false;
                this.mHandler.postDelayed(new Runnable() { // from class: com.lzy.imagepicker.-$$Lambda$ImageDataSource$CtFSm-Xs3Cgio2Je5Nh-y1eOINI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onLoadFinished$0$ImageDataSource();
                    }
                }, 500L);
                return;
            } else {
                this.mImgFolderList.clear();
                ImagePicker.getInstance().setImageFolders(this.mImgFolderList);
                this.loadedListener.onImagesLoaded(this.mImgFolderList);
                return;
            }
        }
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mLoaingIconInfo) {
            this.mLoaingIconInfo = false;
            this.mHandler.postDelayed(new Runnable() { // from class: com.lzy.imagepicker.-$$Lambda$ImageDataSource$Zk8Atx52hv1XSdyUv061BItAMV8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onLoadFinished$1$ImageDataSource(cursor);
                }
            }, 500L);
        } else {
            this.mLoaingIconInfo = true;
            startReadImage(cursor);
        }
    }

    public /* synthetic */ void lambda$onLoadFinished$0$ImageDataSource() {
        this.mImgFolderList.clear();
        ImagePicker.getInstance().setImageFolders(this.mImgFolderList);
        this.loadedListener.onImagesLoaded(this.mImgFolderList);
    }

    public /* synthetic */ void lambda$onLoadFinished$1$ImageDataSource(Cursor cursor) {
        this.mLoaingIconInfo = true;
        startReadImage(cursor);
    }

    /* JADX INFO: renamed from: com.lzy.imagepicker.ImageDataSource$1, reason: invalid class name */
    class AnonymousClass1 extends Thread {
        final /* synthetic */ Cursor val$cursor;

        AnonymousClass1(Cursor cursor) {
            this.val$cursor = cursor;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String string;
            String string2;
            File file;
            super.run();
            try {
                if (this.val$cursor != null) {
                    try {
                        ImageDataSource.this.saveDialLog("开始解析图片");
                        ImageDataSource.this.saveDialLog("cursor = " + this.val$cursor.getClass().getCanonicalName());
                        ImageDataSource.this.saveDialLog("图片大小size=" + this.val$cursor.getCount() + ", mLoaingIconInfo = " + ImageDataSource.this.mLoaingIconInfo);
                        ImageDataSource.this.mImgFolderList.clear();
                        ArrayList arrayList = new ArrayList();
                        this.val$cursor.moveToFirst();
                        ArrayList<ImageItem> arrayList2 = new ArrayList<>();
                        while (!this.val$cursor.isClosed() && this.val$cursor.moveToNext()) {
                            if (!ImageDataSource.this.mLoaingIconInfo) {
                                ImageDataSource.this.close(this.val$cursor);
                            } else if (ImageDataSource.this.destroyed) {
                                ImageDataSource.this.saveDialLog("the loader " + ImageDataSource.this.mLoaderId + " destroyed!");
                                ImageDataSource.this.close(this.val$cursor);
                            } else {
                                try {
                                    string = this.val$cursor.getString(0);
                                    string2 = this.val$cursor.getString(1);
                                    file = new File(string2);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    ImageDataSource.this.saveDialLog("单张图片处理失败：" + e2);
                                    if (ImageDataSource.this.destroyed) {
                                        ImageDataSource.this.saveDialLog("has exception, the loader " + ImageDataSource.this.mLoaderId + " destroyed!");
                                        return;
                                    }
                                }
                                if (file.exists() && file.length() > 0 && (!ImageDataSource.this.filterGif || !string2.toLowerCase().endsWith(".gif"))) {
                                    long j = this.val$cursor.getLong(2);
                                    int i = this.val$cursor.getInt(3);
                                    int i2 = this.val$cursor.getInt(4);
                                    String string3 = this.val$cursor.getString(5);
                                    long j2 = this.val$cursor.getLong(6);
                                    ImageItem imageItem = new ImageItem();
                                    imageItem.name = string;
                                    imageItem.path = string2;
                                    imageItem.size = j;
                                    imageItem.width = i;
                                    imageItem.height = i2;
                                    imageItem.mimeType = string3;
                                    imageItem.addTime = j2;
                                    arrayList2.add(imageItem);
                                    File parentFile = new File(string2).getParentFile();
                                    ImageFolder imageFolder = new ImageFolder();
                                    if (parentFile != null) {
                                        imageFolder.name = parentFile.getName();
                                        imageFolder.path = parentFile.getAbsolutePath();
                                    }
                                    int iIndexOf = arrayList.indexOf(imageFolder);
                                    if (iIndexOf < 0) {
                                        ArrayList<ImageItem> arrayList3 = new ArrayList<>();
                                        arrayList3.add(imageItem);
                                        imageFolder.cover = imageItem;
                                        imageFolder.images = arrayList3;
                                        arrayList.add(imageFolder);
                                    } else {
                                        ((ImageFolder) arrayList.get(iIndexOf)).images.add(imageItem);
                                    }
                                }
                                ImageDataSource.this.saveDialLog("过滤无效图片文件：" + file.getAbsolutePath());
                            }
                            return;
                        }
                        ImageDataSource.this.saveDialLog("allImages = " + arrayList2.size());
                        if (arrayList2.size() > 0) {
                            ImageFolder imageFolder2 = new ImageFolder();
                            imageFolder2.name = ImageDataSource.this.activity.getResources().getString(R.string.ip_all_images);
                            imageFolder2.path = "/";
                            imageFolder2.cover = arrayList2.get(0);
                            imageFolder2.images = arrayList2;
                            arrayList.add(0, imageFolder2);
                        }
                        ImageDataSource.this.saveDialLog("folderList = " + arrayList.size() + ", mLoaingIconInfo = " + ImageDataSource.this.mLoaingIconInfo);
                        if (ImageDataSource.this.mLoaingIconInfo) {
                            ImageDataSource.this.mImgFolderList.clear();
                            if (arrayList.size() > 0) {
                                ImageDataSource.this.mImgFolderList.addAll(arrayList);
                            }
                            ImageDataSource.this.saveDialLog("mImgFolderList = " + ImageDataSource.this.mImgFolderList.size());
                            ImagePicker.getInstance().setImageFolders(ImageDataSource.this.mImgFolderList);
                            ImageDataSource.this.mHandler.post(new Runnable() { // from class: com.lzy.imagepicker.-$$Lambda$ImageDataSource$1$nnERvRZ5R-y23yYvT1G5nwKAxLg
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.lambda$run$0$ImageDataSource$1();
                                }
                            });
                        }
                    } catch (Exception e3) {
                        ImageDataSource.this.mImgFolderList.clear();
                        e3.printStackTrace();
                        ImageDataSource.this.saveDialLog("startReadImage error：" + e3);
                    }
                }
            } finally {
                ImageDataSource.this.close(this.val$cursor);
            }
        }

        public /* synthetic */ void lambda$run$0$ImageDataSource$1() {
            ImageDataSource.this.loadedListener.onImagesLoaded(ImageDataSource.this.mImgFolderList);
        }
    }

    private void startReadImage1(Cursor cursor) {
        new AnonymousClass1(cursor).start();
    }

    /* JADX INFO: renamed from: com.lzy.imagepicker.ImageDataSource$2, reason: invalid class name */
    class AnonymousClass2 extends Thread {
        final /* synthetic */ Cursor val$cursor;

        AnonymousClass2(Cursor cursor) {
            this.val$cursor = cursor;
        }

        /* JADX WARN: Removed duplicated region for block: B:104:0x0242 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:108:0x0218 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x027b  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 755
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lzy.imagepicker.ImageDataSource.AnonymousClass2.run():void");
        }

        public /* synthetic */ void lambda$run$0$ImageDataSource$2() {
            ImageDataSource.this.destroy();
        }
    }

    private void startReadImage(Cursor cursor) {
        new AnonymousClass2(cursor).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postImages(final List<ImageFolder> list, ArrayList<ImageItem> arrayList, int i) {
        saveDialLog("postImages, size = " + list.size());
        saveDialLog("allImages = " + arrayList.size());
        if (arrayList.size() > 0) {
            ImageFolder imageFolder = new ImageFolder();
            imageFolder.name = this.activity.getResources().getString(R.string.ip_all_images);
            imageFolder.path = "/";
            imageFolder.cover = arrayList.get(0);
            imageFolder.images = arrayList;
            if (i == 1) {
                saveDialLog("first page, add index 0");
                list.add(0, imageFolder);
            } else {
                list.set(0, imageFolder);
            }
        }
        saveDialLog("folderList = " + list.size() + ", mLoaingIconInfo = " + this.mLoaingIconInfo);
        if (this.mLoaingIconInfo) {
            saveDialLog("mImgFolderList = " + list.size());
            ImagePicker.getInstance().setImageFolders(list);
            Handler handler = this.mHandler;
            if (handler == null || this.loadedListener == null) {
                return;
            }
            handler.post(new Runnable() { // from class: com.lzy.imagepicker.-$$Lambda$ImageDataSource$0miw3zqr9kVm9sf85fic7rl42Ro
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$postImages$2$ImageDataSource(list);
                }
            });
        }
    }

    public /* synthetic */ void lambda$postImages$2$ImageDataSource(List list) {
        this.loadedListener.onImagesLoaded(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void close(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<Cursor> loader) {
        saveDialLog("onLoaderReset");
    }

    public void destroy() {
        saveDialLog("destroy, loaderManager = " + this.loaderManager + ", mLoaderId = " + this.mLoaderId);
        LoaderManager loaderManager = this.loaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(this.mLoaderId);
            this.destroyed = true;
        }
    }
}