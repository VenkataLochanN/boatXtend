package com.ido.life.util;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.module.sport.map.BaseMap;
import com.ido.life.module.sport.map.MapScreenShotCallback;
import com.ido.life.util.AsyncTaskUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ShareUtil {
    public static String path = LogPathImpl.getInstance().getPicPath() + "s_ido.png";
    boolean isShoting = false;
    volatile boolean isShotComplete = false;

    public void shotWithMapView(final BaseMap baseMap, final ViewGroup viewGroup, final boolean z, final View... viewArr) {
        if (this.isShoting) {
            return;
        }
        this.isShoting = true;
        FileUtil.deleteFile(path);
        this.isShotComplete = false;
        baseMap.onMapScreenShot(new MapScreenShotCallback() { // from class: com.ido.life.util.ShareUtil.1
            @Override // com.ido.life.module.sport.map.MapScreenShotCallback
            public void shotComplet(Bitmap bitmap) {
                final Bitmap mapAndViewScreenShot2 = BitmapUtil.getMapAndViewScreenShot2(bitmap, viewGroup, baseMap.getMapView(), z, viewArr);
                new AsyncTaskUtil(new AsyncTaskUtil.AsyncTaskCallBackAdapter() { // from class: com.ido.life.util.ShareUtil.1.1
                    @Override // com.ido.life.util.AsyncTaskUtil.AsyncTaskCallBackAdapter, com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                    public Object doInBackground(String... strArr) throws Throwable {
                        BitmapUtil.saveBitmap(mapAndViewScreenShot2, ShareUtil.path);
                        ShareUtil.this.isShotComplete = true;
                        ShareUtil.this.isShoting = false;
                        return null;
                    }
                }).execute("");
            }
        });
    }

    public static void getMapScreen(final BaseMap baseMap, final ViewGroup viewGroup, final String str) {
        baseMap.onMapScreenShot(new MapScreenShotCallback() { // from class: com.ido.life.util.ShareUtil.2
            @Override // com.ido.life.module.sport.map.MapScreenShotCallback
            public void shotComplet(Bitmap bitmap) {
                final Bitmap mapScreen = BitmapUtil.getMapScreen(bitmap, baseMap.getMapView(), viewGroup);
                new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.util.ShareUtil.2.1
                    @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                    public void onPostExecute(Object obj) {
                    }

                    @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                    public Object doInBackground(String... strArr) throws Throwable {
                        BitmapUtil.saveBitmap(mapScreen, str);
                        return null;
                    }
                }).execute(new String[0]);
            }
        });
    }

    public void shotLongScreen(ViewGroup viewGroup, View... viewArr) {
        if (this.isShoting) {
            return;
        }
        this.isShoting = true;
        FileUtil.deleteFile(path);
        this.isShotComplete = false;
        final Bitmap totleScreenShot = ScreenUtil.getTotleScreenShot(viewGroup, viewArr);
        new AsyncTaskUtil(new AsyncTaskUtil.AsyncTaskCallBackAdapter() { // from class: com.ido.life.util.ShareUtil.3
            @Override // com.ido.life.util.AsyncTaskUtil.AsyncTaskCallBackAdapter, com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) throws Throwable {
                BitmapUtil.saveBitmap(totleScreenShot, ShareUtil.path);
                ShareUtil shareUtil = ShareUtil.this;
                shareUtil.isShotComplete = true;
                shareUtil.isShoting = false;
                return null;
            }
        }).execute("");
    }

    public void shotLongScreen(ViewGroup viewGroup, int i, View... viewArr) {
        if (this.isShoting) {
            return;
        }
        this.isShoting = true;
        FileUtil.deleteFile(path);
        this.isShotComplete = false;
        final Bitmap totleScreenShot = ScreenUtil.getTotleScreenShot(viewGroup, i, viewArr);
        new AsyncTaskUtil(new AsyncTaskUtil.AsyncTaskCallBackAdapter() { // from class: com.ido.life.util.ShareUtil.4
            @Override // com.ido.life.util.AsyncTaskUtil.AsyncTaskCallBackAdapter, com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) throws Throwable {
                BitmapUtil.saveBitmap(totleScreenShot, ShareUtil.path);
                ShareUtil shareUtil = ShareUtil.this;
                shareUtil.isShotComplete = true;
                shareUtil.isShoting = false;
                return null;
            }
        }).execute("");
    }
}