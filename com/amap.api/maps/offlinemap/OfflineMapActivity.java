package com.amap.api.maps.offlinemap;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.amap.api.mapcore.util.fv;
import com.amap.api.mapcore.util.fw;
import com.amap.api.mapcore.util.fy;
import com.amap.api.offlineservice.AMapPermissionActivity;
import com.amap.api.offlineservice.a;

/* JADX INFO: loaded from: classes.dex */
public class OfflineMapActivity extends AMapPermissionActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f1893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f1894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private fv f1895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private fv[] f1896d = new fv[32];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1897e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private fw f1898f;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            getWindow().setSoftInputMode(32);
            getWindow().setFormat(-3);
            requestWindowFeature(1);
            fy.a(getApplicationContext());
            this.f1897e = -1;
            f1893a = 0;
            b(new fv(1, null));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void showScr() {
        try {
            setContentView(this.f1894b.c());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(fv fvVar) {
        try {
            if (this.f1894b != null) {
                this.f1894b.d();
                this.f1894b = null;
            }
            this.f1894b = c(fvVar);
            if (this.f1894b != null) {
                this.f1895c = fvVar;
                this.f1894b.a(this);
                this.f1894b.a(this.f1895c.f974b);
                this.f1894b.a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b(fv fvVar) {
        try {
            f1893a++;
            a(fvVar);
            this.f1897e = (this.f1897e + 1) % 32;
            this.f1896d[this.f1897e] = fvVar;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private a c(fv fvVar) {
        try {
            if (fvVar.f973a != 1) {
                return null;
            }
            if (this.f1898f == null) {
                this.f1898f = new fw();
            }
            return this.f1898f;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        try {
            super.onStart();
            if (this.f1894b != null) {
                this.f1894b.e();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.offlineservice.AMapPermissionActivity, android.app.Activity
    protected void onResume() {
        try {
            super.onResume();
            if (this.f1894b != null) {
                this.f1894b.f();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            super.onPause();
            if (this.f1894b != null) {
                this.f1894b.h();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            super.onStop();
            if (this.f1894b != null) {
                this.f1894b.g();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        try {
            super.onConfigurationChanged(configuration);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void closeScr() {
        try {
            if (a((Bundle) null)) {
                return;
            }
            if (this.f1894b != null) {
                this.f1894b.d();
            }
            finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void closeScr(Bundle bundle) {
        try {
            if (a(bundle)) {
                return;
            }
            if (this.f1894b != null) {
                this.f1894b.d();
            }
            finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean a(Bundle bundle) {
        try {
            if ((f1893a != 1 || this.f1894b == null) && f1893a > 1) {
                f1893a--;
                this.f1897e = ((this.f1897e - 1) + 32) % 32;
                fv fvVar = this.f1896d[this.f1897e];
                fvVar.f974b = bundle;
                a(fvVar);
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            super.onDestroy();
            if (this.f1894b != null) {
                this.f1894b.d();
                this.f1894b = null;
            }
            this.f1895c = null;
            this.f1896d = null;
            if (this.f1898f != null) {
                this.f1898f.d();
                this.f1898f = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (this.f1894b != null) {
                this.f1894b.a(view);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            try {
                if (this.f1894b != null && !this.f1894b.b()) {
                    return true;
                }
                if (a((Bundle) null)) {
                    return false;
                }
                if (keyEvent == null) {
                    if (f1893a == 1) {
                        finish();
                    }
                    return false;
                }
                this.f1897e = -1;
                f1893a = 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}