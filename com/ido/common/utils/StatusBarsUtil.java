package com.ido.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.ido.common.R;

/* JADX INFO: loaded from: classes2.dex */
public class StatusBarsUtil {
    public static final int ALL_STATUS_BAR_ALPHA = 0;
    protected static final int FAKE_STATUS_BAR_VIEW_ID = R.id.status_bar_fake_status_bar_view;
    protected static final int FAKE_TRANSLUCENT_VIEW_ID = R.id.status_bar_translucent_view;
    public static final int HALF_STATUS_BAR_ALPHA = 112;
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    public static final int NONE_STATUS_BAR_ALPHA = 255;
    static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 16;

    private static int calculateStatusColor(int i, int i2) {
        if (i2 == 0) {
            return i;
        }
        float f2 = 1.0f - (i2 / 255.0f);
        return ((int) (((double) ((i & 255) * f2)) + 0.5d)) | (((int) (((double) (((i >> 16) & 255) * f2)) + 0.5d)) << 16) | ViewCompat.MEASURED_STATE_MASK | (((int) (((double) (((i >> 8) & 255) * f2)) + 0.5d)) << 8);
    }

    public static void setColor(Activity activity, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().setStatusBarColor(calculateStatusColor(i, i2));
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            View viewFindViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (viewFindViewById != null) {
                if (viewFindViewById.getVisibility() == 8) {
                    viewFindViewById.setVisibility(0);
                }
                viewFindViewById.setBackgroundColor(calculateStatusColor(i, i2));
            } else {
                viewGroup.addView(createStatusBarView(activity, i, i2));
            }
            setRootView(activity);
        }
    }

    public static void setStatusTextColor(Activity activity, boolean z) {
        if (!z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            activity.getWindow().getDecorView().setSystemUiVisibility(8192);
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.com_color_black));
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
        }
    }

    public static void setBgAlpha(Activity activity, int i) {
        if (i == 0) {
            transparentStatusBar(activity);
        } else {
            transparentStatusBar(activity);
            addRectangleView(activity, i);
        }
    }

    protected static void addRectangleView(Activity activity, int i) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        View viewFindViewById = viewGroup.findViewById(FAKE_TRANSLUCENT_VIEW_ID);
        if (viewFindViewById != null) {
            if (viewFindViewById.getVisibility() == 8) {
                viewFindViewById.setVisibility(0);
            }
            viewFindViewById.setBackgroundColor(Color.argb(i, 0, 0, 0));
            return;
        }
        viewGroup.addView(getRectangleStatusBarView(activity, i));
    }

    private static View getRectangleStatusBarView(Activity activity, int i) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundColor(Color.argb(i, 0, 0, 0));
        view.setId(FAKE_TRANSLUCENT_VIEW_ID);
        return view;
    }

    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
            activity.getWindow().setStatusBarColor(0);
        } else {
            activity.getWindow().addFlags(67108864);
        }
        setRootView(activity);
    }

    private static void setRootView(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                childAt.setFitsSystemWindows(true);
                ((ViewGroup) childAt).setClipToPadding(true);
            }
        }
    }

    protected static View createStatusBarView(Activity activity, int i, int i2) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundColor(calculateStatusColor(i, i2));
        view.setId(FAKE_STATUS_BAR_VIEW_ID);
        return view;
    }

    protected static int getStatusBarHeight(Context context) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static void setLightStatusBarIcon(Activity activity, boolean z) {
        Window window = activity.getWindow();
        window.addFlags(Integer.MIN_VALUE);
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= 23) {
            systemUiVisibility = z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193);
        } else if (Build.VERSION.SDK_INT >= 21) {
            systemUiVisibility = z ? systemUiVisibility | 16 : systemUiVisibility & (-17);
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    public static boolean isOppo() {
        try {
            String strReplaceAll = ((String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, KEY_VERSION_OPPO)).replaceAll("[vV]", "");
            if (strReplaceAll.length() > 0) {
                return Integer.parseInt(strReplaceAll.substring(0, 1)) >= 3;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}