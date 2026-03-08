package com.ido.life.module.device.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.clip.ClipImageLayout;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.device.presenter.WallpaperDialPresenter;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.CutPictureTool;
import com.ido.life.util.ImageUtil;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ImageFactoryActivity extends BaseActivity {
    public static final String IMG_PATH = "img_path";
    public static final String SCREEN_SIZE = "screen_size";
    private Handler handler = new Handler();

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.cropImageView)
    ClipImageLayout mCropImageView;

    @BindView(R.id.mtv_cancel)
    MediumTextView mMtvCancel;

    @BindView(R.id.mtv_finish)
    MediumTextView mMtvFinish;
    private Point mScreenSize;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_image_factory;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initData() throws Throwable {
        final Uri data = getIntent().getData();
        this.mScreenSize = (Point) getIntent().getParcelableExtra(SCREEN_SIZE);
        Point point = this.mScreenSize;
        if (point == null || point.x == 0 || this.mScreenSize.y == 0) {
            this.mScreenSize = new Point(GlMapUtil.DEVICE_DISPLAY_DPI_HIGH, SpatialRelationUtil.A_CIRCLE_DEGREE);
        }
        this.mCropImageView.setAspectRatio(this.mScreenSize.x, this.mScreenSize.y);
        if (data == null) {
            return;
        }
        String path = CutPictureTool.getPath(this, data);
        Bitmap bitmapFormUri = null;
        if (path != null && ((Build.MANUFACTURER.equalsIgnoreCase("xiaomi") && !path.endsWith(".png")) || Build.MANUFACTURER.equalsIgnoreCase("samsung"))) {
            if (path.endsWith(".png")) {
                bitmapFormUri = BitmapUtil.readBitmapFromFileDescriptor(path, this.mScreenSize.x, this.mScreenSize.y);
            } else {
                try {
                    bitmapFormUri = getBitmapFormUri(data);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (bitmapFormUri != null) {
                this.mCropImageView.mZoomImageView.setImageBitmap(ImageUtil.rotateBitmapByDegree(bitmapFormUri, ImageUtil.getBitmapDegree(path)));
                return;
            }
            return;
        }
        try {
            Bitmap bitmapFormUri2 = getBitmapFormUri(data);
            if (this.mCropImageView != null && this.mCropImageView.mZoomImageView != null) {
                if (bitmapFormUri2 != null) {
                    String stringExtra = getIntent().getStringExtra(IMG_PATH);
                    this.mCropImageView.mZoomImageView.setImageBitmap(ImageUtil.rotateBitmapByDegree(bitmapFormUri2, stringExtra != null ? ImageUtil.getBitmapDegree(stringExtra) : 0));
                    return;
                }
                Bitmap bitmapFromFileDescriptor = BitmapUtil.readBitmapFromFileDescriptor(path, this.mScreenSize.x, this.mScreenSize.y);
                if (bitmapFromFileDescriptor != null) {
                    this.mCropImageView.mZoomImageView.setImageBitmap(bitmapFromFileDescriptor);
                } else {
                    this.handler.postDelayed(new Runnable() { // from class: com.ido.life.module.device.activity.-$$Lambda$ImageFactoryActivity$mgTb9Ubv1EGVYhHitGPSsGbJ0To
                        @Override // java.lang.Runnable
                        public final void run() throws Throwable {
                            this.f$0.lambda$initData$0$ImageFactoryActivity(data);
                        }
                    }, 2000L);
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public /* synthetic */ void lambda$initData$0$ImageFactoryActivity(Uri uri) throws Throwable {
        Bitmap bitmapFormUri = getBitmapFormUri(uri);
        if (bitmapFormUri != null) {
            this.mCropImageView.mZoomImageView.setImageBitmap(bitmapFormUri);
        }
    }

    public Bitmap getBitmapFormUri(Uri uri) throws Throwable {
        try {
            Bitmap bitmapFromUri = ImageUtil.formatBitmapFromUri(uri, 800.0f, 480.0f);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapFromUri.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byteArrayOutputStream.close();
            CommonLogUtil.d("getBitmapFormUri():" + bitmapFromUri);
            return ImageUtil.compressBitmap(bitmapFromUri);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @OnClick({R.id.mtv_cancel, R.id.mtv_finish})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.mtv_cancel) {
            onBackPressed();
        } else {
            if (id != R.id.mtv_finish) {
                return;
            }
            createWallPaper();
        }
    }

    private void createWallPaper() {
        this.mCommLoadingView.setVisibility(0);
        new AsyncTaskUtil().setIAsyncTaskCallBack(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.device.activity.ImageFactoryActivity.1
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                ImageUtil.saveWallPaper(Bitmap.createScaledBitmap(ImageFactoryActivity.this.mCropImageView.clip(), ImageFactoryActivity.this.mScreenSize.x, ImageFactoryActivity.this.mScreenSize.y, true), new WallpaperDialPresenter().getTempWallpaperPath());
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                ImageFactoryActivity.this.mCommLoadingView.setVisibility(8);
                ImageFactoryActivity.this.setResult(9, new Intent());
                ImageFactoryActivity.this.finish();
            }
        }).execute("");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(8);
        finish();
    }
}