package com.ido.common.utils;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.ido.common.IdoApp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceUtil {
    public static String getString(Resources resources, int i) {
        return resources.getString(i);
    }

    public static String getString(Resources resources, int i, Object... objArr) {
        return resources.getString(i, objArr);
    }

    public static Resources getResources() {
        return IdoApp.getAppContext().getResources();
    }

    public static int getColor(Resources resources, int i, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 23) {
            return resources.getColor(i, theme);
        }
        return resources.getColor(i);
    }

    public static String getString(int i) {
        try {
            return getResources().getString(i);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return getResources().getString(getStringResId(str));
    }

    public static String getEntryName(int i) {
        try {
            return getResources().getResourceEntryName(i);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getString(int i, Object... objArr) {
        return getResources().getString(i, objArr);
    }

    public static int getColor(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getColor(i, IdoApp.getAppContext().getTheme());
        }
        return getResources().getColor(i);
    }

    public static ColorStateList getColorStateList(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getColorStateList(i, IdoApp.getAppContext().getTheme());
        }
        return getResources().getColorStateList(i);
    }

    public static Drawable getDrawable(int i) {
        return getResources().getDrawable(i);
    }

    public static int getDimens(int i) {
        return (int) getResources().getDimension(i);
    }

    public static String[] getStringArray(int i) {
        return getResources().getStringArray(i);
    }

    public static int getInteger(int i) {
        return getResources().getInteger(i);
    }

    public static int[] getIntegerArray(int i) {
        return getResources().getIntArray(i);
    }

    public static int getDrawableResId(String str) {
        return getResources().getIdentifier(str, "drawable", AppInfoUtil.getPackageName(IdoApp.getAppContext()));
    }

    public static int getStringResId(String str) {
        return getResources().getIdentifier(str, "string", AppInfoUtil.getPackageName(IdoApp.getAppContext()));
    }

    public static InputStream openRaw(int i) {
        return getResources().openRawResource(i);
    }

    public static String openRawForText(int i) {
        String string;
        byte[] bArr;
        InputStream inputStreamOpenRaw = openRaw(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                bArr = new byte[512];
            } catch (IOException e2) {
                e2.printStackTrace();
                string = null;
            }
            while (true) {
                int i2 = inputStreamOpenRaw.read(bArr);
                if (i2 == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i2);
                return string;
            }
            string = byteArrayOutputStream.toString("utf-8");
            return string;
        } finally {
            IOUtil.close(byteArrayOutputStream);
            IOUtil.close(inputStreamOpenRaw);
        }
    }

    public static Uri resId2Uri(int i) {
        return Uri.parse("android.resource://" + AppInfoUtil.getPackageName(IdoApp.getAppContext()) + "/" + i);
    }
}