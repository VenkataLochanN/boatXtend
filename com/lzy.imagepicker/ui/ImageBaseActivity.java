package com.lzy.imagepicker.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.view.SystemBarTintManager;

/* JADX INFO: loaded from: classes3.dex */
public class ImageBaseActivity extends AppCompatActivity {
    protected SystemBarTintManager tintManager;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 19) {
            setTranslucentStatus(true);
        }
        this.tintManager = new SystemBarTintManager(this);
        this.tintManager.setStatusBarTintEnabled(true);
        this.tintManager.setStatusBarTintResource(R.color.ip_color_primary_dark);
    }

    private void setTranslucentStatus(boolean z) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    public boolean checkPermission(String str) {
        return ActivityCompat.checkSelfPermission(this, str) == 0;
    }

    public void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        ImagePicker.getInstance().restoreInstanceState(bundle);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ImagePicker.getInstance().saveInstanceState(bundle);
    }
}