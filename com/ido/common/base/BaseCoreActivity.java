package com.ido.common.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import com.ido.common.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.HookClickListenerHelper;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.StatusBarsUtil;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseCoreActivity extends AppCompatActivity implements PermissionUtil.RequestResult {
    private long mLastClickTime;
    private PermissionUtil mPermissionUtil;
    private Toast mToast;
    private boolean mCanDoubleClick = false;
    private boolean mStatusBgWhite = false;

    protected void actionBeforeSetContentView() {
    }

    protected abstract int getLayoutResId();

    protected void initData() {
    }

    protected void initEvent() {
    }

    protected void initViews() {
    }

    @Override // com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
    }

    @Override // com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        actionBeforeSetContentView();
        super.onCreate(bundle);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        getWindow().getDecorView().setLayoutDirection(0);
        getWindow().getDecorView().setTextDirection(3);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.color_1E1E1E));
        }
        initViews();
        initData();
        initEvent();
        CommonLogUtil.d("***" + getLocalClassName());
        if (canDoubleClick()) {
            return;
        }
        HookClickListenerHelper.hookOnClickAuto(this);
    }

    protected boolean canDoubleClick() {
        return this.mCanDoubleClick;
    }

    public boolean checkSelfPermission(String... strArr) {
        return PermissionUtil.checkSelfPermission(this, strArr);
    }

    public void requestPermissions(int i, String... strArr) {
        if (this.mPermissionUtil == null) {
            this.mPermissionUtil = new PermissionUtil();
        }
        this.mPermissionUtil.setRequestResult(this);
        PermissionUtil.requestPermissions(this, i, strArr);
    }

    public void requestPermissions(int i, PermissionUtil.RequestResult requestResult, String... strArr) {
        if (this.mPermissionUtil == null) {
            this.mPermissionUtil = new PermissionUtil();
        }
        this.mPermissionUtil.setRequestResult(requestResult);
        PermissionUtil.requestPermissions(this, i, strArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionUtil permissionUtil = this.mPermissionUtil;
        if (permissionUtil != null) {
            permissionUtil.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public boolean isStatusBgWhite() {
        return this.mStatusBgWhite;
    }

    public void setStatusBgWhite(boolean z) {
        this.mStatusBgWhite = z;
        if (z) {
            setStatusBar(getColor(R.color.white), 0);
        } else {
            setStatusBar(getColor(R.color.translate), 0);
        }
    }

    protected void setStatusBar(int i, int i2) {
        StatusBarsUtil.setColor(this, i, i2);
        this.mStatusBgWhite = i == -1;
        StatusBarsUtil.setStatusTextColor(this, this.mStatusBgWhite);
    }

    protected void setStatusBar(int i, int i2, boolean z) {
        StatusBarsUtil.setColor(this, i, i2);
        this.mStatusBgWhite = z;
        StatusBarsUtil.setStatusTextColor(this, this.mStatusBgWhite);
    }

    protected void setStatusBarColor(int i) {
        setStatusBar(i, 0);
    }

    protected void setStatusBarColor(int i, boolean z) {
        setStatusBar(i, 0, z);
    }

    public void showToast(String str) {
        showToast(str, 0);
    }

    public void showToast(int i) {
        showToast(getString(i));
    }

    private Toast getToast(String str, int i) {
        Toast toast = new Toast(this);
        View viewInflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.layout_normal_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.txt_normal_tips)).setText(str);
        toast.setView(viewInflate);
        toast.setGravity(16, 0, 0);
        toast.setDuration(i);
        return toast;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showToast(java.lang.String r4, int r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L60
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            java.lang.Thread r1 = r1.getThread()
            if (r0 == r1) goto L15
            goto L60
        L15:
            r0 = 1000(0x3e8, float:1.401E-42)
            if (r5 != 0) goto L1b
        L19:
            r5 = r0
            goto L24
        L1b:
            r1 = 1
            if (r5 != r1) goto L21
            r5 = 2000(0x7d0, float:2.803E-42)
            goto L24
        L21:
            if (r5 >= r0) goto L24
            goto L19
        L24:
            android.widget.Toast r0 = r3.mToast
            if (r0 != 0) goto L2f
            android.widget.Toast r4 = r3.getToast(r4, r5)
            r3.mToast = r4
            goto L45
        L2f:
            r0.cancel()
            android.widget.Toast r0 = r3.mToast
            android.view.View r0 = r0.getView()
            int r1 = com.ido.common.R.id.txt_normal_tips
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L45
            r0.setText(r4)
        L45:
            android.widget.Toast r4 = r3.mToast
            r4.setDuration(r5)
            android.widget.Toast r4 = r3.mToast
            r4.show()
            android.view.Window r4 = r3.getWindow()
            android.view.View r4 = r4.getDecorView()
            com.ido.common.base.-$$Lambda$BaseCoreActivity$f7wYKc9piXwRa_JW56Zatah8p94 r0 = new com.ido.common.base.-$$Lambda$BaseCoreActivity$f7wYKc9piXwRa_JW56Zatah8p94
            r0.<init>()
            long r1 = (long) r5
            r4.postDelayed(r0, r1)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.base.BaseCoreActivity.showToast(java.lang.String, int):void");
    }

    public /* synthetic */ void lambda$showToast$0$BaseCoreActivity() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        this.mToast = null;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideInput(currentFocus, motionEvent) && (inputMethodManager = (InputMethodManager) getSystemService("input_method")) != null && currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean isShouldHideInput(View view, MotionEvent motionEvent) {
        if (!(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    protected boolean clickValid() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(this.mLastClickTime - jCurrentTimeMillis) < 500) {
            return false;
        }
        this.mLastClickTime = jCurrentTimeMillis;
        return true;
    }
}