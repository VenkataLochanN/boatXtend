package com.ido.common.utils.statusbar;

import android.app.Activity;
import android.view.Window;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class MIUIHelper implements IHelper {
    @Override // com.ido.common.utils.statusbar.IHelper
    public boolean setStatusBarLightMode(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}