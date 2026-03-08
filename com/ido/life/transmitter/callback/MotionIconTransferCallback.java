package com.ido.life.transmitter.callback;

import com.ido.ble.icon.transfer.IIconTransferListener;
import com.ido.ble.icon.transfer.IconTranConfig;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.BitmapUtil;
import com.ido.life.data.Constant;
import com.ido.life.data.cache.MotionTypeManager;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotionIconTransferCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u00012\u00020\u0002:\u0001\u0011B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\"\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¨\u0006\u0012"}, d2 = {"Lcom/ido/life/transmitter/callback/MotionIconTransferCallback;", "Lcom/ido/life/transmitter/callback/BaseIconTransferCallback;", "Lcom/ido/ble/icon/transfer/IIconTransferListener;", "mOnFileTransferCallbackProxy", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "(Lcom/ido/life/transmitter/callback/OnFileTransferCallback;)V", "onHandlePicFile", "", "config", "Lcom/ido/ble/icon/transfer/IconTranConfig;", "width", "", "height", "zoomImage", "", "srcPath", "desPath", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MotionIconTransferCallback extends BaseIconTransferCallback implements IIconTransferListener {
    public static final String PIC_SUFFIX = ".bmp";
    public static final String PIC_SUFFIX_TEMP = "temp.bmp";
    public static final String SIZE_BIG_DIR = "big";
    public static final String SIZE_MIDDLE_DIR = "middle";
    public static final String SIZE_SMALL_ANIM_DIR = "small_anim";
    public static final String SIZE_SMALL_DIR = "small";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MotionIconTransferCallback(OnFileTransferCallback mOnFileTransferCallbackProxy) {
        super(mOnFileTransferCallbackProxy);
        Intrinsics.checkParameterIsNotNull(mOnFileTransferCallbackProxy, "mOnFileTransferCallbackProxy");
    }

    @Override // com.ido.ble.icon.transfer.IIconTransferListener
    public String onHandlePicFile(IconTranConfig config, int width, int height) {
        if (config == null) {
            return "";
        }
        CommonLogUtil.printAndSave("加载图标类型： " + config.type + ",运动类型：" + config.index + ", width = " + width + ", height = " + height);
        int i = config.type;
        String str = i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : SIZE_MIDDLE_DIR : SIZE_SMALL_ANIM_DIR : SIZE_BIG_DIR : SIZE_SMALL_DIR;
        if (str == null) {
            return "";
        }
        return (MotionTypeManager.INSTANCE.getResourceDir().getAbsolutePath() + File.separator + str + File.separator + Constant.PREFIX_MOTION_RESOURCE) + config.index + ".bmp";
    }

    private final void zoomImage(String srcPath, String desPath, int width, int height) throws Throwable {
        BitmapUtil.saveBitmap(BitmapUtil.zoomImg(BitmapUtil.readBitmapFromFileDescriptor(srcPath), width, height), desPath);
    }
}