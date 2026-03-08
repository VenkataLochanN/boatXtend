package com.ido.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.ido.common.log.CommonLogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class InputMethodUtil {
    public static final String TAG = InputMethodUtil.class.getSimpleName();
    private static boolean sLastVisible = false;

    public interface OnSoftKeyBoardVisibleListener {
        void onSoftKeyBoardVisible(boolean z);
    }

    public static void hiddenInput(Context context, View view) {
        if ((context instanceof Context) && (view instanceof View)) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showInput(Context context, View view) {
        if (context instanceof Context) {
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 2);
        }
    }

    public static void toggleInput(Context context) {
        if (context instanceof Context) {
            ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(0, 2);
        }
    }

    public static boolean isInputActive(Context context) {
        if (context instanceof Context) {
            return ((InputMethodManager) context.getSystemService("input_method")).isActive();
        }
        return false;
    }

    public static void addOnSoftKeyBoardVisibleListener(Activity activity, final OnSoftKeyBoardVisibleListener onSoftKeyBoardVisibleListener) {
        CommonLogUtil.d(TAG, String.format("addOnSoftKeyBoardVisibleListener, activity : %s, listener : %s", activity.toString(), onSoftKeyBoardVisibleListener.toString()));
        final View decorView = activity.getWindow().getDecorView();
        sLastVisible = false;
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.common.utils.InputMethodUtil.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                boolean z = ((double) (rect.bottom - rect.top)) / ((double) decorView.getHeight()) < 0.8d;
                if (z != InputMethodUtil.sLastVisible) {
                    onSoftKeyBoardVisibleListener.onSoftKeyBoardVisible(z);
                    CommonLogUtil.d(InputMethodUtil.TAG, String.format("addOnSoftKeyBoardVisibleListener, onSoftKeyBoardVisible visible : %s", Boolean.valueOf(z)));
                }
                boolean unused = InputMethodUtil.sLastVisible = z;
            }
        });
    }
}