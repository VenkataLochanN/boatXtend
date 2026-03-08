package com.ido.life.util.systembar;

import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BarParams implements Cloneable {
    public int flymeOSStatusBarFontColor;
    public View navigationBarView;
    public View statusBarView;
    public View statusBarViewByHeight;
    public int titleBarHeight;
    public int titleBarPaddingTopHeight;
    public View titleBarView;
    public View titleBarViewMarginTop;
    public int statusBarColor = 0;
    public int navigationBarColor = ViewCompat.MEASURED_STATE_MASK;
    public float statusBarAlpha = 0.0f;
    public float navigationBarAlpha = 0.0f;
    public boolean fullScreen = false;
    public boolean fullScreenTemp = this.fullScreen;
    public BarHide barHide = BarHide.FLAG_SHOW_BAR;
    public boolean darkFont = false;
    public boolean statusBarFlag = true;
    public int statusBarColorTransform = ViewCompat.MEASURED_STATE_MASK;
    public int navigationBarColorTransform = ViewCompat.MEASURED_STATE_MASK;
    public Map<View, Map<Integer, Integer>> viewMap = new HashMap();
    public float viewAlpha = 0.0f;
    public boolean fits = false;
    public int navigationBarColorTemp = this.navigationBarColor;
    public boolean isSupportActionBar = false;
    public boolean titleBarViewMarginTopFlag = false;
    public boolean keyboardEnable = false;
    public int keyboardMode = 18;
    public boolean navigationBarEnable = true;
    public boolean navigationBarWithKitkatEnable = true;

    @Deprecated
    public boolean fixMarginAtBottom = false;
    public boolean systemWindows = false;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BarParams m31clone() {
        try {
            return (BarParams) super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}