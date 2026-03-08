package com.ido.common.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.WindowUtil;

/* JADX INFO: loaded from: classes2.dex */
public class WaitingDialog extends BaseDialogFragment {
    private static final String INTENT_EXTRA_CANCELABLE = "intent_extra_cancelable";
    private static final String INTENT_EXTRA_MESSAGE = "intent_extra_message";
    private static final String TAG = "WaitingDialog";
    private static WaitingDialog sWaitingDialog;
    private boolean mIsBackToActivity = true;

    public static WaitingDialog newInstance() {
        return newInstance(null);
    }

    public static WaitingDialog newInstance(String str) {
        return newInstance(str, true);
    }

    public static WaitingDialog newInstance(String str, boolean z) {
        WaitingDialog waitingDialog = new WaitingDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean(INTENT_EXTRA_CANCELABLE, z);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(INTENT_EXTRA_MESSAGE, str);
        }
        waitingDialog.setArguments(bundle);
        return waitingDialog;
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        getDialog().getWindow().setLayout(-1, WindowUtil.getWindowSize(getContext()).getHeight() - DipPixelUtil.dip2px(100.0f));
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        boolean z;
        Bundle arguments = getArguments();
        String string = "";
        if (arguments != null) {
            string = arguments.getString(INTENT_EXTRA_MESSAGE, "");
            z = arguments.getBoolean(INTENT_EXTRA_CANCELABLE);
        } else {
            z = false;
        }
        AppCompatDialog appCompatDialog = new AppCompatDialog(getActivity(), R.style.TransparentStyle);
        appCompatDialog.setContentView(R.layout.dialog_waiting);
        TextView textView = (TextView) appCompatDialog.findViewById(R.id.waiting_tv);
        if (textView != null) {
            textView.setText(string);
            textView.setVisibility(TextUtils.isEmpty(string) ? 8 : 0);
        }
        appCompatDialog.setCanceledOnTouchOutside(false);
        setBackToActivity(z);
        backToActivity(appCompatDialog, z);
        if (getActivity() != null) {
            getActivity().getWindow().setFlags(262144, 262144);
        }
        return appCompatDialog;
    }

    private void backToActivity(AppCompatDialog appCompatDialog, final boolean z) {
        appCompatDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ido.common.dialog.-$$Lambda$WaitingDialog$7tEhfLa2XKg7mscsyT-1nzxVROw
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$backToActivity$0$WaitingDialog(z, dialogInterface, i, keyEvent);
            }
        });
    }

    public /* synthetic */ boolean lambda$backToActivity$0$WaitingDialog(boolean z, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        if (this.mIsBackToActivity && getActivity() != null) {
            getActivity().onBackPressed();
        }
        return !z;
    }

    public void setBackToActivity(boolean z) {
        this.mIsBackToActivity = z;
    }

    public static void showDialogUncancel(FragmentActivity fragmentActivity) {
        showDialog(fragmentActivity, false, null);
    }

    public static void showDialogUncancel(FragmentActivity fragmentActivity, String str) {
        showDialog(fragmentActivity, false, str);
    }

    public static void showDialog(FragmentActivity fragmentActivity) {
        showDialog(fragmentActivity, true, null);
    }

    public static void showDialog(FragmentActivity fragmentActivity, String str) {
        showDialog(fragmentActivity, true, str);
    }

    public static void showDialogBack(FragmentActivity fragmentActivity) {
        showDialog(fragmentActivity, true, null);
        sWaitingDialog.setBackToActivity(true);
    }

    public static void showDialogBack(FragmentActivity fragmentActivity, String str) {
        showDialog(fragmentActivity, true, str);
        sWaitingDialog.setBackToActivity(true);
    }

    private static void showDialog(FragmentActivity fragmentActivity, boolean z, String str) {
        CommonLogUtil.d(TAG, "showDialog: " + isShowing());
        if (isShowing()) {
            return;
        }
        sWaitingDialog = newInstance(str);
        sWaitingDialog.setCancelable(z);
        sWaitingDialog.show(fragmentActivity.getSupportFragmentManager());
    }

    public static void hideDialog() {
        WaitingDialog waitingDialog = sWaitingDialog;
        if (waitingDialog == null || waitingDialog.getFragmentManager() == null) {
            return;
        }
        sWaitingDialog.dismissAllowingStateLoss();
    }

    public static void hideDialogDelay(long j) {
        new Handler().postDelayed(new Runnable() { // from class: com.ido.common.dialog.-$$Lambda$nVbZT0guSO3mcmXN2o1mxglqWek
            @Override // java.lang.Runnable
            public final void run() {
                WaitingDialog.hideDialog();
            }
        }, j);
    }

    public static boolean isShowing() {
        CommonLogUtil.d(TAG, "isDialogShowing: " + sWaitingDialog + AppInfo.DELIM);
        WaitingDialog waitingDialog = sWaitingDialog;
        return (waitingDialog == null || waitingDialog.getDialog() == null || !sWaitingDialog.getDialog().isShowing()) ? false : true;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        RelativeLayout relativeLayout;
        if (getDialog() == null || (relativeLayout = (RelativeLayout) getDialog().findViewById(R.id.rlLayout)) == null || drawable == null) {
            return;
        }
        relativeLayout.setBackground(drawable);
    }
}