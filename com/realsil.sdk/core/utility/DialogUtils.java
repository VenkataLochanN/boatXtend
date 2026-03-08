package com.realsil.sdk.core.utility;

import android.content.Context;
import android.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class DialogUtils {
    public static Toast ec;
    public static DialogUtils mInstance;
    public Context mContext;

    public DialogUtils(Context context) {
        this.mContext = context;
    }

    public static DialogUtils getInstance() {
        return mInstance;
    }

    public static void initialize(Context context) {
        mInstance = new DialogUtils(context.getApplicationContext());
    }

    public void cancelToast() {
        Toast toast = ec;
        if (toast != null) {
            toast.cancel();
        }
    }

    public void showToast(int i) {
        Toast toast = ec;
        if (toast == null) {
            ec = Toast.makeText(this.mContext, i, 0);
        } else {
            toast.setText(i);
        }
        ec.show();
    }

    public void showToast(CharSequence charSequence) {
        Toast toast = ec;
        if (toast == null) {
            ec = Toast.makeText(this.mContext, charSequence, 0);
        } else {
            toast.setText(charSequence);
        }
        ec.show();
    }

    public void showToast(String str) {
        Toast toast = ec;
        if (toast == null) {
            ec = Toast.makeText(this.mContext, str, 0);
        } else {
            toast.setText(str);
        }
        ec.show();
    }
}