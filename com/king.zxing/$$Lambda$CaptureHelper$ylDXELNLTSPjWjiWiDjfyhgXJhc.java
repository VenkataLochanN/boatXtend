package com.king.zxing;

import android.hardware.Camera;

/* JADX INFO: renamed from: com.king.zxing.-$$Lambda$CaptureHelper$ylDXELNLTSPjWjiWiDjfyhgXJhc */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$CaptureHelper$ylDXELNLTSPjWjiWiDjfyhgXJhc implements Camera.AutoFocusCallback {
    private final /* synthetic */ String f$0;

    public /* synthetic */ $$Lambda$CaptureHelper$ylDXELNLTSPjWjiWiDjfyhgXJhc(String str) {
        focusMode = str;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public final void onAutoFocus(boolean z, Camera camera) {
        CaptureHelper.lambda$focusOnTouch$4(focusMode, z, camera);
    }
}