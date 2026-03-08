package com.amap.api.maps.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.FrameLayout;
import com.amap.api.mapcore.util.dz;
import com.amap.api.mapcore.util.er;
import com.amap.api.mapcore.util.ex;
import com.amap.api.mapcore.util.ey;
import com.amap.api.mapcore.util.u;
import java.io.FileInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static final String ICON_ID_PREFIX = "com.amap.api.icon_";
    private static int nextId;

    public static BitmapDescriptor fromResource(int i) {
        try {
            Context context = getContext();
            if (context != null) {
                return fromBitmap(BitmapFactory.decodeStream(context.getResources().openRawResource(i)));
            }
            return null;
        } catch (Throwable th) {
            er.a(th);
            ey.b(ex.f799f, "read bitmap from res failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromView(View view) {
        try {
            Context context = getContext();
            if (context == null) {
                return null;
            }
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.addView(view);
            frameLayout.setDrawingCacheEnabled(true);
            return fromBitmap(er.a(frameLayout));
        } catch (Throwable th) {
            er.a(th);
            ey.b(ex.f799f, "read bitmap from view failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromPath(String str) {
        try {
            return fromBitmap(BitmapFactory.decodeFile(str));
        } catch (Throwable th) {
            er.a(th);
            ey.b(ex.f799f, "read bitmap from disk failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromAsset(String str) {
        try {
            Context context = getContext();
            if (context != null) {
                return fromBitmap(er.a(context, str));
            }
            InputStream resourceAsStream = BitmapDescriptorFactory.class.getResourceAsStream("/assets/" + str);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(resourceAsStream);
            resourceAsStream.close();
            return fromBitmap(bitmapDecodeStream);
        } catch (Throwable th) {
            er.a(th);
            ey.b(ex.f799f, "read bitmap from assets failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor fromFile(String str) {
        try {
            Context context = getContext();
            if (context == null) {
                return null;
            }
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput(str);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStreamOpenFileInput);
            fileInputStreamOpenFileInput.close();
            BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeStream);
            er.b(bitmapDecodeStream);
            return bitmapDescriptorFromBitmap;
        } catch (Throwable th) {
            er.a(th);
            ey.b(ex.f799f, "read bitmap from disk failed " + th.getMessage());
            return null;
        }
    }

    public static BitmapDescriptor defaultMarker() {
        try {
            return fromAsset(dz.a.marker_default.name() + ".png");
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    public static BitmapDescriptor defaultMarker(float f2) {
        try {
            float f3 = (((int) (f2 + 15.0f)) / 30) * 30;
            float f4 = 330;
            if (f3 > f4) {
                f3 = f4;
            } else if (f3 < 0.0f) {
                f3 = 0.0f;
            }
            String str = "";
            if (f3 == 0.0f) {
                str = "RED";
            } else if (f3 == 30.0f) {
                str = "ORANGE";
            } else if (f3 == 60.0f) {
                str = "YELLOW";
            } else if (f3 == 120.0f) {
                str = "GREEN";
            } else if (f3 == 180.0f) {
                str = "CYAN";
            } else if (f3 == 210.0f) {
                str = "AZURE";
            } else if (f3 == 240.0f) {
                str = "BLUE";
            } else if (f3 == 270.0f) {
                str = "VIOLET";
            } else if (f3 == 300.0f) {
                str = "MAGENTA";
            } else if (f3 == 330.0f) {
                str = "ROSE";
            }
            return fromAsset(str + ".png");
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            if (nextId == Integer.MAX_VALUE) {
                nextId = 0;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ICON_ID_PREFIX);
            int i = nextId + 1;
            nextId = i;
            sb.append(i);
            return new BitmapDescriptor(bitmap, sb.toString());
        } catch (Throwable th) {
            er.a(th);
            ey.b(ex.f799f, "read bitmap from bitmap failed " + th.getMessage());
            return null;
        }
    }

    public static Context getContext() {
        return u.f1791a;
    }
}