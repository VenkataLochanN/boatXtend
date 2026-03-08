package com.ido.life.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyboardUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/ido/life/util/KeyboardUtils;", "", "()V", "hideInput", "", "activity", "Landroid/app/Activity;", "showInput", "et", "Landroid/widget/EditText;", "app_release"}, k = 1, mv = {1, 1, 16})
public final class KeyboardUtils {
    public static final KeyboardUtils INSTANCE = new KeyboardUtils();

    private KeyboardUtils() {
    }

    public final void showInput(Activity activity, EditText et) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(et, "et");
        et.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getApplicationContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(et, 1);
        }
    }

    public final void hideInput(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getApplicationContext().getSystemService("input_method");
        View viewPeekDecorView = activity.getWindow().peekDecorView();
        if (viewPeekDecorView == null || inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(viewPeekDecorView.getWindowToken(), 0);
    }
}