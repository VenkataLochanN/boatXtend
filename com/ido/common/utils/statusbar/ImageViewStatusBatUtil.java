package com.ido.common.utils.statusbar;

import android.R;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.ido.common.utils.StatusBarsUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ImageViewStatusBatUtil extends StatusBarsUtil {
    private static final int TAG_KEY_HAVE_SET_OFFSET = -123;

    public static void setAlphaForImageView(Activity activity, int i, View view) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        setTransparentForWindow(activity);
        if (i != 0) {
            StatusBarsUtil.addRectangleView(activity, i);
        }
        if (view != null) {
            Object tag = view.getTag(TAG_KEY_HAVE_SET_OFFSET);
            if (tag == null || !((Boolean) tag).booleanValue()) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + StatusBarsUtil.getStatusBarHeight(activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                view.setTag(TAG_KEY_HAVE_SET_OFFSET, true);
            }
        }
    }

    public static void setAlphaForImageViewInFragment(Activity activity, int i, View view) {
        setAlphaForImageView(activity, i, view);
        if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 21) {
            return;
        }
        clearPreviousSetting(activity);
    }

    private static void clearPreviousSetting(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View viewFindViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
            ((ViewGroup) ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0)).setPadding(0, 0, 0, 0);
        }
    }

    private static void setTransparentForWindow(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(0);
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().setFlags(67108864, 67108864);
        }
    }
}