package com.lzy.imagepicker.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class Utils {
    public static int getStatusHeight(Context context) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getImageItemWidth(Activity activity) {
        int i = activity.getResources().getDisplayMetrics().widthPixels;
        int i2 = i / activity.getResources().getDisplayMetrics().densityDpi;
        if (i2 < 3) {
            i2 = 3;
        }
        return (i - (((int) (activity.getResources().getDisplayMetrics().density * 2.0f)) * (i2 - 1))) / i2;
    }

    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static DisplayMetrics getScreenPix(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int dp2px(Context context, float f2) {
        return (int) TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    public static boolean hasVirtualNavigationBar(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.heightPixels;
            int i2 = displayMetrics.widthPixels;
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics2);
            int i3 = displayMetrics2.heightPixels;
            if (i2 - displayMetrics2.widthPixels > 0 || i - i3 > 0) {
                return true;
            }
        } else {
            if (Build.VERSION.SDK_INT < 14) {
                return true;
            }
            boolean zHasPermanentMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean zDeviceHasKey = KeyCharacterMap.deviceHasKey(4);
            if (!zHasPermanentMenuKey && !zDeviceHasKey) {
                return true;
            }
        }
        return false;
    }

    public static int getNavigationBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static Uri getImageContentUri(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
            Uri uriWithAppendedPath = Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + i);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return uriWithAppendedPath;
        }
        if (!new File(str).exists()) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("_data", str);
        try {
            try {
                Uri uriInsert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return uriInsert;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
}