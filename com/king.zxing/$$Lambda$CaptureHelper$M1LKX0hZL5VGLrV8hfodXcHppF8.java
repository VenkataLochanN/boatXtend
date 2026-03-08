package com.king.zxing;

import android.graphics.Bitmap;
import com.google.zxing.Result;

/* JADX INFO: renamed from: com.king.zxing.-$$Lambda$CaptureHelper$M1LKX0hZL5VGLrV8hfodXcHppF8 */
/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$CaptureHelper$M1LKX0hZL5VGLrV8hfodXcHppF8 implements OnCaptureListener {
    public /* synthetic */ $$Lambda$CaptureHelper$M1LKX0hZL5VGLrV8hfodXcHppF8() {
    }

    @Override // com.king.zxing.OnCaptureListener
    public final void onHandleDecode(Result result, Bitmap bitmap, float f2) {
        this.f$0.lambda$onCreate$0$CaptureHelper(result, bitmap, f2);
    }
}