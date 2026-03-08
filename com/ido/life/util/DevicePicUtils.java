package com.ido.life.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.boat.Xtend.two.R;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ido.ble.LocalDataManager;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.ImageLoaderUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.CircleImageView;
import com.ido.life.customview.CustomRoundAngleImageView;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.util.eventbus.EventBusHelper;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class DevicePicUtils {
    public static void showDialDefault(boolean z, DeviceListEntity.DeviceInfo deviceInfo, RelativeLayout relativeLayout, ImageView imageView) {
        saveLog(z, "showDialDefault");
        if (deviceInfo == null || relativeLayout == null || imageView == null) {
            return;
        }
        try {
            if ("dialview".equals(relativeLayout.getChildAt(relativeLayout.getChildCount() - 1).getTag())) {
                relativeLayout.removeViewAt(relativeLayout.getChildCount() - 1);
            }
            if (!TextUtils.isEmpty(deviceInfo.getImageUrl()) && !TextUtils.equals(deviceInfo.getImageUrl(), "null")) {
                saveLog(z, "showDialDefault  url" + deviceInfo.getImageUrl());
                ImageLoaderUtil.loadImgFillet(imageView, deviceInfo.getImageUrl(), 0, getDefalutDeviceIcon(deviceInfo));
                return;
            }
            imageView.setImageResource(getDefalutDeviceIcon(deviceInfo));
        } catch (Exception e2) {
            saveLog(z, "showDialDefault  error" + e2.getMessage());
        }
    }

    private static int getDefalutDeviceIcon(DeviceListEntity.DeviceInfo deviceInfo) {
        return deviceInfo.type == 5 ? R.mipmap.icon_type_watch_circle_big : deviceInfo.type == 3 ? R.mipmap.icon_type_bracelet_big : R.mipmap.icon_type_watch_big;
    }

    public static void initDialPic(boolean z, DeviceListEntity.DeviceInfo deviceInfo, RelativeLayout relativeLayout, ImageView imageView, DialMarket.DialType.Dial dial, float f2, String str, String str2, boolean z2, Context context, float f3) {
        saveLog(z, "initDialPic");
        if ("dialview".equals(relativeLayout.getChildAt(relativeLayout.getChildCount() - 1).getTag())) {
            relativeLayout.removeViewAt(relativeLayout.getChildCount() - 1);
        }
        if (TextUtils.isEmpty(deviceInfo.getImageUrl3()) || deviceInfo.getFaceWidth() == 0.0f || deviceInfo.getFaceHeight() == 0.0f || deviceInfo.getFaceOffesetLeft() == 0.0f || deviceInfo.getFaceOffsetTop() == 0.0f) {
            saveLog(z, "mDeviceInfo.getImageUrl3 null || mDeviceInfo.getFaceWidth 0");
            showDialDefault(z, deviceInfo, relativeLayout, imageView);
            return;
        }
        if (dial == null || TextUtils.isEmpty(dial.getImageUrl())) {
            saveLog(z, "dial null || getimageurl null");
            showDialDefault(z, deviceInfo, relativeLayout, imageView);
        } else {
            if (TextUtils.equals(dial.getFaceType(), "SELF_CUSTOMIZE")) {
                saveLog(z, "自定义");
                String dialPicPath = getDialPicPath(str, str2);
                if (new File(dialPicPath).exists()) {
                    loadImageFromPath(z, deviceInfo, relativeLayout, imageView, f2, dialPicPath, z2, context, f3);
                    return;
                } else {
                    loadImageFromNetWork(z, deviceInfo, relativeLayout, imageView, dial, f2, z2, context, f3);
                    return;
                }
            }
            saveLog(z, "云表盘或者内置表盘");
            loadImageFromNetWork(z, deviceInfo, relativeLayout, imageView, dial, f2, z2, context, f3);
        }
    }

    public static void initDialPicWallPaper(boolean z, DeviceListEntity.DeviceInfo deviceInfo, RelativeLayout relativeLayout, ImageView imageView, float f2, String str, String str2, boolean z2, Context context, float f3) {
        saveLog(z, "initDialPicWallPaper");
        if ("dialview".equals(relativeLayout.getChildAt(relativeLayout.getChildCount() - 1).getTag())) {
            relativeLayout.removeViewAt(relativeLayout.getChildCount() - 1);
        }
        if (TextUtils.isEmpty(str2)) {
            saveLog(z, "currentDialName  null");
            showDialDefault(z, deviceInfo, relativeLayout, imageView);
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getImageUrl3()) || deviceInfo.getFaceWidth() == 0.0f || deviceInfo.getFaceHeight() == 0.0f || deviceInfo.getFaceOffesetLeft() == 0.0f || deviceInfo.getFaceOffsetTop() == 0.0f) {
            saveLog(z, "mDeviceInfo.getImageUrl3 null || mDeviceInfo.getFaceWidth 0");
            showDialDefault(z, deviceInfo, relativeLayout, imageView);
            return;
        }
        saveLog(z, "壁纸");
        String dialPicPath = getDialPicPath(str, str2);
        if (new File(dialPicPath).exists()) {
            loadImageFromPath(z, deviceInfo, relativeLayout, imageView, f2, dialPicPath, z2, context, f3);
        } else {
            showDialDefault(z, deviceInfo, relativeLayout, imageView);
        }
    }

    private static void loadImageFromNetWork(final boolean z, final DeviceListEntity.DeviceInfo deviceInfo, final RelativeLayout relativeLayout, final ImageView imageView, final DialMarket.DialType.Dial dial, final float f2, final boolean z2, final Context context, final float f3) {
        saveLog(z, "loadImageFromNetWork");
        if (!TextUtils.isEmpty(dial.getImageUrl())) {
            final int dimension = (int) context.getResources().getDimension(z2 ? R.dimen.sw_dp_4 : R.dimen.sw_dp_7);
            ImageLoaderUtil.loadImgFillet(imageView, deviceInfo.getImageUrl3(), dimension, new SimpleTarget<GlideDrawable>() { // from class: com.ido.life.util.DevicePicUtils.1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
                }

                public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    DevicePicUtils.saveLog(z, "onResourceReady loadImageFromNetWork");
                    imageView.setImageDrawable(glideDrawable);
                    DevicePicUtils.addDialViewUrl(z, deviceInfo, relativeLayout, imageView, f2, dial.getImageUrl(), z2, dimension, context, f3);
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Exception exc, Drawable drawable) {
                    super.onLoadFailed(exc, drawable);
                    DevicePicUtils.saveLog(z, "onLoadFailed");
                    DevicePicUtils.showDialDefault(z, deviceInfo, relativeLayout, imageView);
                }
            });
        } else {
            showDialDefault(z, deviceInfo, relativeLayout, imageView);
        }
    }

    private static void loadImageFromPath(final boolean z, final DeviceListEntity.DeviceInfo deviceInfo, final RelativeLayout relativeLayout, final ImageView imageView, final float f2, final String str, final boolean z2, final Context context, final float f3) {
        saveLog(z, "loadImageFromPath");
        ImageLoaderUtil.loadImgFillet(imageView, deviceInfo.getImageUrl3(), (int) context.getResources().getDimension(z2 ? R.dimen.sw_dp_4 : R.dimen.sw_dp_7), new SimpleTarget<GlideDrawable>() { // from class: com.ido.life.util.DevicePicUtils.2
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
            }

            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                DevicePicUtils.saveLog(z, "onResourceReady  loadImageFromPath");
                imageView.setImageDrawable(glideDrawable);
                DevicePicUtils.addDialViewPath(z, deviceInfo, relativeLayout, f2, str, z2, context, f3);
            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Exception exc, Drawable drawable) {
                super.onLoadFailed(exc, drawable);
                DevicePicUtils.saveLog(z, "onLoadFailed");
                DevicePicUtils.showDialDefault(z, deviceInfo, relativeLayout, imageView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addDialViewPath(boolean z, DeviceListEntity.DeviceInfo deviceInfo, RelativeLayout relativeLayout, float f2, String str, boolean z2, Context context, float f3) {
        saveLog(z, "addDialViewPath");
        saveLog(z, "addDialViewPath:" + deviceInfo.getDeviceName() + "---" + f3);
        float fDip2px = f2 / ((float) DipPixelUtil.dip2px(220.0f));
        if ("dialview".equals(relativeLayout.getChildAt(relativeLayout.getChildCount() - 1).getTag())) {
            relativeLayout.removeViewAt(relativeLayout.getChildCount() - 1);
        }
        int iDip2px = DipPixelUtil.dip2px((z2 ? 4.0f : 7.0f) * fDip2px);
        saveLog(z, "circle:" + isCircle());
        if (isCircle()) {
            CircleImageView circleImageView = new CircleImageView(context);
            circleImageView.setImageBitmap(BitmapFactory.decodeFile(str));
            circleImageView.setTag("dialview");
            circleImageView.setAlpha(f3);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DipPixelUtil.dip2px(deviceInfo.getFaceWidth() * fDip2px), DipPixelUtil.dip2px(deviceInfo.getFaceHeight() * fDip2px));
            layoutParams.leftMargin = DipPixelUtil.dip2px(deviceInfo.getFaceOffesetLeft() * fDip2px);
            layoutParams.topMargin = DipPixelUtil.dip2px(deviceInfo.getFaceOffsetTop() * fDip2px);
            circleImageView.setLayoutParams(layoutParams);
            relativeLayout.addView(circleImageView);
            return;
        }
        CustomRoundAngleImageView customRoundAngleImageView = new CustomRoundAngleImageView(context, iDip2px);
        customRoundAngleImageView.setImageBitmap(BitmapFactory.decodeFile(str));
        customRoundAngleImageView.setTag("dialview");
        customRoundAngleImageView.setAlpha(f3);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(DipPixelUtil.dip2px(deviceInfo.getFaceWidth() * fDip2px), DipPixelUtil.dip2px(deviceInfo.getFaceHeight() * fDip2px));
        layoutParams2.leftMargin = DipPixelUtil.dip2px(deviceInfo.getFaceOffesetLeft() * fDip2px);
        layoutParams2.topMargin = DipPixelUtil.dip2px(deviceInfo.getFaceOffsetTop() * fDip2px);
        customRoundAngleImageView.setLayoutParams(layoutParams2);
        relativeLayout.addView(customRoundAngleImageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addDialViewUrl(final boolean z, final DeviceListEntity.DeviceInfo deviceInfo, final RelativeLayout relativeLayout, final ImageView imageView, float f2, String str, boolean z2, int i, Context context, final float f3) {
        saveLog(z, "addDialViewUrl");
        final float fDip2px = f2 / DipPixelUtil.dip2px(220.0f);
        saveLog(z, "addDialViewUrl:  w" + f2 + "---" + fDip2px + "--" + deviceInfo.toString());
        final CustomRoundAngleImageView customRoundAngleImageView = new CustomRoundAngleImageView(context, DipPixelUtil.dip2px((z2 ? 4.0f : 7.0f) * fDip2px));
        ImageLoaderUtil.loadImgFillet(customRoundAngleImageView, str, i, new SimpleTarget<GlideDrawable>() { // from class: com.ido.life.util.DevicePicUtils.3
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
            }

            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                DevicePicUtils.saveLog(z, "addDialViewUrl-----onResourceReady  ");
                if ("dialview".equals(relativeLayout.getChildAt(r4.getChildCount() - 1).getTag())) {
                    relativeLayout.removeViewAt(r4.getChildCount() - 1);
                }
                customRoundAngleImageView.setImageDrawable(glideDrawable);
                customRoundAngleImageView.setTag("dialview");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DipPixelUtil.dip2px(deviceInfo.getFaceWidth() * fDip2px), DipPixelUtil.dip2px(deviceInfo.getFaceHeight() * fDip2px));
                layoutParams.leftMargin = DipPixelUtil.dip2px(deviceInfo.getFaceOffesetLeft() * fDip2px);
                layoutParams.topMargin = DipPixelUtil.dip2px(deviceInfo.getFaceOffsetTop() * fDip2px);
                customRoundAngleImageView.setLayoutParams(layoutParams);
                customRoundAngleImageView.setAlpha(f3);
                relativeLayout.addView(customRoundAngleImageView);
            }

            @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Exception exc, Drawable drawable) {
                super.onLoadFailed(exc, drawable);
                DevicePicUtils.saveLog(z, "addDialViewUrlonLoadFailed");
                DevicePicUtils.showDialDefault(z, deviceInfo, relativeLayout, imageView);
            }
        });
    }

    public static String spGetDial(String str) {
        String str2 = (String) SPUtils.get(getDailKey(str), "");
        saveLog("spGetDial  ：key:" + getDailKey(str) + "---value:" + str2);
        return !TextUtils.isEmpty(str2) ? str2.replace(".iwf", "") : str2;
    }

    public static void spSaveDial(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String strReplace = str2.replace(".iwf", "");
        String strSpGetDial = spGetDial(str);
        if (!TextUtils.isEmpty(strSpGetDial) && strSpGetDial.equals(strReplace)) {
            saveLog("spSaveDial  去掉重复----" + strReplace);
            return;
        }
        SPUtils.put(getDailKey(str), strReplace);
        saveLog("spSaveDial  ：key:" + getDailKey(str) + "---value:" + strReplace);
        if (z) {
            EventBusHelper.post(412);
        }
    }

    public static boolean isCircle() {
        return 1 == getDeviceShape();
    }

    public static int getDeviceShape() {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            return basicInfo.shape;
        }
        return 2;
    }

    public static String getDailKey(String str) {
        return Constants.CURRENT_DIAL_NAME + str;
    }

    public static DialMarket.DialType.Dial spGetDialInfo(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return new DialMarket.DialType.Dial();
        }
        String strReplace = str2.replace(".iwf", "");
        String strReplace2 = str.replace(":", "");
        String str3 = (String) SPUtils.get(Constants.CURRENT_DIAL_INFO + strReplace2 + strReplace, "");
        saveLog("spGetDialInfo  ：key:currentdialinfo" + strReplace2 + strReplace + "---value:" + str3);
        return (DialMarket.DialType.Dial) GsonUtil.fromJson(str3, DialMarket.DialType.Dial.class);
    }

    public static void spSaveDialInfo(String str, String str2, DialMarket.DialType.Dial dial) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        String strReplace = str.replace(":", "");
        String strReplace2 = str2.replace(".iwf", "");
        String json = dial != null ? GsonUtil.toJson(dial) : "";
        saveLog("spSAVEDialInfo  ：key:currentdialinfo" + strReplace + strReplace2 + "---value:" + json);
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.CURRENT_DIAL_INFO);
        sb.append(strReplace);
        sb.append(strReplace2);
        SPUtils.put(sb.toString(), json);
    }

    public static String dialNameTrans(String str) {
        return !TextUtils.isEmpty(str) ? str.replace(".iwf", "") : str;
    }

    public static String getDialPicPath(String str, String str2) {
        String dialPicPath = LogPathImpl.getInstance().getDialPicPath();
        File file = new File(dialPicPath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return dialPicPath + File.separator + (str.replace(":", "") + str2.replace(".iwf", "") + ".png");
    }

    public static boolean isExistDialPic(String str, String str2) {
        StringBuilder sb;
        try {
            String dialPicPath = LogPathImpl.getInstance().getDialPicPath();
            String str3 = str.replace(":", "") + str2.replace("iwf", "") + ".png";
            sb = new StringBuilder();
            sb.append(dialPicPath);
            sb.append(File.separator);
            sb.append(str3);
        } catch (Exception unused) {
        }
        return new File(sb.toString()).exists();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|48|3|46|4|(6:50|5|(1:7)(1:52)|44|10|55)|8|40|9|44|10|55|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void FileCopy(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L46
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L46
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L47
            java.lang.String r4 = getDialPicPath(r4, r5)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L47
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L47
        L13:
            int r4 = r2.read(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r5 = -1
            if (r4 == r5) goto L1f
            r5 = 0
            r3.write(r0, r5, r4)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            goto L13
        L1f:
            r3.close()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r2.close()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L32
            r3.flush()     // Catch: java.lang.Exception -> L2b
            r3.close()     // Catch: java.lang.Exception -> L2b
        L2b:
            r2.close()     // Catch: java.lang.Exception -> L52
            goto L52
        L2f:
            r4 = move-exception
            r1 = r3
            goto L38
        L32:
            r1 = r3
            goto L47
        L34:
            r4 = move-exception
            goto L38
        L36:
            r4 = move-exception
            r2 = r1
        L38:
            if (r1 == 0) goto L40
            r1.flush()     // Catch: java.lang.Exception -> L40
            r1.close()     // Catch: java.lang.Exception -> L40
        L40:
            if (r2 == 0) goto L45
            r2.close()     // Catch: java.lang.Exception -> L45
        L45:
            throw r4
        L46:
            r2 = r1
        L47:
            if (r1 == 0) goto L4f
            r1.flush()     // Catch: java.lang.Exception -> L4f
            r1.close()     // Catch: java.lang.Exception -> L4f
        L4f:
            if (r2 == 0) goto L52
            goto L2b
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.DevicePicUtils.FileCopy(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialPicLogPath(), "devicepicutils", str);
    }

    public static void saveLog(boolean z, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (z) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialPicLogPath(), "devicepicutils---[deviceinfo]", str);
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialPicLogPath(), "devicepicutils", str);
        }
    }
}