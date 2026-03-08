package com.ido.common.utils;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenUtil {
    private static final String TAG = "ScreenUtil";
    static int heightPixel;
    private static float screenDensity;
    private static int screenH;
    private static int screenW;

    public static void initScreen(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenW = displayMetrics.widthPixels;
        screenH = displayMetrics.heightPixels;
        screenDensity = displayMetrics.density;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics2);
        heightPixel = displayMetrics2.heightPixels;
    }

    public static int getScreenW() {
        return screenW;
    }

    public static int getScreenH() {
        return screenH;
    }

    public static int getRealScreenH() {
        return heightPixel;
    }

    public static float getScreenDensity() {
        return screenDensity;
    }

    public static void setNavigationBar(Activity activity, float f2) {
        setImmersiveStatusBar(activity);
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0);
        if (viewGroup.getChildAt(0) instanceof ViewGroup) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(0);
            viewGroup2.setFitsSystemWindows(true);
            viewGroup2.setClipToPadding(true);
            ViewGroup.LayoutParams layoutParams = viewGroup2.getLayoutParams();
            layoutParams.height = (int) (getStatusBarHeight(activity.getResources()) + f2);
            viewGroup2.setLayoutParams(layoutParams);
        }
    }

    public static void setNavigationBar(Activity activity, int i, float f2) {
        setImmersiveStatusBar(activity);
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0)).findViewById(i);
        viewGroup.setFitsSystemWindows(true);
        viewGroup.setClipToPadding(true);
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.height = (int) (getStatusBarHeight(activity.getResources()) + f2);
        viewGroup.setLayoutParams(layoutParams);
    }

    public static Bitmap getTotleScreenShot(ViewGroup viewGroup, View... viewArr) {
        int width = viewGroup.getWidth();
        int paddingBottom = viewGroup.getPaddingBottom() + viewGroup.getPaddingTop();
        CommonLogUtil.d(TAG, "getTotleScreenShot: " + width + AppInfo.DELIM + paddingBottom + AppInfo.DELIM + viewArr.length);
        int height = paddingBottom;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getChildAt(i).getLayoutParams();
            height += viewGroup.getChildAt(i).getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(1973790);
        for (View view : viewArr) {
            if (view.getVisibility() == 0) {
                canvas.drawBitmap(BitmapUtil.loadBitmapFromView(view), view.getLeft(), view.getTop(), (Paint) null);
            }
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getTotleScreenShot(ViewGroup viewGroup, int i, View... viewArr) {
        int width = viewGroup.getWidth();
        int paddingBottom = viewGroup.getPaddingBottom() + viewGroup.getPaddingTop();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getChildAt(i2).getLayoutParams();
            paddingBottom += viewGroup.getChildAt(i2).getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, paddingBottom, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(i);
        for (View view : viewArr) {
            view.setVisibility(0);
            Bitmap bitmapFromView = BitmapUtil.getBitmapFromView(view);
            if (bitmapFromView != null) {
                canvas.drawBitmap(bitmapFromView, view.getLeft(), view.getTop(), (Paint) null);
            }
        }
        return bitmapCreateBitmap;
    }

    public static void rootViewListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Resources resources) {
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setImmersiveStatusBar(Activity activity) {
        activity.getWindow().addFlags(67108864);
    }

    public static int getNavigationBarHeight(Activity activity) {
        if (!checkDeviceHasNavigationBar(activity)) {
            return 0;
        }
        Resources resources = activity.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static void setTranslucentStatus(Activity activity, boolean z) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "qemu.hw.mainkeys");
            if ("1".equals(str)) {
                return false;
            }
            if (AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.equals(str)) {
                return true;
            }
            return z;
        } catch (Exception unused) {
            return z;
        }
    }

    public static double getScreenPhysicalSize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return Math.sqrt(Math.pow(displayMetrics.widthPixels, 2.0d) + Math.pow(displayMetrics.heightPixels, 2.0d)) / ((double) (displayMetrics.density * 160.0f));
    }

    public static boolean isOver6Inch(Activity activity) {
        return getScreenPhysicalSize(activity) >= 6.0d;
    }

    public static boolean isLeEcoPhone() {
        return Build.BRAND.equals("LeEco");
    }

    public static boolean is2kScreen(Context context) {
        return getScreenWidth(context) == 1440 && getScreenHeight(context) == 2560;
    }

    public static Bitmap takeScreenShot(Activity activity) {
        Bitmap bitmapCreateBitmap;
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = drawingCache.getHeight();
        String str = Build.BRAND;
        if (width > 500 && height < 900) {
            height = 900;
            width = 500;
        }
        if (str.equals("Meizu")) {
            bitmapCreateBitmap = Bitmap.createBitmap(drawingCache, 0, 0, width, height);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(drawingCache, 0, 0, width, height);
        }
        decorView.destroyDrawingCache();
        return bitmapCreateBitmap;
    }

    public static Point getScreenMetrics(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static Point getScreenMetrics() {
        DisplayMetrics displayMetrics = IdoApp.getAppContext().getResources().getDisplayMetrics();
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static int getTextHeight(Paint paint, String str) {
        if (paint == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public static int getTextWidth(Paint paint, String str) {
        if (paint == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public static int dip2px(float f2) {
        return (int) ((f2 * getScreenDensity()) + 0.5f);
    }
}