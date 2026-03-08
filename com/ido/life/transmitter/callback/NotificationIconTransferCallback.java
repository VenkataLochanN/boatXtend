package com.ido.life.transmitter.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.ido.ble.icon.transfer.IconTranConfig;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.life.bean.TranIconBean;
import com.ido.life.util.NoticeAppUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationIconTransferCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0002J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ido/life/transmitter/callback/NotificationIconTransferCallback;", "Lcom/ido/life/transmitter/callback/BaseIconTransferCallback;", "mOnFileTransferCallbackProxy", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "(Lcom/ido/life/transmitter/callback/OnFileTransferCallback;)V", "mIconPath", "", "generateNotificationIconFile", "type", "", "width", "height", "getRecoup", "", "onHandlePicFile", "config", "Lcom/ido/ble/icon/transfer/IconTranConfig;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NotificationIconTransferCallback extends BaseIconTransferCallback {
    public static final String ICON_DIR = "notifications";
    public static final String ICON_NAME = "temp";
    public static final float RECOUP = 8.0f;
    private String mIconPath;

    private final float getRecoup(int width) {
        return width > 60 ? 8.0f : 0.0f;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationIconTransferCallback(OnFileTransferCallback mOnFileTransferCallbackProxy) {
        super(mOnFileTransferCallbackProxy);
        Intrinsics.checkParameterIsNotNull(mOnFileTransferCallbackProxy, "mOnFileTransferCallbackProxy");
        StringBuilder sb = new StringBuilder();
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        sb.append(logPathImpl.getLogPath());
        sb.append("/");
        sb.append(ICON_DIR);
        sb.append("/");
        this.mIconPath = sb.toString();
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public String onHandlePicFile(IconTranConfig config, int width, int height) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        return generateNotificationIconFile(config.index, width, height);
    }

    private final String generateNotificationIconFile(int type, int width, int height) throws Throwable {
        File parentFile;
        Drawable icon;
        String strConvertType2Pkg = NoticeAppUtil.convertType2Pkg(type);
        CommonLogUtil.printAndSave("创建图标 type = " + type + ", pkg = " + strConvertType2Pkg + " ,width = " + width + ", height = " + height);
        try {
            TranIconBean noticeApp = NoticeAppUtil.getNoticeApp(type);
            StringBuilder sb = new StringBuilder();
            sb.append("创建图标 mTranIconBean = ");
            sb.append(noticeApp);
            sb.append(", width = ");
            sb.append((noticeApp == null || (icon = noticeApp.getIcon()) == null) ? null : Integer.valueOf(icon.getIntrinsicWidth()));
            CommonLogUtil.printAndSave(sb.toString());
            if (noticeApp != null && noticeApp.getIcon() != null) {
                Drawable icon2 = noticeApp.getIcon();
                Intrinsics.checkExpressionValueIsNotNull(icon2, "mTranIconBean.icon");
                Bitmap bitmapTransform2CycleBitmap = BitmapUtil.transform2CycleBitmap(BitmapUtil.drawableToBitmap(noticeApp.getIcon()), getRecoup(icon2.getIntrinsicWidth()));
                Intrinsics.checkExpressionValueIsNotNull(bitmapTransform2CycleBitmap, "BitmapUtil.transform2Cyc…cWidth)\n                )");
                this.mIconPath = this.mIconPath + type + '_' + strConvertType2Pkg;
                try {
                    File file = new File(this.mIconPath);
                    if (!file.exists() && (parentFile = file.getParentFile()) != null) {
                        parentFile.mkdirs();
                    }
                } catch (Exception unused) {
                    CommonLogUtil.printAndSave("图标：type = " + type + ", 目录创建失败");
                }
                CommonLogUtil.printAndSave("图标：type = " + type + ", pkg = " + strConvertType2Pkg + ",  大小：" + bitmapTransform2CycleBitmap.getByteCount() + "，iconPath = " + this.mIconPath);
                BitmapUtil.saveBitmap(BitmapUtil.zoomImg(bitmapTransform2CycleBitmap, width, height), this.mIconPath);
                return this.mIconPath;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.printAndSave("图片缓存失败");
        return "";
    }
}