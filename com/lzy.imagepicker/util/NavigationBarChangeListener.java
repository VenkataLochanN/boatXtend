package com.lzy.imagepicker.util;

import android.R;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationBarChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final int ORIENTATION_HORIZONTAL = 2;
    public static final int ORIENTATION_VERTICAL = 1;
    private OnSoftInputStateChangeListener listener;
    private int orientation;
    private View rootView;
    private boolean isShowNavigationBar = false;
    private Rect rect = new Rect();

    public interface OnSoftInputStateChangeListener {
        void onNavigationBarHide(int i);

        void onNavigationBarShow(int i, int i2);
    }

    public NavigationBarChangeListener(View view, int i) {
        this.rootView = view;
        this.orientation = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004d  */
    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onGlobalLayout() {
        /*
            r6 = this;
            android.graphics.Rect r0 = r6.rect
            r0.setEmpty()
            android.view.View r0 = r6.rootView
            android.graphics.Rect r1 = r6.rect
            r0.getWindowVisibleDisplayFrame(r1)
            int r0 = r6.orientation
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 != r2) goto L24
            android.view.View r0 = r6.rootView
            int r0 = r0.getHeight()
            android.graphics.Rect r4 = r6.rect
            int r4 = r4.bottom
            android.graphics.Rect r5 = r6.rect
            int r5 = r5.top
        L21:
            int r4 = r4 - r5
            int r0 = r0 - r4
            goto L36
        L24:
            if (r0 != r1) goto L35
            android.view.View r0 = r6.rootView
            int r0 = r0.getWidth()
            android.graphics.Rect r4 = r6.rect
            int r4 = r4.right
            android.graphics.Rect r5 = r6.rect
            int r5 = r5.left
            goto L21
        L35:
            r0 = r3
        L36:
            android.view.View r4 = r6.rootView
            android.content.Context r4 = r4.getContext()
            boolean r4 = com.lzy.imagepicker.util.Utils.hasVirtualNavigationBar(r4)
            if (r4 == 0) goto L4d
            android.view.View r4 = r6.rootView
            android.content.Context r4 = r4.getContext()
            int r4 = com.lzy.imagepicker.util.Utils.getNavigationBarHeight(r4)
            goto L4e
        L4d:
            r4 = r3
        L4e:
            if (r0 < r4) goto L63
            int r4 = r4 * r1
            if (r0 >= r4) goto L63
            boolean r1 = r6.isShowNavigationBar
            if (r1 != 0) goto L60
            com.lzy.imagepicker.util.NavigationBarChangeListener$OnSoftInputStateChangeListener r1 = r6.listener
            if (r1 == 0) goto L60
            int r3 = r6.orientation
            r1.onNavigationBarShow(r3, r0)
        L60:
            r6.isShowNavigationBar = r2
            goto L72
        L63:
            boolean r0 = r6.isShowNavigationBar
            if (r0 == 0) goto L70
            com.lzy.imagepicker.util.NavigationBarChangeListener$OnSoftInputStateChangeListener r0 = r6.listener
            if (r0 == 0) goto L70
            int r1 = r6.orientation
            r0.onNavigationBarHide(r1)
        L70:
            r6.isShowNavigationBar = r3
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lzy.imagepicker.util.NavigationBarChangeListener.onGlobalLayout():void");
    }

    public void setListener(OnSoftInputStateChangeListener onSoftInputStateChangeListener) {
        this.listener = onSoftInputStateChangeListener;
    }

    public static NavigationBarChangeListener with(View view) {
        return with(view, 1);
    }

    public static NavigationBarChangeListener with(Activity activity) {
        return with(activity.findViewById(R.id.content), 1);
    }

    public static NavigationBarChangeListener with(View view, int i) {
        NavigationBarChangeListener navigationBarChangeListener = new NavigationBarChangeListener(view, i);
        view.getViewTreeObserver().addOnGlobalLayoutListener(navigationBarChangeListener);
        return navigationBarChangeListener;
    }

    public static NavigationBarChangeListener with(Activity activity, int i) {
        return with(activity.findViewById(R.id.content), i);
    }
}