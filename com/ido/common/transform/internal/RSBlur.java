package com.ido.common.transform.internal;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class RSBlur {
    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap blur(android.content.Context r5, android.graphics.Bitmap r6, int r7) throws java.lang.Throwable {
        /*
            r0 = 23
            r1 = 0
            android.renderscript.RenderScript r5 = android.renderscript.RenderScript.create(r5)     // Catch: java.lang.Throwable -> L5a
            android.renderscript.RenderScript$RSMessageHandler r2 = new android.renderscript.RenderScript$RSMessageHandler     // Catch: java.lang.Throwable -> L57
            r2.<init>()     // Catch: java.lang.Throwable -> L57
            r5.setMessageHandler(r2)     // Catch: java.lang.Throwable -> L57
            android.renderscript.Allocation$MipmapControl r2 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch: java.lang.Throwable -> L57
            r3 = 1
            android.renderscript.Allocation r2 = android.renderscript.Allocation.createFromBitmap(r5, r6, r2, r3)     // Catch: java.lang.Throwable -> L57
            android.renderscript.Type r3 = r2.getType()     // Catch: java.lang.Throwable -> L54
            android.renderscript.Allocation r3 = android.renderscript.Allocation.createTyped(r5, r3)     // Catch: java.lang.Throwable -> L54
            android.renderscript.Element r4 = android.renderscript.Element.U8_4(r5)     // Catch: java.lang.Throwable -> L50
            android.renderscript.ScriptIntrinsicBlur r1 = android.renderscript.ScriptIntrinsicBlur.create(r5, r4)     // Catch: java.lang.Throwable -> L50
            r1.setInput(r2)     // Catch: java.lang.Throwable -> L50
            float r7 = (float) r7     // Catch: java.lang.Throwable -> L50
            r1.setRadius(r7)     // Catch: java.lang.Throwable -> L50
            r1.forEach(r3)     // Catch: java.lang.Throwable -> L50
            r3.copyTo(r6)     // Catch: java.lang.Throwable -> L50
            if (r5 == 0) goto L40
            int r7 = android.os.Build.VERSION.SDK_INT
            if (r7 < r0) goto L3d
            android.renderscript.RenderScript.releaseAllContexts()
            goto L40
        L3d:
            r5.destroy()
        L40:
            if (r2 == 0) goto L45
            r2.destroy()
        L45:
            if (r3 == 0) goto L4a
            r3.destroy()
        L4a:
            if (r1 == 0) goto L4f
            r1.destroy()
        L4f:
            return r6
        L50:
            r6 = move-exception
            r7 = r1
            r1 = r3
            goto L5e
        L54:
            r6 = move-exception
            r7 = r1
            goto L5e
        L57:
            r6 = move-exception
            r7 = r1
            goto L5d
        L5a:
            r6 = move-exception
            r5 = r1
            r7 = r5
        L5d:
            r2 = r7
        L5e:
            if (r5 == 0) goto L6b
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r0) goto L68
            android.renderscript.RenderScript.releaseAllContexts()
            goto L6b
        L68:
            r5.destroy()
        L6b:
            if (r2 == 0) goto L70
            r2.destroy()
        L70:
            if (r1 == 0) goto L75
            r1.destroy()
        L75:
            if (r7 == 0) goto L7a
            r7.destroy()
        L7a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.transform.internal.RSBlur.blur(android.content.Context, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }
}