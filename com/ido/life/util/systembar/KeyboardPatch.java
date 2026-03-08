package com.ido.life.util.systembar;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes3.dex */
public class KeyboardPatch {
    private Activity mActivity;
    private BarParams mBarParams;
    private View mContentView;
    private View mDecorView;
    private boolean mFlag;
    private Window mWindow;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;

    private KeyboardPatch(Activity activity) {
        this(activity, ((FrameLayout) activity.getWindow().getDecorView().findViewById(R.id.content)).getChildAt(0));
    }

    private KeyboardPatch(Activity activity, View view) {
        this(activity, null, "", view);
    }

    private KeyboardPatch(Activity activity, Dialog dialog, String str) {
        this(activity, dialog, str, dialog.getWindow().findViewById(R.id.content));
    }

    private KeyboardPatch(Activity activity, Dialog dialog, String str, View view) {
        BarParams barParams;
        this.mFlag = false;
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.util.systembar.KeyboardPatch.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                KeyboardPatch.this.mDecorView.getWindowVisibleDisplayFrame(rect);
                int i = KeyboardPatch.this.mDecorView.getContext().getResources().getDisplayMetrics().heightPixels - rect.bottom;
                if (i >= 0) {
                    if (KeyboardPatch.this.mFlag || ((Build.VERSION.SDK_INT >= 21 && !OSUtils.isEMUI3_1()) || !KeyboardPatch.this.mBarParams.navigationBarEnable || !KeyboardPatch.this.mBarParams.navigationBarWithKitkatEnable)) {
                        KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.mContentView.getPaddingTop(), 0, i);
                    } else {
                        KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.mContentView.getPaddingTop(), 0, i + ImmersionBar.getNavigationBarHeight(KeyboardPatch.this.mActivity));
                    }
                }
            }
        };
        this.mActivity = activity;
        this.mWindow = dialog != null ? dialog.getWindow() : activity.getWindow();
        this.mDecorView = activity.getWindow().getDecorView();
        this.mContentView = view == null ? this.mWindow.getDecorView().findViewById(R.id.content) : view;
        if (dialog != null) {
            barParams = ImmersionBar.with(activity, dialog, str).getBarParams();
        } else {
            barParams = ImmersionBar.with(activity).getBarParams();
        }
        this.mBarParams = barParams;
        if (this.mBarParams == null) {
            throw new IllegalArgumentException("先使用ImmersionBar初始化");
        }
        if (this.mContentView.equals(this.mDecorView.findViewById(R.id.content))) {
            return;
        }
        this.mFlag = true;
    }

    private KeyboardPatch(Activity activity, Window window, BarParams barParams) {
        this.mFlag = false;
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.util.systembar.KeyboardPatch.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                KeyboardPatch.this.mDecorView.getWindowVisibleDisplayFrame(rect);
                int i = KeyboardPatch.this.mDecorView.getContext().getResources().getDisplayMetrics().heightPixels - rect.bottom;
                if (i >= 0) {
                    if (KeyboardPatch.this.mFlag || ((Build.VERSION.SDK_INT >= 21 && !OSUtils.isEMUI3_1()) || !KeyboardPatch.this.mBarParams.navigationBarEnable || !KeyboardPatch.this.mBarParams.navigationBarWithKitkatEnable)) {
                        KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.mContentView.getPaddingTop(), 0, i);
                    } else {
                        KeyboardPatch.this.mContentView.setPadding(0, KeyboardPatch.this.mContentView.getPaddingTop(), 0, i + ImmersionBar.getNavigationBarHeight(KeyboardPatch.this.mActivity));
                    }
                }
            }
        };
        this.mActivity = activity;
        this.mWindow = window;
        this.mDecorView = activity.getWindow().getDecorView();
        this.mBarParams = barParams;
        FrameLayout frameLayout = (FrameLayout) this.mWindow.getDecorView().findViewById(R.id.content);
        if (frameLayout.getChildAt(0) != null && !this.mBarParams.systemWindows) {
            this.mFlag = true;
        }
        this.mContentView = frameLayout.getChildAt(0) != null ? frameLayout.getChildAt(0) : frameLayout;
    }

    public static KeyboardPatch patch(Activity activity) {
        return new KeyboardPatch(activity);
    }

    public static KeyboardPatch patch(Activity activity, View view) {
        return new KeyboardPatch(activity, view);
    }

    public static KeyboardPatch patch(Activity activity, Dialog dialog, String str) {
        return new KeyboardPatch(activity, dialog, str);
    }

    public static KeyboardPatch patch(Activity activity, Dialog dialog, String str, View view) {
        return new KeyboardPatch(activity, dialog, str, view);
    }

    protected static KeyboardPatch patch(Activity activity, Window window, BarParams barParams) {
        return new KeyboardPatch(activity, window, barParams);
    }

    public void enable() {
        enable(18);
    }

    public void enable(int i) {
        this.mWindow.setSoftInputMode(i);
        if (Build.VERSION.SDK_INT >= 19) {
            this.mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
    }

    public void disable() {
        disable(18);
    }

    public void disable(int i) {
        this.mWindow.setSoftInputMode(i);
        if (Build.VERSION.SDK_INT >= 19) {
            this.mDecorView.getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        }
    }
}