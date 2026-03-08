package com.ido.life.customview;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;

/* JADX INFO: loaded from: classes2.dex */
public class NormalToast extends Toast {
    private static final boolean IsShowToast = false;
    private static Toast mToast = null;
    public static final int showTime = 1000;

    public static void testDataToast(CharSequence charSequence, int i) {
    }

    private NormalToast(Context context) {
        super(context);
    }

    private static NormalToast makeToastText(CharSequence charSequence, int i) {
        NormalToast normalToast = new NormalToast(IdoApp.getAppContext());
        setContent(normalToast, charSequence, i);
        return normalToast;
    }

    private static void setContent(Toast toast, CharSequence charSequence, int i) {
        View viewInflate = ((LayoutInflater) IdoApp.getAppContext().getSystemService("layout_inflater")).inflate(R.layout.layout_normal_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.txt_normal_tips)).setText(charSequence);
        toast.setView(viewInflate);
        toast.setGravity(16, 0, 0);
        toast.setDuration(i);
    }

    public static Toast showToast(CharSequence charSequence) {
        return showToast(charSequence, 2000);
    }

    public static Toast showToast(CharSequence charSequence, int i) {
        if (TextUtils.isEmpty(charSequence)) {
            return null;
        }
        if (i < 1000) {
            i = 1000;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        try {
            if (mToast != null) {
                setContent(mToast, charSequence, i);
            } else {
                mToast = makeToastText(charSequence, i);
            }
            mToast.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        handler.postDelayed(new Runnable() { // from class: com.ido.life.customview.-$$Lambda$NormalToast$Eanma7GrG0B_duUY2jR_zqj39cs
            @Override // java.lang.Runnable
            public final void run() {
                NormalToast.lambda$showToast$0();
            }
        }, i);
        return mToast;
    }

    static /* synthetic */ void lambda$showToast$0() {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    public static void showToastAtOneThird(Context context, String str) {
        Toast toastMakeText = Toast.makeText(context, str, 0);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        toastMakeText.setGravity(48, 0, point.y / 3);
        toastMakeText.show();
    }

    public static void dismiss() {
        Toast toast = mToast;
        if (toast != null) {
            toast.cancel();
        }
    }
}